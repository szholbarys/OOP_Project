package intranet;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class Journal implements Serializable {
	private static final long serialVersionUID = 5041126817591070041L;
	Map<Course, Map<Date, Double>> gradeJournal;
	Vector<Course> userCourses;

	public Journal(Vector<Course> courses) {
		this.userCourses = courses;
		gradeJournal = new TreeMap<>();
		for (Course course : courses) {
			gradeJournal.put(course, new TreeMap<>());
		}
	}

	public void addGrade(Course course, double grade) {
		if (userCourses.contains(course)) {
			Map<Date, Double> grades = gradeJournal.get(course);
			grades.put(new Date(), grade);
		}
	}

	public String viewGrades(Course course) {
		if (gradeJournal.containsKey(course)) {
			Map<Date, Double> grades = gradeJournal.get(course);
			String result = "Grades for " + course.getName() + ":\n";
			for (Map.Entry<Date, Double> entry : grades.entrySet()) {
				Date date = entry.getKey();
				Double grade = entry.getValue();
				result += date + ": " + grade + "\n";
			}
			return result;
		}
		return "No such course.";
	}

	public Map<Date, Double> getGrades(Course course) {
		return gradeJournal.get(course);
	}

	public Date getFirstAttestationDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.YEAR, 2022);
		calendar.set(Calendar.DAY_OF_MONTH, 22);
		calendar.add(Calendar.MONTH, 3);
		return calendar.getTime();
	}

	public Date getSecondAttestationDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.YEAR, 2022);
		calendar.set(Calendar.DAY_OF_MONTH, 22);
		calendar.add(Calendar.MONTH, 6);
		return calendar.getTime();
	}
}