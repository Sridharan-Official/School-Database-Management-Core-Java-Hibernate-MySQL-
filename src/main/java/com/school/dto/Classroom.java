package com.school.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Classroom {

	@Id
	private String classroomName;
	@OneToOne
	private Staff classTeacher;
	@OneToMany(fetch = FetchType.EAGER)
	List<Student> students;
	
	public String getClassroomName() {
		return classroomName;
	}
	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}
	public Staff getClassTeacher() {
		return classTeacher;
	}
	public void setClassTeacher(Staff classTeacher) {
		this.classTeacher = classTeacher;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	@Override
	public String toString() {
		return "Classroom [classroomName=" + classroomName + ", classTeacher=" + classTeacher + ", students=" + students
				+ "]";
	}
	
	
}
