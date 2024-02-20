package com.school.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class SchoolDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String schoolName;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	@OneToMany(fetch = FetchType.EAGER)
	private List<Staff> schoolStaff;
	@OneToMany
	private List<Classroom> SchoolClassroom;
	private Double schoolExpenses;
	private Double schoolRevenue;
	@Transient
	private static SchoolDetails schoolObject;
	
	private SchoolDetails() {
		
	}
	public int getId() {
		return id;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Staff> getSchoolStaff() {
		return schoolStaff;
	}
	public void setSchoolStaff(List<Staff> schoolStaff) {
		this.schoolStaff = schoolStaff;
	}
	public void setSchoolClassroom(List<Classroom> schoolClassroom) {
		SchoolClassroom = schoolClassroom;
	}
	public List<Classroom> getSchoolClassroom() {
		return SchoolClassroom;
	}
	public Double getSchoolExpenses() {
		return schoolExpenses;
	}
	public void setSchoolExpenses(Double schoolExpenses) {
		this.schoolExpenses = schoolExpenses;
	}
	public Double getSchoolRevenue() {
		return schoolRevenue;
	}
	public void setSchoolRevenue(Double schoolRevenue) {
		this.schoolRevenue = schoolRevenue;
	}
	
	
	public static SchoolDetails objectCreation() {
		if(schoolObject==null)
			schoolObject=new SchoolDetails();
		return schoolObject;
	}
	@Override
	public String toString() {
		return "SchoolDetails [id=" + id + ", schoolName=" + schoolName + ", address=" + address + ", schoolStaff="
				+ schoolStaff + ", SchoolClassroom=" + SchoolClassroom + ", schoolExpenses=" + schoolExpenses
				+ ", schoolRevenue=" + schoolRevenue + "]";
	}

	
	
}
