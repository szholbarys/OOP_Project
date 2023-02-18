package intranet;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Manager extends Employee implements ViewInfoStudents, ViewTranscript, ViewTeachers, Create, Serializable {
	private static final long serialVersionUID = 3493034322424476566L;
	private ManagerType type;
	private ArrayList<News> news;
	private Vector<Teacher> teachers;
	private Vector<Student> students;

	public Manager(String firstName, String lastName, String id, String username, String password, Sex sex, int age,
			String email, double salary) {
		super(firstName, lastName, id, username, password, sex, age, email, salary);
	}

	public boolean approveStudentReg() {
		return false;
	}

	public boolean assignCoursesTeacher() {
		return false;
	}

	public boolean addCoursesForReg() {
		return false;
	}

	public void manageNews() {
	}

	public boolean addNews(News news) {
		return false;
	}

	public boolean removeNews(News news) {
		return false;
	}

	public boolean updateNews(News news) {
		return false;
	}

	public String generateReport() {
		return null;
	}

	public void assignTeacher() {
	}

	public boolean addOrganization() {
		return false;
	}

	public boolean updateOrganization() {
		return false;
	}

	public boolean removeOrganization() {
		return false;
	}

	public boolean assignOrgLeader() {
		return false;
	}

	public boolean removerOrgLeader() {
		return false;
	}

	public boolean sendMessage() {
		return false;
	}

	public void viewTeacher() {
	}

	public boolean addToDB() {
		return false;

	}

	public void viewStudents() {

	}

	public void viewStudentInfo() {

	}

	public String viewTranscript() {
		return null;
	}

	public Transcript getTranscript() {
		return null;
	}
}