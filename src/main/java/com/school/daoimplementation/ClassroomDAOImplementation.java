package com.school.daoimplementation;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.school.dao.ClassroomDAO;
import com.school.dao.SchoolDetailDAO;
import com.school.dao.StaffDAO;
import com.school.dto.Classroom;
import com.school.dto.Staff;
import com.school.dto.Student;

public class ClassroomDAOImplementation implements ClassroomDAO{

	private final Configuration cfg=new Configuration().configure()
			.addAnnotatedClass(Classroom.class).addAnnotatedClass(Student.class).addAnnotatedClass(Staff.class);
	private final SessionFactory factory=cfg.buildSessionFactory();

	public void addClassroom(Classroom classroom) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(classroom);
            transaction.commit();
            System.out.println("Classroom was Successfully Added.........");
            
            Query<Classroom> query=session.createQuery("from Classroom",Classroom.class);
    		List<Classroom> classrooms=query.list();
            
            SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
            schoolDetailDAO.modifySchoolClassroom(classrooms);
        } catch (Exception e) {
            System.err.println("Failed to add classroom: " + e.getMessage());
        }
    }

	public void removeClassroom(String classroomId) {
    try (Session session = factory.openSession()) {
        Classroom classroom = session.find(Classroom.class, classroomId);
        if (classroom == null) {
            System.err.println("Classroom Not in the Database..........");
            return;
        }

        List<Student> students = classroom.getStudents();
        if (students.isEmpty()) {
            Transaction transaction = session.beginTransaction();

            Query<Classroom> query = session.createQuery("from Classroom", Classroom.class);
            List<Classroom> classrooms = query.getResultList();
            classrooms.remove(classroom);
            SchoolDetailDAO schoolDetailDAO = new SchoolDetailDAOImplementation();
            schoolDetailDAO.modifySchoolClassroom(classrooms);

            Query<Staff> query1 = session.createQuery("from Staff", Staff.class);
            List<Staff> staffs = query1.list();

            StaffDAO staffDAO = new StaffDAOImplementation();
            for (Staff staff : staffs) {
                if (staff.getClasses() != null && staff.getClasses().contains(classroom)) {
                    staff.getClasses().remove(classroom);
//                    session.update(staff); // Update the staff object to remove the classroom association
                    staffDAO.changeClasses(staff.getStaffId(), staff.getClasses());
                }
            }

            session.remove(classroom);
            transaction.commit();
            System.out.println("Classroom was Successfully removed.....");
        } else {
            System.err.println("First change the Students classroom...........");
        }
    }/* catch (Exception e) {
        System.err.println("Failed to remove classroom: " + e.getMessage());
    }*/
}

    public void changeClassTeacher(int staffId, String classroomName) {
        try (Session session = factory.openSession()) {
            Staff staff = session.find(Staff.class, staffId);
            Classroom classroom = session.find(Classroom.class, classroomName);
            if (staff == null) {
                System.err.println("Staff Id was not in the database.......");
                return;
            }
            if (classroom == null) {
                System.err.println("Classroom Name is not in the database........");
                return;
            }
            Transaction transaction = session.beginTransaction();
            classroom.setClassTeacher(staff);
            session.update(classroom);
            transaction.commit();
            System.out.println("Class Teacher Was Changed............,,");
            
            Query<Classroom> query=session.createQuery("from Classroom",Classroom.class);
    		List<Classroom> classrooms=query.list();
            
            SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
            schoolDetailDAO.modifySchoolClassroom(classrooms);
        } catch (Exception e) {
            System.err.println("Failed to change class teacher: " + e.getMessage());
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
		
		try (Session session=factory.openSession()){
			Query<Classroom> query=session.createQuery("from Classroom",Classroom.class);
			List<Classroom> classrooms=query.list();
			Transaction transaction=session.beginTransaction();
			session.merge(classroomName);
			transaction.commit();

			SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
			schoolDetailDAO.modifySchoolClassroom(classrooms);
		}
		catch(Exception e) {
			System.err.println("Failed to Update Classroom : " + e.getMessage());
		}
//		Session session=factory.openSession();
//		Query<Classroom> query=session.createQuery("from Classroom",Classroom.class);
//		List<Classroom> classrooms=query.list();
//		Transaction transaction=session.beginTransaction();
//		session.merge(classroomName);
//		transaction.commit();
//
//
//		session.close();
//
//		SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
//		schoolDetailDAO.modifySchoolClassroom(classrooms);

	}

}
