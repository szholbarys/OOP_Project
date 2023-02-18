package intranet;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Transcript implements Serializable {
	private static final long serialVersionUID = 1692018647946859481L;
	private Journal journal;
	private HashMap<Course, Double> transcript;

	public Transcript(Student student, Journal journal) {
		this.journal = journal;
		this.transcript = new HashMap<>();
		if (student.getCourses() == null) {
			return;
		}

		for (Course course : student.getCourses()) {
			Map<Date, Double> grades = journal.getGrades(course);
			if (grades == null || grades.isEmpty()) {
				continue;
			}
			transcript.put(course, gpaForCourse(course));
		}
	}

	public double gpaForCourse(Course course) {
		Map<Date, Double> grades = journal.getGrades(course);
		double total = 0;
		for (double grade : grades.values()) {
			total += grade;
		}
		return total / grades.size();
	}

	public double totalGpa() {
		double total = 0;
		if (transcript.isEmpty()) {
			return 0;
		}
		for (double gpa : transcript.values()) {
			total += gpa;
		}
		return total / transcript.size();
	}

	public String viewTranscript() {
		String output = "";
		output += "Total GPA: " + this.totalGpa() + "\n";
		for (Map.Entry<Course, Double> trans : transcript.entrySet()) {
			output += "Course: " + trans.getKey() + "GPA " + trans.getValue() + "\n";
		}
		return output;
	}

	public HashMap<Course, Double> getTranscript() {
		return transcript;
	}
}