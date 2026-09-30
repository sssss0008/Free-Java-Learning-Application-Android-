package com.example.ui.screens

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
import com.example.data.JavaCurriculumData
import com.example.data.JavaExecutionEngine
import com.example.model.CurriculumLevel
import com.example.model.Lesson
import com.example.ui.theme.*

@Composable
fun LearnScreen(
    completedLessonIds: Set<String>,
    bookmarkedLessonIds: Set<String>,
    onLessonCompleted: (String) -> Unit,
    onToggleBookmark: (String) -> Unit,
    onOpenInCodeLab: (String, String) -> Unit,
    initialSelectedLessonId: String? = null,
    modifier: Modifier = Modifier
) {
    var activeLesson by remember {
        mutableStateOf(
            if (initialSelectedLessonId != null) {
                JavaCurriculumData.findLesson(initialSelectedLessonId)
            } else {
                null
            }
        )
    }

    var selectedLevelIndex by remember { mutableStateOf(0) }

    if (activeLesson != null) {
        LessonDetailView(
            lesson = activeLesson!!,
            isCompleted = completedLessonIds.contains(activeLesson!!.id),
            isBookmarked = bookmarkedLessonIds.contains(activeLesson!!.id),
            onBack = { activeLesson = null },
            onMarkComplete = { onLessonCompleted(activeLesson!!.id) },
            onToggleBookmark = { onToggleBookmark(activeLesson!!.id) },
            onOpenInCodeLab = { code -> onOpenInCodeLab("Main.java", code) }
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
                    text = "Java Academy Curriculum",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "12 Comprehensive Levels • 100% Free • University Grade",
                    fontSize = 12.sp,
                    color = JavaCyanSecondary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Levels Horizontal Scroll Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                JavaCurriculumData.levels.forEachIndexed { index, level ->
                    val isSelected = selectedLevelIndex == index
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) JavaOrangePrimary else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.clickable { selectedLevelIndex = index }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Lvl ${level.levelNumber}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = level.title,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 12.sp,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            val currentLevel = JavaCurriculumData.levels.getOrNull(selectedLevelIndex)
                ?: JavaCurriculumData.levels.first()

            // Level Modules & Lessons List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    // Level Overview Banner
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Level ${currentLevel.levelNumber}: ${currentLevel.title}",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = JavaOrangePrimary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = currentLevel.subtitle,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                currentLevel.modules.forEach { module ->
                    item {
                        Text(
                            text = module.title,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                        )
                    }

                    items(module.lessons) { lesson ->
                        val isDone = completedLessonIds.contains(lesson.id)
                        val isSaved = bookmarkedLessonIds.contains(lesson.id)

                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { activeLesson = lesson }
                                .testTag("lesson_item_${lesson.id}")
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isDone) JavaEmeraldSuccess.copy(alpha = 0.2f)
                                            else JavaOrangePrimary.copy(alpha = 0.15f)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (isDone) Icons.Default.Check else Icons.Default.MenuBook,
                                        contentDescription = null,
                                        tint = if (isDone) JavaEmeraldSuccess else JavaOrangePrimary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = lesson.title,
                                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "${lesson.estimatedMinutes} mins • 10-Part Interactive Lab",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                if (isSaved) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = "Bookmarked",
                                        tint = JavaOrangePrimary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                }

                                Icon(
                                    imageVector = Icons.Default.ChevronRight,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
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
fun LessonDetailView(
    lesson: Lesson,
    isCompleted: Boolean,
    isBookmarked: Boolean,
    onBack: () -> Unit,
    onMarkComplete: () -> Unit,
    onToggleBookmark: () -> Unit,
    onOpenInCodeLab: (String) -> Unit
) {
    var interactiveCodeText by remember(lesson) { mutableStateOf(lesson.interactiveCode) }
    var runResult by remember { mutableStateOf<ExecutionResult?>(null) }
    var showSolution by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Detail Header
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
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
                    text = lesson.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onToggleBookmark) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) JavaOrangePrimary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Lesson 10-Part Scroll Body
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Concept Summary
            LessonSectionCard(title = "1. What is it? (Core Concept)", icon = Icons.Default.Lightbulb) {
                Text(
                    text = lesson.conceptSummary,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp)
                )
            }

            // 2. Practical Why
            LessonSectionCard(title = "2. Why is it useful?", icon = Icons.Default.Psychology) {
                Text(
                    text = lesson.practicalWhy,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                    color = JavaCyanSecondary
                )
            }

            // 3. Syntax Reference
            LessonSectionCard(title = "3. Formal Java Syntax", icon = Icons.Default.Code) {
                Surface(
                    color = CodeEditorBackground,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = lesson.syntaxSnippet,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        color = JavaCyanSecondary,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            // 4. Real Code Example
            LessonSectionCard(title = "4. Real Code Example", icon = Icons.Default.Terminal) {
                Surface(
                    color = CodeEditorBackground,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = lesson.exampleCode,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        color = TextPrimaryDark,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            // 5. Visual Architecture / Flow Diagram
            LessonSectionCard(title = "5. Visual Execution Flow", icon = Icons.Default.AccountTree) {
                Text(
                    text = lesson.diagramTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = JavaOrangePrimary
                )
                Text(
                    text = lesson.diagramDescription,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    lesson.diagramNodes.forEachIndexed { idx, node ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                                .padding(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(JavaCyanSecondary),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${idx + 1}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = node.label,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = node.sublabel,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            // 6. Interactive Code Runner (Editable!)
            LessonSectionCard(title = "6. Interactive Code Lab", icon = Icons.Default.PlayCircle) {
                Text(
                    text = "Modify and execute this code live:",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    color = CodeEditorBackground,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = interactiveCodeText,
                        onValueChange = { interactiveCodeText = it },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp,
                            color = TextPrimaryDark
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { onOpenInCodeLab(interactiveCodeText) },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Open in IDE Lab", fontSize = 12.sp)
                    }

                    Button(
                        onClick = {
                            runResult = JavaExecutionEngine.execute(interactiveCodeText)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = JavaEmeraldSuccess),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Run Java", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                // 7. Output Terminal
                if (runResult != null) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        color = Color(0xFF090D18),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "CONSOLE OUTPUT:",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = JavaCyanSecondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = runResult!!.stdout.ifEmpty { runResult!!.stderr },
                                fontFamily = FontFamily.Monospace,
                                fontSize = 12.sp,
                                color = if (runResult!!.isSuccess) Color.White else JavaRoseError
                            )
                        }
                    }
                }
            }

            // 8. Common Mistakes
            LessonSectionCard(title = "8. Common Mistakes & Pitfalls", icon = Icons.Default.Warning) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    lesson.commonMistakes.forEach { mistake ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text("• ", color = JavaRoseError, fontWeight = FontWeight.Bold)
                            Text(
                                text = mistake,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // 9. Practice Task
            LessonSectionCard(title = "9. Practice Challenge", icon = Icons.Default.FitnessCenter) {
                Text(
                    text = lesson.practiceTask,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = { showSolution = !showSolution }) {
                    Text(if (showSolution) "Hide Solution" else "Show Solution Hint")
                }

                if (showSolution) {
                    Surface(
                        color = CodeEditorBackground,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = lesson.practiceSolutionCode,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp,
                            color = JavaEmeraldSuccess,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }

            // 10. Real-World Use
            LessonSectionCard(title = "10. Real-World Industry Application", icon = Icons.Default.Public) {
                Text(
                    text = lesson.realWorldApplication,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Completion Action Button
            Button(
                onClick = onMarkComplete,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isCompleted) JavaEmeraldSuccess else JavaOrangePrimary
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Icon(
                    imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.Check,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isCompleted) "Completed (+50 XP)" else "Mark Lesson as Complete",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
private fun LessonSectionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = JavaOrangePrimary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}
