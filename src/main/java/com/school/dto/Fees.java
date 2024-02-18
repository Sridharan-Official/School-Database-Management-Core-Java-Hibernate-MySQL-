package com.school.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feesId;
	private Double fees;
	private Double feesPaid;
	
	public int getFeesId() {
		return feesId;
	}
	
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
	public Double getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(Double feesPaid) {
		this.feesPaid = feesPaid;
	}

	@Override
	public String toString() {
		return "Fees [feesId=" + feesId + ", fees=" + fees + ", feesPaid=" + feesPaid + "]";
	}



}
