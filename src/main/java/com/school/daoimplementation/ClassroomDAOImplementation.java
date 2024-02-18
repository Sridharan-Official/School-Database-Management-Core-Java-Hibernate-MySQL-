package com.school.daoimplementation;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.school.dao.ClassroomDAO;
import com.school.dao.SchoolDetailDAO;
import com.school.dto.Classroom;
import com.school.dto.Staff;
import com.school.dto.Student;

public class ClassroomDAOImplementation implements ClassroomDAO{

	private final Configuration cfg=new Configuration().configure()
			.addAnnotatedClass(Classroom.class).addAnnotatedClass(Student.class).addAnnotatedClass(Staff.class);
	private final SessionFactory factory=cfg.buildSessionFactory();

	public void addClassroom(Classroom classroom) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();

		session.save(classroom);
		transaction.commit();

		Query<Classroom> query=session.createQuery("from Classroom",Classroom.class);
		List<Classroom> classrooms=query.list();
		session.close();
		System.out.println("Classroom was Successfully Added.........");

		SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
		schoolDetailDAO.modifySchoolClassroom(classrooms);

	}

	public void removeClassroom(String classroomId) {
		Session session=factory.openSession();

		Classroom classroom=session.find(Classroom.class, classroomId);
		if(classroom==null) {
			System.err.println("Classroom Not in the Database..........");
		}
		else {
			Transaction transaction=session.beginTransaction();
			session.remove(classroom);
			transaction.commit();
			Query<Classroom> query=session.createQuery("from Classroom",Classroom.class);
			List<Classroom> classrooms=query.list();
			session.close();
			System.out.println("Classroom was Succesfully removed.....");

			SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
			schoolDetailDAO.modifySchoolClassroom(classrooms);
		}

	}

	public void changeClassTeacher(int staffId, String classroomName) {
		Session session=factory.openSession();
		Staff staff=session.find(Staff.class, staffId);
		Classroom classroom=session.find(Classroom.class, classroomName);
		if(staff==null) {
			System.err.println("Staff Id was not in the database.......");
		}
		else {
			if(classroom==null) {
				System.err.println("Classroom Name is not in the database........");
			}
			else {
				Transaction transaction=session.beginTransaction();
				classroom.setClassTeacher(staff);
				session.update(classroom);
				transaction.commit();
				
				Query<Classroom> query=session.createQuery("from Classroom",Classroom.class);
				List<Classroom> classrooms=query.list();
				session.close();
				System.out.println("Class Teacher Was Changed............,,");
				
				
				SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
				schoolDetailDAO.modifySchoolClassroom(classrooms);
			}
		}

	}

	public Integer Strength(String classroomId) {

		Session session=factory.openSession();
		Classroom classroom=session.find(Classroom.class, classroomId);
		if(classroom==null) {
			System.err.println("Classroom Was Not in The Database.........");
			return -1;
		}
		else {
			return classroom.getStudents().size();
		}
	}


	public Classroom getClassroom(String classroomId) {
		Session session=factory.openSession();
		Classroom classroom=session.find(Classroom.class, classroomId);
		session.close();
		return classroom;

	}

	public void updateClassroom(Classroom classroomName) {
		Session session=factory.openSession();
		Query<Classroom> query=session.createQuery("from Classroom",Classroom.class);
		List<Classroom> classrooms=query.list();
		Transaction transaction=session.beginTransaction();
		session.merge(classroomName);
		transaction.commit();
		
		
		session.close();
		
		SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
		schoolDetailDAO.modifySchoolClassroom(classrooms);
		
	}

}
