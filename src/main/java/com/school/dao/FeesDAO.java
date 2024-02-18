package com.school.dao;

import com.school.dto.Fees;

public interface FeesDAO {
	Fees addFees(Fees fees);
	void feesPayment(int studentId,Double fees);

}
