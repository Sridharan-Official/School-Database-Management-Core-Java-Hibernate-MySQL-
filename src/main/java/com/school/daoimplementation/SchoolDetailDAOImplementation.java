package com.school.daoimplementation;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.school.dao.SchoolDetailDAO;
import com.school.dto.Classroom;
import com.school.dto.Fees;
import com.school.dto.SchoolDetails;
import com.school.dto.SchoolExpenses;
import com.school.dto.Staff;
import com.school.dto.Student;

public class SchoolDetailDAOImplementation implements SchoolDetailDAO{

	private final Configuration cfg=new Configuration().configure()
			.addAnnotatedClass(SchoolDetails.class).addAnnotatedClass(Classroom.class)
			.addAnnotatedClass(Staff.class).addAnnotatedClass(Student.class).addAnnotatedClass(Fees.class);
	private final SessionFactory factory=cfg.buildSessionFactory();
	
	public void enterSchoolInfo(SchoolDetails schoolDetails) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		session.save(schoolDetails);
		transaction.commit();
		
		transaction.begin();
		SchoolExpenses schoolExpenses=SchoolExpenses.createObject();
		
		Query<Double> query1 =session.createQuery("select sum(staffSalary) from Staff",Double.class);
		Double staffSalary=query1.uniqueResult()!=null?query1.uniqueResult():0.0;
		
		Query<Fees> query2 =session.createQuery("from Fees ",Fees.class);
		List<Fees> studentFees=query2.list();
		
		Double fees=0.0;
		Double feesPaid=0.0;
		
		for(Fees fee : studentFees) {
			fees+=fee.getFees();
			feesPaid+=fee.getFeesPaid();
		}
		schoolExpenses.setStaffSalary(staffSalary);
		schoolExpenses.setSchoolMaintenance(0.0);
		schoolExpenses.setSchoolPurchaseBill(0.0);
		schoolExpenses.setStudentFees(fees);
		schoolExpenses.setStudentFeesPending(fees-feesPaid);
		session.save(schoolExpenses);
		
		transaction.commit();
		
		transaction.begin();
		schoolDetails.setSchoolExpenses(staffSalary);
		schoolDetails.setSchoolRevenue(feesPaid-staffSalary);
		transaction.commit();
		session.close();
		System.out.println("School Information have been Entered........");
	}
	
	public void modifySchoolExpenses(Double amount) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Query<Integer> query=session.createQuery("select s.id from SchoolDetails s",Integer.class);
		int id=query.list().get(0);
		
		SchoolDetails details=session.find(SchoolDetails.class, id);
		
		Double amt=details.getSchoolExpenses()+amount;
		
		details.setSchoolExpenses(amt);
		session.update(details);
		transaction.commit();
		session.close();
		
	}
	
	public void modifySchoolRevenue(Double amount) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();

		Query<Integer> query=session.createQuery("select s.id from SchoolDetails s",Integer.class);
		int id=query.list().get(0);

		SchoolDetails details=session.find(SchoolDetails.class, id);
		details.setSchoolRevenue(amount);
		session.update(details);
		transaction.commit();
		session.close();
		
	}
	
	public void modifySchoolClassroom(List<Classroom> classrooms) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Query<Integer> query=session.createQuery("select s.id from SchoolDetails s",Integer.class);
		int id=query.list().get(0);
		
		SchoolDetails details=session.find(SchoolDetails.class, id);

		details.setSchoolClassroom(classrooms);
		session.update(details);
		transaction.commit();
		session.close();
		
	}
	
	public void modifySchoolStaff(List<Staff> staffs) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Query<Integer> query=session.createQuery("select s.id from SchoolDetails s",Integer.class);
		int id=query.list().get(0);
		
		SchoolDetails details=session.find(SchoolDetails.class, id);

		details.setSchoolStaff(staffs);
		session.update(details);
		transaction.commit();
		session.close();
		
	}

}
