package com.school.dao;

import java.util.List;

import com.school.dto.Classroom;
import com.school.dto.SchoolDetails;
import com.school.dto.Staff;

public interface SchoolDetailDAO {
	
	void enterSchoolInfo(SchoolDetails schoolDetails);
	void modifySchoolExpenses(Double amount);
	void modifySchoolRevenue(Double amount);
	void modifySchoolClassroom(List<Classroom> classrooms);
	void modifySchoolStaff(List<Staff> staffs);

}
