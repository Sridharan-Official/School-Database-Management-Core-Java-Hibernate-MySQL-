package com.school.dao;

import com.school.dto.Classroom;

public interface ClassroomDAO {
	
	void addClassroom(Classroom classroom);
	void removeClassroom(String classroomId);
	void changeClassTeacher(int staffid, String classroomName);
	Integer Strength(String classroomId);
	Classroom getClassroom(String classroomId);
	void updateClassroom(Classroom classroomName);

}
