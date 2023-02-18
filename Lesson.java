package intranet;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Lesson implements Serializable{
	private static final long serialVersionUID = 6338373096898101263L;
	private Course course;
	private Day day;
	private Format format;
	private LessonType lessonType;
	private Date date;
	private int room;
	private boolean attendance;
	private Teacher teacher;

	public Lesson(Course course, Day day, Format format, LessonType lessonType, int room, Teacher teacher) {
		this.course = course;
		this.setDay(day);
		this.setFormat(format);
		this.setLessonType(lessonType);
		this.setDate(new Date());
		this.setRoom(room);
		this.teacher = teacher;
	}
	/**
	 * 
	 * Implements getter of Teacher for Lesson class
	 */
	public Teacher getTeacher() {
		return teacher;
	}
	/**
	 * Attendance or not
	 * 
	 */
	public boolean isAttendance() {
		return attendance;
	}
	/**
	 * 
	 * Implements setter of Attendance for Lesson class
	 */
	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}

	/**
	 * 
	 * Implements getter of Course for Lesson class
	 */
	
	public Course getCourse() {
		return course;
	}
	/**
	 * 
	 * Implements getter of room for Lesson class
	 */
	public int getRoom() {
		return room;
	}
	/**
	 * 
	 * Implements setter of room for Lesson class
	 */
	public void setRoom(int room) {
		this.room = room;
	}
	/**
	 * 
	 * Implements getter of Lesson type for Lesson class
	 */
	public LessonType getLessonType() {
		return lessonType;
	}
	/**
	 * 
	 * Implements setter of Lesson Type for Lesson class
	 */
	public void setLessonType(LessonType lessonType) {
		this.lessonType = lessonType;
	}
	/**
	 * 
	 * Implements getter of Date for Lesson class
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * 
	 * Implements setter of Date for Lesson class
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * 
	 * Implements getter of Format for Lesson class
	 */

	public Format getFormat() {
		return format;
	}
	/**
	 * 
	 * Implements setter of Format for Lesson class
	 */
	public void setFormat(Format format) {
		this.format = format;
	}
	/**
	 * 
	 * Implements getter of Day for Lesson class
	 */
	public Day getDay() {
		return day;
	}
	/**
	 * 
	 * Implements setter of Day for Lesson class
	 */
	public void setDay(Day day) {
		this.day = day;
	}
	/**
	* Returns the primitive value that we gave to the class of Lesson class
	*/

	public String toString() {
		return "Lesson [course=" + course + ", day=" + day + ", format=" + format + ", lessonType=" + lessonType
				+ ", date=" + date + ", room=" + room + ", attendance=" + attendance + "]";
	}
	/**
	* hashCode() implementation by including  salary of the Lesson class
	*/

	public int hashCode() {
		return Objects.hash(attendance, course, date, day, format, lessonType, room);
	}
	/**
	*Implementing equals() for Lesson class
	*/

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		return attendance == other.attendance && Objects.equals(course, other.course)
				&& Objects.equals(date, other.date) && day == other.day && format == other.format
				&& lessonType == other.lessonType && room == other.room;
	}

}
 
