package com.school.service;

import com.school.dao.SchoolExpensesDAO;
import com.school.daoimplementation.schoolExpensesDAoImplementation;

public class SchoolExpensesServices {

	public void modifySchoolMaintance(Double maintance) {
		SchoolExpensesDAO expensesDAO=new schoolExpensesDAoImplementation();
		expensesDAO.maintenanceModification(maintance);
		
	}
	
	public void purchaseBillModification(Double purchase) {
		SchoolExpensesDAO expensesDAO=new schoolExpensesDAoImplementation();
		expensesDAO.maintenanceModification(purchase);
		
	}
	
}
