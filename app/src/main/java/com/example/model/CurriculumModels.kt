package com.example.model

data class CurriculumLevel(
    val id: String,
    val levelNumber: Int,
    val title: String,
    val subtitle: String,
    val iconKey: String,
    val modules: List<CurriculumModule> = emptyList()
) {
    val totalLessons: Int
        get() = modules.sumOf { it.lessons.size }
}

data class CurriculumModule(
    val id: String,
    val levelId: String,
    val title: String,
    val description: String,
    val lessons: List<Lesson> = emptyList()
)

data class Lesson(
    val id: String,
    val moduleId: String,
    val levelId: String,
    val title: String,
    val estimatedMinutes: Int = 10,
    val conceptSummary: String,
    val practicalWhy: String,
    val syntaxSnippet: String,
    val exampleCode: String,
    val diagramTitle: String,
    val diagramDescription: String,
    val diagramNodes: List<DiagramNode> = emptyList(),
    val interactiveCode: String,
    val expectedOutput: String,
    val commonMistakes: List<String> = emptyList(),
    val practiceTask: String,
    val practiceStarterCode: String,
    val practiceSolutionCode: String,
    val realWorldApplication: String
)

data class DiagramNode(
    val id: String,
    val label: String,
    val sublabel: String,
    val nodeType: String // "source", "compiler", "bytecode", "jvm", "heap", "stack", "output", "step"
)
