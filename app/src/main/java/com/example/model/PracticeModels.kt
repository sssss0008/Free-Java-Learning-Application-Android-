package com.example.model

data class DailyChallenge(
    val id: String,
    val title: String,
    val difficulty: String, // "Easy", "Medium", "Hard", "Expert"
    val category: String,
    val description: String,
    val starterCode: String,
    val testCases: List<ChallengeTestCase>,
    val hint: String,
    val solutionCode: String,
    val explanation: String
)

data class ChallengeTestCase(
    val inputDescription: String,
    val expectedOutput: String
)

data class OutputPrediction(
    val id: String,
    val title: String,
    val topic: String,
    val codeSnippet: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val variableTrace: List<String>
)

data class CodeFixingTask(
    val id: String,
    val title: String,
    val errorType: String,
    val brokenCode: String,
    val errorLine: Int,
    val hint: String,
    val fixedCode: String,
    val explanation: String
)

data class QuizQuestion(
    val id: String,
    val category: String,
    val question: String,
    val codeSnippet: String? = null,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)
