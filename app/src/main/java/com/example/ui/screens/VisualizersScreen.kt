package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun VisualizersScreen(
    initialTab: String = "oop",
    modifier: Modifier = Modifier
) {
    var activeLabTab by remember(initialTab) {
        mutableStateOf(
            when (initialTab) {
                "jvm" -> 1
                "collections" -> 2
                "dsa" -> 3
                "spring" -> 4
                else -> 0 // "oop"
            }
        )
    }

    val labTabs = listOf("OOP Visualizer", "JVM Architecture", "Collections Lab", "DSA Algorithms", "Spring Architecture")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Interactive Java Laboratories",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "“DON'T JUST LEARN JAVA — SEE HOW IT WORKS.”",
                fontSize = 12.sp,
                color = JavaOrangePrimary,
                fontWeight = FontWeight.Bold
            )
        }

        // Lab Selector Scrollable Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            labTabs.forEachIndexed { idx, tabTitle ->
                val isSelected = activeLabTab == idx
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) JavaOrangePrimary else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clickable { activeLabTab = idx }
                ) {
                    Text(
                        text = tabTitle,
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
            when (activeLabTab) {
                0 -> OopVisualizerTab()
                1 -> JvmVisualizerTab()
                2 -> CollectionsVisualizerTab()
                3 -> DsaVisualizerTab()
                4 -> SpringArchitectureTab()
            }
        }
    }
}

// 1. OOP Visualizer
@Composable
private fun OopVisualizerTab() {
    var studentNameInput by remember { mutableStateOf("Awiskar") }
    var studentAgeInput by remember { mutableStateOf("22") }
    var objectsList by remember {
        mutableStateOf(
            listOf(
                Pair("student1", Pair("Awiskar", 22)),
                Pair("student2", Pair("Elena", 24))
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Class Blueprint
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "CLASS BLUEPRINT (Method Area / Metaspace)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = JavaCyanSecondary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    color = CodeEditorBackground,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = """class Student {
    private String name;
    private int age;
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void study() { ... }
}""",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        color = TextPrimaryDark,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }

        // Interactive Object Instantiator
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Instantiate New Heap Object with 'new'",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = studentNameInput,
                        onValueChange = { studentNameInput = it },
                        label = { Text("Name", fontSize = 11.sp) },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = studentAgeInput,
                        onValueChange = { studentAgeInput = it },
                        label = { Text("Age", fontSize = 11.sp) },
                        modifier = Modifier.width(80.dp),
                        singleLine = true
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        if (studentNameInput.isNotBlank()) {
                            val nextId = "student${objectsList.size + 1}"
                            val age = studentAgeInput.toIntOrNull() ?: 20
                            objectsList = objectsList + Pair(nextId, Pair(studentNameInput, age))
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = JavaOrangePrimary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("new Student(\"$studentNameInput\", $studentAgeInput)", fontWeight = FontWeight.Bold)
                }
            }
        }

        // Live Heap Objects Inspection
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "LIVING HEAP INSTANCES (${objectsList.size})",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = JavaEmeraldSuccess
            )

            objectsList.forEach { (refName, data) ->
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, JavaCyanSecondary.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Reference: $refName",
                                fontWeight = FontWeight.Bold,
                                color = JavaOrangePrimary,
                                fontSize = 13.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Text(
                                text = "Heap @0x${(refName.hashCode() and 0xFFFF).toString(16).uppercase()}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Text(
                                text = "name → \"${data.first}\"",
                                fontSize = 12.sp,
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "age → ${data.second}",
                                fontSize = 12.sp,
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Inheritance Tree Visualizer
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Inheritance & Dynamic Dispatch Tree",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    TreeBlock("Animal (Parent Base Class)", "eat(), makeSound()", JavaCyanSecondary)
                    Icon(imageVector = Icons.Default.ArrowDownward, contentDescription = null, tint = JavaOrangePrimary)
                    TreeBlock("Dog (extends Animal)", "@Override makeSound() -> \"Woof!\"", JavaOrangePrimary)
                    Icon(imageVector = Icons.Default.ArrowDownward, contentDescription = null, tint = JavaOrangePrimary)
                    TreeBlock("GermanShepherd (extends Dog)", "guard(), specialCoat()", JavaPurpleTertiary)
                }
            }
        }
    }
}

@Composable
private fun TreeBlock(title: String, desc: String, color: Color) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = color.copy(alpha = 0.15f),
        border = androidx.compose.foundation.BorderStroke(1.dp, color),
        modifier = Modifier.fillMaxWidth(0.9f)
    ) {
        Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = color)
            Text(desc, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

// 2. JVM Architecture Visualizer
@Composable
private fun JvmVisualizerTab() {
    var gcTriggered by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "JVM Runtime Data Areas",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Visual inspection of Thread Stacks, Metaspace, and Young/Old Generation Heap",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Stack Column
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFF0F172A), RoundedCornerShape(10.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "THREAD STACK",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = JavaCyanSecondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        StackBox("calculateTax()", "tax = 14.5")
                        Spacer(modifier = Modifier.height(4.dp))
                        StackBox("calculateTotal()", "subtotal = 100")
                        Spacer(modifier = Modifier.height(4.dp))
                        StackBox("main(args)", "userRef = 0x2A")
                    }

                    // Heap Column
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFF0F172A), RoundedCornerShape(10.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "HEAP MEMORY",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = JavaOrangePrimary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        HeapBox("Eden: Student Object", "Reachable", JavaEmeraldSuccess)
                        Spacer(modifier = Modifier.height(4.dp))
                        HeapBox("Eden: String \"Awiskar\"", "Reachable", JavaEmeraldSuccess)
                        Spacer(modifier = Modifier.height(4.dp))
                        HeapBox(
                            title = if (gcTriggered) "[Reclaimed by GC]" else "Unused Buffer Object",
                            sub = if (gcTriggered) "Freed: +4MB" else "Unreachable",
                            color = if (gcTriggered) JavaCyanSecondary else JavaRoseError
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = { gcTriggered = !gcTriggered },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (gcTriggered) JavaEmeraldSuccess else JavaOrangePrimary
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.CleaningServices, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (gcTriggered) "GC Completed (Reset Simulator)" else "Simulate Garbage Collector (Mark & Sweep)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun StackBox(method: String, locals: String) {
    Surface(
        color = Color(0xFF1E293B),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            Text(method, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimaryDark)
            Text(locals, fontSize = 9.sp, color = TextSecondaryDark, fontFamily = FontFamily.Monospace)
        }
    }
}

@Composable
private fun HeapBox(title: String, sub: String, color: Color) {
    Surface(
        color = color.copy(alpha = 0.15f),
        border = androidx.compose.foundation.BorderStroke(1.dp, color),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            Text(title, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = color)
            Text(sub, fontSize = 9.sp, color = TextPrimaryDark)
        }
    }
}

// 3. Collections Lab Visualizer
@Composable
private fun CollectionsVisualizerTab() {
    var arrayListItems by remember { mutableStateOf(listOf("10", "20", "30", "40")) }
    var newItemValue by remember { mutableStateOf("50") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "ArrayList Dynamic Resizing Lab",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Visual Array Cells
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    arrayListItems.forEachIndexed { idx, item ->
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = JavaOrangePrimary.copy(alpha = 0.2f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, JavaOrangePrimary),
                            modifier = Modifier.width(64.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("idx $idx", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(item, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = JavaOrangePrimary)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = newItemValue,
                        onValueChange = { newItemValue = it },
                        label = { Text("Value", fontSize = 10.sp) },
                        modifier = Modifier.width(80.dp),
                        singleLine = true
                    )
                    Button(
                        onClick = {
                            if (newItemValue.isNotBlank()) {
                                arrayListItems = arrayListItems + newItemValue
                                newItemValue = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = JavaOrangePrimary),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(".add()")
                    }
                    Button(
                        onClick = {
                            if (arrayListItems.isNotEmpty()) {
                                arrayListItems = arrayListItems.dropLast(1)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = JavaRoseError),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(".remove(last)")
                    }
                }
            }
        }

        // HashMap Key-Value Bucket Visualizer
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "HashMap Hashing & Buckets",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                val entries = listOf("name" to "Awiskar", "role" to "Engineer", "skill" to "Java", "status" to "Master")
                entries.forEach { (k, v) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                            .background(Color(0xFF0F172A), RoundedCornerShape(6.dp))
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("hash(\"$k\") % 16", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, fontFamily = FontFamily.Monospace)
                        Text("\"$k\"  ──→  \"$v\"", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = JavaCyanSecondary, fontFamily = FontFamily.Monospace)
                        Text("O(1)", fontSize = 11.sp, color = JavaEmeraldSuccess, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

// 4. DSA Visualizer
@Composable
private fun DsaVisualizerTab() {
    var sortArray by remember { mutableStateOf(listOf(64, 25, 12, 22, 11)) }
    var currentStepText by remember { mutableStateOf("Initial unsorted array") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Sorting Step-by-Step Simulation",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = currentStepText,
                    fontSize = 12.sp,
                    color = JavaOrangePrimary,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    sortArray.forEach { num ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                            modifier = Modifier.height(100.dp)
                        ) {
                            Text("$num", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .width(36.dp)
                                    .height((num * 1.2).dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(JavaCyanSecondary)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = {
                            sortArray = sortArray.sorted()
                            currentStepText = "Sorted via Quicksort in O(n log n) comparisons!"
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = JavaEmeraldSuccess),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Sort Array")
                    }
                    Button(
                        onClick = {
                            sortArray = listOf(64, 25, 12, 22, 11).shuffled()
                            currentStepText = "Shuffled randomized array."
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = JavaOrangePrimary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Shuffle")
                    }
                }
            }
        }

        // Big-O Comparison Chart
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Big-O Time Complexity Guide",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(10.dp))
                BigORow("O(1) Constant", "HashMap lookup, Array indexing", JavaEmeraldSuccess)
                BigORow("O(log n) Logarithmic", "Binary Search, Balanced BST", Color(0xFF38BDF8))
                BigORow("O(n) Linear", "Linear Search, Array traversal", Color(0xFFF59E0B))
                BigORow("O(n log n) Log-Linear", "Merge Sort, Arrays.sort()", Color(0xFFFB923C))
                BigORow("O(n²) Quadratic", "Nested loops, Bubble Sort", JavaRoseError)
            }
        }
    }
}

@Composable
private fun BigORow(notation: String, example: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(notation, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = color)
        Text(example, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

// 5. Spring Architecture Visualizer
@Composable
private fun SpringArchitectureTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Spring Boot 3 Enterprise Pipeline",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = JavaOrangePrimary
                )
                Spacer(modifier = Modifier.height(10.dp))
                SpringStepCard("1. @RestController", "Receives HTTP GET /api/v1/students -> validates DTO", JavaCyanSecondary)
                Icon(imageVector = Icons.Default.ArrowDownward, contentDescription = null, tint = JavaOrangePrimary, modifier = Modifier.align(Alignment.CenterHorizontally))
                SpringStepCard("2. @Service (Business Layer)", "Applies GPA calculations, security checks, logging", JavaOrangePrimary)
                Icon(imageVector = Icons.Default.ArrowDownward, contentDescription = null, tint = JavaOrangePrimary, modifier = Modifier.align(Alignment.CenterHorizontally))
                SpringStepCard("3. @Repository (Spring Data JPA)", "Executes SQL 'SELECT * FROM students WHERE id=?'", JavaPurpleTertiary)
                Icon(imageVector = Icons.Default.ArrowDownward, contentDescription = null, tint = JavaOrangePrimary, modifier = Modifier.align(Alignment.CenterHorizontally))
                SpringStepCard("4. PostgreSQL / MySQL", "Returns relational row data mapped into Java Entity", JavaEmeraldSuccess)
            }
        }
    }
}

@Composable
private fun SpringStepCard(title: String, desc: String, color: Color) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = color.copy(alpha = 0.12f),
        border = androidx.compose.foundation.BorderStroke(1.dp, color),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = color)
            Text(desc, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}
