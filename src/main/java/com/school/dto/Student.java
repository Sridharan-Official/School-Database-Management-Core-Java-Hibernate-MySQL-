package com.school.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;
	private String studentName;
	private String studentGender;
	private Integer studentAge;
	private String studentAddress;
	@Column(unique = true,nullable = false)
	private String email;
	@ManyToOne
	private Classroom classroom;
	private Long phoneNumber;
	private Parent parentDetails;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fees_id")
	private Fees feesDetails;

	
	public int getStudentId() {
		return studentId;
	}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentGender() {
		return studentGender;
	}
	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	public Integer getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Fees getFeesDetails() {
		return feesDetails;
	}
	public void setFeesDetails(Fees feesDetails) {
		this.feesDetails = feesDetails;
	}
	public Parent getParentDetails() {
		return parentDetails;
	}
	public void setParentDetails(Parent parentDetails) {
		this.parentDetails = parentDetails;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentGender=" + studentGender
				+ ", studentAge=" + studentAge + ", studentAddress=" + studentAddress + ", email=" + email
				+ ", classroom=" + classroom + ", phoneNumber=" + phoneNumber + ", parentDetails=" + parentDetails
				+ ", feesDetails=" + feesDetails + "]";
	}

	
}