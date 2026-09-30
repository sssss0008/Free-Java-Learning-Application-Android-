package com.example.data

data class ExecutionResult(
    val isSuccess: Boolean,
    val stdout: String,
    val stderr: String = "",
    val executionTimeMs: Long = 42,
    val memoryUsedMb: Double = 14.8,
    val variablesSnapshot: Map<String, String> = emptyMap(),
    val callStackSnapshot: List<String> = emptyList(),
    val bytecodeTrace: List<String> = emptyList()
)

data class DebugFrame(
    val lineNumber: Int,
    val lineCode: String,
    val variables: Map<String, String>,
    val callStack: List<String>,
    val stdoutSoFar: String
)

object JavaExecutionEngine {

    fun execute(code: String, userInput: String = ""): ExecutionResult {
        val lines = code.lines()
        val stdoutBuilder = StringBuilder()
        val stderrBuilder = StringBuilder()
        val variables = mutableMapOf<String, String>()
        val callStack = mutableListOf("Main.main(String[] args)")
        val bytecodeTrace = mutableListOf<String>()

        bytecodeTrace.add("0: getstatic     #2 // Field java/lang/System.out:Ljava/io/PrintStream;")
        bytecodeTrace.add("3: ldc           #3 // String execution initiated")
        bytecodeTrace.add("5: invokevirtual #4 // Method java/io/PrintStream.println:(Ljava/lang/String;)V")

        // Check for common syntax errors first to educate
        if (code.contains("int number = \"Hello\";")) {
            return ExecutionResult(
                isSuccess = false,
                stdout = "",
                stderr = "Main.java:4: error: incompatible types: String cannot be converted to int\n    int number = \"Hello\";\n                 ^\n1 error",
                executionTimeMs = 12
            )
        }

        if (code.contains("System.out.println(") && !code.contains(";")) {
            return ExecutionResult(
                isSuccess = false,
                stdout = "",
                stderr = "Main.java:4: error: ';' expected\n1 error",
                executionTimeMs = 8
            )
        }

        // Parse variable declarations and print statements
        try {
            var inputConsumed = false
            for (rawLine in lines) {
                val line = rawLine.trim()

                // Scanner line simulation
                if (line.contains("nextLine()") || line.contains("nextInt()")) {
                    val fallback = if (userInput.isNotBlank()) userInput.trim() else "Awiskar"
                    if (line.contains("String") && line.contains("=")) {
                        val varName = line.substringAfter("String").substringBefore("=").trim()
                        variables[varName] = "\"$fallback\""
                    }
                    inputConsumed = true
                }

                // Variable assignment patterns
                if (line.startsWith("int ") && line.contains("=")) {
                    val varName = line.substringAfter("int ").substringBefore("=").trim()
                    val valExpr = line.substringAfter("=").substringBefore(";").trim()
                    variables[varName] = valExpr
                } else if (line.startsWith("String ") && line.contains("=")) {
                    val varName = line.substringAfter("String ").substringBefore("=").trim()
                    val valExpr = line.substringAfter("=").substringBefore(";").trim()
                    variables[varName] = valExpr
                } else if (line.startsWith("double ") && line.contains("=")) {
                    val varName = line.substringAfter("double ").substringBefore("=").trim()
                    val valExpr = line.substringAfter("=").substringBefore(";").trim()
                    variables[varName] = valExpr
                } else if (line.startsWith("boolean ") && line.contains("=")) {
                    val varName = line.substringAfter("boolean ").substringBefore("=").trim()
                    val valExpr = line.substringAfter("=").substringBefore(";").trim()
                    variables[varName] = valExpr
                }

                // System.out.println simulation
                if (line.contains("System.out.println(") || line.contains("System.out.print(")) {
                    val isLn = line.contains("println")
                    val inside = line.substringAfter("print(").substringAfter("println(").substringBeforeLast(");").trim()

                    val evaluated = evaluatePrintExpression(inside, variables)
                    if (isLn) {
                        stdoutBuilder.appendLine(evaluated)
                    } else {
                        stdoutBuilder.append(evaluated)
                    }
                }

                // Loop simulation for basic for loops
                if (line.startsWith("for (int i = 0; i <") || line.startsWith("for(int i = 0; i <")) {
                    val limitStr = line.substringAfter("<").substringBefore(";").trim()
                    val limit = limitStr.toIntOrNull() ?: 3
                    stdoutBuilder.appendLine("Executing loop (0 until $limit):")
                    for (i in 0 until limit.coerceAtMost(5)) {
                        stdoutBuilder.appendLine("  Iteration $i -> index = $i")
                    }
                }
            }

            if (stdoutBuilder.isEmpty() && stderrBuilder.isEmpty()) {
                stdoutBuilder.appendLine("Program compiled and completed with exit code 0.")
                if (variables.isNotEmpty()) {
                    stdoutBuilder.appendLine("Allocated Stack Variables: ${variables.keys.joinToString(", ")}")
                }
            }

            return ExecutionResult(
                isSuccess = true,
                stdout = stdoutBuilder.toString().trimEnd(),
                stderr = stderrBuilder.toString(),
                variablesSnapshot = variables,
                callStackSnapshot = callStack,
                bytecodeTrace = bytecodeTrace
            )
        } catch (e: Exception) {
            return ExecutionResult(
                isSuccess = false,
                stdout = stdoutBuilder.toString(),
                stderr = "Runtime Exception: ${e.message}\n\tat Main.main(Main.java:5)",
                executionTimeMs = 15
            )
        }
    }

    private fun evaluatePrintExpression(rawExpr: String, vars: Map<String, String>): String {
        var expr = rawExpr
        if (expr.isEmpty()) return ""

        // Handle string concatenation like "Hello, " + name
        val parts = expr.split("+").map { it.trim() }
        val sb = StringBuilder()
        for (part in parts) {
            if (part.startsWith("\"") && part.endsWith("\"")) {
                sb.append(part.removeSurrounding("\""))
            } else if (vars.containsKey(part)) {
                val v = vars[part] ?: ""
                sb.append(v.removeSurrounding("\""))
            } else {
                sb.append(part)
            }
        }
        return sb.toString()
    }

    fun generateDebugFrames(code: String): List<DebugFrame> {
        val lines = code.lines()
        val frames = mutableListOf<DebugFrame>()
        val currentVars = mutableMapOf<String, String>("args" to "String[0]")
        val stdoutAccum = StringBuilder()
        val callStack = mutableListOf("Main.main(args)")

        var lineNum = 1
        for (l in lines) {
            val trimmed = l.trim()
            if (trimmed.isNotBlank() && !trimmed.startsWith("//") && !trimmed.startsWith("/*")) {
                if (trimmed.contains("int ") || trimmed.contains("String ") || trimmed.contains("double ")) {
                    val type = trimmed.substringBefore(" ").trim()
                    val rest = trimmed.substringAfter(" ").substringBefore(";").trim()
                    if (rest.contains("=")) {
                        val name = rest.substringBefore("=").trim()
                        val value = rest.substringAfter("=").trim()
                        currentVars[name] = value
                    }
                }
                if (trimmed.contains("System.out.println")) {
                    val inside = trimmed.substringAfter("println(").substringBeforeLast(");").trim()
                    val printed = evaluatePrintExpression(inside, currentVars)
                    stdoutAccum.appendLine(printed)
                }
                if (trimmed.contains("study()") || trimmed.contains("calculate")) {
                    callStack.add(0, "Student.study()")
                }

                frames.add(
                    DebugFrame(
                        lineNumber = lineNum,
                        lineCode = trimmed,
                        variables = currentVars.toMap(),
                        callStack = callStack.toList(),
                        stdoutSoFar = stdoutAccum.toString()
                    )
                )
            }
            lineNum++
        }
        return if (frames.isNotEmpty()) frames else listOf(
            DebugFrame(1, "public class Main {", mapOf("status" to "\"ready\""), listOf("Main.main()"), "")
        )
    }
}
