package com.school.service;

import com.school.dao.FeesDAO;
import com.school.dao.StudentDAO;
import com.school.daoimplementation.FeesDAOImplementation;
import com.school.daoimplementation.StudentDAOImplemention;
import com.school.dto.Student;

public class StudentService {
	
	public void addStudent(Student student) {
		StudentDAO studentDAO=new StudentDAOImplemention();
		studentDAO.addStudent(student);
	}
	
	public void removeStudent(int studentId) {
		StudentDAO studentDAO=new StudentDAOImplemention();
		studentDAO.removeStudent(studentId);
	}
	
	public void feesPayment(int fid,Double feesPaid) {
		FeesDAO feesDAO=new FeesDAOImplementation();
		feesDAO.feesPayment(fid, feesPaid);
	}
	
	public void modifyStudentAddress(int sid,String Address) {
		StudentDAO studentDAO=new StudentDAOImplemention();
		studentDAO.modifyStudentAddress(sid, Address);
	}
	
	public void modifyStudentEmailId(int sid, String email) {
		StudentDAO studentDAO=new StudentDAOImplemention();
		studentDAO.modifyStudentEmailId(sid, email);
	}
	
	public void modfiyStudentPhoneNumber(int sid,Long phoneNumber) {
		StudentDAO studentDAO=new StudentDAOImplemention();
		studentDAO.modifyStudentPhoneNumber(sid, phoneNumber);
	}
	
	public Student getStudent(int studentId) {
		StudentDAO studentDAO=new StudentDAOImplemention();
		return studentDAO.getStudent(studentId);
	}


}
