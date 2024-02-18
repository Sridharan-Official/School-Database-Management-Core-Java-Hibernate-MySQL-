package com.school.dto;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int staffId;
	@Column(nullable = false)
	private String staffName;
	private String staffAddress;
	@Column(nullable = false,length = 10)
	private long staffPhoneNumber;
	@Column(unique = true)
	private String staffEmail;
	@Column(nullable = false)
	private String subject;
	@Column(nullable = false)
	private Double staffSalary;
	@Column(nullable = false)
	private Double staffAttendance;
	@ManyToMany
	private List<Classroom> classes;

	public int getStaffId() {
		return staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffAddress() {
		return staffAddress;
	}
	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}
	public String getStaffEmail() {
		return staffEmail;
	}
	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Double getStaffSalary() {
		return staffSalary;
	}
	public void setStaffSalary(Double staffSalary) {
		this.staffSalary = staffSalary;
	}
	public Double getStaffAttendance() {
		return staffAttendance;
	}
	public void setStaffAttendance(Double staffAttendance) {
		this.staffAttendance = staffAttendance;
	}
	public List<Classroom> getClasses() {
		return classes;
	}
	public void setClasses(List<Classroom> classes) {
		this.classes = classes;
	}
	public long getStaffPhoneNumber() {
		return staffPhoneNumber;
	}
	public void setStaffPhoneNumber(long staffPhoneNumber) {
		this.staffPhoneNumber = staffPhoneNumber;
	}
	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffName=" + staffName + ", staffAddress=" + staffAddress
				+ ", staffPhoneNumber=" + staffPhoneNumber + ", staffEmail=" + staffEmail + ", subject=" + subject
				+ ", staffSalary=" + staffSalary + ", staffAttendance=" + staffAttendance + ", classes=" + classes
				+ "]";
	}

	
}
