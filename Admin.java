package intranet;


import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class Admin extends Employee implements Create, Serializable {
	private static final long serialVersionUID = -7996409021489032879L;

	public Admin(String firstName, String lastName, String id, String username, String password, Sex sex, int age,
			String email, double salary) {
		super(firstName, lastName, id, username, password, sex, age, email, salary);
	}

	public void viewLogs() {
	}

	public User createUser(String firstName, String lastName, String id, String username, String password, Sex sex,
			int age, String email, String userType) {
		User newUser = UserFactory.getUser(firstName, lastName, id, username, password, sex, age, email, userType);
		Database.users.add(newUser);
		return newUser;
	}

	public void createStudent(String firstName, String lastName, String id, String username, String password, Sex sex,
			int age, String email, double gpa, Faculty faculty, Date enrollmentDate, boolean dormNeed, int year,
			Semester semester, AcademicDegree academicDegree) {
		Student newStudent = new Student(firstName, lastName, id, username, password, sex, age, email, gpa, faculty,
				enrollmentDate, dormNeed, year, semester, academicDegree);
		Database.users.add(newStudent);
	}

	public void createTeacher(String firstName, String lastName, String id, String username, String password, Sex sex,
			int age, String email, double salary, String department, Vector<Course> courses, TeacherTitle teacherTitle,
			Semester semester, Faculty faculty) {
		Teacher newTeacher = new Teacher(firstName, lastName, id, username, password, sex, age, email, salary,
				department, courses, teacherTitle, semester, faculty);
		Database.users.add(newTeacher);
	}

	public boolean updateUser(User user, String firstName, String lastName, String id, String username, String password,
			Sex sex, int age, String email) {
		for (int i = 0; i < Database.users.size(); i++) {
			if (Database.users.get(i).getId().equals(user.getId())) {
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setSex(sex);
				user.setAge(age);
				user.setEmail(email);
				Database.users.set(i, user);
				return true;
			}
		}
		return false;
	}

	public boolean removeUser(User user) {
		for (int i = 0; i < Database.users.size(); i++) {
			if (Database.users.get(i).getId().equals(user.getId())) {
				Database.users.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean sendMessage() {
		return false;
	}

	public boolean addToDB() {
		return false;
	}

}
 
