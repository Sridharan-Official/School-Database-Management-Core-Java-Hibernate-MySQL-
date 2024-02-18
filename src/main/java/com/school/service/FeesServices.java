package com.school.service;

import com.school.dao.FeesDAO;
import com.school.daoimplementation.FeesDAOImplementation;
import com.school.dto.Fees;

public class FeesServices {

	public Fees addFees(Fees fees) {
		FeesDAO feesDAO=new FeesDAOImplementation();
		return feesDAO.addFees(fees);
		
	}
	
	public void feesPayment(int fid,Double feesPaid) {
		FeesDAO feesDAO=new FeesDAOImplementation();
		feesDAO.feesPayment(fid,feesPaid);
		
	}
	
}
