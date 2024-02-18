package com.school.service;

import com.school.dao.SchoolDetailDAO;
import com.school.daoimplementation.SchoolDetailDAOImplementation;
import com.school.dto.SchoolDetails;

public class SchoolDetailService {
	
	public void enterSchoolInfo(SchoolDetails schoolDetails) {
		
		SchoolDetailDAO detailDAO=new SchoolDetailDAOImplementation();
		detailDAO.enterSchoolInfo(schoolDetails);
		
	}
	

}
