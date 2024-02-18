package com.school.daoimplementation;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.school.dao.SchoolDetailDAO;
import com.school.dao.SchoolExpensesDAO;
import com.school.dto.SchoolExpenses;

public class schoolExpensesDAoImplementation implements SchoolExpensesDAO{

	private final Configuration cfg=new Configuration().configure()
			.addAnnotatedClass(SchoolExpenses.class);
	private final SessionFactory factory=cfg.buildSessionFactory();

	public void staffsSalaryModification(Double salary) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();

		Query<Integer> query = session.createQuery("select s.expensesId from SchoolExpenses s", Integer.class);
		int expenseId = query.list().get(0);

		SchoolExpenses expenses=session.find(SchoolExpenses.class, expenseId);
		Double sal=expenses.getStaffSalary()+salary;
		expenses.setStaffSalary(sal);
		session.update(expenses);
		transaction.commit();
		session.close();
		
		SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
		schoolDetailDAO.modifySchoolExpenses(salary);
		
		

	}

	public void modifyStudentFees(Double fees) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Query<Integer> query = session.createQuery("select s.expensesId from SchoolExpenses s", Integer.class);
		int year = query.list().get(0);

		SchoolExpenses expenses=session.find(SchoolExpenses.class, year);
		try {
			Double fee=expenses.getStudentFees()+fees;
			expenses.setStudentFees(fee);
			SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
			schoolDetailDAO.modifySchoolRevenue(expenses.getStudentFees()-expenses.getStudentFeesPending());
		}
		catch(Exception e) {
			
		}
		
		
		session.update(expenses);
		transaction.commit();
		session.close();
	}
	
	public void modifyStudentFeesPending(Double fees) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Query<Integer> query = session.createQuery("select s.expensesId from SchoolExpenses s", Integer.class);
		int expenseId = query.list().get(0);

		SchoolExpenses expenses=session.find(SchoolExpenses.class, expenseId);
		Double sal=expenses.getStudentFeesPending()+fees;
		expenses.setStudentFeesPending(sal);
		session.update(expenses);
		transaction.commit();
		session.close();
		
		SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
		schoolDetailDAO.modifySchoolRevenue(expenses.getStudentFees()-expenses.getStudentFeesPending());

	}

	public void purchaseBillModification(Double purchase) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		Query<Integer> query = session.createQuery("select s.expensesId from SchoolExpenses s", Integer.class);
		int expenseId = query.list().get(0);
		
		SchoolExpenses expenses=session.find(SchoolExpenses.class, expenseId);
		
		expenses.setSchoolPurchaseBill(purchase);
		session.update(expenses);
		transaction.commit();
		session.close();
		
		SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
		schoolDetailDAO.modifySchoolExpenses(purchase);
	}


	public void maintenanceModification(Double maintance) {
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		Query<Integer> query = session.createQuery("select s.expensesId from SchoolExpenses s", Integer.class);
		int expenseId = query.list().get(0);
		
		SchoolExpenses expenses=session.find(SchoolExpenses.class, expenseId);
		Double maintain=expenses.getSchoolMaintenance();
		expenses.setSchoolMaintenance(maintance);
		session.update(expenses);
		transaction.commit();
		session.close();
		
		SchoolDetailDAO schoolDetailDAO=new SchoolDetailDAOImplementation();
		schoolDetailDAO.modifySchoolExpenses(maintance-maintain);
	}

}
