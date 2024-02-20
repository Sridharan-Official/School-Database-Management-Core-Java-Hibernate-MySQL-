package com.school.daoimplementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.school.dao.ClassroomDAO;
import com.school.dao.SchoolExpensesDAO;
import com.school.dao.StudentDAO;
import com.school.dto.Classroom;
import com.school.dto.Fees;
import com.school.dto.SchoolExpenses;
import com.school.dto.Student;

public class StudentDAOImplemention implements StudentDAO{

	private final Configuration cfg=new Configuration()
			.configure().addAnnotatedClass(Student.class).addAnnotatedClass(Fees.class)
			.addAnnotatedClass(Classroom.class).addAnnotatedClass(SchoolExpenses.class);
	private final SessionFactory sf=cfg.buildSessionFactory();

	public void addStudent(Student student) {

		Session session=sf.openSession();
		Transaction t=session.beginTransaction();
		
		session.save(student);
		t.commit();
		session.close();
		System.out.println("Student Was Succefully added......");
		
		SchoolExpensesDAO expensesDAO=new schoolExpensesDAoImplementation();
		expensesDAO.modifyStudentFees(student.getFeesDetails().getFees());

		expensesDAO.modifyStudentFeesPending(student.getFeesDetails().getFees()-student.getFeesDetails().getFeesPaid());
		
	}

	public void removeStudent(int sid) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Student student=session.find(Student.class, sid);
		if(student!=null) {
			Classroom classroom=student.getClassroom();
			List<Student> students=classroom.getStudents();
			students.remove(students.indexOf(student));
			classroom.setStudents(students);
			ClassroomDAO classroomDAO=new ClassroomDAOImplementation();
			classroomDAO.updateClassroom(classroom);
			session.remove(student);
			transaction.commit();
			session.close();
			
			SchoolExpensesDAO expensesDAO=new schoolExpensesDAoImplementation();
			expensesDAO.modifyStudentFees(-student.getFeesDetails().getFees());

			expensesDAO.modifyStudentFeesPending(-(student.getFeesDetails().getFees()-student.getFeesDetails().getFeesPaid()));
			
			
			
		}
		else {
			System.err.println("Student Id is not in the database......");
		}	

	}

	public void modifyStudentAddress(int sid,String Address) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Student student=session.find(Student.class, sid);
		if(student!=null) {
			student.setStudentAddress(Address);
			session.update(student);
			transaction.commit();
		}
		else {
			System.err.println("Student Id is not in the database......");
		}
		session.close();
		
	}

	public void modifyStudentEmailId(int sid, String email) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Student student=session.find(Student.class, sid);
		if(student!=null) {
			student.setEmail(email);
			session.update(student);
			transaction.commit();
		}
		else {
			System.err.println("Student Id is not in the database......");
		}
		session.close();
		
	}

	public void modifyStudentPhoneNumber(int sid,Long phoneNumber) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Student student=session.find(Student.class, sid);
		if(student!=null) {
			student.setPhoneNumber(phoneNumber);
			session.update(student);
			transaction.commit();
		}
		else {
			System.err.println("Student Id is not in the database......");
		}
		session.close();
		
	}

	public Student getStudent(int studentId) {
		Session session=sf.openSession();
		Student student=session.find(Student.class, studentId);
		
		if(student!=null) {
			session.close();
			return student;
		}
		return null;
	}

}
