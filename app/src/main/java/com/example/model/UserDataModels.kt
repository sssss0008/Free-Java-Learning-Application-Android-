package com.example.model

data class UserProfile(
    val name: String = "Awiskar",
    val experienceLevel: String = "Beginner",
    val learningGoals: List<String> = listOf("OOP", "Data Structures", "Backend Development", "Spring Boot"),
    val dailyGoalMinutes: Int = 20,
    val minutesStudiedToday: Int = 14,
    val streakDays: Int = 15,
    val completedLessonIds: Set<String> = setOf("l1_1", "l1_2", "l2_1"),
    val completedProjectIds: Set<String> = emptySet(),
    val completedChallengeIds: Set<String> = setOf("ch_1"),
    val totalScore: Int = 420,
    val currentLevelId: String = "level_2",
    val currentLessonId: String = "l2_1",
    val themeMode: String = "dark",
    val editorFontSize: Int = 14,
    val certificateClaimed: Boolean = false,
    val certificateId: String = "LJ-AWISKAR-2026-9842"
)

data class StudyNote(
    val id: String,
    val title: String,
    val content: String,
    val tag: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class SavedSnippet(
    val id: String,
    val title: String,
    val code: String,
    val language: String = "Java",
    val category: String = "General"
)

data class JavaAchievement(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val isUnlocked: Boolean = false,
    val unlockedDate: String = ""
)
