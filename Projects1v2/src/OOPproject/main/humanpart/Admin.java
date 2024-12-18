package OOPproject.main.humanpart;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import OOPproject.main.logs.*;
import OOPproject.main.datapart.DataRepository;
public class Admin extends Employee {
	private static final long serialVersionUID = 8977041416238438834L;
	public Admin() {
		super();
        createLogRecord("Admin created");
	}
	/**
     * Constructor for creating an Admin object with specified parameters.
     *
     * @param name     The name of the administrator.
     * @param email    The email address of the administrator.
     * @param password The password for the administrator.
	 * @param salary 
     */
	public Admin(String name, String email, String password, int salary) {
		super(name, email, password, false, salary);
        createLogRecord("Admin created");
	}
	/**
     * Add a new administrator to the system with the specified parameters.
     *
     * @param name     The name of the new administrator.
     * @param email    The email address of the new administrator.
     * @param password The password for the new administrator.
	 * @param salary 
     */
	public void addAdmin(String name, String email, String password, int salary) {
		Admin admin = new Admin(name, email, password, salary);
		DataRepository.addAdmin(admin);
        createLogRecord("Admin added admin");
	}
	/**
     * Add a new employee to the system with the specified parameters.
     *
     * @param name         The name of the new employee.
     * @param email        The email address of the new employee.
     * @param password     The password for the new employee.
     * @param isResearcher Indicates whether the employee is a researcher.
	 * @param salary 
     */
    public void addEmployee(String name, String email, String password, Boolean isResearcher, int salary) {
        Employee employee = new Employee(name, email, password, isResearcher, salary);
        DataRepository.addEmployee(employee);
        if(isResearcher) {
        	ResearcherDecorator researcher = new ResearcherDecorator(employee.getUserId());
            DataRepository.addResearcherDecorator(researcher);
        }
        createLogRecord("Admin added employee");
    }
    /**
     * Add a new teacher to the system with the specified parameters.
     *
     * @param name         The name of the new teacher.
     * @param email        The email address of the new teacher.
     * @param password     The password for the new teacher.
     * @param isResearcher Indicates whether the teacher is a researcher.
     * @param teacherTitle The title of the new teacher.
     * @param salary 
     */
	public void addTeacher(String name, String email, String password, Boolean isResearcher, TeacherTitle teacherTitle, int salary) {
        Teacher teacher = new Teacher(name, email, password, isResearcher, teacherTitle, salary);
        DataRepository.addTeacher(teacher);
        if(isResearcher) {
        	ResearcherDecorator researcher = new ResearcherDecorator(teacher.getUserId());
            DataRepository.addResearcherDecorator(researcher);
        }
        createLogRecord("Admin added teacher");
	}
	/**
     * Add a new student to the system with the specified parameters.
     *
     * @param name         The name of the new student.
     * @param email        The email address of the new student.
     * @param password     The password for the new student.
     * @param isResearcher Indicates whether the student is a researcher.
     * @param degreeType   The degree type of the new student.
     */
	public void addStudent(String name, String email, String password, Boolean isResearcher, DegreeType degreeType) {
        Student student = new Student(name, email, password, isResearcher, degreeType);
        DataRepository.addStudent(student);
        if(isResearcher) {
        	ResearcherDecorator researcher = new ResearcherDecorator(student.getUserId());
            DataRepository.addResearcherDecorator(researcher);
        }
        createLogRecord("Admin added student");
    }
	/**
     * Add a new manager to the system with the specified parameters.
     *
     * @param name        The name of the new manager.
     * @param email       The email address of the new manager.
     * @param password    The password for the new manager.
     * @param managerType The type of the new manager.
	 * @param salary 
     */
	public void addManager(String name, String email, String password, ManagerType managerType, int salary) {
        Manager manager = new Manager(name, email, password, managerType, salary);
        DataRepository.addManager(manager);
        createLogRecord("Admin added manager");
	}
    public void removeEmployee(Employee employee) {
        DataRepository.removeEmployee(employee);
        createLogRecord("Admin removed employee" + "(" + employee.getUserId() + ")");
    }
    /**
     * Remove an administrator from the system.
     *
     * @param admin The administrator to remove.
     */
    public void removeAdmin(Admin admin) {
        DataRepository.removeAdmin(admin);
        createLogRecord("Admin removed admin - " + admin.getUserId() );
    }
    /**
     * Remove a teacher from the system.
     *
     * @param teacher The teacher to remove.
     */
    public void removeTeacher(Teacher teacher) {
        DataRepository.removeTeacher(teacher);
        createLogRecord("Admin removed teacher - " + teacher.getUserId() );
    }
    /**
     * Remove a student from the system.
     *
     * @param student The student to remove.
     */
    public void removeStuden(OOPproject.main.humanpart.Student student) {
        DataRepository.removeStudent(student);
        createLogRecord("Admin removed student - " + student.getUserId() );
    }
    /**
     * Remove a manager from the system.
     *
     * @param manager The manager to remove.
     */
    public void removeManager(Manager manager) {
        DataRepository.removeManager(manager);
        createLogRecord("Admin removed manager - " + manager.getUserId() );
    }
    /**
     * View system logs.
     */
    public void viewLogs() {
        System.out.println("Logs:");
        for (LogRecord log : LogsSettings.getLogs()) {
            System.out.println(log);
        }
        createLogRecord("Admin viewed logs");
    }


    public void createLogRecord(String text){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        OOPproject.main.logs.LogRecord logRecord = new OOPproject.main.logs.LogRecord(this.getUserId(), formattedDateTime, text);
        OOPproject.main.logs.LogsSettings.addLogRecord(logRecord);
    }
    /**
     * Overrides the equals method of the base class to compare Admin objects.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
