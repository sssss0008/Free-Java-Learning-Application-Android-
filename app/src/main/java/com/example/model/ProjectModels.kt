package com.example.model

data class ProjectItem(
    val id: String,
    val title: String,
    val tier: String, // "Beginner", "Intermediate", "Advanced"
    val estimatedHours: String,
    val description: String,
    val learningGoals: List<String>,
    val technologies: List<String>,
    val starterFiles: Map<String, String>, // filename -> code content
    val tasks: List<ProjectTask>,
    val documentationGuide: String
)

data class ProjectTask(
    val id: String,
    val title: String,
    val instruction: String,
    val codeHint: String,
    val isDefaultCompleted: Boolean = false
)
