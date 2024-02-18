package com.school.dao;

import java.util.List;

import com.school.dto.Classroom;
import com.school.dto.Staff;

public interface StaffDAO {
	
	void addStaff(Staff staff);
	void removeStaff(int sid);
	void salaryModification(int sid,Double salary);
	void phoneNumberModification(int sid,Long number);
	void emailModification(int sid,String mail);
	void addressModification(int sid,String address);
	void addAttendance(int sid,Double addtentance);
	void changeClasses(int sid,List<Classroom> classes);
	Staff getStaff(int staffId);
	
}
