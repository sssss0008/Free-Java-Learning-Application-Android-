package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.JavaCyanSecondary
import com.example.ui.theme.JavaOrangePrimary
import com.example.ui.theme.JavaPurpleTertiary

data class TutorMessage(
    val sender: String, // "user" or "tutor"
    val content: String,
    val codeBlock: String? = null
)

@Composable
fun AiJavaTutorDialog(
    onDismissRequest: () -> Unit
) {
    var messages by remember {
        mutableStateOf(
            listOf(
                TutorMessage(
                    sender = "tutor",
                    content = "Hello! I am your AI Java Tutor for Learn Java by Awiskar Acharya.\n\nAsk me any concept: Object-Oriented Programming, JVM memory internals, Streams, debugging exceptions, or writing clean code.",
                    codeBlock = "// Quick Example: Clean Class Definition\npublic class Developer {\n    private final String name;\n    public Developer(String name) { this.name = name; }\n    public void code() { System.out.println(name + \" is mastering Java!\"); }\n}"
                )
            )
        )
    }

    var userInputText by remember { mutableStateOf("") }

    val quickActionChips = listOf(
        "Explain OOP",
        "JVM Stack vs Heap",
        "Debug NPE",
        "Give Hint",
        "Stream API Example",
        "Spring Architecture",
        "Simplify Concept",
        "Go Deeper"
    )

    fun handleQuickAction(action: String) {
        val userMsg = TutorMessage(sender = "user", content = action)
        val reply = when (action) {
            "Explain OOP" -> TutorMessage(
                sender = "tutor",
                content = "Object-Oriented Programming (OOP) is built on 4 foundational pillars:\n\n1. Encapsulation: Bundle data & methods together; hide state via 'private' fields and accessors.\n2. Inheritance: Reuse code hierarchically with 'extends'.\n3. Polymorphism: Treat specialized objects uniformly via parent references (dynamic dispatch).\n4. Abstraction: Hide complex implementation details using 'abstract class' and 'interface'.",
                codeBlock = "Animal myDog = new Dog(); // Polymorphic instantiation\nmyDog.makeSound(); // Dispatches to Dog's sound at runtime"
            )
            "JVM Stack vs Heap" -> TutorMessage(
                sender = "tutor",
                content = "Here is the key distinction in JVM memory management:\n\n• STACK: Thread-isolated memory holding active method frames, execution pointers, and primitive local variables. Very fast, auto-cleared when methods return.\n• HEAP: Global memory shared by all threads where ALL created objects (via 'new') and arrays reside. Managed by the automatic Garbage Collector (GC).",
                codeBlock = "int age = 22; // Primitive stored directly in current Stack frame\nStudent s = new Student(); // 's' is a reference on Stack pointing to Student object on Heap"
            )
            "Debug NPE" -> TutorMessage(
                sender = "tutor",
                content = "NullPointerException (NPE) happens when you invoke an instance method or access a field on a variable pointing to 'null'.\n\nFixing steps:\n1. Check the exact line in your stack trace.\n2. Add a null guard: if (variable != null) { ... }\n3. Use modern Java Optional<T>:\n   Optional.ofNullable(variable).ifPresent(v -> ...);",
                codeBlock = "String name = null;\n// Guard before method call:\nif (name != null) {\n    System.out.println(name.toUpperCase());\n}"
            )
            "Stream API Example" -> TutorMessage(
                sender = "tutor",
                content = "The Stream API lets you process collections declaratively without clunky loops:\n• filter: select items matching condition\n• map: transform elements\n• collect: materialize result into a new List/Set.",
                codeBlock = "List<String> list = Arrays.asList(\"Java\", \"Spring\", \"Python\");\nList<String> jOnly = list.stream()\n    .filter(s -> s.startsWith(\"J\"))\n    .map(String::toUpperCase)\n    .collect(Collectors.toList());"
            )
            "Spring Architecture" -> TutorMessage(
                sender = "tutor",
                content = "Enterprise Spring Boot uses 3 clean separation layers:\n1. @RestController: Handles HTTP routes (GET, POST), validates payload, returns JSON.\n2. @Service: Encapsulates business logic, transactions, and calculations.\n3. @Repository: Connects to PostgreSQL/MySQL using Spring Data JPA.",
                codeBlock = "Controller (HTTP) ---> Service (Logic) ---> Repository (Database)"
            )
            "Simplify Concept" -> TutorMessage(
                sender = "tutor",
                content = "Think of a Java Class like a blueprint for a house, and an Object like the actual house built from that blueprint. You can build 1,000 houses (objects) from one single blueprint (class)!"
            )
            "Give Hint" -> TutorMessage(
                sender = "tutor",
                content = "Always trace variable states step by step! If an array index is failing, check if your loop stops at '< array.length' rather than '<='."
            )
            else -> TutorMessage(
                sender = "tutor",
                content = "Java 21 delivers modern enhancements including Virtual Threads (Project Loom) for ultra-scalable I/O, Pattern Matching for switch expressions, Sequenced Collections, and Record Patterns for clean data decomposition."
            )
        }
        messages = messages + userMsg + reply
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(580.dp)
                .testTag("ai_tutor_dialog"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(JavaCyanSecondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "AI Java Tutor",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Instant guidance for Java mastery",
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }
                    IconButton(onClick = onDismissRequest) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    }
                }

                // Quick Action Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    quickActionChips.forEach { chipText ->
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = JavaOrangePrimary.copy(alpha = 0.12f),
                            modifier = Modifier.clickable { handleQuickAction(chipText) }
                        ) {
                            Text(
                                text = chipText,
                                color = JavaOrangePrimary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }

                // Chat Messages Scroll
                val scrollState = rememberScrollState()
                LaunchedEffect(messages.size) {
                    scrollState.animateScrollTo(scrollState.maxValue)
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    messages.forEach { msg ->
                        val isUser = msg.sender == "user"
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
                        ) {
                            Surface(
                                shape = RoundedCornerShape(
                                    topStart = 14.dp,
                                    topEnd = 14.dp,
                                    bottomStart = if (isUser) 14.dp else 2.dp,
                                    bottomEnd = if (isUser) 2.dp else 14.dp
                                ),
                                color = if (isUser) JavaOrangePrimary else MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier.widthIn(max = 300.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = msg.content,
                                        color = if (isUser) Color.White else MaterialTheme.colorScheme.onSurface,
                                        fontSize = 13.sp,
                                        lineHeight = 18.sp
                                    )

                                    if (msg.codeBlock != null) {
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = Color(0xFF090D18),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text(
                                                text = msg.codeBlock,
                                                color = JavaCyanSecondary,
                                                fontFamily = FontFamily.Monospace,
                                                fontSize = 11.sp,
                                                modifier = Modifier.padding(8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Input bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = userInputText,
                        onValueChange = { userInputText = it },
                        placeholder = { Text("Ask a Java question...", fontSize = 12.sp) },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        shape = RoundedCornerShape(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            if (userInputText.isNotBlank()) {
                                val text = userInputText.trim()
                                userInputText = ""
                                handleQuickAction(text)
                            }
                        },
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(JavaOrangePrimary)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}
