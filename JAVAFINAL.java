import java.util.Scanner;
import java.util.ArrayList;


// User class to store user details
class User {
    private String name;
    private String email;
    private String password;
    private int completedCourses;
    private int progress;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.completedCourses = 0;
        this.progress = 0;
    }

    public String getName() {
        return name;
    }

    public void completeCourse() {
        completedCourses++;
    }

    public void updateProgress(int increment) {
        progress += increment;
        if (progress > 100) progress = 100;
    }

    public void displayProgress() {
        System.out.println("\n--- User Progress ---");
        System.out.println("Name: " + name);
        System.out.println("Courses Completed: " + completedCourses);
        System.out.println("Progress: " + progress + "%");
    }
     public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

// Payment class to handle payment operations (just a placeholder, not used now)
class Payment {
    public static boolean processPayment(double amount, Scanner scanner) {
        return true;  // Always returns true for simplicity
    }
}

// CourseManager class to manage courses
class CourseManager {
    public static void displayAvailableCourses() {
        System.out.println("\n--- Available Courses ---");
        System.out.println("1. Basics of Java");
        System.out.println("2. Object-Oriented Programming");
        System.out.println("3. Advanced Java Topics");
        System.out.println("4. Introduction to Machine Learning");
        System.out.println("5. Basics of C Language");
        System.out.println("6. Python Fundamentals");
    }

    public static String getCourseTitle(int courseId) {
        switch (courseId) {
            case 1:
                return "Basics of Java";
            case 2:
                return "Object-Oriented Programming";
            case 3:
                return "Advanced Java Topics";
            case 4:
                return "Introduction to Machine Learning";
            case 5:
                return "Basics of C Language";
            case 6:
                return "Python Fundamentals";
            default:
                return null;
        }
    }

    public static void startCourse(User user, int courseId, Scanner scanner) {
        String courseTitle = getCourseTitle(courseId);
        if (courseTitle == null) {
            System.out.println("Invalid course ID. Please try again.");
            return;
        }
        System.out.println("\nStarting Course: " + courseTitle);
        System.out.println("Course started successfully!");
        user.completeCourse();
        user.updateProgress(10); // Increment progress by 10%
    }

    public static void proceedToLesson(Scanner scanner, String lessonTitle, User loggedInUser) {
    System.out.println("--- Lesson Details ---");
    System.out.println("Title: " + lessonTitle);
    System.out.println("Description: Learn more about " + lessonTitle);
    System.out.println("Duration: 10 minutes");
    System.out.println("Video Link: https://example.com/video");

    // Clear any leftover newline character in the scanner
    scanner.nextLine(); // Consume the newline left by previous input

    System.out.print("Enter 'yes' when you have finished watching: ");
    String input = scanner.nextLine();

    if ("yes".equalsIgnoreCase(input)) {
        System.out.println("You have completed " + lessonTitle + "!");
        loggedInUser.completeCourse(); // Update completed courses
        loggedInUser.updateProgress(10); // Update progress by 10%
        System.out.println("Progress updated.");
    } else {
        System.out.println("Please watch the video to proceed.");
    }
}


    public static void takeCourseQuiz(User user, int courseId, Scanner scanner) {
        switch (courseId) {
            case 1:
                JAVAFINAL.takeJavaBasicsQuiz(scanner);
                break;
            case 2:
                JAVAFINAL.takeOOPConceptsQuiz(scanner);
                break;
            case 3:
                JAVAFINAL.takeAdvancedJavaQuiz(scanner);
                break;
            case 4:
                JAVAFINAL.takeMachineLearningBasicsQuiz(scanner);
                break;
            default:
                System.out.println("Invalid course selection. Returning to menu.");
                return; // Exit the method if an invalid course is selected
        }
        user.completeCourse();
        user.updateProgress(10); // Increment progress by 10%
    }
}

// Teacher class to manage teacher actions (like adding courses and quizzes)
class Teacher {
    public static void addCourse(Scanner scanner) {
        System.out.print("Enter the new course title: ");
        String courseTitle = scanner.nextLine();
        System.out.println("Course '" + courseTitle + "' added successfully!");
    }

    public static void addQuiz(Scanner scanner) {
        System.out.print("Enter the course title for quiz: ");
        String courseTitle = scanner.nextLine();
        System.out.println("Enter the quiz question: ");
        String quizQuestion = scanner.nextLine();
        System.out.println("Quiz for course '" + courseTitle + "' added: " + quizQuestion);
    }

    public static void viewStudentProgress(User user) {
        user.displayProgress();
    }
}

// Main PLMS class
public class JAVAFINAL {
    private static User loggedInUser;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Enter your role (1 for Teacher, 2 for Student): ");
        int role = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Check if the role is valid (Teacher or Student)
        if (role == 1) {
            // Teacher login
            loggedInUser = new User(name, email, password);
            System.out.println("Teacher login successful. Welcome, " + name + "!");
            teacherMenu(scanner);
        } else if (role == 2) {
            // Student login
            loggedInUser = new User(name, email, password);
            System.out.println("Student login successful. Welcome, " + name + "!");
            studentMenu(scanner);
        } else {
            System.out.println("Invalid role. Exiting...");
        }
    }

    
     public static void registerUser(Scanner scanner, int role, String name) {
        System.out.println("Welcome " + name + "! Please complete your registration.");
        
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Register user
        loggedInUser = new User(name, email, password);

        System.out.println("Registration successful!");
        
        // After registration, proceed to login
        if (role == 1) {
            teacherLogin(scanner);
        } else {
            studentLogin(scanner);
        }
    }


    public static void teacherMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Teacher Menu ---");
            System.out.println("1. Add Course");
            System.out.println("2. Add Quiz");
            System.out.println("3. View Progress of Students");
            System.out.println("4. Log Out");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> Teacher.addCourse(scanner);
                case 2 -> Teacher.addQuiz(scanner);
                case 3 -> Teacher.viewStudentProgress(loggedInUser);
                case 4 -> {
                    System.out.println("You have been logged out.");
                    loggedInUser = null;
                }
                case 5 -> System.out.println("Thank you for using PLMS. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5 && loggedInUser != null);
    }

    public static void studentMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Start Learning");
            System.out.println("2. Take a Quiz");
            System.out.println("3. View Progress");
            System.out.println("4. Log Out");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> startLearning(scanner);
                case 2 -> takeQuiz(scanner);
                case 3 -> loggedInUser.displayProgress();
                case 4 -> {
                    System.out.println("You have been logged out.");
                    loggedInUser = null;
                }
                case 5 -> System.out.println("Thank you for using PLMS. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5 && loggedInUser != null);
    }

    public static void startLearning(Scanner scanner) {
        CourseManager.displayAvailableCourses();
        System.out.print("Select a course to start (1-6): ");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        CourseManager.startCourse(loggedInUser, courseId, scanner);
        String lessonTitle = CourseManager.getCourseTitle(courseId); // Use course title as lesson title
    if (lessonTitle != null) {
        CourseManager.proceedToLesson(scanner, lessonTitle, loggedInUser);
    } else {
        System.out.println("Invalid course selected. Returning to menu.");
    }
}
    

    public static void takeQuiz(Scanner scanner) {
        CourseManager.displayAvailableCourses();
        System.out.print("Select a course to take a quiz: ");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        CourseManager.takeCourseQuiz(loggedInUser, courseId, scanner);
    }

    // Placeholder methods for quizzes
    public static void takeJavaBasicsQuiz(Scanner scanner) {
        System.out.println("\n--- Java Basics Quiz ---");
        System.out.println("Question: What is the correct syntax to print a message in Java?");
        System.out.println("1. System.out.println();\n2. Console.log();\n3. print();\n4. echo();");
        System.out.print("Your answer: ");
        int answer = scanner.nextInt();
        if (answer == 1) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is 1.");
        }
    }

    public static void takeOOPConceptsQuiz(Scanner scanner) {
        System.out.println("\n--- OOP Concepts Quiz ---");
        System.out.println("Question: What does OOP stand for?");
        System.out.println("1. Object-Oriented Programming\n2. Only Object Processing\n3. Optional Object Programming\n4. Oriented Object Processing");
        System.out.print("Your answer: ");
        int answer = scanner.nextInt();
        if (answer == 1) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is 1.");
        }
    }

    public static void takeAdvancedJavaQuiz(Scanner scanner) {
        System.out.println("\n--- Advanced Java Quiz ---");
        System.out.println("Question: Which feature is not part of Java 8?");
        System.out.println("1. Lambda Expressions\n2. Streams\n3. Modules\n4. Default Methods");
        System.out.print("Your answer: ");
        int answer = scanner.nextInt();
        if (answer == 3) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is 3.");
        }
    }
    
    
    public static void takeMachineLearningBasicsQuiz(Scanner scanner) {
        System.out.println("\n--- Machine Learning Basics Quiz ---");
        System.out.println("Question: What is supervised learning?");
        System.out.println("1. Learning with labeled data\n2. Learning without "
                + "labeled data\n3. Learning with reinforcement\n4. None of the above");
        System.out.print("Your answer: ");
        int answer = scanner.nextInt();
        if (answer == 1) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is 1.");
        }
    }

    private static void teacherLogin(Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void studentLogin(Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}