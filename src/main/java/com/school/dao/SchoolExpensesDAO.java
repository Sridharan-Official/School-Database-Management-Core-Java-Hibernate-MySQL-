package com.school.dao;

public interface SchoolExpensesDAO {
	
	void modifyStudentFees(Double fees);
	void modifyStudentFeesPending(Double fees);
	void staffsSalaryModification(Double salary);
	void maintenanceModification(Double maintance);
	void purchaseBillModification(Double purchase);

}
