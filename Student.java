package intranet;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

public class Student extends User implements ViewTranscript, Create, Advisor, Serializable {
	private static final long serialVersionUID = 3588321203524637715L;
	private double gpa;
	private Faculty faculty;
	private Date enrollmentDate;
	private boolean dormNeed;
	private int year;
	private Semester semester;
	private AcademicDegree academicDegree;
	private Vector<Course> courses;
	private Transcript transcript;
	private Journal journal;
	private HashMap<Course, Mark> marks;
	private HashMap<Course, Vector<Boolean>> attendances;

	public Student(String firstName, String lastName, String id, String username, String password, Sex sex, int age,
			String email, double gpa, Faculty faculty, Date enrollmentDate, boolean dormNeed, int year,
			Semester semester, AcademicDegree academicDegree) {
		super(firstName, lastName, id, username, password, sex, age, email);
		this.gpa = gpa;
		this.faculty = faculty;
		this.enrollmentDate = enrollmentDate;
		this.dormNeed = dormNeed;
		this.year = year;
		this.semester = semester;
		this.academicDegree = academicDegree;
		this.courses = new Vector<Course>();
		this.journal = new Journal(courses);
		this.transcript = new Transcript(this, journal);
		this.attendances = new HashMap<>();
		for (Course course : courses) {
			attendances.put(course, new Vector<>());
		}
	}

	/**
	 * The student can see all his information
	 */

	public String viewStudentInfo() {
		return String.format(
				"First name: %s\nLast name: %s\nID: %s\nUsername: %s\nSex: %s\nAge: %d\nEmail: %s\nFaculty: %s\nEnrollment date: %s\nYear: %d\nSemester: %s\nAcademic degree: %s",
				this.getFirstName(), this.getLastName(), this.getId(), this.getUsername(), this.getSex(), this.getAge(),
				this.getEmail(), this.faculty, this.enrollmentDate, this.year, this.semester, this.academicDegree);
	}

	/**
	 * returns an attachment that takes data from the course
	 */

	public Vector<Boolean> attendedList(Course course) {
		return attendances.get(course);
	}

	/**
	 * when the teacher opens an attachment for a certain course, the student can
	 * check in
	 */

	public boolean attendLesson(Lesson lesson) {
		if (lesson.isAttendance()) {
			Vector<Boolean> attendanceRecord = attendances.get(lesson.getCourse());
			attendanceRecord.add(true);
			attendances.put(lesson.getCourse(), attendanceRecord);
			lesson.getTeacher().recordAttendance(lesson, this);
			return true;
		}
		return false;
	}

	/**
	 * can see which organizations exist
	 */

	public Vector<Organization> viewOrganizations() {
		Vector<Organization> orgs = new Vector<>();
		for (Organization org : Database.organizations) {
			if (org.getMembers().contains(this)) {
				orgs.add(org);
			}
		}
		return orgs;
	}

	/**
	 * To join the organization, to begin with, it is added to the list of
	 * candidates
	 */

	public void joinOrganization(Organization org) {
		org.addCandidate(this);
		Database.updateOrganization(org);
	}

	/**
	 * The student can leave the organization
	 */

	public void leaveOrganization(Organization org) {
		org.leaveOrganization(this);
		Database.updateOrganization(org);
	}

	/**
	 * Implements getter of courses for Student class
	 */

	public Vector<Course> getCourses() {
		return courses;
	}

	/**
	 * If his limit for adding a course does not exceed, then he can add a course
	 * that has a journal and an attendance
	 */
	public boolean addCourse(Course course) {
		if (course.getLimit() > course.getEnrolled().size() && !this.getCourses().contains(course)) {
			this.courses.add(course);
			this.transcript = new Transcript(this, journal);
			this.journal = new Journal(courses);
			this.attendances.put(course, new Vector<Boolean>());
			Database.updateUser(this);
			return true;
		}
		return false;
	}

	/**
	 * Can drop the course and it will be removed from the transcript and journal
	 */

	public boolean dropCourse(Course course) {
		if (this.getCourses().contains(course)) {
			courses.remove(course);
			this.transcript = new Transcript(this, journal);
			this.journal = new Journal(courses);
			Database.updateUser(this);
			return true;
		}
		return false;
	}

	/**
	 * Can view transcript
	 */

	public String viewTranscript() {
		return transcript.viewTranscript();
	}

	/**
	 * Can view Journal
	 */

	public String viewJournal(Course course) {
		return journal.viewGrades(course);
	}

	/**
	 * Implements getter of journal for Student class
	 */

	public Journal getJournal() {
		return journal;
	}

	/**
	 * Implements getter of journal for Student class
	 */

	public Transcript getTranscript() {
		return transcript;
	}

	/**
	 * Implements getter of full name for Student class
	 */

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	public boolean addToDB() {
		return true;
	}

	/**
	 * Returns the primitive value that we gave to the class of Student class
	 */

	public String toString() {
		return "Student [gpa=" + gpa + ", faculty=" + faculty + ", enrollmentDate=" + enrollmentDate + ", dormNeed="
				+ dormNeed + ", year=" + year + ", semester=" + semester + ", academicDegree=" + academicDegree
				+ ", courses=" + courses + ", transcript=" + transcript + ", journal=" + journal + ", marks=" + marks
				+ ", attendances=" + attendances + "]";
	}

	/**
	 * hashCode() implementation by including salary of the Student class
	 */

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(academicDegree, attendances, courses, dormNeed, enrollmentDate, faculty,
				gpa, journal, marks, semester, transcript, year);
		return result;
	}

	/**
	 * Implementing equals() for Student class
	 */

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return academicDegree == other.academicDegree && Objects.equals(attendances, other.attendances)
				&& Objects.equals(courses, other.courses) && dormNeed == other.dormNeed
				&& Objects.equals(enrollmentDate, other.enrollmentDate) && faculty == other.faculty
				&& Double.doubleToLongBits(gpa) == Double.doubleToLongBits(other.gpa)
				&& Objects.equals(journal, other.journal) && Objects.equals(marks, other.marks)
				&& semester == other.semester && Objects.equals(transcript, other.transcript) && year == other.year;
	}

	public HashMap<Course, Mark> getMarks() {
		return marks;
	}

}