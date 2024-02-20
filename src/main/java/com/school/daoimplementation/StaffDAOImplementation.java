package com.school.daoimplementation;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.school.dao.SchoolDetailDAO;
import com.school.dao.SchoolExpensesDAO;
import com.school.dao.StaffDAO;
import com.school.dto.Classroom;
import com.school.dto.SchoolDetails;
import com.school.dto.Staff;

public class StaffDAOImplementation implements StaffDAO {

	private final Configuration cfg=new Configuration()
			.configure().addAnnotatedClass(Staff.class);
	private final SessionFactory sf=cfg.buildSessionFactory();


	public void addStaff(Staff staff) {

		Session session=sf.openSession();
		Transaction t=session.beginTransaction();
		session.save(staff);
		Query<SchoolDetails> query=session.createQuery("from SchoolDetails",SchoolDetails.class);
		SchoolDetails schoolDetail=query.list().get(0);
		t.commit();
		session.close();
		System.out.println("Staff Was Succefully added......");
		
		SchoolExpensesDAO expensesDAO=new schoolExpensesDAoImplementation();
		expensesDAO.staffsSalaryModification(staff.getStaffSalary());
		
		List<Staff> staffs=schoolDetail.getSchoolStaff();
		staffs.add(staff);
		SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
		schoolDetailDAO.modifySchoolStaff(staffs);

	}

	public void removeStaff(int id) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, id);
		if(staff!=null) {
			session.remove(staff);
			Query<SchoolDetails> query=session.createQuery("from SchoolDetails",SchoolDetails.class);
			SchoolDetails schoolDetail=query.list().get(0);
			transaction.commit();
			session.close();
			
			SchoolExpensesDAO expensesDAO=new schoolExpensesDAoImplementation();
			expensesDAO.staffsSalaryModification(-staff.getStaffSalary());
			
			List<Staff> staffs=schoolDetail.getSchoolStaff();
			staffs.remove(staff);
			SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
			schoolDetailDAO.modifySchoolStaff(staffs);
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
			
			Query<SchoolDetails> query=session.createQuery("from SchoolDetails",SchoolDetails.class);
			SchoolDetails schoolDetail=query.list().get(0);
			List<Staff> staffs=schoolDetail.getSchoolStaff();
			int staffIndex=staffs.indexOf(staff);
			
			staff.setStaffSalary(salary);
			session.update(staff);
			transaction.commit();
			
			session.close();
			System.out.println("salray is Modified......");
			
			SchoolExpensesDAO expensesDAO=new schoolExpensesDAoImplementation();
			expensesDAO.staffsSalaryModification(sal);
			
			staffs.set(staffIndex, staff);
			SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
			schoolDetailDAO.modifySchoolStaff(staffs);
		}
		
	}

	public void phoneNumberModification(int sid,Long number) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, sid);

		if(staff==null)
			System.err.println("Staff id is not in the database");
		else {
			Query<SchoolDetails> query=session.createQuery("from SchoolDetails",SchoolDetails.class);
			SchoolDetails schoolDetail=query.list().get(0);
			List<Staff> staffs=schoolDetail.getSchoolStaff();
			int staffIndex=staffs.indexOf(staff);
			
			staff.setStaffPhoneNumber(number);
			session.update(staff);
			transaction.commit();
			session.close();
			System.out.println("Phone Number is Modified......");
			
			staffs.set(staffIndex, staff);
			SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
			schoolDetailDAO.modifySchoolStaff(staffs);
		}
		
	}

	public void emailModification(int sid,String mail) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, sid);

		if(staff==null)
			System.err.println("Staff id is not in the database");
		else {
			Query<SchoolDetails> query=session.createQuery("from SchoolDetails",SchoolDetails.class);
			SchoolDetails schoolDetail=query.list().get(0);
			List<Staff> staffs=schoolDetail.getSchoolStaff();
			int staffIndex=staffs.indexOf(staff);
			
			staff.setStaffEmail(mail);
			session.update(staff);
			transaction.commit();
			session.close();
			System.out.println("Email is Modified......");
			
			staffs.set(staffIndex, staff);
			SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
			schoolDetailDAO.modifySchoolStaff(staffs);
		}
		
	}

	public void addressModification(int sid,String address) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, sid);

		if(staff==null)
			System.err.println("Staff id is not in the database");
		else {
			Query<SchoolDetails> query=session.createQuery("from SchoolDetails",SchoolDetails.class);
			SchoolDetails schoolDetail=query.list().get(0);
			List<Staff> staffs=schoolDetail.getSchoolStaff();
			int staffIndex=staffs.indexOf(staff);
			
			staff.setStaffAddress(address);
			session.update(staff);
			transaction.commit();
			session.close();
			System.out.println("Address is Modified......");
		
			staffs.set(staffIndex, staff);
			SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
			schoolDetailDAO.modifySchoolStaff(staffs);
		}
		
	}

	public void addAttendance(int sid,Double attendance) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, sid);

		if(staff==null)
			System.err.println("Staff id is not in the database");
		else {
			Query<SchoolDetails> query=session.createQuery("from SchoolDetails",SchoolDetails.class);
			SchoolDetails schoolDetail=query.list().get(0);
			List<Staff> staffs=schoolDetail.getSchoolStaff();
			int staffIndex=staffs.indexOf(staff);
			
			attendance=staff.getStaffAttendance()+attendance;
			staff.setStaffAttendance(attendance);
			session.update(staff);
			transaction.commit();
			session.close();
			System.out.println("Attendance Added......");
			
			staffs.set(staffIndex, staff);
			SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
			schoolDetailDAO.modifySchoolStaff(staffs);
		}
		

	}

	public void changeClasses(int sid,List<Classroom> classes) {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		Staff staff=session.find(Staff.class, sid);

		if(staff==null)
			System.err.println("Staff id is not in the database");
		else {
			Query<SchoolDetails> query=session.createQuery("from SchoolDetails",SchoolDetails.class);
			SchoolDetails schoolDetail=query.list().get(0);
			List<Staff> staffs=schoolDetail.getSchoolStaff();
			int staffIndex=staffs.indexOf(staff);
			
			staff.setClasses(classes);
			session.update(staff);
			transaction.commit();
			session.close();
			System.out.println("Class was changed......");
			
			staffs.set(staffIndex, staff);
			SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
			schoolDetailDAO.modifySchoolStaff(staffs);
		}
		

	}

	public Staff getStaff(int staffId) {
		Session session=sf.openSession();
		Staff staff=session.find(Staff.class, staffId);
		session.close();
		return staff;
	}
	
	public void displayStaff(int staffId) {
		Session session=sf.openSession();	
		Staff staff=session.createQuery("select s from Staff s JOIN FETCH s.classes where s.staffId="+staffId,Staff.class).getSingleResult();
		
		System.out.print(staff.toString());
		System.out.println(staff.classReturn());
		session.close();
		
	}
	
}
