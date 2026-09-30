package com.example.data

import com.example.model.ChallengeTestCase
import com.example.model.CodeFixingTask
import com.example.model.DailyChallenge
import com.example.model.OutputPrediction
import com.example.model.QuizQuestion

object JavaPracticeData {

    val dailyChallenges: List<DailyChallenge> = listOf(
        DailyChallenge(
            id = "ch_1",
            title = "Two Sum: Fast Index Lookup",
            difficulty = "Medium",
            category = "Arrays & HashMap",
            description = "Given an array of integers 'nums' and an integer 'target', return the indices of the two numbers such that they add up to target. Optimize to O(n) using a Java HashMap.",
            starterCode = "import java.util.*;\n\npublic class Solution {\n    public static int[] twoSum(int[] nums, int target) {\n        Map<Integer, Integer> map = new HashMap<>();\n        for (int i = 0; i < nums.length; i++) {\n            int complement = target - nums[i];\n            if (map.containsKey(complement)) {\n                return new int[] { map.get(complement), i };\n            }\n            map.put(nums[i], i);\n        }\n        return new int[] {};\n    }\n\n    public static void main(String[] args) {\n        int[] nums = { 2, 7, 11, 15 };\n        int[] res = twoSum(nums, 9);\n        System.out.println(\"[\" + res[0] + \", \" + res[1] + \"]\");\n    }\n}",
            testCases = listOf(
                ChallengeTestCase("nums = [2, 7, 11, 15], target = 9", "[0, 1]"),
                ChallengeTestCase("nums = [3, 2, 4], target = 6", "[1, 2]")
            ),
            hint = "As you iterate through the array, check if (target - current_number) already exists in your map. If so, you found the pair!",
            solutionCode = "// O(n) One-pass HashMap solution\nMap<Integer, Integer> map = new HashMap<>();\nfor (int i = 0; i < nums.length; i++) {\n    int diff = target - nums[i];\n    if (map.containsKey(diff)) return new int[] { map.get(diff), i };\n    map.put(nums[i], i);\n}",
            explanation = "Instead of a brute-force O(n²) nested loop, hashing each element's value to its index allows O(1) instantaneous complement lookup, reducing overall time complexity to linear O(n)."
        ),
        DailyChallenge(
            id = "ch_2",
            title = "Valid Palindrome: String Cleaning",
            difficulty = "Easy",
            category = "Strings",
            description = "A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.",
            starterCode = "public class Solution {\n    public static boolean isPalindrome(String s) {\n        s = s.replaceAll(\"[^a-zA-Z0-9]\", \"\").toLowerCase();\n        int left = 0, right = s.length() - 1;\n        while (left < right) {\n            if (s.charAt(left) != s.charAt(right)) return false;\n            left++;\n            right--;\n        }\n        return true;\n    }\n\n    public static void main(String[] args) {\n        System.out.println(isPalindrome(\"A man, a plan, a canal: Panama\"));\n    }\n}",
            testCases = listOf(
                ChallengeTestCase("\"A man, a plan, a canal: Panama\"", "true"),
                ChallengeTestCase("\"race a car\"", "false")
            ),
            hint = "Two-pointer technique: compare characters from both ends moving towards the center.",
            solutionCode = "int l = 0, r = s.length() - 1;\nwhile (l < r) {\n    if (s.charAt(l++) != s.charAt(r--)) return false;\n}\nreturn true;",
            explanation = "Using two pointers allows checking character symmetry in O(n) time and O(1) auxiliary space."
        ),
        DailyChallenge(
            id = "ch_3",
            title = "Reverse a Linked List",
            difficulty = "Medium",
            category = "Linked Lists",
            description = "Given the head of a singly linked list, reverse the list in-place and return the reversed list's head.",
            starterCode = "class ListNode {\n    int val;\n    ListNode next;\n    ListNode(int val) { this.val = val; }\n}\npublic class Solution {\n    public static ListNode reverseList(ListNode head) {\n        ListNode prev = null;\n        ListNode curr = head;\n        while (curr != null) {\n            ListNode nextTemp = curr.next;\n            curr.next = prev;\n            prev = curr;\n            curr = nextTemp;\n        }\n        return prev;\n    }\n    public static void main(String[] args) {\n        System.out.println(\"Linked list reversed successfully.\");\n    }\n}",
            testCases = listOf(
                ChallengeTestCase("[1, 2, 3, 4, 5]", "[5, 4, 3, 2, 1]")
            ),
            hint = "Keep track of 'prev', 'curr', and temporarily save 'curr.next' before flipping the pointer.",
            solutionCode = "ListNode prev = null, curr = head;\nwhile (curr != null) {\n    ListNode nxt = curr.next;\n    curr.next = prev;\n    prev = curr;\n    curr = nxt;\n}\nreturn prev;",
            explanation = "Flipping pointers iteratively takes O(n) time and strictly O(1) memory space."
        )
    )

    val outputPredictions: List<OutputPrediction> = listOf(
        OutputPrediction(
            id = "op_1",
            title = "String Pool vs new String() Equality",
            topic = "Strings & Memory",
            codeSnippet = "String s1 = \"Java\";\nString s2 = \"Java\";\nString s3 = new String(\"Java\");\n\nSystem.out.print((s1 == s2) + \" \" + (s1 == s3));",
            options = listOf(
                "true false",
                "true true",
                "false false",
                "Compilation Error"
            ),
            correctIndex = 0,
            explanation = "'s1 == s2' is true because both point to the identical literal in the JVM String Constant Pool. 's1 == s3' is false because 'new' explicitly allocates a distinct object instance on the regular Heap. Always use .equals() for content equality!",
            variableTrace = listOf(
                "s1 points to StringPool[\"Java\"] (addr: 0x100)",
                "s2 points to StringPool[\"Java\"] (addr: 0x100)",
                "s3 points to HeapObject[\"Java\"] (addr: 0x540)",
                "s1 == s2: compares memory addresses -> true",
                "s1 == s3: compares memory addresses -> false"
            )
        ),
        OutputPrediction(
            id = "op_2",
            title = "Post-Increment vs Pre-Increment Operator",
            topic = "Operators",
            codeSnippet = "int x = 5;\nint y = x++ + ++x;\nSystem.out.println(y);",
            options = listOf(
                "12",
                "11",
                "10",
                "13"
            ),
            correctIndex = 0,
            explanation = "'x++' evaluates to 5, and increments x to 6. Next, '++x' increments x to 7, and evaluates to 7. Thus, 5 + 7 = 12.",
            variableTrace = listOf(
                "x starts at 5",
                "x++ yields 5, then x becomes 6",
                "++x increments x to 7, then yields 7",
                "5 + 7 = 12 stored in y"
            )
        ),
        OutputPrediction(
            id = "op_3",
            title = "Polymorphic Dynamic Method Dispatch",
            topic = "OOP Polymorphism",
            codeSnippet = "class Parent {\n    void show() { System.out.print(\"Parent \"); }\n}\nclass Child extends Parent {\n    void show() { System.out.print(\"Child \"); }\n}\nParent p = new Child();\np.show();",
            options = listOf(
                "Child ",
                "Parent ",
                "Parent Child ",
                "Compilation Error"
            ),
            correctIndex = 0,
            explanation = "At runtime, Java checks the actual object instance type on the Heap (Child) rather than the reference type (Parent). Because Child overrides show(), Child's version is dynamically invoked.",
            variableTrace = listOf(
                "Reference type: Parent",
                "Runtime instance: Child (in Heap)",
                "JVM dynamic method table (vtable) invokes Child.show()"
            )
        )
    )

    val codeFixingTasks: List<CodeFixingTask> = listOf(
        CodeFixingTask(
            id = "fix_1",
            title = "Fix: Incompatible Types (Type Mismatch)",
            errorType = "Compilation Error",
            brokenCode = "public class Main {\n    public static void main(String[] args) {\n        int age = \"22\";\n        System.out.println(\"Age: \" + age);\n    }\n}",
            errorLine = 3,
            hint = "String literals wrapped in double quotes cannot be stored directly in an 'int' variable without quotes or parsing.",
            fixedCode = "public class Main {\n    public static void main(String[] args) {\n        int age = 22;\n        System.out.println(\"Age: \" + age);\n    }\n}",
            explanation = "Java is strongly statically typed. An 'int' must receive a numeric integer literal (22) or Integer.parseInt(\"22\")."
        ),
        CodeFixingTask(
            id = "fix_2",
            title = "Fix: ArrayIndexOutOfBoundsException",
            errorType = "Runtime Exception",
            brokenCode = "public class Main {\n    public static void main(String[] args) {\n        int[] nums = { 10, 20, 30 };\n        for (int i = 0; i <= nums.length; i++) {\n            System.out.println(nums[i]);\n        }\n    }\n}",
            errorLine = 4,
            hint = "Array indices run from 0 to length - 1. Using '<=' tries to read index 3 in a 3-element array!",
            fixedCode = "public class Main {\n    public static void main(String[] args) {\n        int[] nums = { 10, 20, 30 };\n        for (int i = 0; i < nums.length; i++) {\n            System.out.println(nums[i]);\n        }\n    }\n}",
            explanation = "Change '<= nums.length' to '< nums.length' to prevent the fatal off-by-one boundary breach."
        ),
        CodeFixingTask(
            id = "fix_3",
            title = "Fix: NullPointerException Guard",
            errorType = "Runtime Exception",
            brokenCode = "public class Main {\n    public static void main(String[] args) {\n        String text = null;\n        System.out.println(text.length());\n    }\n}",
            errorLine = 4,
            hint = "Calling instance methods like .length() on a null pointer crashes the JVM. Add a null check or provide a non-null default.",
            fixedCode = "public class Main {\n    public static void main(String[] args) {\n        String text = \"Learn Java\";\n        if (text != null) {\n            System.out.println(text.length());\n        }\n    }\n}",
            explanation = "Always verify reference validity or use Optional<String> to safeguard against unexpected null references."
        )
    )

    val quizQuestions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = "q1",
            category = "Java Basics",
            question = "Which memory area does the JVM use to store all created Objects and Arrays?",
            options = listOf("JVM Stack", "Heap Memory", "Method Area (Metaspace)", "PC Register"),
            correctIndex = 1,
            explanation = "All Java objects and arrays are allocated on the Heap, which is shared among all application threads and managed by the Garbage Collector."
        ),
        QuizQuestion(
            id = "q2",
            category = "OOP",
            question = "Which keyword is used in Java to inherit a class?",
            options = listOf("implements", "inherits", "extends", "subclass"),
            correctIndex = 2,
            explanation = "'extends' is used for class inheritance (e.g., class Dog extends Animal). 'implements' is reserved for interfaces."
        ),
        QuizQuestion(
            id = "q3",
            category = "Collections",
            question = "What is the average time complexity of get(key) and put(key, val) in a Java HashMap?",
            options = listOf("O(1)", "O(log n)", "O(n)", "O(n²)"),
            correctIndex = 0,
            explanation = "With a well-distributed hash function, HashMap operations perform in constant O(1) average time."
        ),
        QuizQuestion(
            id = "q4",
            category = "Concurrency",
            question = "What happens when two threads access a shared unsynchronized variable simultaneously?",
            options = listOf(
                "The JVM automatically pauses one thread",
                "A Race Condition occurs causing inconsistent state",
                "A NullPointerException is thrown",
                "Compilation fails with concurrent error"
            ),
            correctIndex = 1,
            explanation = "Without synchronization (e.g. 'synchronized', AtomicInteger, or ReentrantLock), threads interleave non-deterministically causing race conditions and dirty reads."
        ),
        QuizQuestion(
            id = "q5",
            category = "Spring Boot",
            question = "Which annotation marks a class as a REST endpoint controller in Spring Boot?",
            options = listOf("@Controller", "@RestController", "@Service", "@Endpoint"),
            correctIndex = 1,
            explanation = "@RestController combines @Controller and @ResponseBody, automatically serializing return values directly into JSON HTTP responses."
        )
    )
}
