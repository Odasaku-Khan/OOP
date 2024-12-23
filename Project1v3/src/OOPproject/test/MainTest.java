package OOPproject.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import OOPproject.main.academicpart.*;
import OOPproject.main.datapart.DataRepository;
import OOPproject.main.humanpart.*;
import OOPproject.main.logs.LogsSettings;
import OOPproject.main.researchpart.ResearchProject;
import OOPproject.main.researchpart.ResearchPaper;
import OOPproject.main.languagepart.LanguageSettings;

public class MainTest {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Load existing data from the database
        DataRepository.pullDataFromDatabase();
        LogsSettings.retrieveLogs();

        User user = attemptLogin(reader);

        while (user != null) {
            if (user instanceof Admin) {
                user = adminMenu(reader, (Admin) user);
            } else if (user instanceof Manager) {
                user = managerMenu(reader, (Manager) user);
            } else if (user instanceof Teacher) {
                user = teacherMenu(reader, (Teacher) user);
            } else if (user instanceof Student) {
                user = studentMenu(reader, (Student) user);
            } else if (user instanceof Librarian) {
                user = librarianMenu(reader, (Librarian) user);
            } else if (user instanceof FinanceManager) {
                user = financeManagerMenu(reader, (FinanceManager) user);
            } else {
                System.out.println("Invalid user type.");
                break;
            }
        }

        System.out.println("Exiting the application. Goodbye!");
    }

    private static User attemptLogin(BufferedReader reader) throws IOException {
        System.out.println("=== Welcome to the OOP Project Management System ===");
        System.out.print("Please enter your username: ");
        String username = reader.readLine();
        System.out.print("Please enter your password: ");
        String password = reader.readLine();

        User user = DataRepository.login(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getName() + "!");
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
        return user;
    }

    private static User adminMenu(BufferedReader reader, Admin admin) throws IOException {
        String choice;
        do {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1) Create User");
            System.out.println("2) Remove User");
            System.out.println("3) View Logs");
            System.out.println("4) Send Message");
            System.out.println("5) View Messages");
            System.out.println("6) Borrow Book");
            System.out.println("7) Return Book");
            System.out.println("8) Logout");

            System.out.print("Enter your choice: ");
            choice = reader.readLine();

            switch (choice) {
                case "1":
                    createUser(reader, admin);
                    break;
                case "2":
                    removeUser(reader, admin);
                    break;
                case "3":
                    admin.viewLogs();
                    break;
                case "4":
                    sendMessage(reader, admin);
                    break;
                case "5":
                    admin.viewMessages();
                    break;
                case "6":
                    borrowBook(reader, admin);
                    break;
                case "7":
                    returnBook(reader, admin);
                    break;
                case "8":
                    DataRepository.logout();
                    System.out.println("Logged out successfully.");
                    return null;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!"8".equals(choice));

        return admin;
    }

    private static User managerMenu(BufferedReader reader, Manager manager) throws IOException {
        String choice;
        do {
            System.out.println("\n=== Manager Menu ===");
            System.out.println("1) Create Course");
            System.out.println("2) Manage Student Registrations");
            System.out.println("3) Assign Teacher to Course");
            System.out.println("4) Publish News");
            System.out.println("5) Create Student Organization");
            System.out.println("6) View Complaints");
            System.out.println("7) Add Journal");
            System.out.println("8) Publish News to Journal");
            System.out.println("9) Send Message");
            System.out.println("10) View Messages");
            System.out.println("11) Logout");

            System.out.print("Enter your choice: ");
            choice = reader.readLine();

            switch (choice) {
                case "1":
                    createCourse(reader, manager);
                    break;
                case "2":
                    manageStudentRegistrations(reader, manager);
                    break;
                case "3":
                    assignTeacherToCourse(reader, manager);
                    break;
                case "4":
                    publishNews(reader, manager);
                    break;
                case "5":
                    createStudentOrganization(manager);
                    break;
                case "6":
                    manager.viewComplaints();
                    break;
                case "7":
                    addJournal(manager);
                    break;
                case "8":
                    publishNewsToJournal(reader, manager);
                    break;
                case "9":
                    sendMessage(reader, manager);
                    break;
                case "10":
                    manager.viewMessages();
                    break;
                case "11":
                    DataRepository.logout();
                    System.out.println("Logged out successfully.");
                    return null;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!"11".equals(choice));

        return manager;
    }

    private static User teacherMenu(BufferedReader reader, Teacher teacher) throws IOException {
        String choice;
        ResearcherDecorator researcher = DataRepository.getResearcherById(teacher.getUserId());

        do {
            System.out.println("\n=== Teacher Menu ===");
            System.out.println("1) Add Lesson to Course");
            System.out.println("2) Put Mark for Student");
            System.out.println("3) Send Complaint to Dean");
            System.out.println("4) View Courses");
            System.out.println("5) Send Message");
            System.out.println("6) View Messages");
            if (researcher != null) {
                System.out.println("7) Submit Research Paper");
                System.out.println("8) Create Research Project");
                System.out.println("9) Add Paper to Project");
                System.out.println("10) View Papers");
            }
            System.out.println("11) Logout");

            System.out.print("Enter your choice: ");
            choice = reader.readLine();

            switch (choice) {
                case "1":
                    addLessonToCourse(reader, teacher);
                    break;
                case "2":
                    putMarkForStudent(reader, teacher);
                    break;
                case "3":
                    sendComplaintToDean(reader, teacher);
                    break;
                case "4":
                    teacher.viewCourses();
                    break;
                case "5":
                    sendMessage(reader, teacher);
                    break;
                case "6":
                    teacher.viewMessages();
                    break;
                case "7":
                    if (researcher != null) {
                        submitResearchPaper(reader, researcher);
                    } else {
                        System.out.println("You are not a researcher.");
                    }
                    break;
                case "8":
                    if (researcher != null) {
                        createResearchProject(reader, researcher);
                    } else {
                        System.out.println("You are not a researcher.");
                    }
                    break;
                case "9":
                    if (researcher != null) {
                        addPaperToProject(reader, researcher);
                    } else {
                        System.out.println("You are not a researcher.");
                    }
                    break;
                case "10":
                    if (researcher != null) {
                        researcher.printPapers(null); // You can pass a specific comparator if needed
                    } else {
                        System.out.println("You are not a researcher.");
                    }
                    break;
                case "11":
                    DataRepository.logout();
                    System.out.println("Logged out successfully.");
                    return null;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!"11".equals(choice));

        return teacher;
    }

    private static User studentMenu(BufferedReader reader, Student student) throws IOException {
        String choice;
        ResearcherDecorator researcher = DataRepository.getResearcherById(student.getUserId());

        do {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1) Enroll in a Course");
            System.out.println("2) View Enrolled Courses");
            System.out.println("3) View Marks");
            System.out.println("4) View Transcript");
            System.out.println("5) Apply for Student Organization");
            System.out.println("6) Subscribe to Journal");
            System.out.println("7) Send Message");
            System.out.println("8) View Messages");
            System.out.println("9) Borrow Book");
            System.out.println("10) Return Book");
            if (researcher != null) {
                System.out.println("11) Submit Research Paper");
                System.out.println("12) Create Research Project");
                System.out.println("13) Add Paper to Project");
                System.out.println("14) View Papers");
            }
            System.out.println("15) Logout");

            System.out.print("Enter your choice: ");
            choice = reader.readLine();

            switch (choice) {
                case "1":
                    enrollInCourse(reader, student);
                    break;
                case "2":
                    displayEnrolledCourses(student);
                    break;
                case "3":
                    student.viewMarks();
                    break;
                case "4":
                    student.viewTranscript();
                    break;
                case "5":
                    applyForStudentOrganization1(reader, student);
                    break;
                case "6":
                    subscribeToJournal1(reader, student);
                    break;
                case "7":
                    sendMessage(reader, student);
                    break;
                case "8":
                    student.viewMessages();
                    break;
                case "9":
                    borrowBook(reader, student);
                    break;
                case "10":
                    returnBook(reader, student);
                    break;
                case "11":
                    if (researcher != null) {
                        submitResearchPaper(reader, researcher);
                    } else {
                        System.out.println("You are not a researcher.");
                    }
                    break;
                case "12":
                    if (researcher != null) {
                        createResearchProject(reader, researcher);
                    } else {
                        System.out.println("You are not a researcher.");
                    }
                    break;
                case "13":
                    if (researcher != null) {
                        addPaperToProject(reader, researcher);
                    } else {
                        System.out.println("You are not a researcher.");
                    }
                    break;
                case "14":
                    if (researcher != null) {
                        researcher.printPapers(null); // You can pass a specific comparator if needed
                    } else {
                        System.out.println("You are not a researcher.");
                    }
                    break;
                case "15":
                    DataRepository.logout();
                    System.out.println("Logged out successfully.");
                    return null;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!"15".equals(choice));

        return student;
    }

    private static User librarianMenu(BufferedReader reader, Librarian librarian) throws IOException {
        String choice;
        do {
            System.out.println("\n=== Librarian Menu ===");
            System.out.println("1) Add Book");
            System.out.println("2) Remove Book");
            System.out.println("3) View All Books");
            System.out.println("4) Send Message");
            System.out.println("5) View Messages");
            System.out.println("6) Logout");

            System.out.print("Enter your choice: ");
            choice = reader.readLine();

            switch (choice) {
                case "1":
                    addBook(reader, librarian);
                    break;
                case "2":
                    removeBook(reader, librarian);
                    break;
                case "3":
                    viewAllBooks(librarian);
                    break;
                case "4":
                    sendMessage(reader, librarian);
                    break;
                case "5":
                    librarian.viewMessages();
                    break;
                case "6":
                    DataRepository.logout();
                    System.out.println("Logged out successfully.");
                    return null;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!"6".equals(choice));

        return librarian;
    }

    private static User financeManagerMenu(BufferedReader reader, FinanceManager financeManager) throws IOException {
        String choice;
        do {
            System.out.println("\n=== Finance Manager Menu ===");
            System.out.println("1) Track Payment");
            System.out.println("2) Check and Block Unpaid Students");
            System.out.println("3) Unblock Student");
            System.out.println("4) Send Message");
            System.out.println("5) View Messages");
            System.out.println("6) Logout");

            System.out.print("Enter your choice: ");
            choice = reader.readLine();

            switch (choice) {
                case "1":
                    trackPayment(reader, financeManager);
                    break;
                case "2":
                    financeManager.checkAndBlockUnpaidStudents();
                    break;
                case "3":
                    unblockStudent(reader, financeManager);
                    break;
                case "4":
                    sendMessage(reader, financeManager);
                    break;
                case "5":
                    financeManager.viewMessages();
                    break;
                case "6":
                    DataRepository.logout();
                    System.out.println("Logged out successfully.");
                    return null;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!"6".equals(choice));

        return financeManager;
    }

    // ==================== Admin Menu Methods ====================

    private static void createUser(BufferedReader reader, Admin admin) throws IOException {
        System.out.println("\nSelect the type of user to create:");
        System.out.println("1) Admin");
        System.out.println("2) Employee");
        System.out.println("3) Teacher");
        System.out.println("4) Student");
        System.out.println("5) Manager");
        System.out.println("6) Librarian");
        System.out.println("7) Finance Manager");

        System.out.print("Enter your choice: ");
        String userType = reader.readLine();

        System.out.print("Enter name: ");
        String name = reader.readLine();
        System.out.print("Enter email: ");
        String email = reader.readLine();
        System.out.print("Enter password: ");
        String password = reader.readLine();

        switch (userType) {
            case "1":
                System.out.print("Enter salary: ");
                int adminSalary = Integer.parseInt(reader.readLine());
                admin.addAdmin(name, email, password, adminSalary);
                break;
            case "2":
                System.out.print("Is the employee a researcher? (true/false): ");
                boolean isResearcher = Boolean.parseBoolean(reader.readLine());
                System.out.print("Enter salary: ");
                int empSalary = Integer.parseInt(reader.readLine());
                admin.addEmployee(name, email, password, isResearcher, empSalary);
                break;
            case "3":
                System.out.print("Is the teacher a researcher? (true/false): ");
                isResearcher = Boolean.parseBoolean(reader.readLine());
                System.out.println("Select Teacher Title:");
                System.out.println("1) Tutor");
                System.out.println("2) Lector");
                System.out.println("3) Senior");
                System.out.println("4) Professional");
                System.out.print("Enter your choice: ");
                String titleInput = reader.readLine();
                TeacherTitle title;
                switch (titleInput) {
                    case "1":
                        title = TeacherTitle.Tutor;
                        break;
                    case "2":
                        title = TeacherTitle.Lector;
                        break;
                    case "3":
                        title = TeacherTitle.Senior;
                        break;
                    case "4":
                        title = TeacherTitle.Professional;
                        break;
                    default:
                        System.out.println("Invalid choice. Defaulting to Professional.");
                        title = TeacherTitle.Professional;
                        break;
                }
                System.out.print("Enter salary: ");
                int teacherSalary = Integer.parseInt(reader.readLine());
                admin.addTeacher(name, email, password, isResearcher, title, teacherSalary);
                break;
            case "4":
                System.out.println("Select Degree Type:");
                System.out.println("1) Bachelor");
                System.out.println("2) Master");
                System.out.println("3) PhD");
                System.out.print("Enter your choice: ");
                String degreeInput = reader.readLine();
                DegreeType degreeType;
                switch (degreeInput) {
                    case "1":
                        degreeType = DegreeType.Bachelor;
                        break;
                    case "2":
                        degreeType = DegreeType.Master;
                        break;
                    case "3":
                        degreeType = DegreeType.PhD;
                        break;
                    default:
                        System.out.println("Invalid choice. Defaulting to Bachelor.");
                        degreeType = DegreeType.Bachelor;
                        break;
                }
                System.out.print("Is the student a researcher? (true/false): ");
                isResearcher = Boolean.parseBoolean(reader.readLine());
                admin.addStudent(name, email, password, isResearcher, degreeType);
                break;
            case "5":
                System.out.println("Select Manager Type:");
                System.out.println("1) OfficeRegister");
                System.out.println("2) SEOGIManager");
                System.out.println("3) SGManager");
                System.out.println("4) SITEManager");
                System.out.println("5) BSManager");
                System.out.println("6) ISEManager");
                System.out.println("7) KMAManager");
                System.out.println("8) SAMManager");
                System.out.println("9) SCEManager");
                System.out.println("10) SSSManager");
                System.out.println("11) SMSGManager");
                System.out.print("Enter your choice: ");
                String managerTypeInput = reader.readLine();
                ManagerType managerType;
                switch (managerTypeInput) {
                    case "1":
                        managerType = ManagerType.OfficeRegister;
                        break;
                    case "2":
                        managerType = ManagerType.SEOGIManager;
                        break;
                    case "3":
                        managerType = ManagerType.SGManager;
                        break;
                    case "4":
                        managerType = ManagerType.SITEManager;
                        break;
                    case "5":
                        managerType = ManagerType.BSManager;
                        break;
                    case "6":
                        managerType = ManagerType.ISEManager;
                        break;
                    case "7":
                        managerType = ManagerType.KMAManager;
                        break;
                    case "8":
                        managerType = ManagerType.SAMManager;
                        break;
                    case "9":
                        managerType = ManagerType.SCEManager;
                        break;
                    case "10":
                        managerType = ManagerType.SSSManager;
                        break;
                    case "11":
                        managerType = ManagerType.SMSGManager;
                        break;
                    default:
                        System.out.println("Invalid choice. Defaulting to OfficeRegister.");
                        managerType = ManagerType.OfficeRegister;
                        break;
                }
                System.out.print("Enter salary: ");
                int managerSalary = Integer.parseInt(reader.readLine());
                admin.addManager(name, email, password, managerType, managerSalary);
                break;
            case "6":
                System.out.print("Enter salary: ");
                int librarianSalary = Integer.parseInt(reader.readLine());
                admin.addLibrarian(name, email, password, librarianSalary);
                break;
            case "7":
                System.out.print("Enter salary: ");
                int financeManagerSalary = Integer.parseInt(reader.readLine());
                admin.addFinanceManager(name, email, password, financeManagerSalary);
                break;
            default:
                System.out.println("Invalid option selected.");
                break;
        }
        System.out.println("User created successfully.");
    }

    private static void removeUser(BufferedReader reader, Admin admin) throws IOException {
        System.out.println("\n=== Remove User ===");
        System.out.print("Enter the username of the user to remove: ");
        String username = reader.readLine();

        admin.removeUserByUsername(username);
    }

    private static void sendMessage(BufferedReader reader, User sender) throws IOException {
        int receiverId = selectUser(reader);
        if (receiverId != -1) {
            System.out.print("Enter your message: ");
            String content = reader.readLine();
            sender.sendMessage(receiverId, content);
            System.out.println("Message sent successfully.");
        }
    }

    private static void borrowBook(BufferedReader reader, Admin admin) throws IOException {
        System.out.println("\n=== Borrow Book ===");
        Vector<Book> books = DataRepository.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books available to borrow.");
            return;
        }

        System.out.println("Available Books:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.isAvailable()) {
                System.out.println((i + 1) + ") " + book.getBookName() + " by " + book.getAuthor());
            }
        }

        System.out.print("Enter the number of the book to borrow: ");
        int bookIndex;
        try {
            bookIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (bookIndex < 0 || bookIndex >= books.size()) {
            System.out.println("Invalid book selection.");
            return;
        }

        Book selectedBook = books.get(bookIndex);
        if (!selectedBook.isAvailable()) {
            System.out.println("Book is not available.");
            return;
        }

        admin.borrowBook(selectedBook);
    }

    private static void returnBook(BufferedReader reader, Admin admin) throws IOException {
        System.out.println("\n=== Return Book ===");
        Vector<Book> borrowedBooks = admin.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("You have no borrowed books to return.");
            return;
        }

        System.out.println("Borrowed Books:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book book = borrowedBooks.get(i);
            System.out.println((i + 1) + ") " + book.getBookName() + " by " + book.getAuthor());
        }

        System.out.print("Enter the number of the book to return: ");
        int bookIndex;
        try {
            bookIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (bookIndex < 0 || bookIndex >= borrowedBooks.size()) {
            System.out.println("Invalid book selection.");
            return;
        }

        //Book selectedBook = borrowedBooks.get(bookIndex);
        //admin.returnBook(selectedBook);
        //System.out.println("Book returned successfully.");
    }

    // ==================== Manager Menu Methods ====================

    private static void createCourse(BufferedReader reader, Manager manager) throws IOException {
        System.out.println("\n=== Create Course ===");
        System.out.print("Enter course name: ");
        String courseName = reader.readLine();

        System.out.println("Select course type:");
        System.out.println("1) Mandatory");
        System.out.println("2) Free");
        System.out.println("3) Elective");
        System.out.print("Enter your choice: ");
        String courseTypeInput = reader.readLine();
        CourseType courseType;

        switch (courseTypeInput) {
            case "1":
                courseType = CourseType.Mandatory;
                break;
            case "2":
                courseType = CourseType.Free;
                break;
            case "3":
                courseType = CourseType.Elective;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Mandatory.");
                courseType = CourseType.Mandatory;
                break;
        }

        System.out.print("Enter required year of study: ");
        int year;
        try {
            year = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Defaulting to year 1.");
            year = 1;
        }

        System.out.print("Enter number of credits: ");
        int credits;
        try {
            credits = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Defaulting to 3 credits.");
            credits = 3;
        }

        manager.createCourse(courseName, courseType, year, credits);
        System.out.println("Course created successfully.");
    }

    private static void manageStudentRegistrations(BufferedReader reader, Manager manager) throws IOException {
        System.out.println("\n=== Manage Student Registrations ===");
        Vector<StudentRegistration> registrations = DataRepository.getStudentRegistrations();
        if (registrations.isEmpty()) {
            System.out.println("There are no pending student registrations.");
            return;
        }

        System.out.println("Pending Student Registrations:");
        for (int i = 0; i < registrations.size(); i++) {
            StudentRegistration reg = registrations.get(i);
            System.out.println((i + 1) + ") Registration ID: " + reg.getRegistrationId()
                    + ", Student: " + reg.getStudent().getName()
                    + ", Course: " + reg.getCourse().getCourseName());
        }

        System.out.print("Enter the number of the registration to manage: ");
        int regIndex;
        try {
            regIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (regIndex < 0 || regIndex >= registrations.size()) {
            System.out.println("Invalid registration selection.");
            return;
        }

        StudentRegistration selectedRegistration = registrations.get(regIndex);
        System.out.println("Do you want to approve (1) or reject (2) this registration?");
        String action = reader.readLine();

        if ("1".equals(action)) {
            manager.approveStudentRegistration(selectedRegistration.getRegistrationId());
            System.out.println("Registration approved.");
        } else if ("2".equals(action)) {
            DataRepository.removeStudentRegistration(selectedRegistration);
            System.out.println("Registration rejected.");
        } else {
            System.out.println("Invalid action selected.");
        }
    }

    private static void assignTeacherToCourse(BufferedReader reader, Manager manager) throws IOException {
        System.out.println("\n=== Assign Teacher to Course ===");
        Vector<Teacher> teachers = DataRepository.getTeachers();
        if (teachers.isEmpty()) {
            System.out.println("There are no teachers available.");
            return;
        }

        System.out.println("Available Teachers:");
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);
            System.out.println((i + 1) + ") " + teacher.getName() + " (" + teacher.getTitle() + ")");
        }

        System.out.print("Select the number of the teacher: ");
        int teacherIndex;
        try {
            teacherIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (teacherIndex < 0 || teacherIndex >= teachers.size()) {
            System.out.println("Invalid teacher selection.");
            return;
        }

        Teacher selectedTeacher = teachers.get(teacherIndex);

        Vector<Course> courses = DataRepository.getCourses();
        if (courses.isEmpty()) {
            System.out.println("There are no courses available.");
            return;
        }

        System.out.println("Available Courses:");
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.println((i + 1) + ") " + course.getCourseName());
        }

        System.out.print("Select the number of the course: ");
        int courseIndex;
        try {
            courseIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (courseIndex < 0 || courseIndex >= courses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selectedCourse = courses.get(courseIndex);

        manager.assignTeacherToCourse(selectedCourse, selectedTeacher);
        System.out.println("Teacher assigned to course successfully.");
    }

    private static void publishNews(BufferedReader reader, Manager manager) throws IOException {
        System.out.println("\n=== Publish News ===");
        System.out.print("Enter news title: ");
        String title = reader.readLine();
        System.out.print("Enter news content: ");
        String content = reader.readLine();

        manager.publishNews(title, content);
        System.out.println("News published successfully.");
    }

    private static void createStudentOrganization(Manager manager) {
        System.out.println("\n=== Create Student Organization ===");
        manager.createStudentOrganization();
    }

    private static void addJournal(Manager manager) {
        System.out.println("\n=== Add Journal ===");
        manager.addJournal();
    }

    private static void publishNewsToJournal(BufferedReader reader, Manager manager) throws IOException {
        System.out.println("\n=== Publish News to Journal ===");
        Vector<Journal> journals = DataRepository.getJournals();
        if (journals.isEmpty()) {
            System.out.println("There are no journals available.");
            return;
        }

        System.out.println("Available Journals:");
        for (int i = 0; i < journals.size(); i++) {
            Journal journal = journals.get(i);
            System.out.println((i + 1) + ") Journal ID: " + journal.getJournalId());
        }

        System.out.print("Select the number of the journal: ");
        int journalIndex;
        try {
            journalIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (journalIndex < 0 || journalIndex >= journals.size()) {
            System.out.println("Invalid journal selection.");
            return;
        }

        Journal selectedJournal = journals.get(journalIndex);

        System.out.print("Enter news title: ");
        String title = reader.readLine();
        System.out.print("Enter news content: ");
        String content = reader.readLine();

        manager.publishNewsToJournal(selectedJournal, title, content);
        System.out.println("News published to journal successfully.");
    }

    // ==================== Teacher Menu Methods ====================

    private static void addLessonToCourse(BufferedReader reader, Teacher teacher) throws IOException {
        System.out.println("\n=== Add Lesson to Course ===");
        Vector<Course> teacherCourses = teacher.getCourses();
        if (teacherCourses.isEmpty()) {
            System.out.println("You are not teaching any courses.");
            return;
        }

        System.out.println("Your Courses:");
        for (int i = 0; i < teacherCourses.size(); i++) {
            Course course = teacherCourses.get(i);
            System.out.println((i + 1) + ") " + course.getCourseName());
        }

        System.out.print("Select the number of the course to add a lesson to: ");
        int courseIndex;
        try {
            courseIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (courseIndex < 0 || courseIndex >= teacherCourses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selectedCourse = teacherCourses.get(courseIndex);

        System.out.print("Enter the lesson topic: ");
        String topic = reader.readLine();

        System.out.println("Select the lesson type:");
        System.out.println("1) Lecture");
        System.out.println("2) Practise");
        System.out.println("3) Lab");
        System.out.print("Enter your choice: ");
        String lessonTypeInput = reader.readLine();
        LessonType lessonType;

        switch (lessonTypeInput) {
            case "1":
                lessonType = LessonType.Lecture;
                break;
            case "2":
                lessonType = LessonType.Practise;
                break;
            case "3":
                lessonType = LessonType.Lab;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Lecture.");
                lessonType = LessonType.Lecture;
                break;
        }

        teacher.addLesson(selectedCourse, topic, lessonType);
        System.out.println("Lesson added successfully.");
    }

    private static void putMarkForStudent(BufferedReader reader, Teacher teacher) throws IOException {
        System.out.println("\n=== Put Mark for Student ===");
        Vector<Course> teacherCourses = teacher.getCourses();
        if (teacherCourses.isEmpty()) {
            System.out.println("You are not teaching any courses.");
            return;
        }

        System.out.println("Your Courses:");
        for (int i = 0; i < teacherCourses.size(); i++) {
            Course course = teacherCourses.get(i);
            System.out.println((i + 1) + ") " + course.getCourseName());
        }

        System.out.print("Select the number of the course: ");
        int courseIndex;
        try {
            courseIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (courseIndex < 0 || courseIndex >= teacherCourses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selectedCourse = teacherCourses.get(courseIndex);
        Vector<Lesson> lessons = selectedCourse.getLessons();

        if (lessons.isEmpty()) {
            System.out.println("There are no lessons in this course.");
            return;
        }

        System.out.println("Lessons in " + selectedCourse.getCourseName() + ":");
        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            System.out.println((i + 1) + ") " + lesson.getTopic() + " (" + lesson.getType() + ")");
        }

        System.out.print("Select the number of the lesson: ");
        int lessonIndex;
        try {
            lessonIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (lessonIndex < 0 || lessonIndex >= lessons.size()) {
            System.out.println("Invalid lesson selection.");
            return;
        }

        Lesson selectedLesson = lessons.get(lessonIndex);
        Vector<Student> students = selectedCourse.getStudents();

        if (students.isEmpty()) {
            System.out.println("There are no students enrolled in this course.");
            return;
        }

        System.out.println("Students in " + selectedCourse.getCourseName() + ":");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println((i + 1) + ") " + student.getName());
        }

        System.out.print("Select the number of the student: ");
        int studentIndex;
        try {
            studentIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (studentIndex < 0 || studentIndex >= students.size()) {
            System.out.println("Invalid student selection.");
            return;
        }

        Student selectedStudent = students.get(studentIndex);

        System.out.print("Enter the mark (0-100): ");
        int mark;
        try {
            mark = Integer.parseInt(reader.readLine());
            if (mark < 0 || mark > 100) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid mark. Please enter a number between 0 and 100.");
            return;
        }

        teacher.putMark(selectedStudent, selectedCourse, selectedLesson, mark);
        System.out.println("Mark assigned successfully.");
    }

    private static void sendComplaintToDean(BufferedReader reader, Teacher teacher) throws IOException {
        System.out.println("\n=== Send Complaint to Dean ===");
        System.out.print("Enter complaint text: ");
        String complaintText = reader.readLine();

        System.out.println("Select urgency level:");
        System.out.println("1) Low");
        System.out.println("2) Mid");
        System.out.println("3) High");
        System.out.print("Enter your choice: ");
        String urgencyInput = reader.readLine();
        UrgencyLevel urgencyLevel;

        switch (urgencyInput) {
            case "1":
                urgencyLevel = UrgencyLevel.Low;
                break;
            case "2":
                urgencyLevel = UrgencyLevel.Mid;
                break;
            case "3":
                urgencyLevel = UrgencyLevel.High;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Low.");
                urgencyLevel = UrgencyLevel.Low;
                break;
        }

        System.out.println("Select the student to complain about:");
        Vector<Student> students = DataRepository.getStudents();
        if (students.isEmpty()) {
            System.out.println("There are no students available.");
            return;
        }

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println((i + 1) + ") " + student.getName());
        }

        System.out.print("Enter the number of the student: ");
        int studentIndex;
        try {
            studentIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (studentIndex < 0 || studentIndex >= students.size()) {
            System.out.println("Invalid student selection.");
            return;
        }

        Student selectedStudent = students.get(studentIndex);
        teacher.sendComplaintToDean(complaintText, urgencyLevel, selectedStudent);
        System.out.println("Complaint sent to Dean successfully.");
    }

    // ==================== Student Menu Methods ====================

    private static void enrollInCourse(BufferedReader reader, Student student) throws IOException {
        System.out.println("\n=== Enroll in a Course ===");
        Vector<Course> courses = DataRepository.getCourses();
        if (courses.isEmpty()) {
            System.out.println("There are no courses available for enrollment.");
            return;
        }

        System.out.println("Available Courses:");
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.println((i + 1) + ") " + course.getCourseName() + " (" + course.getType() + ")");
        }

        System.out.print("Enter the number of the course you want to enroll in: ");
        int courseIndex;
        try {
            courseIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (courseIndex < 0 || courseIndex >= courses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selectedCourse = courses.get(courseIndex);
        student.registerForCourse(selectedCourse);
        System.out.println("Registration for course '" + selectedCourse.getCourseName() + "' submitted successfully.");
    }

    private static void displayEnrolledCourses(Student student) {
        System.out.println("\n=== Enrolled Courses ===");
        Vector<Course> enrolledCourses = student.getEnrolledCourses();
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }

        for (Course course : enrolledCourses) {
            System.out.println(course);
        }
    }

    private static void viewMarks(Student student) {
        System.out.println("\n=== Your Marks ===");
        student.viewMarks();
    }

    private static void viewTranscript(Student student) {
        System.out.println("\n=== Your Transcript ===");
        student.viewTranscript();
    }

    private static void applyForStudentOrganization1(BufferedReader reader, Student student) throws IOException {
        System.out.println("\n=== Apply for Student Organization ===");
        Vector<StudentOrganization> organizations = DataRepository.getStudentOrganizations();
        if (organizations.isEmpty()) {
            System.out.println("There are no student organizations available.");
            return;
        }

        System.out.println("Available Student Organizations:");
        for (int i = 0; i < organizations.size(); i++) {
            StudentOrganization org = organizations.get(i);
            System.out.println((i + 1) + ") Organization ID: " + org.getOrganizationId());
        }

        System.out.print("Enter the number of the organization to apply to: ");
        int orgIndex;
        try {
            orgIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (orgIndex < 0 || orgIndex >= organizations.size()) {
            System.out.println("Invalid organization selection.");
            return;
        }

        StudentOrganization selectedOrg = organizations.get(orgIndex);
        student.applyForStudentOrganization(selectedOrg);
        System.out.println("Applied for student organization successfully.");
    }

    private static void subscribeToJournal1(BufferedReader reader, Student student) throws IOException {
        System.out.println("\n=== Subscribe to Journal ===");
        Vector<Journal> journals = DataRepository.getJournals();
        if (journals.isEmpty()) {
            System.out.println("There are no journals available.");
            return;
        }

        System.out.println("Available Journals:");
        for (int i = 0; i < journals.size(); i++) {
            Journal journal = journals.get(i);
            System.out.println((i + 1) + ") Journal ID: " + journal.getJournalId());
        }

        System.out.print("Enter the number of the journal to subscribe to: ");
        int journalIndex;
        try {
            journalIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (journalIndex < 0 || journalIndex >= journals.size()) {
            System.out.println("Invalid journal selection.");
            return;
        }

        Journal selectedJournal = journals.get(journalIndex);
        student.subscribeToJournal(selectedJournal);
        System.out.println("Subscribed to journal successfully.");
    }

    private static void borrowBook(BufferedReader reader, Student student) throws IOException {
        System.out.println("\n=== Borrow Book ===");
        Vector<Book> books = DataRepository.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books available to borrow.");
            return;
        }

        System.out.println("Available Books:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.isAvailable()) {
                System.out.println((i + 1) + ") " + book.getBookName() + " by " + book.getAuthor());
            }
        }

        System.out.print("Enter the number of the book to borrow: ");
        int bookIndex;
        try {
            bookIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (bookIndex < 0 || bookIndex >= books.size()) {
            System.out.println("Invalid book selection.");
            return;
        }

        Book selectedBook = books.get(bookIndex);
        if (!selectedBook.isAvailable()) {
            System.out.println("Book is not available.");
            return;
        }

        student.borrowBook(selectedBook);
        selectedBook.setAvailable(false);
        System.out.println("Book borrowed successfully: " + selectedBook.getBookName());
    }

    private static void returnBook(BufferedReader reader, Student student) throws IOException {
        System.out.println("\n=== Return Book ===");
        Vector<Book> borrowedBooks = student.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("You have no borrowed books to return.");
            return;
        }

        System.out.println("Your Borrowed Books:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book book = borrowedBooks.get(i);
            System.out.println((i + 1) + ") " + book.getBookName() + " by " + book.getAuthor());
        }

        System.out.print("Enter the number of the book to return: ");
        int bookIndex;
        try {
            bookIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (bookIndex < 0 || bookIndex >= borrowedBooks.size()) {
            System.out.println("Invalid book selection.");
            return;
        }

        Book selectedBook = borrowedBooks.get(bookIndex);
        student.returnBook(selectedBook);
        selectedBook.setAvailable(true);
        System.out.println("Book returned successfully: " + selectedBook.getBookName());
    }

    private static void submitResearchPaper(BufferedReader reader, ResearcherDecorator researcher) throws IOException {
        System.out.println("\n=== Submit Research Paper ===");
        System.out.print("Enter title: ");
        String title = reader.readLine();
        System.out.print("Enter authors: ");
        String authors = reader.readLine();
        System.out.print("Enter journal: ");
        String journal = reader.readLine();
        System.out.print("Enter number of pages: ");
        int pagesNumber;
        try {
            pagesNumber = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Defaulting to 10 pages.");
            pagesNumber = 10;
        }
        System.out.print("Enter publication date (YYYY-MM-DD): ");
        String publicationDate = reader.readLine();
        System.out.print("Enter DOI: ");
        String doi = reader.readLine();

        researcher.submitResearchPaper(title, authors, journal, pagesNumber, publicationDate, doi);
        System.out.println("Research paper submitted successfully.");
    }

    private static void createResearchProject(BufferedReader reader, ResearcherDecorator researcher) throws IOException {
        System.out.println("\n=== Create Research Project ===");
        System.out.print("Enter research project topic: ");
        String topic = reader.readLine();

        researcher.createResearchProject(topic);
        System.out.println("Research project created successfully.");
    }

    private static void addPaperToProject(BufferedReader reader, ResearcherDecorator researcher) throws IOException {
        System.out.println("\n=== Add Paper to Project ===");
        Vector<ResearchProject> projects = researcher.getProjects();
        Vector<ResearchPaper> papers = researcher.getPapers();

        if (projects.isEmpty()) {
            System.out.println("You have no research projects.");
            return;
        }

        if (papers.isEmpty()) {
            System.out.println("You have no research papers to add.");
            return;
        }

        System.out.println("Your Research Projects:");
        for (int i = 0; i < projects.size(); i++) {
            ResearchProject project = projects.get(i);
            System.out.println((i + 1) + ") " + project.getTopic());
        }

        System.out.print("Select the number of the project: ");
        int projectIndex;
        try {
            projectIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (projectIndex < 0 || projectIndex >= projects.size()) {
            System.out.println("Invalid project selection.");
            return;
        }

        ResearchProject selectedProject = projects.get(projectIndex);

        System.out.println("Your Research Papers:");
        for (int i = 0; i < papers.size(); i++) {
            ResearchPaper paper = papers.get(i);
            System.out.println((i + 1) + ") " + paper.getTitle());
        }

        System.out.print("Select the number of the paper to add: ");
        int paperIndex;
        try {
            paperIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (paperIndex < 0 || paperIndex >= papers.size()) {
            System.out.println("Invalid paper selection.");
            return;
        }

        ResearchPaper selectedPaper = papers.get(paperIndex);
        researcher.addPaperToProject(selectedProject, selectedPaper);
        System.out.println("Paper added to project successfully.");
    }

    // ==================== Student Menu Helper Methods ====================

    private static void applyForStudentOrganization(BufferedReader reader, Student student) throws IOException {
        System.out.println("\n=== Apply for Student Organization ===");
        Vector<StudentOrganization> organizations = DataRepository.getStudentOrganizations();
        if (organizations.isEmpty()) {
            System.out.println("There are no student organizations available.");
            return;
        }

        System.out.println("Available Student Organizations:");
        for (int i = 0; i < organizations.size(); i++) {
            StudentOrganization org = organizations.get(i);
            System.out.println((i + 1) + ") Organization ID: " + org.getOrganizationId());
        }

        System.out.print("Enter the number of the organization to apply to: ");
        int orgIndex;
        try {
            orgIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (orgIndex < 0 || orgIndex >= organizations.size()) {
            System.out.println("Invalid organization selection.");
            return;
        }

        StudentOrganization selectedOrg = organizations.get(orgIndex);
        student.applyForStudentOrganization(selectedOrg);
        System.out.println("Applied for student organization successfully.");
    }

    private static void subscribeToJournal(BufferedReader reader, Student student) throws IOException {
        System.out.println("\n=== Subscribe to Journal ===");
        Vector<Journal> journals = DataRepository.getJournals();
        if (journals.isEmpty()) {
            System.out.println("There are no journals available.");
            return;
        }

        System.out.println("Available Journals:");
        for (int i = 0; i < journals.size(); i++) {
            Journal journal = journals.get(i);
            System.out.println((i + 1) + ") Journal ID: " + journal.getJournalId());
        }

        System.out.print("Enter the number of the journal to subscribe to: ");
        int journalIndex;
        try {
            journalIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (journalIndex < 0 || journalIndex >= journals.size()) {
            System.out.println("Invalid journal selection.");
            return;
        }

        Journal selectedJournal = journals.get(journalIndex);
        student.subscribeToJournal(selectedJournal);
        System.out.println("Subscribed to journal successfully.");
    }

    // ==================== Librarian Menu Methods ====================

    private static void addBook(BufferedReader reader, Librarian librarian) throws IOException {
        System.out.println("\n=== Add Book ===");
        System.out.print("Enter book name: ");
        String bookName = reader.readLine();
        System.out.print("Enter author: ");
        String author = reader.readLine();
        System.out.print("Enter quantity: ");
        int quantity;
        try {
            quantity = Integer.parseInt(reader.readLine());
            if (quantity < 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Must be a positive integer.");
            return;
        }

        Book book = new Book(quantity, bookName, author, 0);
        DataRepository.addBook(book);
        System.out.println("Book added successfully: " + bookName);
    }

    private static void removeBook(BufferedReader reader, Librarian librarian) throws IOException {
        System.out.println("\n=== Remove Book ===");
        Vector<Book> books = DataRepository.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books available to remove.");
            return;
        }

        System.out.println("Available Books:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ") " + book.getBookName() + " by " + book.getAuthor());
        }

        System.out.print("Enter the number of the book to remove: ");
        int bookIndex;
        try {
            bookIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (bookIndex < 0 || bookIndex >= books.size()) {
            System.out.println("Invalid book selection.");
            return;
        }

        Book selectedBook = books.get(bookIndex);
        DataRepository.removeBook(selectedBook);
        System.out.println("Book removed successfully: " + selectedBook.getBookName());
    }

    private static void viewAllBooks(Librarian librarian) {
        System.out.println("\n=== All Books ===");
        Vector<Book> books = DataRepository.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    // ==================== Finance Manager Menu Methods ====================

    private static void trackPayment(BufferedReader reader, FinanceManager financeManager) throws IOException {
        System.out.println("\n=== Track Payment ===");
        System.out.print("Enter Student ID: ");
        int studentId;
        try {
            studentId = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID.");
            return;
        }

        System.out.print("Has the student paid? (true/false): ");
        boolean isPaid;
        try {
            isPaid = Boolean.parseBoolean(reader.readLine());
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter true or false.");
            return;
        }

        financeManager.trackPayment(studentId, isPaid);
        System.out.println("Payment status updated.");
    }

    private static void unblockStudent(BufferedReader reader, FinanceManager financeManager) throws IOException {
        System.out.println("\n=== Unblock Student ===");
        System.out.print("Enter Student ID to unblock: ");
        int studentId;
        try {
            studentId = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID.");
            return;
        }

        financeManager.unblockStudent(studentId);
    }

    // ==================== Common Helper Methods ====================

    private static int selectUser(BufferedReader reader) throws IOException {
        Vector<User> users = DataRepository.getUsers();
        if (users.isEmpty()) {
            System.out.println("No users available.");
            return -1;
        }

        System.out.println("\n=== Select a User to Send a Message ===");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println((i + 1) + ") " + user.getName() + " (" + user.getClass().getSimpleName() + ")");
        }

        System.out.print("Enter the number of the user: ");
        int userIndex;
        try {
            userIndex = Integer.parseInt(reader.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return -1;
        }

        if (userIndex < 0 || userIndex >= users.size()) {
            System.out.println("Invalid user selection.");
            return -1;
        }

        return users.get(userIndex).getUserId();
    }
}
