package com.example.data

import com.example.model.CurriculumLevel
import com.example.model.CurriculumModule
import com.example.model.DiagramNode
import com.example.model.Lesson

object JavaCurriculumData {

    val levels: List<CurriculumLevel> = listOf(
        CurriculumLevel(
            id = "level_1",
            levelNumber = 1,
            title = "Programming Fundamentals",
            subtitle = "What is Java, JVM architecture, and your first program",
            iconKey = "code",
            modules = listOf(
                CurriculumModule(
                    id = "mod_1_1",
                    levelId = "level_1",
                    title = "The Java Architecture & First Program",
                    description = "Source code, bytecode, JVM, JRE, JDK, and write once run anywhere",
                    lessons = listOf(
                        Lesson(
                            id = "l1_1",
                            moduleId = "mod_1_1",
                            levelId = "level_1",
                            title = "How Java Works: Source to Execution",
                            estimatedMinutes = 8,
                            conceptSummary = "Java is a high-level, class-based, object-oriented language. Unlike pure compiled languages like C++, Java compiles into platform-neutral Bytecode (.class) which runs on any Java Virtual Machine (JVM).",
                            practicalWhy = "'Write Once, Run Anywhere' (WORA). Your same Java application compiles once and executes seamlessly on Linux cloud servers, Windows workstations, macOS, and Android devices.",
                            syntaxSnippet = "public class Main {\n    public static void main(String[] args) {\n        // Your instructions here\n    }\n}",
                            exampleCode = "public class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, Java!\");\n        System.out.println(\"Created by Awiskar Acharya\");\n    }\n}",
                            diagramTitle = "Java Execution Pipeline",
                            diagramDescription = "From readable source code to hardware machine instructions via the JVM",
                            diagramNodes = listOf(
                                DiagramNode("d1", "Main.java", "Human Readable Source", "source"),
                                DiagramNode("d2", "javac Compiler", "Syntax check & transforms", "compiler"),
                                DiagramNode("d3", "Main.class", "Platform Neutral Bytecode", "bytecode"),
                                DiagramNode("d4", "Java Virtual Machine (JVM)", "Class Loader & JIT Execution", "jvm"),
                                DiagramNode("d5", "Console Output", "Hello, Java!", "output")
                            ),
                            interactiveCode = "public class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Learn Java by Awiskar Acharya\");\n        System.out.println(\"Status: 100% Free Programming Platform\");\n    }\n}",
                            expectedOutput = "Learn Java by Awiskar Acharya\nStatus: 100% Free Programming Platform",
                            commonMistakes = listOf(
                                "Mismatch between class name and file name (Main class must be in Main.java).",
                                "Missing semicolon (;) at the end of statements.",
                                "Case sensitivity: 'system.out.println' will fail because Java is strictly case-sensitive ('System')."
                            ),
                            practiceTask = "Print your name and your programming goal on two separate lines.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        // Write your code here\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Name: Awiskar\");\n        System.out.println(\"Goal: Master Enterprise Java Backend\");\n    }\n}",
                            realWorldApplication = "Used by enterprise systems at Netflix, Amazon, Google, LinkedIn, and banking infrastructures worldwide processing billions of transactions per second."
                        ),
                        Lesson(
                            id = "l1_2",
                            moduleId = "mod_1_1",
                            levelId = "level_1",
                            title = "Structure of a Java Program",
                            estimatedMinutes = 10,
                            conceptSummary = "Every Java instruction resides inside a class. The main method 'public static void main(String[] args)' is the official entry point where execution begins.",
                            practicalWhy = "Understanding 'public' (accessible anywhere), 'static' (callable without creating an object), 'void' (returns no value), and 'String[] args' (accepts command-line arguments) is the cornerstone of all Java apps.",
                            syntaxSnippet = "accessModifier class ClassName {\n    accessModifier static returnType methodName(params) {\n        // block\n    }\n}",
                            exampleCode = "public class ProgramStructure {\n    public static void main(String[] args) {\n        System.out.println(\"Entry point invoked successfully.\");\n    }\n}",
                            diagramTitle = "Class and Method Hierarchy",
                            diagramDescription = "Package -> Class -> Member Methods -> Statements",
                            diagramNodes = listOf(
                                DiagramNode("p1", "Package", "Organization scope", "step"),
                                DiagramNode("p2", "Class Declaration", "Blueprint wrapper", "step"),
                                DiagramNode("p3", "main() Method", "Execution starting point", "step"),
                                DiagramNode("p4", "Statements ;", "Executable instructions", "step")
                            ),
                            interactiveCode = "public class ProgramStructure {\n    public static void main(String[] args) {\n        System.out.println(\"Understanding main() is essential for every Java engineer.\");\n    }\n}",
                            expectedOutput = "Understanding main() is essential for every Java engineer.",
                            commonMistakes = listOf(
                                "Omitting 'static' in main causes runtime error: Main method not found in class.",
                                "Misspelling 'String' as 'string' (String is a class in java.lang)."
                            ),
                            practiceTask = "Add another System.out.println statement with a welcoming message.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Ready to master Java\");\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Ready to master Java\");\n        System.out.println(\"Welcome to the Java Academy\");\n    }\n}",
                            realWorldApplication = "Foundation for every standalone Java microservice, Spring Boot start runner, and Android app initialization."
                        )
                    )
                )
            )
        ),
        CurriculumLevel(
            id = "level_2",
            levelNumber = 2,
            title = "Java Basics & Data Types",
            subtitle = "Variables, 8 primitives, reference types, operators, and casting",
            iconKey = "data",
            modules = listOf(
                CurriculumModule(
                    id = "mod_2_1",
                    levelId = "level_2",
                    title = "Variables & Memory Storage",
                    description = "Declarations, identifiers, variable scopes, and primitive types",
                    lessons = listOf(
                        Lesson(
                            id = "l2_1",
                            moduleId = "mod_2_1",
                            levelId = "level_2",
                            title = "Variables & Identifiers",
                            estimatedMinutes = 12,
                            conceptSummary = "A variable is a named storage container in memory holding a value. In Java, all variables are strongly typed: you must declare what type of data it holds before storing it.",
                            practicalWhy = "Strong typing prevents subtle runtime bugs. The compiler verifies at build time that you cannot accidentally assign text into a numerical calculation.",
                            syntaxSnippet = "dataType variableName = initialValue;\nfinal dataType CONSTANT_NAME = fixedValue;",
                            exampleCode = "public class VariablesDemo {\n    public static void main(String[] args) {\n        int age = 22;\n        String name = \"Awiskar\";\n        double rating = 4.95;\n        boolean isFree = true;\n        \n        System.out.println(name + \" is \" + age + \" years old.\");\n        System.out.println(\"Platform Rating: \" + rating + \" | 100% Free: \" + isFree);\n    }\n}",
                            diagramTitle = "Memory Stack Allocation",
                            diagramDescription = "How primitive variables are stored on the JVM Thread Stack",
                            diagramNodes = listOf(
                                DiagramNode("v1", "age [int]", "Stored: 22 (32-bit)", "stack"),
                                DiagramNode("v2", "rating [double]", "Stored: 4.95 (64-bit)", "stack"),
                                DiagramNode("v3", "isFree [boolean]", "Stored: true (1-bit logical)", "stack"),
                                DiagramNode("v4", "name [String ref]", "Pointer to Heap \"Awiskar\"", "heap")
                            ),
                            interactiveCode = "public class VariablesLab {\n    public static void main(String[] args) {\n        int age = 22;\n        String name = \"Awiskar\";\n        System.out.println(\"Learner: \" + name);\n        System.out.println(\"Age: \" + age);\n    }\n}",
                            expectedOutput = "Learner: Awiskar\nAge: 22",
                            commonMistakes = listOf(
                                "Using a variable before declaring it.",
                                "Trying to reassign a 'final' constant.",
                                "Invalid identifier names starting with numbers (e.g. '1stValue' is invalid)."
                            ),
                            practiceTask = "Declare an int variable 'score' set to 100, and a double 'gpa' set to 3.9, then print them.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        // declare score and gpa\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        int score = 100;\n        double gpa = 3.9;\n        System.out.println(\"Score: \" + score + \", GPA: \" + gpa);\n    }\n}",
                            realWorldApplication = "Holding session tokens, user profiles, financial balances, and database record IDs in backend microservices."
                        ),
                        Lesson(
                            id = "l2_2",
                            moduleId = "mod_2_1",
                            levelId = "level_2",
                            title = "Primitive vs Reference Types & Casting",
                            estimatedMinutes = 14,
                            conceptSummary = "Java has 8 primitive types: byte, short, int, long, float, double, char, boolean. All other types (String, Arrays, Classes) are reference types that live on the Heap.",
                            practicalWhy = "Knowing the exact size and precision of types prevents integer overflow and memory bloat when scaling backend systems to millions of users.",
                            syntaxSnippet = "// Widening (implicit)\ndouble d = 10; \n// Narrowing (explicit casting)\nint i = (int) 99.75;",
                            exampleCode = "public class CastingDemo {\n    public static void main(String[] args) {\n        double price = 99.75;\n        int rounded = (int) price;\n        System.out.println(\"Original price: \" + price);\n        System.out.println(\"Truncated int: \" + rounded);\n    }\n}",
                            diagramTitle = "Type Conversion Flow",
                            diagramDescription = "Automatic widening vs Explicit casting with truncation",
                            diagramNodes = listOf(
                                DiagramNode("c1", "byte (8-bit)", "Automatic Widening", "step"),
                                DiagramNode("c2", "short/char (16-bit)", "Automatic Widening", "step"),
                                DiagramNode("c3", "int (32-bit)", "Standard Integer", "step"),
                                DiagramNode("c4", "long (64-bit)", "High capacity integer", "step"),
                                DiagramNode("c5", "double (64-bit)", "High precision decimal", "step")
                            ),
                            interactiveCode = "public class CastingDemo {\n    public static void main(String[] args) {\n        double price = 99.5;\n        int value = (int) price;\n        System.out.println(\"Converted value: \" + value);\n    }\n}",
                            expectedOutput = "Converted value: 99",
                            commonMistakes = listOf(
                                "Assuming (int) 9.9 rounds to 10; casting truncates the decimal part to 9.",
                                "Integer division: 5 / 2 yields 2, not 2.5! (Use 5.0 / 2)."
                            ),
                            practiceTask = "Convert double tax = 12.85 to an integer and print the difference.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        double tax = 12.85;\n        // cast and print\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        double tax = 12.85;\n        int intTax = (int) tax;\n        System.out.println(\"Truncated tax: \" + intTax);\n    }\n}",
                            realWorldApplication = "Currency calculations in banking software, physics coordinate calculations in games, and timestamp math in distributed systems."
                        )
                    )
                )
            )
        ),
        CurriculumLevel(
            id = "level_3",
            levelNumber = 3,
            title = "Control Flow & Loops",
            subtitle = "if-else, modern switch, for, while, do-while, break & continue",
            iconKey = "control",
            modules = listOf(
                CurriculumModule(
                    id = "mod_3_1",
                    levelId = "level_3",
                    title = "Conditional Branching & Decision Making",
                    description = "Master boolean logic, nested branching, and switch expressions",
                    lessons = listOf(
                        Lesson(
                            id = "l3_1",
                            moduleId = "mod_3_1",
                            levelId = "level_3",
                            title = "if-else Statements & Boolean Logic",
                            estimatedMinutes = 12,
                            conceptSummary = "Conditionals allow your software to make dynamic decisions based on data. The if-else construct executes code blocks when boolean expressions evaluate to true.",
                            practicalWhy = "Authentication gates, validation checks, discount calculators, and game physics all rely on conditional decision branching.",
                            syntaxSnippet = "if (condition) {\n    // executes when true\n} else if (anotherCondition) {\n    // alternate path\n} else {\n    // fallback\n}",
                            exampleCode = "public class ConditionDemo {\n    public static void main(String[] args) {\n        int score = 85;\n        if (score >= 90) {\n            System.out.println(\"Grade: A+\");\n        } else if (score >= 75) {\n            System.out.println(\"Grade: B+\");\n        } else {\n            System.out.println(\"Grade: Need Practice\");\n        }\n    }\n}",
                            diagramTitle = "Condition Branching Flow",
                            diagramDescription = "Evaluation path through boolean condition gates",
                            diagramNodes = listOf(
                                DiagramNode("cb1", "Evaluate condition", "score >= 90", "step"),
                                DiagramNode("cb2", "False branch", "Check score >= 75", "step"),
                                DiagramNode("cb3", "True branch", "Execute Block B: Grade B+", "output")
                            ),
                            interactiveCode = "public class ConditionVisualizer {\n    public static void main(String[] args) {\n        int age = 20;\n        if (age >= 18) {\n            System.out.println(\"Eligible to vote and drive.\");\n        } else {\n            System.out.println(\"Minor status.\");\n        }\n    }\n}",
                            expectedOutput = "Eligible to vote and drive.",
                            commonMistakes = listOf(
                                "Using single '=' (assignment) instead of '==' (comparison).",
                                "Forgetting that String comparisons should use .equals() rather than '=='."
                            ),
                            practiceTask = "Write a condition checking if a number is positive, negative, or zero.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        int number = -7;\n        // check sign\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        int number = -7;\n        if (number > 0) System.out.println(\"Positive\");\n        else if (number < 0) System.out.println(\"Negative\");\n        else System.out.println(\"Zero\");\n    }\n}",
                            realWorldApplication = "User role authorization checks, payment status transitions, and fraud detection algorithms."
                        ),
                        Lesson(
                            id = "l3_2",
                            moduleId = "mod_3_1",
                            levelId = "level_3",
                            title = "Loops: for, while & Enhanced for-each",
                            estimatedMinutes = 14,
                            conceptSummary = "Loops automate repetitive tasks. The 'for' loop is ideal when count is known; 'while' is ideal when waiting for a condition; 'for-each' iterates cleanly over arrays and collections.",
                            practicalWhy = "Processing data records, rendering list items, computing aggregations, and running game loops.",
                            syntaxSnippet = "for (int i = 0; i < count; i++) { ... }\nfor (Type item : collection) { ... }\nwhile (condition) { ... }",
                            exampleCode = "public class LoopDemo {\n    public static void main(String[] args) {\n        for (int i = 1; i <= 3; i++) {\n            System.out.println(\"Iteration \" + i);\n        }\n    }\n}",
                            diagramTitle = "Loop Cycle Visualizer",
                            diagramDescription = "Initialization -> Condition check -> Body execution -> Increment",
                            diagramNodes = listOf(
                                DiagramNode("lp1", "Init: i = 1", "Stack variable created", "step"),
                                DiagramNode("lp2", "Condition: i <= 3", "Evaluates to true", "step"),
                                DiagramNode("lp3", "Execute Body", "Print Iteration", "output"),
                                DiagramNode("lp4", "Increment: i++", "i becomes 2, repeats", "step")
                            ),
                            interactiveCode = "public class LoopVisualizer {\n    public static void main(String[] args) {\n        for (int i = 1; i <= 4; i++) {\n            System.out.println(\"Step \" + i + \": Processing Java node\");\n        }\n    }\n}",
                            expectedOutput = "Step 1: Processing Java node\nStep 2: Processing Java node\nStep 3: Processing Java node\nStep 4: Processing Java node",
                            commonMistakes = listOf(
                                "Infinite while loops when forgotten to update loop variable.",
                                "Off-by-one errors (using '<=' instead of '<' leading to ArrayIndexOutOfBounds)."
                            ),
                            practiceTask = "Write a loop calculating the sum of numbers from 1 to 5.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        int sum = 0;\n        // write loop\n        System.out.println(\"Sum: \" + sum);\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        int sum = 0;\n        for (int i = 1; i <= 5; i++) sum += i;\n        System.out.println(\"Sum: \" + sum);\n    }\n}",
                            realWorldApplication = "Batch file processing, database pagination iterations, and real-time sensor polling."
                        )
                    )
                )
            )
        ),
        CurriculumLevel(
            id = "level_4",
            levelNumber = 4,
            title = "Arrays & Strings",
            subtitle = "Fixed-size arrays, String immutability, StringBuilder, and methods",
            iconKey = "array",
            modules = listOf(
                CurriculumModule(
                    id = "mod_4_1",
                    levelId = "level_4",
                    title = "Data Containers & Text Processing",
                    description = "Array memory indexing and efficient text manipulation",
                    lessons = listOf(
                        Lesson(
                            id = "l4_1",
                            moduleId = "mod_4_1",
                            levelId = "level_4",
                            title = "Arrays: Allocation, Indexing & Traversal",
                            estimatedMinutes = 15,
                            conceptSummary = "An array is a fixed-length container storing homogeneous elements in contiguous heap memory. Elements are accessed in O(1) time using a 0-based index.",
                            practicalWhy = "Arrays offer unmatched memory cache locality and instant random access for numerical computations and algorithm foundations.",
                            syntaxSnippet = "int[] numbers = new int[5];\nint[] values = { 10, 20, 30, 40 };\nint length = values.length;",
                            exampleCode = "public class ArrayDemo {\n    public static void main(String[] args) {\n        int[] scores = { 88, 92, 95, 100 };\n        for (int i = 0; i < scores.length; i++) {\n            System.out.println(\"Index \" + i + \": \" + scores[i]);\n        }\n    }\n}",
                            diagramTitle = "Contiguous Memory Layout",
                            diagramDescription = "Array allocated as consecutive heap blocks with 0-based indices",
                            diagramNodes = listOf(
                                DiagramNode("arr0", "Index 0", "Value: 88 (Base offset + 0)", "heap"),
                                DiagramNode("arr1", "Index 1", "Value: 92 (Base offset + 4)", "heap"),
                                DiagramNode("arr2", "Index 2", "Value: 95 (Base offset + 8)", "heap"),
                                DiagramNode("arr3", "Index 3", "Value: 100 (Base offset + 12)", "heap")
                            ),
                            interactiveCode = "public class ArrayLab {\n    public static void main(String[] args) {\n        String[] languages = {\"Java\", \"Kotlin\", \"SQL\"};\n        for (String lang : languages) {\n            System.out.println(\"Active skill: \" + lang);\n        }\n    }\n}",
                            expectedOutput = "Active skill: Java\nActive skill: Kotlin\nActive skill: SQL",
                            commonMistakes = listOf(
                                "Accessing index equal to array.length causes ArrayIndexOutOfBoundsException.",
                                "Attempting to resize a Java array (arrays cannot change length once instantiated; use ArrayList instead)."
                            ),
                            practiceTask = "Find the maximum number in an array { 14, 82, 33, 99, 45 }.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        int[] nums = { 14, 82, 33, 99, 45 };\n        int max = nums[0];\n        // loop to find max\n        System.out.println(\"Max: \" + max);\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        int[] nums = { 14, 82, 33, 99, 45 };\n        int max = nums[0];\n        for (int n : nums) if (n > max) max = n;\n        System.out.println(\"Max: \" + max);\n    }\n}",
                            realWorldApplication = "Buffer storage in I/O streams, pixel buffers in graphics rendering, and lookup tables."
                        ),
                        Lesson(
                            id = "l4_2",
                            moduleId = "mod_4_1",
                            levelId = "level_4",
                            title = "Strings & String Pool Immutability",
                            estimatedMinutes = 14,
                            conceptSummary = "Strings in Java are immutable objects. When you create string literals, Java stores them in the String Constant Pool inside heap memory to save memory and ensure thread safety.",
                            practicalWhy = "Immutability makes Strings secure for network sockets, database URLs, and hash keys in HashMaps without race conditions.",
                            syntaxSnippet = "String s1 = \"Hello\"; // String pool\nString s2 = new String(\"Hello\"); // Explicit Heap object\nboolean equals = s1.equals(s2); // Value equality",
                            exampleCode = "public class StringDemo {\n    public static void main(String[] args) {\n        String course = \"Learn Java\";\n        String upper = course.toUpperCase();\n        System.out.println(\"Original: \" + course);\n        System.out.println(\"Upper: \" + upper);\n    }\n}",
                            diagramTitle = "String Pool vs Heap Object",
                            diagramDescription = "String literal deduplication in the JVM String Constant Pool",
                            diagramNodes = listOf(
                                DiagramNode("sp1", "\"Java\" in Pool", "Single shared reference", "heap"),
                                DiagramNode("sp2", "s1 pointer", "Points to Pool instance", "stack"),
                                DiagramNode("sp3", "s2 pointer", "Points to separate Heap object", "stack")
                            ),
                            interactiveCode = "public class StringExplorer {\n    public static void main(String[] args) {\n        String author = \"Awiskar Acharya\";\n        System.out.println(\"Length: \" + author.length());\n        System.out.println(\"First name: \" + author.substring(0, 7));\n    }\n}",
                            expectedOutput = "Length: 15\nFirst name: Awiskar",
                            commonMistakes = listOf(
                                "Using '==' instead of '.equals()' to compare string contents.",
                                "Concatenating strings inside a heavy loop with '+' instead of StringBuilder."
                            ),
                            practiceTask = "Check if String email contains '@' and ends with '.com'.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        String email = \"awiskar@example.com\";\n        // check validity\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        String email = \"awiskar@example.com\";\n        boolean valid = email.contains(\"@\") && email.endsWith(\".com\");\n        System.out.println(\"Valid email: \" + valid);\n    }\n}",
                            realWorldApplication = "Parsing JSON payloads, security hashing passwords, and building dynamic SQL queries."
                        )
                    )
                )
            )
        ),
        CurriculumLevel(
            id = "level_5",
            levelNumber = 5,
            title = "Object-Oriented Programming (OOP)",
            subtitle = "Classes, objects, constructors, encapsulation, inheritance, polymorphism, and abstraction",
            iconKey = "oop",
            modules = listOf(
                CurriculumModule(
                    id = "mod_5_1",
                    levelId = "level_5",
                    title = "Core OOP Pillars",
                    description = "Model real-world systems with high modularity and clean abstractions",
                    lessons = listOf(
                        Lesson(
                            id = "l5_1",
                            moduleId = "mod_5_1",
                            levelId = "level_5",
                            title = "Classes, Objects & Constructors",
                            estimatedMinutes = 18,
                            conceptSummary = "A Class is a blueprint defining state (fields) and behavior (methods). An Object is a living runtime instance created with the 'new' keyword. Constructors initialize that state.",
                            practicalWhy = "OOP allows modular software architecture. Real systems model Users, Accounts, Transactions, Orders, and Products as distinct encapsulated classes.",
                            syntaxSnippet = "class Student {\n    private String name;\n    public Student(String name) { this.name = name; }\n    public void study() { ... }\n}",
                            exampleCode = "class Student {\n    String name;\n    int age;\n    \n    public Student(String name, int age) {\n        this.name = name;\n        this.age = age;\n    }\n    \n    public void introduce() {\n        System.out.println(\"Student: \" + name + \", Age: \" + age);\n    }\n}\n\npublic class OOPDemo {\n    public static void main(String[] args) {\n        Student s1 = new Student(\"Awiskar\", 22);\n        s1.introduce();\n    }\n}",
                            diagramTitle = "Class Blueprint to Heap Instance",
                            diagramDescription = "How class definition instantiates distinct objects in JVM Heap",
                            diagramNodes = listOf(
                                DiagramNode("bp1", "Student Class", "Blueprint definition", "compiler"),
                                DiagramNode("bp2", "new Student()", "Constructor allocation", "jvm"),
                                DiagramNode("bp3", "Instance s1", "name=\"Awiskar\", age=22", "heap"),
                                DiagramNode("bp4", "s1 reference", "Stack pointer to Heap", "stack")
                            ),
                            interactiveCode = "class Learner {\n    String name;\n    String level;\n    Learner(String name, String level) {\n        this.name = name;\n        this.level = level;\n    }\n    void display() {\n        System.out.println(name + \" is at \" + level + \" level.\");\n    }\n}\npublic class Main {\n    public static void main(String[] args) {\n        Learner l1 = new Learner(\"Awiskar\", \"Advanced\");\n        l1.display();\n    }\n}",
                            expectedOutput = "Awiskar is at Advanced level.",
                            commonMistakes = listOf(
                                "Forgetting 'this' when constructor parameter has identical name to instance field.",
                                "Calling methods on a null reference resulting in NullPointerException."
                            ),
                            practiceTask = "Create a 'Book' class with title and price, and print its details.",
                            practiceStarterCode = "class Book {\n    // fields & constructor\n}\npublic class Main {\n    public static void main(String[] args) {\n        // instantiate and print\n    }\n}",
                            practiceSolutionCode = "class Book {\n    String title;\n    double price;\n    Book(String t, double p) { title = t; price = p; }\n    void print() { System.out.println(title + \" costs $\" + price); }\n}\npublic class Main {\n    public static void main(String[] args) {\n        Book b = new Book(\"Java Mastery\", 0.0);\n        b.print();\n    }\n}",
                            realWorldApplication = "Core of all Java backend systems: Entities, DTOs, Repositories, Services, and Controller components."
                        ),
                        Lesson(
                            id = "l5_2",
                            moduleId = "mod_5_1",
                            levelId = "level_5",
                            title = "Encapsulation, Inheritance & Polymorphism",
                            estimatedMinutes = 20,
                            conceptSummary = "Encapsulation hides internal data using private modifiers and accessors. Inheritance (extends) enables code reuse. Polymorphism enables dynamic method dispatch at runtime.",
                            practicalWhy = "Writing resilient code where you can add new payment methods or notification channels without breaking existing services.",
                            syntaxSnippet = "class Payment { void pay() { ... } }\nclass CardPayment extends Payment { @Override void pay() { ... } }",
                            exampleCode = "abstract class Payment {\n    abstract void process(double amount);\n}\nclass CardPayment extends Payment {\n    void process(double amount) {\n        System.out.println(\"Processed card payment: $\" + amount);\n    }\n}\npublic class PolymorphismDemo {\n    public static void main(String[] args) {\n        Payment p = new CardPayment();\n        p.process(150.0);\n    }\n}",
                            diagramTitle = "Inheritance & Polymorphism Tree",
                            diagramDescription = "Parent abstraction with specialized polymorphic subclasses",
                            diagramNodes = listOf(
                                DiagramNode("inh1", "Payment (Base)", "Contract: process()", "step"),
                                DiagramNode("inh2", "CardPayment", "Specialized implementation", "step"),
                                DiagramNode("inh3", "WalletPayment", "Specialized implementation", "step"),
                                DiagramNode("inh4", "Dynamic Dispatch", "JVM selects concrete method", "jvm")
                            ),
                            interactiveCode = "class Animal {\n    void speak() { System.out.println(\"Animal sound\"); }\n}\nclass Dog extends Animal {\n    @Override void speak() { System.out.println(\"Dog barks: Woof!\"); }\n}\npublic class Main {\n    public static void main(String[] args) {\n        Animal myPet = new Dog();\n        myPet.speak();\n    }\n}",
                            expectedOutput = "Dog barks: Woof!",
                            commonMistakes = listOf(
                                "Trying to instantiate an abstract class or interface directly.",
                                "Forgetting @Override annotation which protects against method signature mismatches."
                            ),
                            practiceTask = "Create a Shape class with area() method and a Rectangle subclass.",
                            practiceStarterCode = "class Shape { double area() { return 0.0; } }\n// implement Rectangle\npublic class Main { public static void main(String[] args) { } }",
                            practiceSolutionCode = "class Shape { double area() { return 0.0; } }\nclass Rectangle extends Shape {\n    double w, h;\n    Rectangle(double w, double h) { this.w = w; this.h = h; }\n    @Override double area() { return w * h; }\n}\npublic class Main {\n    public static void main(String[] args) {\n        Shape s = new Rectangle(5, 4);\n        System.out.println(\"Area: \" + s.area());\n    }\n}",
                            realWorldApplication = "Spring framework Dependency Injection, Android View hierarchies, and database driver abstraction layers."
                        )
                    )
                )
            )
        ),
        CurriculumLevel(
            id = "level_6",
            levelNumber = 6,
            title = "Collections Framework & Generics",
            subtitle = "List, Set, Map, Queue, ArrayList, HashMap, and type safety",
            iconKey = "collections",
            modules = listOf(
                CurriculumModule(
                    id = "mod_6_1",
                    levelId = "level_6",
                    title = "The Java Collections Academy",
                    description = "Dynamic data structures, hashing, key-value storage, and generics",
                    lessons = listOf(
                        Lesson(
                            id = "l6_1",
                            moduleId = "mod_6_1",
                            levelId = "level_6",
                            title = "ArrayList vs LinkedList & Generics",
                            estimatedMinutes = 16,
                            conceptSummary = "ArrayList is backed by a resizable dynamic array with O(1) random access. Generics <T> guarantee compile-time type safety so collections only store intended types.",
                            practicalWhy = "90% of business applications pass collections of objects between database, backend logic, and frontend APIs.",
                            syntaxSnippet = "List<String> list = new ArrayList<>();\nlist.add(\"Java\");\nString item = list.get(0);",
                            exampleCode = "import java.util.*;\n\npublic class CollectionDemo {\n    public static void main(String[] args) {\n        List<String> topics = new ArrayList<>();\n        topics.add(\"Fundamentals\");\n        topics.add(\"OOP\");\n        topics.add(\"Spring\");\n        \n        System.out.println(\"Total modules: \" + topics.size());\n        for (String t : topics) {\n            System.out.println(\"- \" + t);\n        }\n    }\n}",
                            diagramTitle = "ArrayList Internal Resizing",
                            diagramDescription = "Initial capacity -> dynamic 1.5x growth reallocation when threshold reached",
                            diagramNodes = listOf(
                                DiagramNode("al1", "Capacity: 10", "Default internal Object[]", "heap"),
                                DiagramNode("al2", "11th element added", "Triggers growth check", "step"),
                                DiagramNode("al3", "New Array: 15", "Arrays.copyOf() reallocates", "heap"),
                                DiagramNode("al4", "O(1) Access", "Fast direct indexing", "output")
                            ),
                            interactiveCode = "import java.util.ArrayList;\npublic class ListLab {\n    public static void main(String[] args) {\n        ArrayList<String> students = new ArrayList<>();\n        students.add(\"Awiskar\");\n        students.add(\"Alex\");\n        System.out.println(\"First student: \" + students.get(0));\n    }\n}",
                            expectedOutput = "First student: Awiskar",
                            commonMistakes = listOf(
                                "Using primitive types in generics (e.g. List<int> is invalid, use List<Integer>).",
                                "Modifying a collection while iterating with for-each causes ConcurrentModificationException."
                            ),
                            practiceTask = "Create a list of 3 cities and print the list in reverse order.",
                            practiceStarterCode = "import java.util.*;\npublic class Main {\n    public static void main(String[] args) {\n        // create list & reverse\n    }\n}",
                            practiceSolutionCode = "import java.util.*;\npublic class Main {\n    public static void main(String[] args) {\n        List<String> cities = Arrays.asList(\"Kathmandu\", \"Tokyo\", \"New York\");\n        for (int i = cities.size() - 1; i >= 0; i--) {\n            System.out.println(cities.get(i));\n        }\n    }\n}",
                            realWorldApplication = "Returning lists of query results from Spring Data JPA repositories to REST controllers."
                        ),
                        Lesson(
                            id = "l6_2",
                            moduleId = "mod_6_1",
                            levelId = "level_6",
                            title = "HashMap: Buckets, Keys & Values",
                            estimatedMinutes = 18,
                            conceptSummary = "HashMap provides associative Key -> Value mapping with average O(1) time complexity for put() and get(). It uses hash codes to assign keys to internal hash table buckets.",
                            practicalWhy = "Fast caches, dictionary lookups, user session storage, and routing tables all depend on HashMaps.",
                            syntaxSnippet = "Map<String, Integer> map = new HashMap<>();\nmap.put(\"age\", 22);\nint val = map.getOrDefault(\"age\", 0);",
                            exampleCode = "import java.util.*;\n\npublic class MapDemo {\n    public static void main(String[] args) {\n        Map<String, String> user = new HashMap<>();\n        user.put(\"name\", \"Awiskar\");\n        user.put(\"role\", \"Software Engineer\");\n        user.put(\"platform\", \"Learn Java\");\n        \n        System.out.println(\"User: \" + user.get(\"name\"));\n        System.out.println(\"Role: \" + user.get(\"role\"));\n    }\n}",
                            diagramTitle = "HashMap Bucket Hashing",
                            diagramDescription = "Key.hashCode() -> modulo hash table buckets -> linked node / Red-Black tree",
                            diagramNodes = listOf(
                                DiagramNode("hm1", "Key: \"name\"", "Generates hashCode()", "step"),
                                DiagramNode("hm2", "Bucket Index", "index = hash & (n-1)", "step"),
                                DiagramNode("hm3", "Node Storage", "K: \"name\", V: \"Awiskar\"", "heap"),
                                DiagramNode("hm4", "O(1) Retrieve", "Instant lookup by key", "output")
                            ),
                            interactiveCode = "import java.util.HashMap;\npublic class MapLab {\n    public static void main(String[] args) {\n        HashMap<String, Integer> scores = new HashMap<>();\n        scores.put(\"Java\", 98);\n        scores.put(\"Algorithms\", 95);\n        System.out.println(\"Java score: \" + scores.get(\"Java\"));\n    }\n}",
                            expectedOutput = "Java score: 98",
                            commonMistakes = listOf(
                                "Using mutable objects as HashMap keys (if hash code changes, object is lost).",
                                "Forgetting to override both hashCode() and equals() when using custom classes as keys."
                            ),
                            practiceTask = "Count word occurrences in an array of words using a HashMap.",
                            practiceStarterCode = "import java.util.*;\npublic class Main {\n    public static void main(String[] args) {\n        String[] words = {\"java\", \"code\", \"java\", \"master\"};\n        // count map\n    }\n}",
                            practiceSolutionCode = "import java.util.*;\npublic class Main {\n    public static void main(String[] args) {\n        String[] words = {\"java\", \"code\", \"java\", \"master\"};\n        Map<String, Integer> counts = new HashMap<>();\n        for (String w : words) counts.put(w, counts.getOrDefault(w, 0) + 1);\n        System.out.println(\"Counts: \" + counts);\n    }\n}",
                            realWorldApplication = "In-memory caching (Redis-like patterns), fast ID lookups, and JSON object serialization."
                        )
                    )
                )
            )
        ),
        CurriculumLevel(
            id = "level_7",
            levelNumber = 7,
            title = "Exceptions & File Handling",
            subtitle = "try-catch-finally, checked vs unchecked, custom exceptions, and Files I/O",
            iconKey = "error",
            modules = listOf(
                CurriculumModule(
                    id = "mod_7_1",
                    levelId = "level_7",
                    title = "Robust Error Recovery & Storage",
                    description = "Defensive programming and reliable disk read/write operations",
                    lessons = listOf(
                        Lesson(
                            id = "l7_1",
                            moduleId = "mod_7_1",
                            levelId = "level_7",
                            title = "Exception Flow & Error Prevention",
                            estimatedMinutes = 15,
                            conceptSummary = "Exceptions represent unexpected events during runtime. Using try-catch-finally blocks, applications catch failures gracefully and prevent crashes.",
                            practicalWhy = "Ensures server applications remain alive and return clean 400/500 error messages instead of terminating completely during bad inputs.",
                            syntaxSnippet = "try {\n    // risky operation\n} catch (SpecificException e) {\n    // handle\n} finally {\n    // always executes (cleanup)\n}",
                            exampleCode = "public class ExceptionDemo {\n    public static void main(String[] args) {\n        try {\n            int result = 10 / 0;\n            System.out.println(result);\n        } catch (ArithmeticException e) {\n            System.out.println(\"Caught error: Cannot divide by zero.\");\n        } finally {\n            System.out.println(\"Cleanup executed successfully.\");\n        }\n    }\n}",
                            diagramTitle = "Exception Handling Call Stack",
                            diagramDescription = "Exception throw -> Call stack traversal -> Catch block execution",
                            diagramNodes = listOf(
                                DiagramNode("ex1", "Error Occurs", "ArithmeticException created", "step"),
                                DiagramNode("ex2", "try block", "Interrupts normal execution", "step"),
                                DiagramNode("ex3", "catch matched", "Handles error gracefully", "output"),
                                DiagramNode("ex4", "finally guaranteed", "Releases locks & closes resources", "output")
                            ),
                            interactiveCode = "public class TryCatchLab {\n    public static void main(String[] args) {\n        try {\n            int[] arr = new int[2];\n            arr[5] = 100;\n        } catch (ArrayIndexOutOfBoundsException e) {\n            System.out.println(\"Safe catch: Invalid index accessed!\");\n        }\n    }\n}",
                            expectedOutput = "Safe catch: Invalid index accessed!",
                            commonMistakes = listOf(
                                "Empty catch blocks (swallowing exceptions hides critical bugs).",
                                "Catching generic 'Throwable' or 'Error' instead of specific exceptions."
                            ),
                            practiceTask = "Write a method that parses an integer from a string and catches NumberFormatException.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        String input = \"abc\";\n        // try parse\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        String input = \"abc\";\n        try {\n            int val = Integer.parseInt(input);\n            System.out.println(val);\n        } catch (NumberFormatException e) {\n            System.out.println(\"Invalid number format: \" + input);\n        }\n    }\n}",
                            realWorldApplication = "Spring Boot @ControllerAdvice global exception handlers returning standardized REST error responses."
                        )
                    )
                )
            )
        ),
        CurriculumLevel(
            id = "level_8",
            levelNumber = 8,
            title = "Streams, Lambdas & Modern Java",
            subtitle = "Functional programming, Stream API pipelines, Records, and Optional",
            iconKey = "stream",
            modules = listOf(
                CurriculumModule(
                    id = "mod_8_1",
                    levelId = "level_8",
                    title = "Functional Java 8 to 21",
                    description = "Declarative data pipelines with filter, map, reduce, and immutable Records",
                    lessons = listOf(
                        Lesson(
                            id = "l8_1",
                            moduleId = "mod_8_1",
                            levelId = "level_8",
                            title = "Stream API: filter(), map() & collect()",
                            estimatedMinutes = 16,
                            conceptSummary = "The Stream API allows declarative data processing on sequences of elements. Streams do not store data; they transform source collections through pipeline intermediate operations to a terminal operation.",
                            practicalWhy = "Replaces 20 lines of clumsy nested loops and if-statements with 3 clean, readable, parallelizable lines.",
                            syntaxSnippet = "list.stream()\n    .filter(x -> x > 10)\n    .map(String::valueOf)\n    .collect(Collectors.toList());",
                            exampleCode = "import java.util.*;\nimport java.util.stream.*;\n\npublic class StreamDemo {\n    public static void main(String[] args) {\n        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);\n        List<Integer> evensSquared = nums.stream()\n            .filter(n -> n % 2 == 0)\n            .map(n -> n * n)\n            .collect(Collectors.toList());\n        \n        System.out.println(\"Result: \" + evensSquared);\n    }\n}",
                            diagramTitle = "Stream Pipeline Flow",
                            diagramDescription = "Source -> filter() -> map() -> collect() terminal result",
                            diagramNodes = listOf(
                                DiagramNode("st1", "Source [1..6]", "Collection stream source", "heap"),
                                DiagramNode("st2", "filter(even)", "Emits [2, 4, 6]", "step"),
                                DiagramNode("st3", "map(n*n)", "Transforms to [4, 16, 36]", "step"),
                                DiagramNode("st4", "collect(toList)", "Produces terminal List result", "output")
                            ),
                            interactiveCode = "import java.util.*;\nimport java.util.stream.Collectors;\npublic class StreamLab {\n    public static void main(String[] args) {\n        List<String> names = Arrays.asList(\"Java\", \"Spring\", \"Jvm\", \"C++\");\n        List<String> jNames = names.stream()\n            .filter(s -> s.startsWith(\"J\"))\n            .collect(Collectors.toList());\n        System.out.println(\"J Tech: \" + jNames);\n    }\n}",
                            expectedOutput = "J Tech: [Java, Jvm]",
                            commonMistakes = listOf(
                                "Reusing a Stream after a terminal operation has already executed (streams cannot be re-operated).",
                                "Forgetting a terminal operation (filter and map are lazy and will do nothing without collect or forEach)."
                            ),
                            practiceTask = "Given a list of prices, filter prices greater than 50 and calculate their sum.",
                            practiceStarterCode = "import java.util.*;\npublic class Main {\n    public static void main(String[] args) {\n        List<Integer> prices = Arrays.asList(20, 60, 80, 15);\n        // stream filter & sum\n    }\n}",
                            practiceSolutionCode = "import java.util.*;\npublic class Main {\n    public static void main(String[] args) {\n        List<Integer> prices = Arrays.asList(20, 60, 80, 15);\n        int total = prices.stream().filter(p -> p > 50).mapToInt(Integer::intValue).sum();\n        System.out.println(\"Total: \" + total);\n    }\n}",
                            realWorldApplication = "Filtering large catalog datasets, mapping entity lists to DTOs in REST APIs, and aggregating analytics metrics."
                        )
                    )
                )
            )
        ),
        CurriculumLevel(
            id = "level_9",
            levelNumber = 9,
            title = "Concurrency, Multithreading & JVM",
            subtitle = "Threads, synchronizations, thread pools, memory areas, stack, heap, and GC",
            iconKey = "jvm",
            modules = listOf(
                CurriculumModule(
                    id = "mod_9_1",
                    levelId = "level_9",
                    title = "Concurrency & JVM Deep Architecture",
                    description = "Thread life cycles, race conditions, memory model, and Garbage Collection",
                    lessons = listOf(
                        Lesson(
                            id = "l9_1",
                            moduleId = "mod_9_1",
                            levelId = "level_9",
                            title = "JVM Architecture: Stack, Heap & Garbage Collection",
                            estimatedMinutes = 20,
                            conceptSummary = "The JVM runtime data area divides into: Method Area (class metadata), Heap (all objects and arrays shared across threads), and Stack (per-thread frames for primitive locals and method calls). The Garbage Collector (GC) automatically reclaims unreferenced heap memory.",
                            practicalWhy = "Diagnosing OutOfMemoryError, tuning heap flags (-Xms, -Xmx), and building low-latency high-throughput servers.",
                            syntaxSnippet = "// Triggering GC suggestion (non-deterministic)\nSystem.gc();\n// Checking memory\nRuntime.getRuntime().freeMemory();",
                            exampleCode = "public class JVMInfoDemo {\n    public static void main(String[] args) {\n        Runtime rt = Runtime.getRuntime();\n        System.out.println(\"Available CPU Processors: \" + rt.availableProcessors());\n        System.out.println(\"Total JVM Memory: \" + (rt.totalMemory() / (1024 * 1024)) + \" MB\");\n    }\n}",
                            diagramTitle = "JVM Runtime Memory Model",
                            diagramDescription = "Thread-private stacks alongside shared Heap & Metaspace",
                            diagramNodes = listOf(
                                DiagramNode("jvm1", "Thread Stacks", "Local variables & frames", "stack"),
                                DiagramNode("jvm2", "Young Gen Heap", "Eden & Survivor spaces", "heap"),
                                DiagramNode("jvm3", "Old Gen Heap", "Tenured long-lived objects", "heap"),
                                DiagramNode("jvm4", "Garbage Collector", "Mark, Sweep & Compact", "jvm")
                            ),
                            interactiveCode = "public class JVMLab {\n    public static void main(String[] args) {\n        System.out.println(\"Java Version: \" + System.getProperty(\"java.version\"));\n        System.out.println(\"JVM Vendor: \" + System.getProperty(\"java.vm.vendor\"));\n    }\n}",
                            expectedOutput = "Java Version: 21.0.2\nJVM Vendor: OpenJDK 64-Bit Server VM",
                            commonMistakes = listOf(
                                "Retaining static references to temporary objects causing severe memory leaks.",
                                "Assuming System.gc() immediately runs garbage collection (it is only a non-binding hint)."
                            ),
                            practiceTask = "Print max memory available to the JVM in MB.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        // print max memory\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        long maxMb = Runtime.getRuntime().maxMemory() / (1024 * 1024);\n        System.out.println(\"Max Memory: \" + maxMb + \" MB\");\n    }\n}",
                            realWorldApplication = "Configuring Docker container memory limits for Kubernetes pods running Java microservices."
                        )
                    )
                )
            )
        ),
        CurriculumLevel(
            id = "level_10",
            levelNumber = 10,
            title = "DSA & Problem Solving",
            subtitle = "Big-O notation, binary search, sorting algorithms, stacks, queues, and trees",
            iconKey = "dsa",
            modules = listOf(
                CurriculumModule(
                    id = "mod_10_1",
                    levelId = "level_10",
                    title = "Algorithms & Data Structures",
                    description = "Master computational complexity and algorithmic problem-solving",
                    lessons = listOf(
                        Lesson(
                            id = "l10_1",
                            moduleId = "mod_10_1",
                            levelId = "level_10",
                            title = "Big-O Complexity & Binary Search",
                            estimatedMinutes = 18,
                            conceptSummary = "Big-O characterizes an algorithm's time or space scaling as input size n grows. Binary Search achieves O(log n) efficiency by halving the search space on sorted datasets.",
                            practicalWhy = "Searching 1,000,000 items takes 1,000,000 comparisons with Linear Search O(n), but only ~20 comparisons with Binary Search O(log n).",
                            syntaxSnippet = "int low = 0, high = arr.length - 1;\nwhile (low <= high) {\n    int mid = low + (high - low) / 2;\n    if (arr[mid] == target) return mid;\n    if (arr[mid] < target) low = mid + 1;\n    else high = mid - 1;\n}",
                            exampleCode = "public class BinarySearchDemo {\n    public static void main(String[] args) {\n        int[] sorted = { 10, 25, 38, 49, 72, 91 };\n        int target = 49;\n        int index = java.util.Arrays.binarySearch(sorted, target);\n        System.out.println(\"Target \" + target + \" found at index: \" + index);\n    }\n}",
                            diagramTitle = "Binary Search Halving",
                            diagramDescription = "Repeated division of sorted search range until target located",
                            diagramNodes = listOf(
                                DiagramNode("bs1", "[10, 25, 38, 49, 72, 91]", "Range: 0 to 5, Mid: 38", "step"),
                                DiagramNode("bs2", "49 > 38", "Discard left half", "step"),
                                DiagramNode("bs3", "[49, 72, 91]", "Range: 3 to 5, Mid: 72", "step"),
                                DiagramNode("bs4", "Match found!", "Return index 3 in 2 steps", "output")
                            ),
                            interactiveCode = "public class DSALab {\n    public static void main(String[] args) {\n        int[] nums = { 2, 4, 6, 8, 10, 12 };\n        int target = 8;\n        int low = 0, high = nums.length - 1, found = -1;\n        while (low <= high) {\n            int mid = (low + high) / 2;\n            if (nums[mid] == target) { found = mid; break; }\n            if (nums[mid] < target) low = mid + 1; else high = mid - 1;\n        }\n        System.out.println(\"Found at index: \" + found);\n    }\n}",
                            expectedOutput = "Found at index: 3",
                            commonMistakes = listOf(
                                "Running Binary Search on an unsorted array (it will return incorrect results).",
                                "Integer overflow in calculating mid: '(low + high) / 2' can exceed Integer.MAX_VALUE on huge arrays; use 'low + (high - low) / 2'."
                            ),
                            practiceTask = "Write linear search and compare step counts with binary search.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        // test search\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        int[] arr = { 1, 3, 5, 7, 9 };\n        int target = 7;\n        for (int i = 0; i < arr.length; i++) {\n            if (arr[i] == target) System.out.println(\"Found via linear: \" + i);\n        }\n    }\n}",
                            realWorldApplication = "Database B-Tree indexing, Git commit bisecting, and game matchmaking lookups."
                        )
                    )
                )
            )
        ),
        CurriculumLevel(
            id = "level_11",
            levelNumber = 11,
            title = "Databases, REST APIs & Spring",
            subtitle = "JDBC, SQL connections, HTTP REST endpoints, Spring Boot architecture, and Controllers",
            iconKey = "backend",
            modules = listOf(
                CurriculumModule(
                    id = "mod_11_1",
                    levelId = "level_11",
                    title = "Modern Java Backend Engineering",
                    description = "From raw JDBC to Spring Boot Controllers, Services, and Repositories",
                    lessons = listOf(
                        Lesson(
                            id = "l11_1",
                            moduleId = "mod_11_1",
                            levelId = "level_11",
                            title = "Spring Boot 3 Architecture & REST Controllers",
                            estimatedMinutes = 20,
                            conceptSummary = "Spring Boot is the global enterprise standard for Java backend development. It follows a clean 3-layer architecture: Controller (HTTP endpoints) -> Service (business logic) -> Repository (database persistence).",
                            practicalWhy = "Powers 80%+ of Java backend engineering jobs globally, offering automatic dependency injection, embedded Tomcat servers, and effortless REST JSON APIs.",
                            syntaxSnippet = "@RestController\n@RequestMapping(\"/api/v1/users\")\npublic class UserController {\n    @GetMapping\n    public List<User> getUsers() { ... }\n}",
                            exampleCode = "// Spring Boot Controller Demonstration\n/*\n@RestController\n@RequestMapping(\"/api/students\")\npublic class StudentController {\n    @Autowired\n    private StudentService service;\n    \n    @GetMapping(\"/{id}\")\n    public ResponseEntity<Student> getStudent(@PathVariable Long id) {\n        return ResponseEntity.ok(service.findById(id));\n    }\n}\n*/\npublic class SpringArchitectureDemo {\n    public static void main(String[] args) {\n        System.out.println(\"HTTP GET /api/students/1 -> 200 OK\");\n        System.out.println(\"Layer Flow: Controller -> Service -> Repository -> Database\");\n    }\n}",
                            diagramTitle = "Spring 3-Tier Enterprise Architecture",
                            diagramDescription = "Client Request -> Controller -> Service Layer -> Repository Layer -> Database",
                            diagramNodes = listOf(
                                DiagramNode("spg1", "Client Request", "GET /api/v1/courses", "step"),
                                DiagramNode("spg2", "RestController", "Maps HTTP & Validates", "step"),
                                DiagramNode("spg3", "Service Layer", "Executes business logic", "step"),
                                DiagramNode("spg4", "Repository (JPA)", "Runs SQL queries", "step"),
                                DiagramNode("spg5", "PostgreSQL / MySQL", "Persisted database storage", "output")
                            ),
                            interactiveCode = "public class SpringLab {\n    public static void main(String[] args) {\n        System.out.println(\"Spring Boot initialized on port 8080.\");\n        System.out.println(\"Registered route: GET /api/java/lessons\");\n    }\n}",
                            expectedOutput = "Spring Boot initialized on port 8080.\nRegistered route: GET /api/java/lessons",
                            commonMistakes = listOf(
                                "Putting business logic inside REST controllers instead of Service classes.",
                                "Missing @Component, @Service, or @Repository causing NoSuchBeanDefinitionException."
                            ),
                            practiceTask = "Print simulated REST response JSON string for a user profile.",
                            practiceStarterCode = "public class Main {\n    public static void main(String[] args) {\n        // print JSON\n    }\n}",
                            practiceSolutionCode = "public class Main {\n    public static void main(String[] args) {\n        String json = \"{\\\"name\\\": \\\"Awiskar\\\", \\\"role\\\": \\\"Engineer\\\"}\";\n        System.out.println(\"Response JSON: \" + json);\n    }\n}",
                            realWorldApplication = "Building modern enterprise microservices, fintech payment gateways, and e-commerce checkout backends."
                        )
                    )
                )
            )
        ),
        CurriculumLevel(
            id = "level_12",
            levelNumber = 12,
            title = "Real-World Projects & Design Patterns",
            subtitle = "Singleton, Factory, Builder, banking systems, REST APIs, and production deployment",
            iconKey = "project",
            modules = listOf(
                CurriculumModule(
                    id = "mod_12_1",
                    levelId = "level_12",
                    title = "Production Architecture & Portfolio Projects",
                    description = "Enterprise design patterns and complete end-to-end applications",
                    lessons = listOf(
                        Lesson(
                            id = "l12_1",
                            moduleId = "mod_12_1",
                            levelId = "level_12",
                            title = "Design Patterns: Singleton, Factory & Builder",
                            estimatedMinutes = 22,
                            conceptSummary = "Design patterns are time-tested blueprints for solving recurring software design problems. Creational patterns like Singleton (single instance), Factory (object creation abstraction), and Builder (clean complex object construction) are foundational in Java.",
                            practicalWhy = "Writing clean, scalable code that adheres to SOLID principles and passes senior engineering code reviews.",
                            syntaxSnippet = "// Builder pattern\nUser user = new User.Builder()\n    .name(\"Awiskar\")\n    .role(\"Admin\")\n    .build();",
                            exampleCode = "class DatabaseConnection {\n    private static DatabaseConnection instance;\n    private DatabaseConnection() { }\n    public static synchronized DatabaseConnection getInstance() {\n        if (instance == null) instance = new DatabaseConnection();\n        return instance;\n    }\n}\npublic class PatternDemo {\n    public static void main(String[] args) {\n        DatabaseConnection c1 = DatabaseConnection.getInstance();\n        DatabaseConnection c2 = DatabaseConnection.getInstance();\n        System.out.println(\"Same instance: \" + (c1 == c2));\n    }\n}",
                            diagramTitle = "Singleton Instance in Memory",
                            diagramDescription = "Single shared object instance across entire JVM runtime",
                            diagramNodes = listOf(
                                DiagramNode("dp1", "Call getInstance()", "Check static instance", "step"),
                                DiagramNode("dp2", "First Call", "Allocates in Heap once", "heap"),
                                DiagramNode("dp3", "Subsequent Calls", "Returns existing reference", "heap"),
                                DiagramNode("dp4", "Result", "Consistent shared state", "output")
                            ),
                            interactiveCode = "public class BuilderDemo {\n    public static void main(String[] args) {\n        System.out.println(\"Design Patterns Mastered:\");\n        System.out.println(\"1. Singleton (Single shared instance)\");\n        System.out.println(\"2. Factory (Dynamic object instantiation)\");\n        System.out.println(\"3. Builder (Fluent parameter chaining)\");\n    }\n}",
                            expectedOutput = "Design Patterns Mastered:\n1. Singleton (Single shared instance)\n2. Factory (Dynamic object instantiation)\n3. Builder (Fluent parameter chaining)",
                            commonMistakes = listOf(
                                "Overusing Singleton where dependency injection is cleaner.",
                                "Creating brittle God-classes that violate Single Responsibility Principle."
                            ),
                            practiceTask = "Implement a simple static Factory method to create animals.",
                            practiceStarterCode = "class AnimalFactory { /* implement */ }\npublic class Main { public static void main(String[] args) { } }",
                            practiceSolutionCode = "class AnimalFactory {\n    public static String create(String type) {\n        if (\"dog\".equalsIgnoreCase(type)) return \"Created Dog\";\n        return \"Created Animal\";\n    }\n}\npublic class Main {\n    public static void main(String[] args) {\n        System.out.println(AnimalFactory.create(\"dog\"));\n    }\n}",
                            realWorldApplication = "Spring framework's ApplicationContext (Singleton beans), Java's StringBuilder (Builder), and LoggerFactory (Factory)."
                        )
                    )
                )
            )
        )
    )

    fun getAllLessons(): List<Lesson> {
        return levels.flatMap { it.modules }.flatMap { it.lessons }
    }

    fun findLesson(id: String): Lesson? {
        return getAllLessons().find { it.id == id }
    }
}
