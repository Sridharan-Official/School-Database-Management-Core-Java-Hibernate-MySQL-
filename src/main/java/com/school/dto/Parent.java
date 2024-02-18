package com.school.dto;

import javax.persistence.Embeddable;

@Embeddable
public class Parent {

	private String fatherName;
	private Integer fatherAge;
	private String fatherOccupation;
	private String motherName;
	private Integer motherAge;
	private String motherOccupation;
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public Integer getFatherAge() {
		return fatherAge;
	}
	public void setFatherAge(Integer fatherAge) {
		this.fatherAge = fatherAge;
	}
	public String getFatherOccupation() {
		return fatherOccupation;
	}
	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public Integer getMotherAge() {
		return motherAge;
	}
	public void setMotherAge(Integer motherAge) {
		this.motherAge = motherAge;
	}
	public String getMotherOccupation() {
		return motherOccupation;
	}
	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}
	@Override
	public String toString() {
		return "Parent [fatherName=" + fatherName + ", fatherAge=" + fatherAge + ", fatherOccupation="
				+ fatherOccupation + ", motherName=" + motherName + ", motherAge=" + motherAge + ", motherOccupation="
				+ motherOccupation + "]";
	}
	
	
	
}
