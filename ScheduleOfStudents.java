package intranet;

import java.util.*;
import java.util.Date;

public class ScheduleOfStudents {
	private Student id;
	private Student firstName;
	private Student lastName;
	private Faculty faculty;
	private int year;
	private Semester semester;
	private Teacher teacherName;
	private Vector<Course> course;
	private Date time;
	private Enum RoomType;
	
	public ScheduleOfStudents() {
		
	}
	public ScheduleOfStudents(Student id, Student firstName, Student lastName,Faculty faculty, int year,
			Semester semester, Date time, Teacher teacherName, Vector<Course> course, Enum RoomType) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.faculty = faculty;
		this.year = year;
		this.semester = semester;
		this.teacherName = teacherName;
		this.course = new Vector<Course>();
		this.time = time;
		this.RoomType = RoomType;
	}
	
	public boolean availableCourses(Vector<Course> course) {
		if(course.contains(Faculty.class)) {
			return true;
		}else {
			return false;
		}
	}

}
