package intranet;

import java.io.Serializable;
import java.util.*;


public class ReseacherDec implements Researcher, Serializable {	
	private static final long serialVersionUID = 1L; 
	private HashMap<Student, Enum> students;
	private HashMap<Teacher, Enum> teachers;
	public ResearchPaper publish;
	

	public boolean doReserch(Student student, Teacher teacher){
	if(((Map<Teacher, Enum>) teacher).containsValue(TeacherTitle.PROFESSOR) && ((Map<Student, Enum>) student).containsValue(AcademicDegree.PHD)){
		if(students.containsKey(student) && teachers.containsKey(teacher)) 
			return true;
		return true;
		}else
			return false;
	   }

	public String publishResearch(){
		return publish.getName() + " , " + publish.getContent();
	}

 
	public int hashCode() {
		return Objects.hash(publish, students, teachers);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReseacherDec other = (ReseacherDec) obj;
		return Objects.equals(publish, other.publish) && Objects.equals(students, other.students)
				&& Objects.equals(teachers, other.teachers);
	}
 

}
