package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.JavaAchievement
import com.example.model.SavedSnippet
import com.example.model.StudyNote
import com.example.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserPreferencesRepository(private val context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("learn_java_prefs", Context.MODE_PRIVATE)

    private val _userProfile = MutableStateFlow(loadInitialProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _notes = MutableStateFlow(loadDefaultNotes())
    val notes: StateFlow<List<StudyNote>> = _notes.asStateFlow()

    private val _bookmarks = MutableStateFlow(loadDefaultBookmarks())
    val bookmarks: StateFlow<Set<String>> = _bookmarks.asStateFlow()

    private val _snippets = MutableStateFlow(loadDefaultSnippets())
    val snippets: StateFlow<List<SavedSnippet>> = _snippets.asStateFlow()

    private val _achievements = MutableStateFlow(loadDefaultAchievements())
    val achievements: StateFlow<List<JavaAchievement>> = _achievements.asStateFlow()

    private fun loadInitialProfile(): UserProfile {
        val name = prefs.getString("user_name", "") ?: ""
        val exp = prefs.getString("exp_level", "Beginner") ?: "Beginner"
        val streak = prefs.getInt("streak_days", 15)
        val dailyGoal = prefs.getInt("daily_goal", 20)
        val minutesToday = prefs.getInt("minutes_today", 14)
        val score = prefs.getInt("total_score", 480)
        val completedSet = prefs.getStringSet("completed_lessons", setOf("l1_1", "l1_2", "l2_1")) ?: setOf("l1_1", "l1_2", "l2_1")
        val theme = prefs.getString("theme_mode", "dark") ?: "dark"

        return UserProfile(
            name = name.ifEmpty { "Awiskar" },
            experienceLevel = exp,
            streakDays = streak,
            dailyGoalMinutes = dailyGoal,
            minutesStudiedToday = minutesToday,
            completedLessonIds = completedSet,
            totalScore = score,
            themeMode = theme
        )
    }

    fun completeOnboarding(name: String, experience: String, goals: List<String>) {
        prefs.edit()
            .putString("user_name", name.trim().ifEmpty { "Awiskar" })
            .putString("exp_level", experience)
            .putBoolean("onboarding_complete", true)
            .apply()

        _userProfile.update { current ->
            current.copy(
                name = name.trim().ifEmpty { "Awiskar" },
                experienceLevel = experience,
                learningGoals = goals
            )
        }
    }

    fun isOnboardingComplete(): Boolean {
        return prefs.getBoolean("onboarding_complete", false)
    }

    fun markLessonComplete(lessonId: String) {
        val newSet = _userProfile.value.completedLessonIds + lessonId
        prefs.edit().putStringSet("completed_lessons", newSet).apply()
        _userProfile.update {
            it.copy(
                completedLessonIds = newSet,
                totalScore = it.totalScore + 50,
                minutesStudiedToday = (it.minutesStudiedToday + 10).coerceAtMost(60)
            )
        }
    }

    fun toggleBookmark(lessonId: String) {
        val current = _bookmarks.value
        val updated = if (current.contains(lessonId)) current - lessonId else current + lessonId
        _bookmarks.value = updated
    }

    fun addNote(title: String, content: String, tag: String) {
        val newNote = StudyNote(
            id = "note_${System.currentTimeMillis()}",
            title = title,
            content = content,
            tag = tag
        )
        _notes.update { listOf(newNote) + it }
    }

    fun addSnippet(title: String, code: String, category: String) {
        val newSnippet = SavedSnippet(
            id = "snip_${System.currentTimeMillis()}",
            title = title,
            code = code,
            category = category
        )
        _snippets.update { listOf(newSnippet) + it }
    }

    fun updateTheme(newTheme: String) {
        prefs.edit().putString("theme_mode", newTheme).apply()
        _userProfile.update { it.copy(themeMode = newTheme) }
    }

    private fun loadDefaultNotes(): List<StudyNote> {
        return listOf(
            StudyNote(
                id = "n1",
                title = "JVM Stack vs Heap Memory",
                content = "Stack stores primitive variables and method call frames. Heap holds all created objects and arrays. Garbage Collector cleans unused heap objects.",
                tag = "JVM"
            ),
            StudyNote(
                id = "n2",
                title = "String Immutability Rule",
                content = "Strings in Java cannot be altered once created. Any modification returns a new String in the String Pool. Use StringBuilder for heavy concatenations.",
                tag = "Strings"
            ),
            StudyNote(
                id = "n3",
                title = "OOP 4 Pillars Checklist",
                content = "1. Encapsulation: private fields + getters/setters\n2. Inheritance: extends\n3. Polymorphism: method overriding & overloading\n4. Abstraction: interfaces and abstract classes",
                tag = "OOP"
            )
        )
    }

    private fun loadDefaultBookmarks(): Set<String> {
        return setOf("l1_1", "l2_2", "l3_1", "l5_1")
    }

    private fun loadDefaultSnippets(): List<SavedSnippet> {
        return listOf(
            SavedSnippet(
                id = "s1",
                title = "Safe Scanner Reader",
                category = "Input/Output",
                code = "import java.util.Scanner;\n\npublic class InputHelper {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        System.out.print(\"Enter your name: \");\n        String name = sc.nextLine();\n        System.out.println(\"Welcome to Java, \" + name + \"!\");\n        sc.close();\n    }\n}"
            ),
            SavedSnippet(
                id = "s2",
                title = "Stream Filter & Map",
                category = "Streams",
                code = "import java.util.*;\nimport java.util.stream.*;\n\npublic class StreamDemo {\n    public static void main(String[] args) {\n        List<String> names = Arrays.asList(\"Awiskar\", \"Java\", \"Spring\", \"Android\");\n        List<String> result = names.stream()\n            .filter(s -> s.length() > 4)\n            .map(String::toUpperCase)\n            .collect(Collectors.toList());\n        System.out.println(\"Filtered: \" + result);\n    }\n}"
            )
        )
    }

    private fun loadDefaultAchievements(): List<JavaAchievement> {
        return listOf(
            JavaAchievement("a1", "First Java Program", "Compiled and ran Hello World", "code", true, "Sept 2026"),
            JavaAchievement("a2", "First Class & Object", "Instantiated your first OOP class", "class", true, "Sept 2026"),
            JavaAchievement("a3", "OOP Explorer", "Completed Encapsulation, Inheritance & Polymorphism", "layers", true, "Sept 2026"),
            JavaAchievement("a4", "JVM Explorer", "Inspected Stack, Heap and Garbage Collector", "memory", true, "Sept 2026"),
            JavaAchievement("a5", "Collections Master", "Traversed ArrayList, HashMap, and Queue", "storage", false),
            JavaAchievement("a6", "Debugging Champion", "Resolved 5 runtime Java errors in Lab", "bug", true, "Sept 2026"),
            JavaAchievement("a7", "Backend Architect", "Explored JDBC and REST API controllers", "api", false),
            JavaAchievement("a8", "15-Day Streak", "Consistent coding practice for 15 days", "streak", true, "Sept 2026")
        )
    }
}
