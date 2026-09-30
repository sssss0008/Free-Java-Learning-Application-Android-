package com.example.data

data class JavaKeywordItem(
    val keyword: String,
    val category: String,
    val description: String,
    val syntaxExample: String
)

data class JavaGlossaryItem(
    val term: String,
    val category: String,
    val definition: String,
    val realWorldAnalogy: String
)

data class JavaErrorItem(
    val exceptionName: String,
    val errorType: String, // Checked or Unchecked
    val cause: String,
    val howToFix: String,
    val codeExample: String
)

object JavaReferenceData {

    val keywords: List<JavaKeywordItem> = listOf(
        JavaKeywordItem("class", "OOP", "Defines a class blueprint", "class Student { ... }"),
        JavaKeywordItem("interface", "OOP", "Defines a contract with abstract method signatures", "interface Payment { void pay(); }"),
        JavaKeywordItem("extends", "OOP", "Denotes class inheritance", "class Dog extends Animal { ... }"),
        JavaKeywordItem("implements", "OOP", "Denotes interface implementation", "class Card implements Payment { ... }"),
        JavaKeywordItem("static", "Modifiers", "Belongs to the class rather than instance", "public static void main(String[] args)"),
        JavaKeywordItem("final", "Modifiers", "Prevents reassignment, method overriding, or class inheritance", "final double PI = 3.14159;"),
        JavaKeywordItem("abstract", "OOP", "Declares an incomplete class or method that must be overridden", "abstract class Shape { abstract double area(); }"),
        JavaKeywordItem("synchronized", "Concurrency", "Prevents concurrent execution of a method or block by multiple threads", "public synchronized void transfer() { ... }"),
        JavaKeywordItem("volatile", "Concurrency", "Guarantees direct visibility of variable changes across CPU caches", "private volatile boolean running = true;"),
        JavaKeywordItem("new", "Memory", "Instantiates a new object in JVM Heap memory", "Student s = new Student();"),
        JavaKeywordItem("this", "OOP", "Refers to the current object instance", "this.name = name;"),
        JavaKeywordItem("super", "OOP", "Refers to the direct superclass constructor or method", "super.speak();"),
        JavaKeywordItem("try", "Exceptions", "Encloses code that might throw an exception", "try { ... } catch (Exception e) { ... }"),
        JavaKeywordItem("catch", "Exceptions", "Handles specific exception types thrown inside a try block", "catch (IOException e) { ... }"),
        JavaKeywordItem("finally", "Exceptions", "Guarantees execution of cleanup code regardless of exceptions", "finally { resource.close(); }"),
        JavaKeywordItem("throw", "Exceptions", "Explicitly throws an exception object", "throw new IllegalArgumentException(\"Invalid input\");"),
        JavaKeywordItem("throws", "Exceptions", "Declares exceptions a method may throw up the call stack", "public void readFile() throws IOException { ... }"),
        JavaKeywordItem("record", "Modern Java", "Defines an immutable, data-carrier class with automatic getters and equals", "public record User(String id, String name) {}")
    )

    val glossary: List<JavaGlossaryItem> = listOf(
        JavaGlossaryItem(
            term = "JVM (Java Virtual Machine)",
            category = "Architecture",
            definition = "The runtime engine that loads, verifies, and executes Java Bytecode on the host operating system.",
            realWorldAnalogy = "Like a universal translator engine that reads universal instructions and drives any local engine."
        ),
        JavaGlossaryItem(
            term = "Bytecode",
            category = "Architecture",
            definition = "The intermediate, platform-neutral instruction set (.class file) compiled by javac.",
            realWorldAnalogy = "Like architectural blueprints that any licensed builder anywhere can construct without alterations."
        ),
        JavaGlossaryItem(
            term = "Heap Memory",
            category = "Memory",
            definition = "The runtime data area where all Java objects and arrays are allocated and managed by the Garbage Collector.",
            realWorldAnalogy = "A communal warehouse where all manufactured furniture (objects) is kept while in use."
        ),
        JavaGlossaryItem(
            term = "Stack Memory",
            category = "Memory",
            definition = "Thread-private memory holding method call stack frames and primitive local variables.",
            realWorldAnalogy = "A personal notepad where a worker jots down the current task, discarding pages when the task finishes."
        ),
        JavaGlossaryItem(
            term = "Garbage Collection (GC)",
            category = "Memory",
            definition = "Automatic JVM process that detects and frees heap memory occupied by unreferenced objects.",
            realWorldAnalogy = "An automated sanitation crew that recycles items no longer reachable or used by anyone."
        ),
        JavaGlossaryItem(
            term = "Dependency Injection (DI)",
            category = "Spring / Design",
            definition = "A design pattern where an object receives its dependencies from an external container (like Spring) rather than instantiating them itself.",
            realWorldAnalogy = "Plugging your laptop into a wall outlet rather than building a custom power generator inside your laptop."
        )
    )

    val errorLabItems: List<JavaErrorItem> = listOf(
        JavaErrorItem(
            exceptionName = "NullPointerException (NPE)",
            errorType = "Unchecked Runtime",
            cause = "Attempting to invoke a method, access a field, or measure length of an object reference that points to null.",
            howToFix = "Always initialize objects, use Optional<T>, or add null guards before access (if (obj != null)).",
            codeExample = "// Broken:\nString s = null;\ns.length(); // Throws NPE!\n\n// Correct:\nif (s != null) { System.out.println(s.length()); }"
        ),
        JavaErrorItem(
            exceptionName = "ArrayIndexOutOfBoundsException",
            errorType = "Unchecked Runtime",
            cause = "Accessing an array with an index that is either negative or greater than or equal to array.length.",
            howToFix = "Ensure loop indices use strictly '< array.length' and never '<= array.length'.",
            codeExample = "// Broken: for (int i = 0; i <= arr.length; i++)\n// Correct: for (int i = 0; i < arr.length; i++)"
        ),
        JavaErrorItem(
            exceptionName = "ArithmeticException: / by zero",
            errorType = "Unchecked Runtime",
            cause = "Attempting integer division or modulo by 0.",
            howToFix = "Guard denominators with condition: if (denominator != 0) or catch ArithmeticException.",
            codeExample = "if (denom != 0) { int result = num / denom; }"
        ),
        JavaErrorItem(
            exceptionName = "ConcurrentModificationException",
            errorType = "Unchecked Runtime",
            cause = "Modifying a collection (add/remove) while traversing it with an enhanced for-loop or Iterator.",
            howToFix = "Use Iterator.remove(), removeIf() predicate, or ConcurrentHashMap/CopyOnWriteArrayList.",
            codeExample = "// Correct: list.removeIf(item -> item.equals(\"old\"));"
        )
    )
}
