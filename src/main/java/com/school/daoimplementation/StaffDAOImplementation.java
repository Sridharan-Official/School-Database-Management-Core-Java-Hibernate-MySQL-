package com.school.daoimplementation;



import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.school.dao.SchoolExpensesDAO;
import com.school.dao.StaffDAO;
import com.school.dto.Classroom;
import com.school.dto.Staff;

public class StaffDAOImplementation implements StaffDAO {

	private final Configuration cfg=new Configuration()
			.configure().addAnnotatedClass(Staff.class);
	private final SessionFactory sf=cfg.buildSessionFactory();


	public void addStaff(Staff staff) {

		Session session=sf.openSession();
		Transaction t=session.beginTransaction();
		session.save(staff);
		t.commit();
		session.close();
		System.out.println("Staff Was Succefully added......");
		
		SchoolExpensesDAO expensesDAO=new schoolExpensesDAoImplementation();
		expensesDAO.staffsSalaryModification(staff.getStaffSalary());

	}

	public void removeStaff(int id) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, id);
		if(staff!=null) {
			session.remove(staff);
			transaction.commit();
			session.close();
			
			SchoolExpensesDAO expensesDAO=new schoolExpensesDAoImplementation();
			expensesDAO.staffsSalaryModification(-staff.getStaffSalary());
		}
		else {
			System.err.println("Staff id is not in the database");
		}

	}

	public void salaryModification(int sid,Double salary) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, sid);
		
		if(staff==null)
			System.err.println("Staff id is not in the database");
		else {
			
			Double sal=salary-staff.getStaffSalary();
			
			staff.setStaffSalary(salary);
			session.update(staff);
			transaction.commit();
			session.close();
			System.out.println("salray is Modified......");
			
			SchoolExpensesDAO expensesDAO=new schoolExpensesDAoImplementation();
			expensesDAO.staffsSalaryModification(sal);
		}
		
	}

	public void phoneNumberModification(int sid,Long number) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, sid);

		if(staff==null)
			System.err.println("Staff id is not in the database");
		else {
			staff.setStaffPhoneNumber(number);
			session.update(staff);
			transaction.commit();
			System.out.println("Phone Number is Modified......");
		}
		session.close();
	}

	public void emailModification(int sid,String mail) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, sid);

		if(staff==null)
			System.err.println("Staff id is not in the database");
		else {
			staff.setStaffEmail(mail);
			session.update(staff);
			transaction.commit();
			System.out.println("Email is Modified......");
		}
		session.close();
	}

	public void addressModification(int sid,String address) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, sid);

		if(staff==null)
			System.err.println("Staff id is not in the database");
		else {
			staff.setStaffEmail(address);
			session.update(staff);
			transaction.commit();
			System.out.println("Address is Modified......");
		}
		session.close();
	}

	public void addAttendance(int sid,Double attendance) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, sid);

		if(staff==null)
			System.err.println("Staff id is not in the database");
		else {
			attendance=staff.getStaffAttendance()+attendance;
			staff.setStaffAttendance(attendance);
			session.update(staff);
			transaction.commit();
			System.out.println("Attendance Added......");
		}
		session.close();

	}

	public void changeClasses(int sid,List<Classroom> classes) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, sid);

		if(staff==null)
			System.err.println("Staff id is not in the database");
		else {
			staff.setClasses(classes);
			session.update(staff);
			transaction.commit();
			System.out.println("Class was changed......");
		}
		session.close();

	}

	public Staff getStaff(int staffId) {
		Session session=sf.openSession();
		Staff staff=session.find(Staff.class, staffId);
		session.close();
		return staff;
	}
	
}
