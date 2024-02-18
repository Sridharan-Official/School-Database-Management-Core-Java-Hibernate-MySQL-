package com.school.dto;

import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SchoolExpenses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expensesId;
	private Double staffSalary;
	private Double studentFees;
	private Double studentFeesPending;
	private Double schoolMaintenance;
	private Double schoolPurchaseBill;
	@Transient
	private static SchoolExpenses schoolExpensesObject;
	
	public int getExpensesId() {
		return expensesId;
	}
	public void setExpensesId(int expensesId) {
		this.expensesId = expensesId;
	}
	public Double getStaffSalary() {
		return staffSalary;
	}
	public void setStaffSalary(Double staffSalary) {
		this.staffSalary = staffSalary;
	}
	public Double getStudentFees() {
		return studentFees;
	}
	public void setStudentFees(Double studentFees) {
		this.studentFees = studentFees;
	}
	public Double getStudentFeesPending() {
		return studentFeesPending;
	}
	public void setStudentFeesPending(Double studentFeesPending) {
		this.studentFeesPending = studentFeesPending;
	}
	public Double getSchoolMaintenance() {
		return schoolMaintenance;
	}
	public void setSchoolMaintenance(Double schoolMaintenance) {
		this.schoolMaintenance = schoolMaintenance;
	}
	public Double getSchoolPurchaseBill() {
		return schoolPurchaseBill;
	}
	public void setSchoolPurchaseBill(Double schoolPurchaseBill) {
		this.schoolPurchaseBill = schoolPurchaseBill;
	}
	
	public static SchoolExpenses createObject() {
		if(schoolExpensesObject==null)
			schoolExpensesObject=new SchoolExpenses();
		return schoolExpensesObject;
	}
	@Override
	public String toString() {
		return "SchoolExpenses [expensesId=" + expensesId + ", staffSalary=" + staffSalary + ", studentFees="
				+ studentFees + ", studentFeesPending=" + studentFeesPending + ", schoolMaintenance="
				+ schoolMaintenance + ", schoolPurchaseBill=" + schoolPurchaseBill + "]";
	}
	
	

}
