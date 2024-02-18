package com.school.dao;

import com.school.dto.Student;

public interface StudentDAO {

	void addStudent(Student student);
	void removeStudent(int sid);
	void modifyStudentAddress(int sid,String Address);
	void modifyStudentEmailId(int sid, String email);
	void modifyStudentPhoneNumber(int sid,Long phoneNumber);
	Student getStudent(int studentId);
}
