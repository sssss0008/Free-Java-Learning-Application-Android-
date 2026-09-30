package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ExecutionResult
import com.example.data.JavaExecutionEngine
import com.example.data.JavaPracticeData
import com.example.data.JavaProjectsData
import com.example.model.DailyChallenge
import com.example.model.OutputPrediction
import com.example.model.ProjectItem
import com.example.ui.components.InteractiveCodeLabView
import com.example.ui.theme.*

@Composable
fun PracticeScreen(
    onOpenInCodeLab: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var subTab by remember { mutableStateOf(0) } // 0: Challenges, 1: Output Prediction, 2: Error Fixing, 3: Quiz Center, 4: Project Center
    var activeChallenge by remember { mutableStateOf<DailyChallenge?>(null) }
    var activeProject by remember { mutableStateOf<ProjectItem?>(null) }

    val subTabTitles = listOf("Daily Challenge", "Predict Output", "Fix Errors", "Quiz Center", "Projects")

    if (activeChallenge != null) {
        ChallengeDetailView(
            challenge = activeChallenge!!,
            onBack = { activeChallenge = null }
        )
    } else if (activeProject != null) {
        ProjectDetailView(
            project = activeProject!!,
            onBack = { activeProject = null }
        )
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Header
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Practice & Problem Solving",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold)
                )
                Text(
                    text = "Coding Challenges • Output Prediction • Projects • Quizzes",
                    fontSize = 12.sp,
                    color = JavaCyanSecondary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Sub Tab Horizontal Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                subTabTitles.forEachIndexed { idx, title ->
                    val isSelected = subTab == idx
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) JavaOrangePrimary else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.clickable { subTab = idx }
                    ) {
                        Text(
                            text = title,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                when (subTab) {
                    0 -> ChallengesListView(onSelectChallenge = { activeChallenge = it })
                    1 -> OutputPredictionListView()
                    2 -> CodeFixingListView()
                    3 -> QuizCenterView()
                    4 -> ProjectsListView(onSelectProject = { activeProject = it })
                }
            }
        }
    }
}

// SubTab 0: Challenges
@Composable
private fun ChallengesListView(onSelectChallenge: (DailyChallenge) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(JavaPracticeData.dailyChallenges) { ch ->
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectChallenge(ch) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = ch.category,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = JavaCyanSecondary
                        )
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = if (ch.difficulty == "Easy") JavaEmeraldSuccess.copy(alpha = 0.2f) else JavaOrangePrimary.copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = ch.difficulty,
                                color = if (ch.difficulty == "Easy") JavaEmeraldSuccess else JavaOrangePrimary,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = ch.title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = ch.description,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )
                }
            }
        }
    }
}

@Composable
private fun ChallengeDetailView(
    challenge: DailyChallenge,
    onBack: () -> Unit
) {
    var codeText by remember(challenge) { mutableStateOf(challenge.starterCode) }
    var runResult by remember { mutableStateOf<ExecutionResult?>(null) }
    var showHint by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // App bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = challenge.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "Problem Statement",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = JavaOrangePrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = challenge.description, fontSize = 13.sp, lineHeight = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    challenge.testCases.forEach { tc ->
                        Surface(
                            color = Color(0xFF0F172A),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                        ) {
                            Text(
                                text = "Input: ${tc.inputDescription} ➔ Expected: ${tc.expectedOutput}",
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                color = JavaCyanSecondary,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }

            // Code Editor
            Surface(
                color = CodeEditorBackground,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = codeText,
                    onValueChange = { codeText = it },
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        color = TextPrimaryDark
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { runResult = JavaExecutionEngine.execute(codeText) },
                    colors = ButtonDefaults.buttonColors(containerColor = JavaEmeraldSuccess),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Run Solution")
                }
                OutlinedButton(
                    onClick = { showHint = !showHint },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(if (showHint) "Hide Hint" else "Hint")
                }
            }

            if (showHint) {
                Surface(
                    color = Color(0xFF0F172A),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "💡 Hint: ${challenge.hint}",
                        fontSize = 12.sp,
                        color = Color(0xFFFBBF24),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            if (runResult != null) {
                Surface(
                    color = Color(0xFF090D18),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text("EXECUTION FEEDBACK:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = JavaCyanSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = runResult!!.stdout.ifEmpty { runResult!!.stderr },
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp,
                            color = if (runResult!!.isSuccess) JavaEmeraldSuccess else JavaRoseError
                        )
                    }
                }
            }
        }
    }
}

// SubTab 1: Output Prediction
@Composable
private fun OutputPredictionListView() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(JavaPracticeData.outputPredictions) { op ->
            var selectedChoice by remember { mutableStateOf<Int?>(null) }

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(op.topic, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = JavaOrangePrimary)
                        Text("What will this print?", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        color = CodeEditorBackground,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = op.codeSnippet,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp,
                            color = TextPrimaryDark,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        op.options.forEachIndexed { idx, optText ->
                            val isChosen = selectedChoice == idx
                            val isCorrect = idx == op.correctIndex
                            val showFeedback = selectedChoice != null

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = when {
                                    showFeedback && isCorrect -> JavaEmeraldSuccess.copy(alpha = 0.2f)
                                    showFeedback && isChosen -> JavaRoseError.copy(alpha = 0.2f)
                                    isChosen -> JavaOrangePrimary.copy(alpha = 0.2f)
                                    else -> MaterialTheme.colorScheme.surface
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedChoice = idx }
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${('A'.code + idx).toChar()}.",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = optText, fontSize = 12.sp, fontFamily = FontFamily.Monospace)
                                }
                            }
                        }
                    }

                    if (selectedChoice != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = op.explanation,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

// SubTab 2: Code Fixing
@Composable
private fun CodeFixingListView() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(JavaPracticeData.codeFixingTasks) { task ->
            var codeText by remember(task) { mutableStateOf(task.brokenCode) }
            var fixResult by remember { mutableStateOf<ExecutionResult?>(null) }
            var showHint by remember { mutableStateOf(false) }

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(task.title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface)
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = JavaRoseError.copy(alpha = 0.2f)
                        ) {
                            Text(task.errorType, color = JavaRoseError, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        color = CodeEditorBackground,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = codeText,
                            onValueChange = { codeText = it },
                            textStyle = androidx.compose.ui.text.TextStyle(
                                fontFamily = FontFamily.Monospace,
                                fontSize = 11.sp,
                                color = TextPrimaryDark
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = { fixResult = JavaExecutionEngine.execute(codeText) },
                            colors = ButtonDefaults.buttonColors(containerColor = JavaEmeraldSuccess),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Compile & Test")
                        }
                        OutlinedButton(
                            onClick = { showHint = !showHint },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(if (showHint) "Hide" else "Hint")
                        }
                    }
                    if (showHint) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("💡 ${task.hint}", fontSize = 11.sp, color = Color(0xFFFBBF24))
                    }
                    if (fixResult != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (fixResult!!.isSuccess) "✅ FIXED! " + fixResult!!.stdout else "❌ " + fixResult!!.stderr,
                            color = if (fixResult!!.isSuccess) JavaEmeraldSuccess else JavaRoseError,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}

// SubTab 3: Quiz Center
@Composable
private fun QuizCenterView() {
    var currentQuestionIdx by remember { mutableStateOf(0) }
    var userAnswers by remember { mutableStateOf(mapOf<Int, Int>()) }
    var quizFinished by remember { mutableStateOf(false) }

    val questions = JavaPracticeData.quizQuestions
    val currentQuestion = questions.getOrNull(currentQuestionIdx)

    if (quizFinished) {
        val correctCount = userAnswers.count { (qIdx, ansIdx) -> questions[qIdx].correctIndex == ansIdx }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(JavaEmeraldSuccess.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.EmojiEvents, contentDescription = null, tint = JavaEmeraldSuccess, modifier = Modifier.size(36.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Quiz Completed!", style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
            Text("Score: $correctCount / ${questions.size} Correct", fontSize = 15.sp, color = JavaOrangePrimary, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    currentQuestionIdx = 0
                    userAnswers = emptyMap()
                    quizFinished = false
                },
                colors = ButtonDefaults.buttonColors(containerColor = JavaOrangePrimary),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Restart Quiz")
            }
        }
    } else if (currentQuestion != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Question ${currentQuestionIdx + 1} of ${questions.size} • ${currentQuestion.category}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = JavaCyanSecondary
            )
            Text(
                text = currentQuestion.question,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                currentQuestion.options.forEachIndexed { idx, opt ->
                    val isChosen = userAnswers[currentQuestionIdx] == idx
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isChosen) JavaOrangePrimary.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant,
                        border = if (isChosen) androidx.compose.foundation.BorderStroke(1.dp, JavaOrangePrimary) else null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                userAnswers = userAnswers + (currentQuestionIdx to idx)
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("${('A'.code + idx).toChar()}.", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(opt, fontSize = 13.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (currentQuestionIdx < questions.size - 1) {
                        currentQuestionIdx++
                    } else {
                        quizFinished = true
                    }
                },
                enabled = userAnswers.containsKey(currentQuestionIdx),
                colors = ButtonDefaults.buttonColors(containerColor = JavaOrangePrimary),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (currentQuestionIdx < questions.size - 1) "Next Question" else "Submit Quiz")
            }
        }
    }
}

// SubTab 4: Projects
@Composable
private fun ProjectsListView(onSelectProject: (ProjectItem) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(JavaProjectsData.projects) { proj ->
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectProject(proj) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${proj.tier} Project • ${proj.estimatedHours}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = JavaCyanSecondary
                        )
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = JavaEmeraldSuccess.copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = "100% Free",
                                color = JavaEmeraldSuccess,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = proj.title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = proj.description,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        proj.technologies.forEach { tech ->
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.surface
                            ) {
                                Text(
                                    text = tech,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProjectDetailView(
    project: ProjectItem,
    onBack: () -> Unit
) {
    var activeTab by remember { mutableStateOf(0) } // 0: Overview & Tasks, 1: Multi-file Code Lab

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // App bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = project.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f)
            )
        }

        TabRow(
            selectedTabIndex = activeTab,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = JavaOrangePrimary
        ) {
            Tab(selected = activeTab == 0, onClick = { activeTab = 0 }, text = { Text("Milestones & Tasks") })
            Tab(selected = activeTab == 1, onClick = { activeTab = 1 }, text = { Text("Project Workspace IDE") })
        }

        if (activeTab == 0) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Project Architecture & Requirements",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = JavaOrangePrimary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = project.description, fontSize = 12.sp, lineHeight = 18.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = project.documentationGuide, fontSize = 11.sp, color = JavaCyanSecondary)
                    }
                }

                Text("Milestone Tasks Checklist", fontWeight = FontWeight.Bold, fontSize = 13.sp)

                project.tasks.forEach { task ->
                    var isDone by remember { mutableStateOf(task.isDefaultCompleted) }
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isDone,
                                onCheckedChange = { isDone = it },
                                colors = CheckboxDefaults.colors(checkedColor = JavaEmeraldSuccess)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(task.title, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                Text(task.instruction, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        } else {
            InteractiveCodeLabView(initialFiles = project.starterFiles)
        }
    }
}
