package com.example.data

import com.example.model.ProjectItem
import com.example.model.ProjectTask

object JavaProjectsData {

    val projects: List<ProjectItem> = listOf(
        ProjectItem(
            id = "proj_1",
            title = "Bank Account Simulation System",
            tier = "Beginner",
            estimatedHours = "3-4 Hours",
            description = "Build a complete object-oriented Banking application simulating account creation, deposit, withdrawal validation, and balance tracking with transaction history.",
            learningGoals = listOf(
                "Encapsulation with private balances and public methods",
                "Input validation and negative balance prevention",
                "Transaction log collection using ArrayList",
                "Custom banking business logic"
            ),
            technologies = listOf("Java 21", "OOP Encapsulation", "ArrayList", "Exceptions"),
            starterFiles = mapOf(
                "BankAccount.java" to """public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New Balance: $" + balance);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + ". Remaining Balance: $" + balance);
            return true;
        }
        System.out.println("Insufficient funds or invalid withdrawal amount.");
        return false;
    }

    public double getBalance() {
        return balance;
    }
}""",
                "Main.java" to """public class Main {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-9021", "Awiskar Acharya", 500.0);
        acc.deposit(250.0);
        acc.withdraw(120.0);
        System.out.println("Final Account Balance: $" + acc.getBalance());
    }
}"""
            ),
            tasks = listOf(
                ProjectTask("t1", "Create Account Class", "Define private fields for accountNumber, accountHolder, and balance.", "Encapsulation prevents direct field modification outside."),
                ProjectTask("t2", "Implement Deposit & Withdraw", "Add validation so negative amounts are rejected and overdrafts guarded.", "Use if (amount <= balance) ..."),
                ProjectTask("t3", "Transaction History Log", "Store deposit/withdrawal timestamps in a List<String>.", "List<String> transactions = new ArrayList<>();"),
                ProjectTask("t4", "Run & Test Scenarios", "Test account setup and consecutive transactions in Main.java.", "Test both successful and insufficient balance attempts.")
            ),
            documentationGuide = "Architecture Note: Demonstrates strict Encapsulation. The internal balance can never be corrupted by direct assignment from external classes."
        ),
        ProjectItem(
            id = "proj_2",
            title = "Student Management System",
            tier = "Intermediate",
            estimatedHours = "6-8 Hours",
            description = "Develop an in-memory student registry with CRUD operations (Add, Update, Delete, Search by ID), course enrollment, and GPA ranking using the Java Collections Framework.",
            learningGoals = listOf(
                "HashMap for O(1) Student ID retrieval",
                "Comparator interface for student GPA ranking",
                "Clean Separation of Concerns (Model, Service, Main)",
                "Robust error handling for missing student IDs"
            ),
            technologies = listOf("Java 21", "HashMap", "Collections.sort", "Lambda Comparator"),
            starterFiles = mapOf(
                "Student.java" to """public class Student {
    private String id;
    private String name;
    private double gpa;

    public Student(String id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getGpa() { return gpa; }

    @Override
    public String toString() {
        return "Student[ID=" + id + ", Name=" + name + ", GPA=" + gpa + "]";
    }
}""",
                "StudentService.java" to """import java.util.*;

public class StudentService {
    private Map<String, Student> registry = new HashMap<>();

    public void registerStudent(Student s) {
        registry.put(s.getId(), s);
    }

    public Student findById(String id) {
        return registry.get(id);
    }

    public List<Student> getAllRankedByGpa() {
        List<Student> list = new ArrayList<>(registry.values());
        list.sort((a, b) -> Double.compare(b.getGpa(), a.getGpa()));
        return list;
    }
}""",
                "Main.java" to """public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        service.registerStudent(new Student("STU-1", "Awiskar", 3.95));
        service.registerStudent(new Student("STU-2", "Elena", 3.82));
        service.registerStudent(new Student("STU-3", "Devin", 3.98));

        System.out.println("Top Student: " + service.getAllRankedByGpa().get(0));
    }
}"""
            ),
            tasks = listOf(
                ProjectTask("st1", "Model Student Entity", "Create immutable Student data container with getters.", "Ensure id and name cannot be null."),
                ProjectTask("st2", "Build Registry Service", "Use a Map<String, Student> for fast lookup by student ID.", "Use Map.put and Map.getOrDefault."),
                ProjectTask("st3", "Sort & Rank Students", "Sort students by GPA descending using lambdas.", "list.sort((a, b) -> Double.compare(b.getGpa(), a.getGpa()));"),
                ProjectTask("st4", "Console Menu Interface", "Simulate command menu for interactive administrative operations.", "Use Scanner or automated test runner.")
            ),
            documentationGuide = "Architecture Note: Prepares learners for Service Layer patterns commonly seen in enterprise Spring Boot frameworks."
        ),
        ProjectItem(
            id = "proj_3",
            title = "E-Commerce REST API Backend",
            tier = "Advanced",
            estimatedHours = "12-15 Hours",
            description = "Architect a production-grade Spring Boot 3 REST API for an e-commerce platform. Features Product catalog, Shopping Cart, Order placement, DTO mapping, and exception handling.",
            learningGoals = listOf(
                "Spring Boot 3 REST Controller, Service, and Repository pattern",
                "DTO (Data Transfer Object) pattern with Records",
                "Global @ControllerAdvice exception responses",
                "Clean REST conventions (GET, POST, PUT, DELETE)"
            ),
            technologies = listOf("Spring Boot 3", "REST APIs", "Java Records", "JPA Architecture", "Maven/Gradle"),
            starterFiles = mapOf(
                "ProductController.java" to """package com.example.ecommerce;

import java.util.*;

public class ProductController {
    // Simulated @RestController
    private final ProductService service = new ProductService();

    public List<ProductDTO> getProducts() {
        return service.getAllProducts();
    }

    public ProductDTO getProductById(String id) {
        return service.getProductById(id);
    }
}""",
                "ProductDTO.java" to """package com.example.ecommerce;

public record ProductDTO(String id, String title, double price, int stock) {
    // Java 21 Record for clean immutable DTO
}""",
                "ProductService.java" to """package com.example.ecommerce;

import java.util.*;

public class ProductService {
    private final List<ProductDTO> catalog = Arrays.asList(
        new ProductDTO("PROD-101", "Java Masterclass Book", 29.99, 100),
        new ProductDTO("PROD-102", "Developer Mechanical Keyboard", 89.50, 45),
        new ProductDTO("PROD-103", "JVM Architecture Poster", 14.99, 200)
    );

    public List<ProductDTO> getAllProducts() {
        return catalog;
    }

    public ProductDTO getProductById(String id) {
        return catalog.stream()
            .filter(p -> p.id().equals(id))
            .findFirst()
            .orElse(null);
    }
}""",
                "Main.java" to """package com.example.ecommerce;

public class Main {
    public static void main(String[] args) {
        ProductController api = new ProductController();
        System.out.println("GET /api/v1/products -> 200 OK");
        System.out.println("Total Catalog Products: " + api.getProducts().size());
        System.out.println("Product 1: " + api.getProductById("PROD-101").title());
    }
}"""
            ),
            tasks = listOf(
                ProjectTask("ep1", "Define Domain Records", "Use Java Records for immutable Request and Response DTOs.", "public record ProductDTO(...)"),
                ProjectTask("ep2", "Create Service Business Rules", "Implement stock reduction and price calculation logic in Service.", "Catalog stream processing."),
                ProjectTask("ep3", "Wire Controller Endpoints", "Map HTTP routes to service methods with status codes.", "HTTP GET, POST endpoints simulation."),
                ProjectTask("ep4", "Simulate Order Placement", "Write end-to-end checkout flow and verification.", "Verify stock decrements upon order completion.")
            ),
            documentationGuide = "Architecture Note: Represents the exact 3-tier REST architecture powering fintech and e-commerce platforms worldwide."
        )
    )
}
