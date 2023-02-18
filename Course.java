package intranet;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;

public class Course implements Comparable<Course>, Serializable {
	private static final long serialVersionUID = -9078255111996827057L;
	private String name;
	private int credits;
	private String description;
	private String courseId;
	private Vector<Course> prerequisite;
	private boolean isElective;
	private Semester semester;
	private Faculty faculty;
	private int maxNumberOfStudents;

	public Course(String name, int credits, String description, String courseId, Vector<Course> prerequisite,
			boolean isElective, Semester semester, Faculty faculty, int maxNumberOfStudents) {
		this.name = name;
		this.credits = credits;
		this.setDescription(description);
		this.setCourseId(courseId);
		this.prerequisite = prerequisite;
		this.semester = semester;
		this.isElective = isElective;
		this.faculty = faculty;
		this.maxNumberOfStudents = maxNumberOfStudents;
	}

	public boolean addStudent(Student student) {
		if (!student.getCourses().contains(this)) {
			for (Course course : prerequisite) {
				if (!student.getCourses().contains(course)) {
					return false;
				}
			}
			student.addCourse(this);
			return true;
		}
		return false;
	}/*
		 * 
		 * Implements getter of Limit for Course class
		 */

	public int getLimit() {
		return maxNumberOfStudents;
	}/*
		 * 
		 * Implements getter of Enrolled for Course class
		 */

	public Vector<Student> getEnrolled() {
		return Database.users.stream().filter(user -> user instanceof Student).map(user -> (Student) user)
				.filter(student -> student.getCourses().contains(this)).collect(Collectors.toCollection(Vector::new));
	}/*
		 * 
		 * Implements getter of name for Course class
		 */

	public String getName() {
		return name;
	}

	public boolean removeStudent(Student student) {
		return student.dropCourse(this);
	}/*
		 * 
		 * Implements getter of credits for Course class
		 */

	public int getCredits() {
		return credits;
	}/*
		 * 
		 * Implements setter of Limit for Course class
		 */

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public boolean isElective() {
		return isElective;
	}

	/*
	 * 
	 * Implements getter of Description for Course class
	 */

	public String getDescription() {
		return description;
	}/*
		 * 
		 * Implements setter of Description for Course class
		 */

	public void setDescription(String description) {
		this.description = description;
	}/*
		 * 
		 * Implements getter of course id for Course class
		 */

	public String getCourseId() {
		return courseId;
	}/*
		 * 
		 * Implements setter of course id for Course class
		 */

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}/*
		 * 
		 * Implements getter of mark for Course class
		 */

	public HashMap<Date, Mark> getMark(Student student) {
		return null;
	}

	public void addMark(Student student, Mark mark) {
	}

	public void removeMark(Student student, Date date) {
	}

	public void addTeacher(Teacher teacher) {
		teacher.addCourse(this);
	}

	public void removeTeacher(Teacher teacher) {
		teacher.removeCourse(this);
	}

	public Vector<Teacher> getTeachers() {
		return Database.users.stream().filter(u -> u instanceof Teacher).map(u -> (Teacher) u)
				.filter(t -> t.getCourses().contains(this)).collect(Collectors.toCollection(Vector::new));
	}/*
		 * 
		 * Returns the primitive value that we gave to the
		 * 
		 * class of Course class
		 */

	public String toString() {
		return "Course [name=" + name + ", credits=" + credits + ", description=" + description + ", courseId="
				+ courseId + ", prerequisite=" + prerequisite + ", isElective=" + isElective + ", semester=" + semester
				+ ", faculty=" + faculty + ", maxNumberOfStudents=" + maxNumberOfStudents + "]";
	}

	/*
	 * 
	 * hashCode() implementation by including salary of the Course class
	 */
	public int hashCode() {
		return Objects.hash(courseId, credits, description, faculty, isElective, maxNumberOfStudents, name,
				prerequisite, semester);
	}/*
		 * Implementing equals() for Course class
		 */

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseId, other.courseId) && credits == other.credits
				&& Objects.equals(description, other.description) && faculty == other.faculty
				&& isElective == other.isElective && maxNumberOfStudents == other.maxNumberOfStudents
				&& Objects.equals(name, other.name) && Objects.equals(prerequisite, other.prerequisite)
				&& semester == other.semester;
	}/*
		 * Implementing compareTo() for using comparator for Course class
		 */

	public int compareTo(Course o) {
		Course other = (Course) o;
		if (this.name.equals(other.name)) {
			return this.semester.compareTo(other.semester);
		} else {
			return this.name.compareTo(other.name);
		}
	}

}