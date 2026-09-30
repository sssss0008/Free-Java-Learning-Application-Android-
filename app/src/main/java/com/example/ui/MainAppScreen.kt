package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.data.UserPreferencesRepository
import com.example.model.UserProfile
import com.example.ui.components.*
import com.example.ui.screens.*

@Composable
fun MainAppScreen(
    repository: UserPreferencesRepository,
    modifier: Modifier = Modifier
) {
    val userProfile by repository.userProfile.collectAsState()
    val notes by repository.notes.collectAsState()
    val snippets by repository.snippets.collectAsState()
    val bookmarks by repository.bookmarks.collectAsState()
    val achievements by repository.achievements.collectAsState()

    var isOnboarding by remember {
        mutableStateOf(!repository.isOnboardingComplete())
    }

    var currentNav by remember { mutableStateOf(NavigationDestination.HOME) }
    var activeLabId by remember { mutableStateOf("oop") }
    var deepSelectedLessonId by remember { mutableStateOf<String?>(null) }
    var customCodeInLab by remember {
        mutableStateOf<Map<String, String>?>(null)
    }

    var showSearchSheet by remember { mutableStateOf(false) }
    var showAiTutorDialog by remember { mutableStateOf(false) }

    // Android Hardware/Gesture Back Handler
    BackHandler(enabled = !isOnboarding && currentNav != NavigationDestination.HOME) {
        currentNav = NavigationDestination.HOME
    }

    if (isOnboarding) {
        OnboardingScreen(
            onOnboardingFinished = { name, exp, goals ->
                repository.completeOnboarding(name, exp, goals)
                isOnboarding = false
            }
        )
    } else {
        Scaffold(
            topBar = {
                LearnJavaTopAppBar(
                    streakDays = userProfile.streakDays,
                    onSearchClick = { showSearchSheet = true },
                    onAiTutorClick = { showAiTutorDialog = true }
                )
            },
            bottomBar = {
                LearnJavaBottomNav(
                    currentDestination = currentNav,
                    onDestinationSelected = { nav ->
                        currentNav = nav
                    }
                )
            },
            modifier = modifier
                .fillMaxSize()
                .testTag("main_app_scaffold")
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (currentNav) {
                    NavigationDestination.HOME -> {
                        HomeScreen(
                            userProfile = userProfile,
                            onContinueLearning = {
                                deepSelectedLessonId = userProfile.currentLessonId
                                currentNav = NavigationDestination.LEARN
                            },
                            onOpenLab = { labId ->
                                activeLabId = labId
                                currentNav = NavigationDestination.VISUALIZERS
                            },
                            onOpenChallenge = {
                                currentNav = NavigationDestination.PRACTICE
                            },
                            onOpenCurriculum = {
                                currentNav = NavigationDestination.LEARN
                            }
                        )
                    }
                    NavigationDestination.LEARN -> {
                        LearnScreen(
                            completedLessonIds = userProfile.completedLessonIds,
                            bookmarkedLessonIds = bookmarks,
                            initialSelectedLessonId = deepSelectedLessonId,
                            onLessonCompleted = { lessonId ->
                                repository.markLessonComplete(lessonId)
                            },
                            onToggleBookmark = { lessonId ->
                                repository.toggleBookmark(lessonId)
                            },
                            onOpenInCodeLab = { fileName, code ->
                                customCodeInLab = mapOf(fileName to code)
                                currentNav = NavigationDestination.CODE
                            }
                        )
                    }
                    NavigationDestination.CODE -> {
                        val initialFiles = customCodeInLab ?: mapOf(
                            "Main.java" to """public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
        System.out.println("Platform: Learn Java by Awiskar Acharya");
        
        int age = 22;
        String goal = "Master Enterprise Java";
        System.out.println("Learner Age: " + age);
        System.out.println("Current Goal: " + goal);
    }
}""",
                            "Student.java" to """public class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void display() {
        System.out.println(name + " has score " + score);
    }
}""",
                            "Utils.java" to """public class Utils {
    public static int add(int a, int b) {
        return a + b;
    }
}"""
                        )
                        InteractiveCodeLabView(initialFiles = initialFiles)
                    }
                    NavigationDestination.VISUALIZERS -> {
                        VisualizersScreen(initialTab = activeLabId)
                    }
                    NavigationDestination.PRACTICE -> {
                        PracticeScreen(
                            onOpenInCodeLab = { fileName, code ->
                                customCodeInLab = mapOf(fileName to code)
                                currentNav = NavigationDestination.CODE
                            }
                        )
                    }
                    NavigationDestination.PROFILE -> {
                        ProfileScreen(
                            userProfile = userProfile,
                            notes = notes,
                            snippets = snippets,
                            achievements = achievements,
                            onThemeChanged = { newTheme ->
                                repository.updateTheme(newTheme)
                            }
                        )
                    }
                }
            }
        }

        // Global Search Bottom Sheet
        if (showSearchSheet) {
            GlobalSearchModal(
                onDismissRequest = { showSearchSheet = false },
                onItemSelected = { item ->
                    when (item.type) {
                        "lesson" -> {
                            deepSelectedLessonId = item.id
                            currentNav = NavigationDestination.LEARN
                        }
                        "project" -> {
                            currentNav = NavigationDestination.PRACTICE
                        }
                        else -> {
                            // Keyword or Error: Open in Learn or Visualizers
                            currentNav = NavigationDestination.LEARN
                        }
                    }
                }
            )
        }

        // AI Java Tutor Dialog
        if (showAiTutorDialog) {
            AiJavaTutorDialog(
                onDismissRequest = { showAiTutorDialog = false }
            )
        }
    }
}
