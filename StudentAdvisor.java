package intranet;

import java.util.Vector;

public class StudentAdvisor implements Advisor {
	private final Vector<Student> subordinates;
	private final Advisor advisor;

	public StudentAdvisor(Advisor advisor) {
		this.advisor = advisor;
		this.subordinates = new Vector<>();
	}

	public String giveAdvice(String advise) {
		return ((Student) advisor).getFirstName() + " said: " + advise;
	}

	public void addSubordinate(Student student) {
		if (!subordinates.contains(student)) {
			subordinates.add(student);
		}
	}

	public void removeSubordinate(Student student) {
		if (subordinates.contains(student)) {
			subordinates.remove(student);
		}
	}

	public String viewStudentJournal(Student student, Course course) {
		return student.viewJournal(course);
	}

	public String viewStudentTranscript(Student student) {
		return student.viewTranscript();
	}
}


