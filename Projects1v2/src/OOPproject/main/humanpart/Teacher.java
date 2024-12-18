package OOPproject.main.humanpart;
import OOPproject.main.datapart.DataRepository;
import OOPproject.main.academicpart.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class Teacher extends Employee {
	private static final long serialVersionUID = 6742277529241668832L;
	/**
     * A vector containing the IDs of courses taught by the teacher.
     */
    private Vector<Integer> courses;
    /**
     * The title of the teacher 
     */
    private TeacherTitle title;
    /**
     * A vector containing the rates or evaluations received by the teacher.
     */
    private Vector<Double> rates;
    /**
     * Default constructor for creating a Teacher object.
     */


    public Teacher() {
		super();
	}
    /**
     * Constructor for creating a Teacher object with specified parameters.
     *
     * @param name        The name of the teacher.
     * @param email       The email address of the teacher.
     * @param password    The password for the teacher.
     * @param isReseacher A boolean indicating whether the teacher is a researcher.
     * @param title       The title of the teacher.
     * @param salary 
     */
	public Teacher(String name, String email, String password, Boolean isReseacher, TeacherTitle title, int salary) {
		super(name, email, password, isReseacher, salary);
        this.title = title;
        this.courses = new Vector<Integer>();
        this.rates = new Vector<Double>();
        createLogRecord("Teacher has been created");
	}
	/**
     * Get the title of the teacher.
     *
     * @return The title of the teacher.
     */
	public TeacherTitle getTitle() {
		return this.title;
	}
    /**
     * Add a course to the list of courses taught by the teacher.
     *
     * @param course The course to add.
     */
    public void addCourse(OOPproject.main.academicpart.Course course) {
        this.courses.add(course.getCourseID());
        createLogRecord("Teacher added course");
    }
    /**
     * Print a list of courses taught by the teacher.
     */
	public void viewCourses() {
        System.out.println("Courses for " + getName());
        System.out.println("-------------------------------");
        for (Integer courseId : this.courses) {
            Course course = DataRepository.getCourseById(courseId);
            if (course != null) {
                System.out.println(course);
            }
        }
        createLogRecord("Teacher viewed courses");
	}


    public Vector<Course> getCourses() {
        Vector<Course> courses = DataRepository.getCourses();
        Vector<Course> teacherCourses = new Vector<>();
        for (Course course : courses) {
            if (course.getInstructors().contains(this)) {
                teacherCourses.add(course);
            }
        }
        return teacherCourses;
    }
	/**
     * Send a complaint to the dean about a student.
     *
     * @param text         The text of the complaint.
     * @param urgencyLevel The urgency level of the complaint.
     * @param student      The student being complained about.
     */
	public void sendComplaintToDean(String text, OOPproject.main.academicpart.UrgencyLevel urgencyLevel, OOPproject.main.humanpart.Student student) {
		OOPproject.main.academicpart.Complaint complaint = new OOPproject.main.academicpart.Complaint(text, urgencyLevel, getUserId(), student.getUserId());
        DataRepository.addComplaint(complaint);
        createLogRecord("Teacher send complaint to dean");
	}
	/**
     * Put a mark for a student in a specific course and lesson.
     *
     * @param student The student to put the mark for.
     * @param course  The course for which the mark is assigned.
     * @param lesson  The lesson for which the mark is assigned.
     * @param score   The score to assign to the student.
     */
	public void putMark(Student student, Course course, Lesson lesson, int score) {
		Mark mark = new Mark(student.getUserId(), course.getCourseID(), lesson.getLessonID(), score);
		DataRepository.addMark(mark);
        createLogRecord("Teacher putted mark");
	}
	/**
     * Add a new lesson to a specific course taught by the teacher.
     *
     * @param course The course for which the lesson is added.
     * @param topic  The topic or subject of the lesson.
     * @param type   The type of lesson (e.g., Lecture, Lab).
     */
	public void addLesson(Course course, String topic, LessonType type) {
        Lesson lesson = new Lesson(course.getCourseID(), topic, type);
        DataRepository.addLesson(lesson);
        createLogRecord("Teacher added lesson");
    }
	/**
     * Receive a mark or evaluation from a student.
     *
     * @param mark The mark received from a student.
     */
    public void recieveMark(Double mark) {
        this.rates.add(mark);

    }
    /**
     * Calculate and get the average mark received by the teacher.
     *
     * @return The average mark received by the teacher.
     */
    public Double getAverageMark() {
        Double sum = 0.0;
        for (Double rate : this.rates) {
            sum += rate;
        }
        return sum / this.rates.size();
    }


    public void createLogRecord(String text){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        OOPproject.main.logs.LogRecord logRecord = new OOPproject.main.logs.LogRecord(this.getUserId(), formattedDateTime, text);
        OOPproject.main.logs.LogsSettings.addLogRecord(logRecord);
    }


    /**
     * Overrides the equals method of the base class to compare Teacher objects.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}