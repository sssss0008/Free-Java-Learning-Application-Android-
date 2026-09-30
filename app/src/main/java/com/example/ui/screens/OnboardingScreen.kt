package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun OnboardingScreen(
    onOnboardingFinished: (name: String, experience: String, goals: List<String>) -> Unit
) {
    var step by remember { mutableStateOf(0) }
    var userName by remember { mutableStateOf("Awiskar") }
    var selectedExperience by remember { mutableStateOf("Beginner") }
    var selectedGoals by remember {
        mutableStateOf(setOf("Object-Oriented Programming", "Backend Development", "Spring Boot", "Data Structures"))
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (step) {
            0 -> {
                // Welcome Screen
                WelcomeStep(
                    onStartLearning = { step = 1 },
                    onExploreJava = { step = 1 }
                )
            }
            1 -> {
                // Name Step
                NameStep(
                    name = userName,
                    onNameChange = { userName = it },
                    onContinue = { step = 2 }
                )
            }
            2 -> {
                // Experience Level Step
                ExperienceStep(
                    selectedExp = selectedExperience,
                    onExpSelect = { selectedExperience = it },
                    onContinue = { step = 3 }
                )
            }
            3 -> {
                // Learning Goals Step
                GoalsStep(
                    selectedGoals = selectedGoals,
                    onGoalToggle = { goal ->
                        selectedGoals = if (selectedGoals.contains(goal)) {
                            selectedGoals - goal
                        } else {
                            selectedGoals + goal
                        }
                    },
                    onContinue = { step = 4 }
                )
            }
            4 -> {
                // Personalized Roadmap & Confirmation
                RoadmapPreviewStep(
                    name = userName.ifEmpty { "Awiskar" },
                    experience = selectedExperience,
                    goals = selectedGoals.toList(),
                    onComplete = {
                        onOnboardingFinished(
                            userName.ifEmpty { "Awiskar" },
                            selectedExperience,
                            selectedGoals.toList()
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun WelcomeStep(
    onStartLearning: () -> Unit,
    onExploreJava: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Hero Graphic & Brand Crest
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(JavaOrangePrimary, JavaOrangeDark)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Terminal,
                        contentDescription = "Java Code",
                        tint = Color.White,
                        modifier = Modifier.size(54.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = JavaOrangePrimary.copy(alpha = 0.12f)
            ) {
                Text(
                    text = "Created by Awiskar Acharya • 100% Free",
                    color = JavaOrangePrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Learn Java From Zero",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.5.sp
                ),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "“Learn. Code. Build. Master Java.”",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = JavaCyanSecondary
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Master programming fundamentals, object-oriented architecture, data structures, Spring Boot backends, and real-world Java applications.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 22.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Tech Ecosystem Chips Grid
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TechPill("JVM Internals")
                TechPill("OOP Academy")
                TechPill("Spring Boot")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TechPill("DSA Lab")
                TechPill("REST APIs")
                TechPill("100% Free Forever")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Action Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onStartLearning,
                colors = ButtonDefaults.buttonColors(containerColor = JavaOrangePrimary),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("start_learning_button")
            ) {
                Text(
                    text = "Start Learning",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            OutlinedButton(
                onClick = onExploreJava,
                shape = RoundedCornerShape(14.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("explore_java_button")
            ) {
                Text(
                    text = "Explore Java Curriculum",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
private fun TechPill(text: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Composable
private fun NameStep(
    name: String,
    onNameChange: (String) -> Unit,
    onContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Welcome to Java Academy",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = JavaOrangePrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "What should we call you?",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "We will personalize your daily learning goals, achievements, and completion certificate with this name.",
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Enter your name") },
                placeholder = { Text("e.g. Awiskar") },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = JavaOrangePrimary,
                    focusedLabelColor = JavaOrangePrimary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("name_input_field")
            )
        }

        Button(
            onClick = onContinue,
            enabled = name.isNotBlank(),
            colors = ButtonDefaults.buttonColors(containerColor = JavaOrangePrimary),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("name_continue_button")
        ) {
            Text("Continue", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun ExperienceStep(
    selectedExp: String,
    onExpSelect: (String) -> Unit,
    onContinue: () -> Unit
) {
    val levels = listOf(
        Pair("Complete Beginner", "New to programming. Start with core principles."),
        Pair("Beginner", "Know basics of coding, eager to learn Java syntax."),
        Pair("Some Programming Experience", "Coming from Python, C++, JavaScript or Dart."),
        Pair("Intermediate", "Familiar with Java syntax, seeking OOP & DSA mastery."),
        Pair("Advanced", "Engineering backend microservices, Spring Boot & JVM.")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Experience Level",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = JavaOrangePrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "What's your programming experience?",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                levels.forEach { (levelTitle, desc) ->
                    val isSelected = selectedExp == levelTitle
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = if (isSelected) JavaOrangePrimary.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surfaceVariant,
                        border = if (isSelected) androidx.compose.foundation.BorderStroke(1.5.dp, JavaOrangePrimary) else null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onExpSelect(levelTitle) }
                            .testTag("exp_card_${levelTitle.take(5)}")
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { onExpSelect(levelTitle) },
                                colors = RadioButtonDefaults.colors(selectedColor = JavaOrangePrimary)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = levelTitle,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = if (isSelected) JavaOrangePrimary else MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = desc,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = onContinue,
            colors = ButtonDefaults.buttonColors(containerColor = JavaOrangePrimary),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("exp_continue_button")
        ) {
            Text("Continue", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun GoalsStep(
    selectedGoals: Set<String>,
    onGoalToggle: (String) -> Unit,
    onContinue: () -> Unit
) {
    val goalsList = listOf(
        "Learn Programming",
        "Object-Oriented Programming",
        "Data Structures",
        "Algorithms",
        "Backend Development",
        "Spring Boot",
        "REST APIs",
        "Database Applications",
        "Android Development",
        "Interview Preparation",
        "College Study",
        "Competitive Programming"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Target Outcomes",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = JavaOrangePrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "What do you want to build with Java?",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Select all that apply to tailor your personalized study plan.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                goalsList.forEach { goal ->
                    val isChecked = selectedGoals.contains(goal)
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isChecked) JavaOrangePrimary.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surfaceVariant,
                        border = if (isChecked) androidx.compose.foundation.BorderStroke(1.2.dp, JavaOrangePrimary) else null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onGoalToggle(goal) }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { onGoalToggle(goal) },
                                colors = CheckboxDefaults.colors(checkedColor = JavaOrangePrimary)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = goal,
                                fontSize = 14.sp,
                                fontWeight = if (isChecked) FontWeight.Bold else FontWeight.Medium,
                                color = if (isChecked) JavaOrangePrimary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        Button(
            onClick = onContinue,
            colors = ButtonDefaults.buttonColors(containerColor = JavaOrangePrimary),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("goals_continue_button")
        ) {
            Text("Generate Roadmap", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun RoadmapPreviewStep(
    name: String,
    experience: String,
    goals: List<String>,
    onComplete: () -> Unit
) {
    val roadmapSteps = listOf(
        "Programming Fundamentals & JVM",
        "Java Basics & Variables",
        "Control Flow & Loops",
        "Arrays & Strings",
        "Object-Oriented Programming (OOP)",
        "Collections & Generics",
        "Exceptions & File Handling",
        "Streams & Modern Java",
        "Concurrency & Multithreading",
        "Data Structures & Algorithms",
        "Databases (JDBC) & REST APIs",
        "Spring Boot Enterprise Architecture",
        "Real-World Projects & Interview Mastery"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = JavaEmeraldSuccess,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Personalized Java Roadmap",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = JavaEmeraldSuccess
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Welcome aboard, $name!",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "Here is your customized journey from zero to advanced Java mastery:",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                roadmapSteps.forEachIndexed { idx, stepTitle ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant,
                                RoundedCornerShape(10.dp)
                            )
                            .padding(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(if (idx < 2) JavaEmeraldSuccess else JavaOrangePrimary.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${idx + 1}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (idx < 2) Color.White else JavaOrangePrimary
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = stepTitle,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onComplete,
            colors = ButtonDefaults.buttonColors(containerColor = JavaOrangePrimary),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("launch_dashboard_button")
        ) {
            Text("Launch Learning Platform", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}
