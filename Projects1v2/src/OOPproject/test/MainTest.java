package OOPproject.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import OOPproject.main.*;
import OOPproject.main.academicpart.Course;
import OOPproject.main.academicpart.CourseType;
import OOPproject.main.academicpart.Lesson;
import OOPproject.main.academicpart.LessonType;
import OOPproject.main.academicpart.StudentRegistration;
import OOPproject.main.datapart.DataRepository;
import OOPproject.main.humanpart.Admin;
import OOPproject.main.humanpart.DegreeType;
import OOPproject.main.humanpart.User;
import OOPproject.main.researchpart.ResearchPaper;
import OOPproject.main.researchpart.ResearchProject;
import OOPproject.main.humanpart.Manager;
import OOPproject.main.humanpart.ManagerType;
import OOPproject.main.humanpart.ResearcherDecorator;
import OOPproject.main.humanpart.Student;
import OOPproject.main.humanpart.Teacher;
import OOPproject.main.humanpart.TeacherTitle;
public class MainTest {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        User user = attemptLogin(reader);

        while (user != null) {
            if (user instanceof Admin) {
                user = adminMenu(reader, (Admin) user);
            } else if (user instanceof Manager) {
                user = managerMenu(reader, (Manager) user);
            } else if (user instanceof Student) {
                user = studentMenu(reader, (Student) user);
            } else if (user instanceof Teacher) {
                user = teacherMenu(reader, (Teacher) user);
            } else {
                System.out.println("Invalid user type.");
                break;
            }
        }
    }

    private static User attemptLogin(BufferedReader reader) throws IOException {
        System.out.println("Please enter your username:");
        String username = reader.readLine();
        System.out.println("Please enter your password:");
        String password = reader.readLine();

        User user = DataRepository.login(username, password);
        if (user != null) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
        return user;
    }

    private static User adminMenu(BufferedReader reader, Admin admin) throws IOException {
        String choice;
        do {
            System.out.println("Admin Menu:");
            System.out.println("1) Create user");
            System.out.println("2) View logs");
            System.out.println("3) Logout");
            System.out.println("4) Send message");
            System.out.println("5) View messages");
            System.out.print("Enter your choice: ");

            choice = reader.readLine();

            switch (choice) {
                case "1":
                    createUser(reader, admin);
                    break;
                case "2":
                    admin.viewLogs();
                    break;
                case "3":
                    DataRepository.logout();
                    return null; // Returning null to indicate logout
                    case "4":
                        int receiverId = selectUser(reader);
                        if (receiverId != -1) {
                            System.out.println("Enter your message:");
                            String content = reader.readLine();
                            admin.sendMessage(receiverId, content);
                        }
                        break;
                    case "5":
                        admin.viewMessages();
                        break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!"3".equals(choice));

        return admin; // Return admin to continue in the loop
    }

	private static User managerMenu(BufferedReader reader, Manager manager) throws IOException {
	    String choice;
	    do {
	        System.out.println("Manager Menu:");
	        System.out.println("1) Create Course");
	        System.out.println("2) Manage Student Registrations");
	        System.out.println("3) Assign Teacher to Course");
	        System.out.println("4) Logout");
            System.out.println("5) Send message");
            System.out.println("6) View messages");
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
	                DataRepository.logout();
	                return null;
                    case "5":
                        int receiverId = selectUser(reader);
                        if (receiverId != -1) {
                            System.out.println("Enter your message:");
                            String content = reader.readLine();
                            manager.sendMessage(receiverId, content);
                        }
                        break;
                    case "6":
                        manager.viewMessages();
                        break;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	                break;
	        }
	    } while (!"4".equals(choice));
	    return manager;
	}

	private static User teacherMenu(BufferedReader reader, Teacher teacher) throws IOException {
	    String choice;
        ResearcherDecorator researcher = DataRepository.getResearcherById(teacher.getUserId());
	    do {
	        System.out.println("Teacher Menu:");
	        System.out.println("1) Add Lesson to Course");
	        System.out.println("2) Put Mark for Student");
	        System.out.println("3) Logout");
            System.out.println("4) Send message");
            System.out.println("5) View messages");
            if (researcher != null) {
                System.out.println("6) Submit Research Paper");
                System.out.println("7) Create Research Project");
                System.out.println("8) Add Paper to Project");
                System.out.println("9) View Papers");
            }
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
	                DataRepository.logout();
	                return null;
                case "4":
                    int receiverId = selectUser(reader);
                    if (receiverId != -1) {
                        System.out.println("Enter your message:");
                        String content = reader.readLine();
                        teacher.sendMessage(receiverId, content);
                    }
                    break;
                case "5":
                    teacher.viewMessages();
                    break;
                case "6":
                    submitResearchPaper(reader, researcher);
                    break;
                case "7":
                    createResearchProject(reader, researcher);
                    break;
                case "8":
                    addPaperToProject(reader, researcher);
                    break;
                case "9":
                    researcher.printPapers(null);
                    break;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	                break;
	        }
	    } while (!"3".equals(choice));
	    return teacher;
	}

	private static User studentMenu(BufferedReader reader, Student student) throws IOException {
	    String choice;
        ResearcherDecorator researcher = DataRepository.getResearcherById(student.getUserId());
	    do {
	        System.out.println("Student Menu:");
	        System.out.println("1) Enroll in a Course");
	        System.out.println("2) View Enrolled Courses");
	        System.out.println("3) View Marks");
	        System.out.println("4) Logout");
            System.out.println("5) Send message");
            System.out.println("6) View messages");
            if (researcher != null) {
                System.out.println("7) Submit Research Paper");
                System.out.println("8) Create Research Project");
                System.out.println("9) Add Paper to Project");
                System.out.println("10) View Papers");
            }
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
	                DataRepository.logout();
	                return null;
                case "5":
                    int receiverId = selectUser(reader);
                    if (receiverId != -1) {
                        System.out.println("Enter your message:");
                        String content = reader.readLine();
                        student.sendMessage(receiverId, content);
                    }
                    break;
                case "6":
                    student.viewMessages();
                    break;
                case "7":
                    submitResearchPaper(reader, researcher);
                    break;
                case "8":
                    createResearchProject(reader, researcher);
                    break;
                case "9":
                    addPaperToProject(reader, researcher);
                    break;
                case "10":
                    researcher.printPapers(null);
                    break;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	                break;
	        }
	    } while (!"4".equals(choice));
	    return student;
	}

    private static void createResearchProject(BufferedReader reader, ResearcherDecorator researcher) throws IOException {
        System.out.println("Enter research project topic:");
        String topic = reader.readLine();
        researcher.createResearchProject(topic);
        System.out.println("Research project created successfully.");
    }

    private static void addPaperToProject(BufferedReader reader, ResearcherDecorator researcher) throws IOException {
        Vector<ResearchProject> projects = researcher.getProjects();
        Vector<ResearchPaper> papers = researcher.getPapers();

        // Let the researcher select a project
        System.out.println("Select a Project:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ") " + projects.get(i).getTopic());
        }
        int projectIndex = Integer.parseInt(reader.readLine()) - 1;
        if (projectIndex < 0 || projectIndex >= projects.size()) {
            System.out.println("Invalid project selection.");
            return;
        }
        ResearchProject selectedProject = projects.get(projectIndex);

        // Let the researcher select a paper
        System.out.println("Select a Paper:");
        for (int i = 0; i < papers.size(); i++) {
            System.out.println((i + 1) + ") " + papers.get(i).getTitle());
        }
        int paperIndex = Integer.parseInt(reader.readLine()) - 1;
        if (paperIndex < 0 || paperIndex >= papers.size()) {
            System.out.println("Invalid paper selection.");
            return;
        }
        ResearchPaper selectedPaper = papers.get(paperIndex);

        researcher.addPaperToProject(selectedProject, selectedPaper);
        System.out.println("Paper added to project successfully.");
    }

    private static void submitResearchPaper(BufferedReader reader, ResearcherDecorator researcher) throws IOException {
        System.out.println("Enter research paper details:");
        System.out.print("Title: ");
        String title = reader.readLine();
        System.out.print("Authors: ");
        String authors = reader.readLine();
        System.out.print("Journal: ");
        String journal = reader.readLine();
        System.out.print("Number of Pages: ");
        int pagesNumber = Integer.parseInt(reader.readLine());
        System.out.print("Publication Date (YYYY-MM-DD): ");
        String publicationDate = reader.readLine();
        System.out.print("DOI: ");
        String doi = reader.readLine();

        researcher.submitResearchPaper(title, authors, journal, pagesNumber, publicationDate, doi);
        System.out.println("Research paper submitted successfully.");
    }
    
    private static void putMarkForStudent(BufferedReader reader, Teacher teacher) throws IOException {
        // Select Course
        Vector<Course> teacherCourses = teacher.getCourses();
        if (teacherCourses.isEmpty()) {
            System.out.println("You are not teaching any courses.");
            return;
        }

        System.out.println("Select a Course:");
        for (int i = 0; i < teacherCourses.size(); i++) {
            System.out.println((i + 1) + ") " + teacherCourses.get(i).getCourseName());
        }

        int courseIndex = Integer.parseInt(reader.readLine()) - 1;
        if (courseIndex < 0 || courseIndex >= teacherCourses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selectedCourse = teacherCourses.get(courseIndex);

        // Select Lesson
        Vector<Lesson> lessons = DataRepository.getLessons(); // Assuming lessons are filtered by the selected course
        System.out.println("Select a Lesson:");
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getCourseID() == selectedCourse.getCourseID()) {
                System.out.println((i + 1) + ") " + lessons.get(i).getTopic());
            }
        }

        int lessonIndex = Integer.parseInt(reader.readLine()) - 1;
        if (lessonIndex < 0 || lessonIndex >= lessons.size()) {
            System.out.println("Invalid lesson selection.");
            return;
        }

        Lesson selectedLesson = lessons.get(lessonIndex);

        // Select Student
        Vector<Student> students = selectedCourse.getStudents();
        System.out.println("Select a Student:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ") " + students.get(i).getName());
        }

        int studentIndex = Integer.parseInt(reader.readLine()) - 1;
        if (studentIndex < 0 || studentIndex >= students.size()) {
            System.out.println("Invalid student selection.");
            return;
        }

        Student selectedStudent = students.get(studentIndex);

        // Input Mark
        System.out.println("Enter the mark for the student:");
        int mark;
        try {
            mark = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number for the mark.");
            return;
        }

        // Assign Mark
        teacher.putMark(selectedStudent, selectedCourse, selectedLesson, mark);
        System.out.println("Mark assigned successfully.");
    }

    private static void addLessonToCourse(BufferedReader reader, Teacher teacher) throws IOException {
        Vector<Course> teacherCourses = teacher.getCourses();
        if (teacherCourses.isEmpty()) {
            System.out.println("You are not teaching any courses.");
            return;
        }

        System.out.println("Your Courses:");
        for (int i = 0; i < teacherCourses.size(); i++) {
            System.out.println((i + 1) + ") " + teacherCourses.get(i).getCourseName());
        }

        System.out.print("Select the number of the course to add a lesson to: ");
        int courseIndex = Integer.parseInt(reader.readLine()) - 1;
        if (courseIndex < 0 || courseIndex >= teacherCourses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selectedCourse = teacherCourses.get(courseIndex);

        System.out.println("Enter the lesson topic:");
        String topic = reader.readLine();
        System.out.println("Select the lesson type:");
        System.out.println("1) LECTURE");
        System.out.println("2) PRACTICE");
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
            default:
                System.out.println("Invalid lesson type selected. Defaulting to LECTURE.");
                lessonType = LessonType.Lecture;
                break;
        }

        teacher.addLesson(selectedCourse, topic, lessonType);
        System.out.println("Lesson added successfully.");
    }

	private static void assignTeacherToCourse(BufferedReader reader, Manager manager) throws IOException {
	    Vector<Teacher> teachers = DataRepository.getTeachers();
	    if (teachers.isEmpty()) {
	        System.out.println("There are no teachers available.");
	        return;
	    }
	    System.out.println("Available Teachers:");
	    for (int i = 0; i < teachers.size(); i++) {
	        System.out.println((i + 1) + ") " + teachers.get(i).getName());
	    }

	    System.out.print("Select the number of the teacher: ");
	    int teacherIndex = Integer.parseInt(reader.readLine()) - 1;
	    if (teacherIndex < 0 || teacherIndex >= teachers.size()) {
	        System.out.println("Invalid teacher selection.");
	        return;
	    }

	    Vector<Course> courses = DataRepository.getCourses();
	    if (courses.isEmpty()) {
	        System.out.println("There are no courses available.");
	        return;
	    }
	    System.out.println("Available Courses:");
	    for (int i = 0; i < courses.size(); i++) {
	        System.out.println((i + 1) + ") " + courses.get(i).getCourseName());
	    }

	    System.out.print("Select the number of the course: ");
	    int courseIndex = Integer.parseInt(reader.readLine()) - 1;
	    if (courseIndex < 0 || courseIndex >= courses.size()) {
	        System.out.println("Invalid course selection.");
	        return;
	    }

	    manager.assignCourseToTeacher(courses.get(courseIndex), teachers.get(teacherIndex));
	    System.out.println("Teacher assigned to course successfully.");
	}
    
    private static void displayEnrolledCourses(Student student) {
        Vector<Course> enrolledCourses = student.getEnrolledCourses();
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }

        System.out.println("Enrolled Courses:");
        for (Course course : enrolledCourses) {
            System.out.println(course);
        }
    }

    private static void enrollInCourse(BufferedReader reader, Student student) throws IOException {
        Vector<Course> courses = DataRepository.getCourses();
        if (courses.isEmpty()) {
            System.out.println("There are no courses available for registration.");
            return;
        }

        System.out.println("Available courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ") " + courses.get(i).getCourseName());
        }

        System.out.print("Enter the number of the course you want to enroll in: ");
        int courseNumber;
        try {
            courseNumber = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (courseNumber < 1 || courseNumber > courses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selectedCourse = courses.get(courseNumber - 1);
        student.registerForCourse(selectedCourse);
    }

    private static void createCourse(BufferedReader reader, Manager manager) throws IOException {
        System.out.println("Enter course name:");
        String courseName = reader.readLine();
        System.out.println("Select course type:");
        System.out.println("1) MAJOR");
        System.out.println("2) MINOR");
        System.out.println("3) ELECTIVE");
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
                System.out.println("Invalid course type selected. Defaulting to MAJOR.");
                courseType = CourseType.Mandatory;
                break;
        }

        System.out.println("Enter required year of study:");
        int year = Integer.parseInt(reader.readLine());
        System.out.println("Enter number of credits:");
        int credits = Integer.parseInt(reader.readLine());

        manager.createCourse(courseName, courseType, year, credits);
        System.out.println("Course created successfully.");
    }

    private static void manageStudentRegistrations(BufferedReader reader, Manager manager) throws IOException {
        Vector<StudentRegistration> registrations = DataRepository.getStudentRegistrations();
        if (registrations.isEmpty()) {
            System.out.println("There are no pending student registrations.");
            return;
        }

        System.out.println("Pending Student Registrations:");
        for (int i = 0; i < registrations.size(); i++) {
            StudentRegistration reg = registrations.get(i);
            System.out.println((i + 1) + ") Registration ID: " + reg.getRegistrationId() + ", Student: " + reg.getStudent().getName() + ", Course: " + reg.getCourse().getCourseName());
        }

        System.out.print("Enter the number of the registration to manage: ");
        int registrationNumber = Integer.parseInt(reader.readLine()) - 1;

        if (registrationNumber < 0 || registrationNumber >= registrations.size()) {
            System.out.println("Invalid registration selection.");
            return;
        }

        StudentRegistration selectedRegistration = registrations.get(registrationNumber);
        System.out.println("Do you want to approve (1) or reject (2) this registration?");
        String action = reader.readLine();

        if ("1".equals(action)) {
            manager.approveStudentRegistration(selectedRegistration.getRegistrationId());
        } else if ("2".equals(action)) {
            DataRepository.removeStudentRegistration(selectedRegistration);
            System.out.println("Registration rejected.");
        } else {
            System.out.println("Invalid action selected.");
        }
    }

    private static void createUser(BufferedReader reader, Admin admin) throws IOException {
        System.out.println("Select the type of user to create:");
        System.out.println("1) Admin");
        System.out.println("2) Employee");
        System.out.println("3) Teacher");
        System.out.println("4) Student");
        System.out.println("5) Manager");
        System.out.println("6) Tech Support Specialist");

        String userType = reader.readLine();
        System.out.println("Enter name:");
        String name = reader.readLine();
        System.out.println("Enter email:");
        String email = reader.readLine();
        System.out.println("Enter password:");
        String password = reader.readLine();

        switch (userType) {
            case "1":
                admin.addAdmin(name, email, password, 140000);
                break;
            case "2":
                System.out.println("Is the employee a researcher? (true/false):");
                boolean isResearcher = Boolean.parseBoolean(reader.readLine());
                admin.addEmployee(name, email, password, isResearcher, 212110);
                break;
            case "3":
                System.out.println("Is the teacher a researcher? (true/false):");
                isResearcher = Boolean.parseBoolean(reader.readLine());
                admin.addTeacher(name, email, password, isResearcher, TeacherTitle.Professional, 111110); // Example title
                break;
            case "4":
                System.out.println("Select Degree Type:");
                System.out.println("1) BACHELOR");
                System.out.println("2) MASTER");
                System.out.println("3) PHD");
                String degreeTypeInput = reader.readLine();
                DegreeType degreeType;

                switch (degreeTypeInput) {
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
                        System.out.println("Invalid degree type selected. Defaulting to BACHELOR.");
                        degreeType = DegreeType.Bachelor;
                        break;
                }

                System.out.println("Is the student a researcher? (true/false):");
                isResearcher = Boolean.parseBoolean(reader.readLine());
                admin.addStudent(name, email, password, isResearcher, degreeType);
                break;
            case "5":
                admin.addManager(name, email, password, ManagerType.SITEManager, 3333330); // Example manager type
                break;
            default:
                System.out.println("Invalid option selected.");
                break;
        }
        System.out.println("User created successfully.");
    }
    
    private static int selectUser(BufferedReader reader) throws IOException {
        Vector<User> users = DataRepository.getUsers();
        System.out.println("Select a user to send a message:");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println((i + 1) + ") " + user.getName() + " (" + user.getClass().getSimpleName() + ")");
        }

        int userIndex = Integer.parseInt(reader.readLine()) - 1;
        if (userIndex < 0 || userIndex >= users.size()) {
            System.out.println("Invalid user selection.");
            return -1;
        }
        return users.get(userIndex).getUserId();
    }
}
