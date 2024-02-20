package com.school.service;


import java.util.List;

import com.school.dao.StaffDAO;
import com.school.daoimplementation.StaffDAOImplementation;
import com.school.dto.Classroom;
import com.school.dto.Staff;

public class StaffServices {

	public void addStaff(Staff s) {

		StaffDAO staffDAO=new StaffDAOImplementation();
		staffDAO.addStaff(s);

	}

	public void removeStaff(int id) {
		StaffDAO staffDAO=new StaffDAOImplementation();
		staffDAO.removeStaff(id);
	}

	public void salaryModification(int sid, Double sal) {
		StaffDAO staffDAO=new StaffDAOImplementation();
		staffDAO.salaryModification(sid, sal);
	}

	
	public void phoneNumberModification(int sid,Long number) {
		StaffDAO staffDAO=new StaffDAOImplementation();
		staffDAO.phoneNumberModification(sid, number);
		
	}
	
	public void emailModification(int sid,String mail) {
		StaffDAO staffDAO=new StaffDAOImplementation();
		staffDAO.emailModification(sid, mail);
		
	}
	
	public void addressModification(int sid,String address) {
		StaffDAO staffDAO=new StaffDAOImplementation();
		staffDAO.addressModification(sid, address);
		
	}
	
	public void addAttendance(int sid,Double attendance) {
		StaffDAO staffDAO=new StaffDAOImplementation();
		staffDAO.addAttendance(sid, attendance);
		
	}
	
	public void changeClasses(int sid,List<Classroom> classes) {
		StaffDAO staffDAO=new StaffDAOImplementation();
		staffDAO.changeClasses(sid, classes);

	}

	public Staff getStaff(int staffId) {
		StaffDAO staffDAO=new StaffDAOImplementation();
		return staffDAO.getStaff(staffId);
	}

	public void displayStaff(int staffId) {
		StaffDAO staffDAO=new StaffDAOImplementation();
		staffDAO.displayStaff(staffId);
		
	}

}
