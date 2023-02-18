package intranet;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Teacher extends Employee implements Create, Serializable {
	private static final long serialVersionUID = 8064236395019936415L;
	private String department;
	private Vector<Course> courses;
	private TeacherTitle teacherTitle;
	private Semester semester;
	private Faculty faculty;
	private HashMap<Lesson, HashMap<Student, Boolean>> attendanceRecords;
	private Vector<Lesson> hostedLessons;

	public Teacher(String firstName, String lastName, String id, String username, String password, Sex sex, int age,
			String email, double salary, String department, Vector<Course> courses, TeacherTitle teacherTitle,
			Semester semester, Faculty faculty) {
		super(firstName, lastName, id, username, password, sex, age, email, salary);
		this.department = department;
		this.courses = courses;
		this.teacherTitle = teacherTitle;
		this.semester = semester;
		this.faculty = faculty;
		this.attendanceRecords = new HashMap<Lesson, HashMap<Student, Boolean>>();
		this.hostedLessons = new Vector<Lesson>();
	}

	/**
	 * Implements getter of Department for Teacher class
	 */

	public String getDepartment() {
		return department;
	}

	/**
	 * Implements setter of Department for Teacher class
	 */

	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * Implements getter of Courses for Teacher class
	 */

	public Vector<Course> getCourses() {
		return courses;
	}

	/**
	 * Add a course to a teacher to teach there
	 */

	public void addCourse(Course course) {
		if (!courses.contains(course)) {
			courses.add(course);
			course.addTeacher(this);
		}
	}

	/**
	 * Implements getter of Teacher Title for Teacher class
	 */

	public TeacherTitle getTeacherTitle() {
		return teacherTitle;
	}

	/**
	 * Implements setter of Teacher Title for Teacher class
	 */

	public void setTeacherTitle(TeacherTitle teacherTitle) {
		this.teacherTitle = teacherTitle;
	}

	/**
	 * Implements getter of Semester for Teacher class
	 */

	public Semester getSemester() {
		return semester;
	}

	/**
	 * Implements setter of Semester for Teacher class
	 */
	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	/**
	 * Implements setter of Faculty for Teacher class
	 */

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public boolean getMarks(Student student, Course course) {
		if (courses.contains(course)) {
			Map<Date, Double> grades = student.getJournal().getGrades(course);
			int firstAttestation = 0;
			int secondAttestation = 0;
			int finalExam = 0;
			for (Map.Entry<Date, Double> grade : grades.entrySet()) {
				Date date = grade.getKey();
				Double value = grade.getValue();
				if (date.before(student.getJournal().getFirstAttestationDate())) {
					firstAttestation += value;
				} else if (date.before(student.getJournal().getSecondAttestationDate())
						&& date.after(student.getJournal().getFirstAttestationDate())) {
					secondAttestation += value;
				} else {
					finalExam += value;
				}
			}
			Mark mark = new Mark(course, firstAttestation, secondAttestation, finalExam);
			student.getMarks().put(course, mark);
			return true;
		}
		return false;
	}

	/**
	 * A teacher can give a grade to a certain student for a certain course
	 */

	public boolean putGrade(Student student, Course course, double grade) {
		if (courses.contains(course)) {
			student.getJournal().addGrade(course, grade);
			return true;
		}
		return false;
	}

	public boolean manageCourseFiles() {
		return false;
	}

	public void viewMarks() {
	}

	public boolean addCourse() {
		return false;
	}

	/**
	 * can remove course
	 */
	public boolean removeCourse(Course course) {
		if (courses.contains(course)) {
			courses.remove(course);
			return true;
		}
		return false;
	}

	public void manageFiles() {
	}

	/**
	 * Can send a text message to employees
	 */

	public boolean sendMessage(Employee employee, String text) {
		return false;
	}

	/**
	 * The lesson that the teacher conducted ( day, format, type of lesson, room )
	 */

	public void hostLesson(Course course, Day day, Format format, LessonType lessonType, int room) {
		Lesson lesson = new Lesson(course, day, format, lessonType, room, this);
		hostedLessons.add(lesson);
	}

	/**
	 * Opens an attendance for a specific lesson
	 */

	public void openAttendance(Lesson lesson) {
		if (!attendanceRecords.containsKey(lesson)) {
			HashMap<Student, Boolean> attendance = new HashMap<>();
			attendanceRecords.put(lesson, attendance);
		}
		lesson.setAttendance(true);
	}

	/**
	 * Closes an attendance for a specific lesson
	 */

	public void closeAttendance(Lesson lesson) {
		lesson.setAttendance(false);
	}

	/**
	 * Maintain the student's presence
	 */

	public void recordAttendance(Lesson lesson, Student student) {
		HashMap<Student, Boolean> attendance = attendanceRecords.get(lesson);
		attendance.put(student, true);
		attendanceRecords.put(lesson, attendance);
	}

	/**
	 * Implements getter of Attendance Record for Teacher class
	 */

	public HashMap<Student, Boolean> getAttendanceRecords(Lesson lesson) {
		return attendanceRecords.get(lesson);
	}

	/**
	 * Implements getter of Faculty for Teacher class
	 */

	public Faculty getFaculty() {
		return faculty;
	}

	public boolean addToDB() {
		return false;
	}

	/**
	 * Can view students
	 */

	public void viewStudents(Course course) {
		if (courses.contains(course)) {
			System.out.println(course.getEnrolled());
		}
	}

	/**
	 * Can view students info
	 */

	public void viewStudentInfo(Student student, Course course) {
		if (this.getCourses().contains(course)) {
			student.viewJournal(course);
		}
	}

}