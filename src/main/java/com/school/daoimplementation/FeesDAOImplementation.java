package com.school.daoimplementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.school.dao.FeesDAO;
import com.school.dao.SchoolExpensesDAO;
import com.school.dto.Fees;
import com.school.dto.Student;

public class FeesDAOImplementation implements FeesDAO{
	
	private final Configuration cfg=new Configuration()
			.configure().addAnnotatedClass(Fees.class).addAnnotatedClass(Student.class);
	private final SessionFactory sf=cfg.buildSessionFactory();
	
	public Fees addFees(Fees fees) {
		Session session=sf.openSession();
		Transaction t=session.beginTransaction();
		session.save(fees);
		t.commit();
		session.close();
		System.out.println("Fees Was Succefully added......");
		return fees;
	}

	public void feesPayment(int studentId,Double feesPaid) {
		Configuration cfg=new Configuration().configure().addAnnotatedClass(Fees.class).addAnnotatedClass(Student.class);
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Student student=session.find(Student.class, studentId);
		Fees fee=student.getFeesDetails();
		
		Double diff=fee.getFeesPaid()+feesPaid;
		
		if(diff<=fee.getFees()) {
			fee.setFeesPaid(diff);
			Transaction t=session.beginTransaction();
			session.update(fee);
			t.commit();
			System.out.println("Fees Was Updated......");
			
			SchoolExpensesDAO schoolExpensesDAO=new schoolExpensesDAoImplementation();
			schoolExpensesDAO.modifyStudentFeesPending(-feesPaid);
			
		}
		else {
			System.err.println("Amount you Trying to pay was Invalid");
		}	
		session.close();
	
	}



}
