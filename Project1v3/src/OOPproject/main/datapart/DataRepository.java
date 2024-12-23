package OOPproject.main.datapart;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import OOPproject.main.academicpart.*;
import OOPproject.main.humanpart.*;
import OOPproject.main.logs.*;
import OOPproject.main.researchpart.*;
public class DataRepository {
    private static Vector<Employee> employees;
    private static Vector<Student> students;
    private static Vector<Teacher> teachers;
    private static Vector<Admin> admins;
    private static Vector<Manager> managers;
    private static Vector<ResearcherDecorator> researchers;
    private static Vector<Course> courses;
    private static Vector<Lesson> lessons;
    private static Vector<Mark> marks;
    private static Vector<Message> messages;
    private static Vector<Complaint> complaints;
    private static Vector<StudentOrganization> studentOrganizations;
    private static Vector<ResearchPaper> researchPapers;
    private static Vector<ResearchProject> researchProjects;
	private static Vector<News> neews;
    private static Vector<Journal> journals;
    private static Vector<StudentRegistration> studentRegistrations;
    private static Vector<Book> books ;
    private static Vector<FinanceManager> financeManagers;
    private static Vector<Librarian> librarians;

    private static int indexCounter = 0;

    static {
    	librarians=new Vector<Librarian>();
    	financeManagers=new Vector<FinanceManager>();
        employees = new Vector<Employee>();
        students = new Vector<Student>();
        teachers = new Vector<Teacher>();
        admins = new Vector<Admin>();
        managers = new Vector<Manager>();
        researchers = new Vector<ResearcherDecorator>();
        courses = new Vector<Course>();
        lessons = new Vector<Lesson>();
        marks = new Vector<Mark>();
        neews = new Vector<News>();
        messages = new Vector<Message>();
        complaints = new Vector<Complaint>();
        studentOrganizations = new Vector<StudentOrganization>();
        researchPapers = new Vector<ResearchPaper>();
        researchProjects = new Vector<ResearchProject>();
        journals = new Vector<Journal>();
        studentRegistrations = new Vector<StudentRegistration>();
        books= new Vector<Book>();

        try {
            indexCounter = (int) deserialize("data/indexCounter.dat");
        } catch (IOException | ClassNotFoundException e) {
            indexCounter = 0;
            e.printStackTrace();
        }
    }
    // constructor
    public DataRepository() {}
    /**
     * Retrieves the next unique ID for entities in the system. It increments and returns the counter.
     *
     * @return The next unique ID.
     */
    public static int getNextId() {
        return ++indexCounter; // Increment and return the counter
    }

    /**
     * Pulls data from the database and populates the static vectors in the DataRepository.
     * This method deserializes data from corresponding files and loads it into the application's memory.
     * It is typically used to initialize or refresh the application's data state from persistent storage.
     */
    @SuppressWarnings("unchecked")
	public static void pullDataFromDatabase() {
        try {
            employees = (Vector<Employee>) deserialize("data/employees.dat");
            students = (Vector<Student>) deserialize("data/students.dat");
            teachers = (Vector<Teacher>) deserialize("data/teachers.dat");
            admins = (Vector<Admin>) deserialize("data/admins.dat");
            managers = (Vector<Manager>) deserialize("data/managers.dat");
            researchers = (Vector<ResearcherDecorator>) deserialize("data/researchers.dat");
            courses = (Vector<Course>) deserialize("data/courses.dat");
            lessons = (Vector<Lesson>) deserialize("data/lessons.dat");
            marks = (Vector<Mark>) deserialize("data/marks.dat");
            neews = (Vector<News>) deserialize("data/news.dat");
            messages = (Vector<Message>) deserialize("data/messages.dat");
            complaints = (Vector<Complaint>) deserialize("data/complaints.dat");
            studentOrganizations = (Vector<StudentOrganization>) deserialize("data/studentOrganizations.dat");
            researchPapers = (Vector<ResearchPaper>) deserialize("data/researchPapers.dat");
            researchProjects = (Vector<ResearchProject>) deserialize("data/researchProjects.dat");
            journals = (Vector<Journal>) deserialize("data/journals.dat");
            studentRegistrations = (Vector<StudentRegistration>) deserialize("data/studentRegistrations.dat");
            books=(Vector<Book>) deserialize("data/book.dat");
            financeManagers=(Vector<FinanceManager>) deserialize("data/financeManagers.dat");
            librarians=(Vector<Librarian>) deserialize("data/librarian.dat");
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return ois.readObject();
        }
    }

    /**
     * Saves transaction data to the database. This method serializes the current state of the static arrays
     * in the DataRepository and writes them to corresponding files for persistent storage.
     * It is typically used for saving the current state of the application data such as students, teachers,
     * courses, etc., to ensure data consistency and durability.
     */
    public static void saveTransactionDataToDB() {
        try {
            serialize(employees, "data/employees.dat");
            serialize(students, "data/students.dat");
            serialize(teachers, "data/teachers.dat");
            serialize(admins, "data/admins.dat");
            serialize(managers, "data/managers.dat");
            serialize(researchers, "data/researchers.dat");
            serialize(courses, "data/courses.dat");
            serialize(lessons, "data/lessons.dat");
            serialize(marks, "data/marks.dat");
            serialize(neews, "data/news.dat");
            serialize(messages, "data/messages.dat");
            serialize(complaints, "data/complaints.dat");
            serialize(studentOrganizations, "data/studentOrganizations.dat");
            serialize(researchPapers, "data/researchPapers.dat");
            serialize(researchProjects, "data/researchProjects.dat");
            serialize(journals, "data/journals.dat");
            serialize(studentRegistrations, "data/studentRegistrations.dat");
            serialize(financeManagers,"data/financeManager.dat");       
            serialize(librarians,"data/librarians.dat"); 

            serialize(indexCounter, "data/indexCounter.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void serialize(Object obj, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
        }
    }

    /**
     * Retrieves the current user of the application. This method searches for a user by their ID
     * in the various vectors like Student, Teacher, Admin, Manager, TechSupportSpecialist.
     * It is useful for identifying the current user based on their unique identifier and obtaining
     * their specific data and permissions.
     *
     * @return User the current user of the application, or null if the user is not found.
     */
    public static User login(String name, String password) {
        pullDataFromDatabase();
        LogsSettings.retrieveLogs();
        // create a vector of vectors we will iterate through
        Vector<Vector<? extends User>> vectors = new Vector<>();
        vectors.add(employees);
        vectors.add(students);
        vectors.add(teachers);
        vectors.add(admins);
        vectors.add(managers);
        vectors.add(financeManagers);
        vectors.add(librarians);
        

        // iterate through the vectors and search for the user
       for (Vector<? extends User> vector : vectors) {
           for (User user : vector) {
               if (user.getName().equals(name) && user.getPassword().equals(password)) {
                   return user;
               }
           }
       }
        return null;
    }
    /**
     * Logs out the current user and saves the transaction data to the database.
     */
    public static void logout() {
        saveTransactionDataToDB();
    }
    /**
     * Retrieves a user based on their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return User the user with the specified ID, or null if not found.
     */
    public static User getUserById(int id) {
        User user = getStudentById(id);
        if (user == null) {
            user = getTeacherById(id);
        }
        if (user == null) {
            user = getAdminById(id);
        }
        if (user == null) {
            user = getManagerById(id);
        }
  
        if (user == null) {
            user = getEmployeeById(id);
        }
        if (user==null) {
        	user=getFinanceManagerById(id);
        }
        if(user==null) {
        	user=getLibrarianById(id);
        }
        return user;
    }

    public static Vector<User> getUsers() {
        Vector<User> users = new Vector<>();
        users.addAll(employees);
        users.addAll(students);
        users.addAll(teachers);
        users.addAll(admins);
        users.addAll(managers);
        users.addAll(financeManagers);
        users.addAll(librarians);
        return users;
    }


    // get methods
    public static Vector<Librarian> getLibrarian(){
    	return librarians;}
    public static User getLibrarianById(int id) {
    	for(Librarian librarian:librarians) {
    		if(librarian.getUserId()==id) {
    			return librarian;
    			}
    		}
    	return null;
    	}
    public static Vector<FinanceManager> getFinanceManager(){
    	return financeManagers;
    }
    public static FinanceManager getFinanceManagerById(int id) {
    	for(FinanceManager financeManager: financeManagers ){
    		if(financeManager.getUserId()==id) {
    			return financeManager;
    		}
    	}
		return null;
    }
    public static Vector<Employee> getEmployees() {
        return employees;
    }
    public static Employee getEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getUserId() == id) {
                return employee;
            }
        }
        return null;
    }

    public static Vector<Student> getStudents() {
        return students;
    }
    public static Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getUserId() == id) {
                return student;
            }
        }
        return null;
    }
    public static Vector<Teacher> getTeachers() {
        return teachers;
    }
    public static Teacher getTeacherById(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getUserId() == id) {
                return teacher;
            }
        }
        return null;
    }
    public static Vector<Admin> getAdmins() {
        return admins;
    }
    public static Admin getAdminById(int id) {
        for (Admin admin : admins) {
            if (admin.getUserId() == id) {
                return admin;
            }
        }
        return null;
    }
    public static Vector<Manager> getManagers() {
        return managers;
    }
    public static Manager getManagerById(int id) {
        for (Manager manager : managers) {
            if (manager.getUserId() == id) {
                return manager;
            }
        }
        return null;
    }
    public static Vector<ResearcherDecorator> getResearchers() {
        return researchers;
    }
    public static ResearcherDecorator getResearcherById(int id) {
        for (ResearcherDecorator researcherDecorator : researchers) {
            if (researcherDecorator.getDecoratedUser().getUserId() == id) {
                return researcherDecorator;
            }
        }
        return null;
    }
    public static Vector<Course> getCourses() {
        return courses;
    }
    public static Course getCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getCourseID() == courseId) {
                return course;
            }
        }
        return null;
    }
    public static Vector<Lesson> getLessons() {
        return lessons;
    }
    public static Lesson getLessonById(int lessonId) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonID() == lessonId) {
                return lesson;
            }
        }
        return null;
    }
    public static Vector<Mark> getMarks() {
        return marks;
    }
    public static Mark getMarkById(int markId) {
        for (Mark mark : marks) {
            if (mark.getMarkID() == markId) {
                return mark;
            }
        }
        return null;
    }
    public static Vector<News> getNews() {
        return neews;
    }
    public static News getNewsById(int newsId) {
        for (News news : neews) {
            if (news.getNewsID() == newsId) {
                return news;
            }
        }
        return null;
    }
    public static Vector<Message> getMessages() {
        return messages;
    }
    public static Message getMessageById(int messageId) {
        for (Message message : messages) {
            if (message.getMessageID() == messageId) {
                return message;
            }
        }
        return null;
    }
    public static Vector<Complaint> getComplaints() {
        return complaints;
    }
    public static Complaint getComplaintById(int complaintId) {
        for (Complaint complaint : complaints) {
            if (complaint.getComplaintId() == complaintId) {
                return complaint;
            }
        }
        return null;
    }
    public static Vector<StudentOrganization> getStudentOrganizations() {
        return studentOrganizations;
    }
    public static StudentOrganization getStudentOrganizationById(int studentOrganizationId) {
        for (StudentOrganization studentOrganization : studentOrganizations) {
            if (studentOrganization.getOrganizationId() == studentOrganizationId) {
                return studentOrganization;
            }
        }
        return null;
    }
    public static Vector<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }
    public static ResearchPaper getResearchPaperById(int researchPaperId) {
        for (ResearchPaper researchPaper : researchPapers) {
            if (researchPaper.getPaperID() == researchPaperId) {
                return researchPaper;
            }
        }
        return null;
    }
    public static Vector<ResearchProject> getResearchProjects() {
        return researchProjects;
    }
    public static ResearchProject getResearchProjectById(int researchProjectId) {
        for (ResearchProject researchProject : researchProjects) {
            if (researchProject.getProjectID() == researchProjectId) {
                return researchProject;
            }
        }
        return null;
    }
    public static void addBook(Book book) {
        books.add(book);
    }

    // Example method to get all books
    public static Vector<Book> getBooks() {
        return books;
    }

    public static Vector<Journal> getJournals() {
        return journals;
    }
    public static Journal getJournalById(int journalId) {
        for (Journal journal : journals) {
            if (journal.getJournalId() == journalId) {
                return journal;
            }
        }
        return null;
    }
    public static Vector<StudentRegistration> getStudentRegistrations() {
        return studentRegistrations;
    }
    public static StudentRegistration getStudentRegistrationById(int studentRegistrationId) {
        for (StudentRegistration studentRegistration : studentRegistrations) {
            if (studentRegistration.getRegistrationId() == studentRegistrationId) {
                return studentRegistration;
            }
        }
        return null;
    }
   
    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }
    public static void addStudent(Student student) {
        students.add(student);
    }
    public static void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
    public static void addAdmin(Admin admin) {
        admins.add(admin);
    }
    public static void addManager(Manager manager) {
        managers.add(manager);
    }
    public static void addResearcherDecorator(ResearcherDecorator researcherDecorator) {
        researchers.add(researcherDecorator);
    }
    public static void addCourse(Course course) {
        courses.add(course);
    }
    public static void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }
    public static void addMark(Mark mark) {
        marks.add(mark);
    }
    public static void addNews(News news) {
    	neews.add(news);
    }
    public static void addMessage(Message message) {
        messages.add(message);
    }
    public static void addComplaint(Complaint complaint) {
        complaints.add(complaint);
    }
    public static void addStudentOrganization(StudentOrganization studentOrganization) {
        studentOrganizations.add(studentOrganization);
    }
    public static void addResearchPaper(ResearchPaper researchPaper) {
        researchPapers.add(researchPaper);
    }
    public static void addResearchProject(ResearchProject researchProject) {
        researchProjects.add(researchProject);
    }
    public static void addJournal(Journal journal) {
        journals.add(journal);
    }
    public static void addStudentRegistration(StudentRegistration studentRegistration) {
        studentRegistrations.add(studentRegistration);
    }

    // remove methods
    public static void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
    public static void removeStudent(Student student) {
        students.remove(student);
    }
    public static void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }
    public static void removeAdmin(Admin admin) {
        admins.remove(admin);
    }
    public static void removeManager(Manager manager) {
        managers.remove(manager);
    }
    public static void removeResearcherDecorator(ResearcherDecorator researcherDecorator) {
        researchers.remove(researcherDecorator);
    }
    public static void removeCourse(Course course) {
        courses.remove(course);
    }
    public static void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }
    public static void removeMark(Mark mark) {
        marks.remove(mark);
    }
    public static void removeNews(News news) {
    	neews.remove(news);
    }
    public static void removeMessage(Message message) {
        messages.remove(message);
    }
    public static void removeComplaint(Complaint complaint) {
        complaints.remove(complaint);
    }

    public static void removeStudentOrganization(StudentOrganization studentOrganization) {
        studentOrganizations.remove(studentOrganization);
    }
    public static void removeResearchPaper(ResearchPaper researchPaper) {
        researchPapers.remove(researchPaper);
    }
    public static void removeJournal(Journal journal) {
        journals.remove(journal);
    }
    public static void removeResearchProject(ResearchProject researchProject) {
        researchProjects.remove(researchProject);
    }
    public static void removeStudentRegistration(StudentRegistration studentRegistration) {
        studentRegistrations.remove(studentRegistration);
    }
    public static Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookID() == bookId) {
                return book;
            }
        }
        return null;
    }

    public static void removeBook(Book book) {
        books.remove(book);
    }
	public static void addLibrarian(Librarian librarian) {
		librarians.add(librarian);
	}
	public static void addFinanceManager(FinanceManager financeManager) {
		financeManagers.add(financeManager);
	}
    
}
    
    