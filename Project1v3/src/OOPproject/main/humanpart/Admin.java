package OOPproject.main.humanpart;

import OOPproject.main.academicpart.Book;
import OOPproject.main.datapart.DataRepository;
import OOPproject.main.logs.LogRecord;
import OOPproject.main.logs.LogsSettings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class Admin extends Employee {

    private static final long serialVersionUID = 1L;

    public Admin(String name, String email, String password, int salary) {
        super(name, email, password, false, salary);
    }

    public void viewLogs() {
        Vector<LogRecord> logs = LogsSettings.getLogs();
        System.out.println("System Logs:");
        for (LogRecord log : logs) {
            System.out.println(log);
        }
    }

    public void addAdmin(String name, String email, String password, int salary) {
        Admin admin = new Admin(name, email, password, salary);
        DataRepository.addAdmin(admin);
        createLogRecord("Admin added: " + name);
        System.out.println("Admin added successfully: " + name);
    }

    public void addEmployee(String name, String email, String password, boolean isResearcher, int salary) {
        Employee employee = new Employee(name, email, password, isResearcher, salary);
        DataRepository.addEmployee(employee);
        createLogRecord("Employee added: " + name);
        System.out.println("Employee added successfully: " + name);
    }

    public void addTeacher(String name, String email, String password, boolean isResearcher, TeacherTitle title, int salary) {
        Teacher teacher = new Teacher(name, email, password, isResearcher, title, salary);
        DataRepository.addTeacher(teacher);
        createLogRecord("Teacher added: " + name);
        System.out.println("Teacher added successfully: " + name);
    }

    public void addStudent(String name, String email, String password, boolean isResearcher, DegreeType degreeType) {
        Student student = new Student(name, email, password, isResearcher, degreeType);
        DataRepository.addStudent(student);
        createLogRecord("Student added: " + name);
        System.out.println("Student added successfully: " + name);
    }

    public void addManager(String name, String email, String password, ManagerType type, int salary) {
        Manager manager = new Manager(name, email, password, type, salary);
        DataRepository.addManager(manager);
        createLogRecord("Manager added: " + name);
        System.out.println("Manager added successfully: " + name);
    }

    public void addLibrarian(String name, String email, String password, int salary) {
        Boolean isResearcher = false;
		Librarian librarian = new Librarian(name, email, password, isResearcher, salary);
        DataRepository.addLibrarian(librarian);
        createLogRecord("Librarian added: " + name);
        System.out.println("Librarian added successfully: " + name);
    }

    public void addFinanceManager(String name, String email, String password, int salary) {
        FinanceManager financeManager = new FinanceManager();
        DataRepository.addFinanceManager(financeManager);
        createLogRecord("Finance Manager added: " + name);
        System.out.println("Finance Manager added successfully: " + name);
    }

    public void removeUser(User user) {
        if (user instanceof Admin) {
            System.out.println("Cannot remove another admin.");
            return;
        }

        if (user instanceof Employee) {
            DataRepository.removeEmployee((Employee) user);
        } else if (user instanceof Student) {
            DataRepository.removeStudent((Student) user);
        } else if (user instanceof Teacher) {
            DataRepository.removeTeacher((Teacher) user);
        } else if (user instanceof Manager) {
            DataRepository.removeManager((Manager) user);
        } else {
            System.out.println("Unknown user type, cannot remove.");
            return;
        }

        createLogRecord("User removed: " + user.getName());
        System.out.println("User removed successfully: " + user.getName());
    }

    public void removeUserByUsername(String username) {
        Vector<User> users = DataRepository.getUsers();
        for (User user : users) {
            if (user.getName().equals(username)) {
                removeUser(user);
                return;
            }
        }
        System.out.println("User with username '" + username + "' not found.");
    }

    public void borrowBook(Book book) {
        if (book == null || !book.isAvailable()) {
            System.out.println("Book is not available.");
            return;
        }

        book.setAvailable(false);
        createLogRecord("Borrowed book: " + book.getBookName());
        System.out.println("Book borrowed successfully: " + book.getBookName());
    }

    public void createLogRecord(String text) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        LogRecord logRecord = new LogRecord(this.getUserId(), text, formattedDateTime);
        LogsSettings.addLogRecord(logRecord);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Admin [" + super.toString() + "]";
    }
}
