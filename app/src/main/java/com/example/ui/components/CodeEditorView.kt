package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ExecutionResult
import com.example.data.JavaExecutionEngine
import com.example.ui.theme.*

@Composable
fun InteractiveCodeLabView(
    initialFiles: Map<String, String>,
    modifier: Modifier = Modifier
) {
    var filesMap by remember { mutableStateOf(initialFiles) }
    var activeFileName by remember { mutableStateOf(initialFiles.keys.firstOrNull() ?: "Main.java") }
    var currentCode by remember(activeFileName) {
        mutableStateOf(filesMap[activeFileName] ?: "")
    }

    var consoleTab by remember { mutableStateOf(0) } // 0: Output, 1: Variables, 2: Debugger, 3: Input
    var executionResult by remember { mutableStateOf<ExecutionResult?>(null) }
    var isRunning by remember { mutableStateOf(false) }
    var userInput by remember { mutableStateOf("") }
    var breakpoints by remember { mutableStateOf(setOf(4)) }
    var activeDebugLine by remember { mutableStateOf<Int?>(null) }

    fun runCode() {
        isRunning = true
        val result = JavaExecutionEngine.execute(currentCode, userInput)
        executionResult = result
        isRunning = false
        consoleTab = if (!result.isSuccess && result.stderr.isNotBlank()) 0 else 0
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // File tabs bar & Quick Actions
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                filesMap.keys.forEach { fileName ->
                    val isSelected = fileName == activeFileName
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (isSelected) JavaOrangePrimary.copy(alpha = 0.2f) else Color.Transparent,
                        border = if (isSelected) androidx.compose.foundation.BorderStroke(1.dp, JavaOrangePrimary) else null,
                        modifier = Modifier
                            .clickable {
                                // Save current file edits
                                filesMap = filesMap + (activeFileName to currentCode)
                                activeFileName = fileName
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Description,
                                contentDescription = null,
                                tint = if (isSelected) JavaOrangePrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = fileName,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) JavaOrangePrimary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Run Java Button
                Button(
                    onClick = { runCode() },
                    colors = ButtonDefaults.buttonColors(containerColor = JavaEmeraldSuccess),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.testTag("run_code_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Run",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Run Java",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        // Code Editor area with Line Numbers
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(CodeEditorBackground)
        ) {
            val lines = currentCode.lines()
            val verticalScroll = rememberScrollState()

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(verticalScroll)
            ) {
                // Line numbers gutter & breakpoints
                Column(
                    modifier = Modifier
                        .width(44.dp)
                        .background(Color(0xFF090D18))
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    lines.indices.forEach { index ->
                        val lineNum = index + 1
                        val hasBp = breakpoints.contains(lineNum)
                        val isCurrentDebug = activeDebugLine == lineNum

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(22.dp)
                                .clickable {
                                    breakpoints = if (hasBp) breakpoints - lineNum else breakpoints + lineNum
                                }
                        ) {
                            if (hasBp) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(JavaRoseError)
                                )
                            } else {
                                Text(
                                    text = "$lineNum",
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = if (isCurrentDebug) JavaCyanSecondary else TextMutedDark
                                )
                            }
                        }
                    }
                }

                // Code Input Area
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    BasicTextField(
                        value = currentCode,
                        onValueChange = {
                            currentCode = it
                            filesMap = filesMap + (activeFileName to it)
                        },
                        textStyle = TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 13.sp,
                            lineHeight = 22.sp,
                            color = TextPrimaryDark
                        ),
                        cursorBrush = SolidColor(JavaOrangePrimary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("code_text_field")
                    )
                }
            }
        }

        // Bottom Console / Output / Debugger Panel
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Console Tabs (Output, Variables, Debugger, Input)
                TabRow(
                    selectedTabIndex = consoleTab,
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = JavaOrangePrimary,
                    modifier = Modifier.height(36.dp)
                ) {
                    val tabs = listOf("Console Output", "Variables", "Call Stack", "User Input")
                    tabs.forEachIndexed { idx, title ->
                        Tab(
                            selected = consoleTab == idx,
                            onClick = { consoleTab = idx },
                            text = {
                                Text(
                                    text = title,
                                    fontSize = 11.sp,
                                    fontWeight = if (consoleTab == idx) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    when (consoleTab) {
                        0 -> {
                            // Standard output / errors
                            val res = executionResult
                            val verticalScroll = rememberScrollState()
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(verticalScroll)
                            ) {
                                if (res == null) {
                                    Text(
                                        text = "Terminal idle. Click 'Run Java' to compile & execute.",
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily.Monospace,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                } else {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(bottom = 6.dp)
                                    ) {
                                        Surface(
                                            shape = RoundedCornerShape(4.dp),
                                            color = if (res.isSuccess) JavaEmeraldSuccess.copy(alpha = 0.2f) else JavaRoseError.copy(alpha = 0.2f)
                                        ) {
                                            Text(
                                                text = if (res.isSuccess) "BUILD SUCCESS" else "BUILD FAILED",
                                                color = if (res.isSuccess) JavaEmeraldSuccess else JavaRoseError,
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Time: ${res.executionTimeMs}ms | JVM Heap: ${res.memoryUsedMb}MB",
                                            fontSize = 11.sp,
                                            fontFamily = FontFamily.Monospace,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }

                                    if (res.stdout.isNotBlank()) {
                                        Text(
                                            text = res.stdout,
                                            fontFamily = FontFamily.Monospace,
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }

                                    if (res.stderr.isNotBlank()) {
                                        Text(
                                            text = res.stderr,
                                            fontFamily = FontFamily.Monospace,
                                            fontSize = 12.sp,
                                            color = JavaRoseError
                                        )
                                    }
                                }
                            }
                        }
                        1 -> {
                            // Variable Inspector
                            val vars = executionResult?.variablesSnapshot ?: emptyMap()
                            if (vars.isEmpty()) {
                                Text(
                                    text = "No runtime variables captured yet. Run program to inspect JVM state.",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            } else {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Text(
                                        text = "JVM Stack Variables Snapshot:",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = JavaCyanSecondary
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    vars.forEach { (name, value) ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 2.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                                    RoundedCornerShape(4.dp)
                                                )
                                                .padding(horizontal = 8.dp, vertical = 4.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = name,
                                                fontFamily = FontFamily.Monospace,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = JavaOrangePrimary
                                            )
                                            Text(
                                                text = value,
                                                fontFamily = FontFamily.Monospace,
                                                fontSize = 12.sp,
                                                color = JavaCyanSecondary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        2 -> {
                            // Call Stack & Bytecode Trace
                            val callStack = executionResult?.callStackSnapshot ?: listOf("Main.main(String[] args)")
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Text(
                                    text = "Active Call Stack Frames (Top to Bottom):",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = JavaCyanSecondary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                callStack.forEachIndexed { index, frame ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "#$index",
                                            fontSize = 11.sp,
                                            fontFamily = FontFamily.Monospace,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = frame,
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }
                        3 -> {
                            // User Input (Scanner stdin simulation)
                            Column(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = "Standard Input (stdin for Scanner):",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = JavaOrangePrimary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                OutlinedTextField(
                                    value = userInput,
                                    onValueChange = { userInput = it },
                                    placeholder = { Text("e.g. Awiskar", fontSize = 12.sp) },
                                    singleLine = true,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
