package com.school.service;

import com.school.dao.ClassroomDAO;
import com.school.daoimplementation.ClassroomDAOImplementation;
import com.school.dto.Classroom;

public class ClassroomServices {
	
	public void addClassroom(Classroom classroom) {
		ClassroomDAO classroomDAO=new ClassroomDAOImplementation();
		classroomDAO.addClassroom(classroom);
	}
	
	public void removeClassroom(String classroomId) {
		ClassroomDAO classroomDAO=new ClassroomDAOImplementation();
		classroomDAO.removeClassroom(classroomId);
	}
	
	public void changeClassTeacher(int staffId,String classroomName) {
		ClassroomDAO classroomDAO=new ClassroomDAOImplementation();
		classroomDAO.changeClassTeacher(staffId,classroomName);
		
	}
	
	public int strength(String classroomId) {
		ClassroomDAO classroomDAO=new ClassroomDAOImplementation();
		return classroomDAO.Strength(classroomId);
	}
	
	public Classroom getClassroom(String classroomID) {
		ClassroomDAO classroomDAO=new ClassroomDAOImplementation();
		return classroomDAO.getClassroom(classroomID);
		
	}
	
	public void updateClassroom(Classroom classroomName) {
		ClassroomDAO classroomDAO=new ClassroomDAOImplementation();
		classroomDAO.updateClassroom(classroomName);
		
	}

}
