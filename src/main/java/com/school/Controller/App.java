package com.school.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.school.dto.Classroom;
import com.school.dto.Fees;
import com.school.dto.Parent;
import com.school.dto.SchoolDetails;
import com.school.dto.Staff;
import com.school.dto.Student;
import com.school.service.ClassroomServices;
import com.school.service.FeesServices;
import com.school.service.SchoolDetailService;
import com.school.service.SchoolExpensesServices;
import com.school.service.StaffServices;
import com.school.service.StudentService;

public class App {

	private static final Scanner scanner = new Scanner(System.in);
	private static final App app=new App();
	
	
	public static void basicEntry() {


		System.out.println("First of All you have to Enter the Schools Basic Information----");
		app.enterSchoolInfo();

		System.out.println("Enter the classroom In the School----");
		while (true) {

			System.out.println("Enter the classroom Name :");

			String classroomName = scanner.next();
			app.addClassroom(classroomName);

			System.out.println("Enter 0 if You have Entered all the classroom :");
			int flag = 1;

			flag = scanner.nextInt();


			if (flag == 0)
				break;
		}

	}

	public void addClassroom(String classroomId) {

		ClassroomServices classroomServices=new ClassroomServices();

		System.out.println("If class Have the Class teacher Enter the "
				+ "Class Teacher Id else 0 :");
		int classteacherId=scanner.nextInt();

		Staff staff=null;
		if(classteacherId!=0) {
			staff=app.getStaff(classteacherId);
			if(staff==null) {
				System.err.println("Entered Teacher ID was Not in database......");
				System.err.println("Something Went Wrong........");
				return;
			}
		}

		List<Student> students=new ArrayList<Student>();

		Classroom classroom=new Classroom();
		classroom.setClassroomName(classroomId);
		classroom.setClassTeacher(staff);
		classroom.setStudents(students);

		classroomServices.addClassroom(classroom);

	}

	public void removeClassroom() {
		System.out.println("Enter the Classroom Name :");
		String classroomId=scanner.next();

		ClassroomServices classroomServices=new ClassroomServices();
		classroomServices.removeClassroom(classroomId);

	}

	public void changeClassTeacher() {
		System.out.println("Enter the Staff Id :");
		int staffId=scanner.nextInt();
		System.out.println("Enter the Classroom Name :");
		String classroomName=scanner.next();

		ClassroomServices classroomServices=new ClassroomServices();
		classroomServices.changeClassTeacher(staffId, classroomName);

	}

	public int strength() {
		System.out.println("ENter the Classroom Name :");
		String classroomId=scanner.next();
		ClassroomServices services=new ClassroomServices();
		return services.strength(classroomId);

	}

	public Classroom getClassroom(String classroomId) {
		ClassroomServices services=new ClassroomServices();
		return services.getClassroom(classroomId);
	}

	public void updateClassroom(Classroom classroomName) {
		ClassroomServices services=new ClassroomServices();
		services.updateClassroom(classroomName);

	}

	public void enterSchoolInfo() {
		SchoolDetails schoolDetails= SchoolDetails.objectCreation();
		System.out.println();
		System.out.println("Enter the School Name :");
		String schoolName=scanner.next();
		System.out.println("Enter the School Address :");
		String schoolAddress=scanner.next();

		List<Staff> staffList=new ArrayList<Staff>();
		List<Classroom> schoolClassroom=new ArrayList<Classroom>();

		schoolDetails.setSchoolName(schoolName);
		schoolDetails.setAddress(schoolAddress);
		schoolDetails.setSchoolStaff(staffList);
		schoolDetails.setSchoolClassroom(schoolClassroom);

		SchoolDetailService service=new SchoolDetailService();
		service.enterSchoolInfo(schoolDetails);
	}

	public void modifySchoolMaintance() {
		System.out.println("Enter the Maintance amount if ppositive or Negative :");
		Double maintance=scanner.nextDouble();

		SchoolExpensesServices services=new SchoolExpensesServices();
		services.modifySchoolMaintance(maintance);

	}

	public void purchaseBillModification() {
		System.out.println("Enter the school purchase :");
		Double purchase=scanner.nextDouble();

		SchoolExpensesServices expensesServices=new SchoolExpensesServices();
		expensesServices.modifySchoolMaintance(purchase);

	}

	public void addStaff() {
		Staff s=new Staff();
		StaffServices services=new StaffServices();
		ClassroomServices classroomServices=new ClassroomServices();

		System.out.println("Enter the Staff Name : ");
		String staffName=scanner.nextLine();
		System.out.println("Enter the Staff Address : ");
		String address=scanner.nextLine();
		System.out.println("Enter the subject : ");
		String subject=scanner.nextLine();
		System.out.println("Enter the Staff Phone Number :");
		Long staffPhoneNumber=scanner.nextLong();
		System.out.println("Enter the Staff Email Id :");
		String staffEmail=scanner.next();
		System.out.println("Enter the Staff Salary :");
		Double staffSalary=scanner.nextDouble();
		System.out.println("Enter the Staff Aattendance : ");
		Double staffAttendnce=scanner.nextDouble();


		List<Classroom> classes=new ArrayList<Classroom>();

		boolean flag=true;
		System.out.println("Enter the number of classes : ");

		while(flag){
			System.out.println("Enter the classroom name");
			String classroomID=scanner.next();
			Classroom c=classroomServices.getClassroom(classroomID);

			if(c!=null) 
				classes.add(c);
			else 
				System.err.println("Enter the Valid class");
			System.out.println("if wnat to enter more press 1 else any number");
			Integer f=scanner.nextInt();
			if(f!=1)
				flag=false;
		}

		s.setStaffName(staffName);
		s.setStaffAddress(address);
		s.setStaffPhoneNumber(staffPhoneNumber);
		s.setStaffEmail(staffEmail);
		s.setSubject(subject);
		s.setStaffSalary(staffSalary);
		s.setStaffAttendance(staffAttendnce);
		s.setClasses(classes);
		services.addStaff(s);

	}

	public void staffRemove() {
		System.out.print("Enter the Staff id : ");
		int sid=scanner.nextInt();
		StaffServices services=new StaffServices();
		services.removeStaff(sid);
	}

	public void modifyStaffDetails() {
		StaffServices service=new StaffServices();

		System.out.println("Enter\n1. Salary Modification\n2. Phone Number Modifiacation\n"
				+ "3. Email Modification\n4. Address Modification\n"
				+ "5. get out of this Service");
		int key=scanner.nextInt();
		System.out.println("Emter the Staff ID : ");
		int sid=scanner.nextInt();
		switch (key) {
		case 1:
			System.out.println("Enter the Modified Salary :");
			Double sal=scanner.nextDouble();
			service.salaryModification(sid, sal);
			break;
		case 2:
			System.out.println("Enter the Phone Number :");
			Long num=scanner.nextLong();
			service.phoneNumberModification(sid, num);
			break;
		case 3:
			System.out.println("Enter the Email :");
			String mail=scanner.next();
			service.emailModification(sid, mail);
			break;
		case 4:
			System.out.println("Enter the Address :");
			String address=scanner.next();
			service.addressModification(sid, address);
			break;

		default:
			return;
		}

	}


	public void addAttendance() {
		System.out.println("Enter the Staff Id : ");
		int sid=scanner.nextInt();
		System.out.println("Enter 1 for FullDay Attendance and 2 for HalfDay Attendance");
		int key=scanner.nextInt();
		Double attendance;
		switch (key) {
		case 1:
			attendance=1.0;
			break;
		default:
			attendance=0.5;
			break;
		}
		StaffServices services=new StaffServices();
		services.addAttendance(sid, attendance);


	}

	public void changeClasses() {
		ClassroomServices classroomServices=new ClassroomServices();
		StaffServices staffServices=new StaffServices();
		
		System.out.println("Enter the staff id :");
		int staffId=scanner.nextInt();

		Staff staff=staffServices.getStaff(staffId);
		int sid=0;
		if(staff!=null) {
			sid=staff.getStaffId();
		}
		else {
			System.err.println("Enter the Valid Staff Id");
			return;
		}
		
		List<Classroom> classes=new ArrayList<Classroom>();
		boolean flag=true;
		System.out.println("Enter the number of classes : ");

		while(flag){
			System.out.println("Enter the classroom name");
			String classroom=scanner.next();
			Classroom c=classroomServices.getClassroom(classroom);

			if(c!=null) 
				classes.add(c);
			else 
				System.err.println("Enter the Valid class");
			System.out.println("if wnat to enter more press 1 else any number");
			Integer f=scanner.nextInt();
			if(f!=1)
				flag=false;
		}

		
		staffServices.changeClasses(sid, classes);

	}

	public Staff getStaff(int staffId) {
		StaffServices services=new StaffServices();
		return services.getStaff(staffId);
	}

	public void addStudent() {
		System.out.println(scanner.hasNextLine());
		System.out.println("Enter the Student Name :");
		String studentName=scanner.next();
		System.out.println("Enter the Student Gender :");
		String studentGender=scanner.next();
		
		System.out.println("Enter the Student Age :");
		Integer stduentAge=scanner.nextInt();
		System.out.println("Enter the Address :");
		String studentAddress=scanner.next();
		System.out.println("Enter the Student Email Id :");
		String email=scanner.next();

		Classroom classroom=null;
		for(int i=0;i<=3;i++) {
			if(i==3) {
				System.err.println("Something Went Wrong........");
				return;
			}
			System.out.println("Enter the Student Classroom : ");
			String classroomId=scanner.next();

			classroom=app.getClassroom(classroomId);
			if(classroom!=null) {
				break;
			}
		}



		System.out.println("Enter the Phone Number :");
		Long phoneNumber=scanner.nextLong();

		Parent parent=new Parent();
		System.out.println("Enter the Parent Details............");
		System.out.println("Enter the Father Name :");
		String fatherName=scanner.next();
		System.out.println("Enter the Father Age :");
		Integer fatherAge=scanner.nextInt();
		System.out.println("Enter the Father Occupation :");
		String fatherOccupation=scanner.next();
		System.out.println("Enter the Mother Name :");
		String motherName=scanner.next();
		System.out.println("Enter the Mother Age :");
		Integer motherAge=scanner.nextInt();
		System.out.println("Enter the Mother Occupation :");
		String motherOccupation=scanner.next();

		System.out.println("Enter the Full Fees :");
		Double fees=scanner.nextDouble();
		System.out.println("Enter the Fees Paid :");
		Double feesPaid=scanner.nextDouble();

		parent.setFatherName(fatherName);
		parent.setFatherAge(fatherAge);
		parent.setFatherOccupation(fatherOccupation);
		parent.setMotherName(motherName);
		parent.setMotherAge(motherAge);
		parent.setMotherOccupation(motherOccupation);


		Fees feesDetails=addFees(fees,feesPaid);


		Student student=new Student();
		student.setStudentName(studentName);
		student.setStudentGender(studentGender);
		student.setStudentAge(stduentAge);
		student.setStudentAddress(studentAddress);
		student.setClassroom(classroom);
		student.setEmail(email);
		student.setPhoneNumber(phoneNumber);
		student.setParentDetails(parent);
		student.setFeesDetails(feesDetails);

		StudentService service=new StudentService();
		service.addStudent(student);
		
		List<Student> list= classroom.getStudents();//new ArrayList<Student>();
		list.add(student);
		classroom.setStudents(list);

		app.updateClassroom(classroom);
	}

	public void removeStudent() {
		System.out.println("Enter the Student ID :");
		int studentId=scanner.nextInt();

		StudentService service=new StudentService();
		service.removeStudent(studentId);

	}

	private Fees addFees(Double fees,Double feesPaid) {
		Fees fee=new Fees();
		FeesServices service=new FeesServices();
		fee.setFees(fees);
		fee.setFeesPaid(feesPaid);
		return service.addFees(fee);
	}

	public void feesPayment() {
		System.out.println("Enter the Student Id :");
		int fid=scanner.nextInt();
		System.out.println("Enter the Fees Paid :");
		Double feesPaid=scanner.nextDouble();

		FeesServices service=new FeesServices();
		service.feesPayment(fid, feesPaid);

	}

	public void modifyStudentDetails() {
		StudentService service=new StudentService();

		System.out.println("Enter\n1. Student Address\n2. Student Email Id"
				+ "\n3. Student Phone Number\n4. get out of this Service");
		int key=scanner.nextInt();
		System.out.println("Enter the Student Id :");
		int studentId=scanner.nextInt();
		switch (key) {
		case 1:
			System.out.println("Enter the Modified Student Address :");
			String studentAddress=scanner.nextLine();
			service.modifyStudentAddress(studentId, studentAddress);
			break;
		case 2:
			System.out.println("Enter the Modified Student Email Id :");
			String mail=scanner.next();
			service.modifyStudentEmailId(studentId,mail);
			break;
		case 3:
			System.out.println("Enter the Modified Phone Number :");
			Long num=scanner.nextLong();
			service.modfiyStudentPhoneNumber(studentId, num);
			break;

		default:
			return;
		}
	}

	public Student getStudent(int studentId) {
		StudentService service=new StudentService();
		return service.getStudent(studentId);
	}


	public static void main(String[] args) {



//		basicEntry();

		boolean flag = true;

		while (flag) {
			System.out.println("Enter the option for the service:\n101.Basic Entry for school\n1. Add Student\n2. Remove Student"
					+ "\n3. Pay Fees\n4. Change Student Details\n5. To see the details about the student");

			System.out.println("6. Add Staff\n7. Remove Staff\n8. change Staff details\n9. Add Staff Attendance"
					+ "\n10. Change classes for Staff\n11. To see the Staff Details");

			System.out.println("12. Add classroom\n13. Remove Classroom\n14. Change The class Teacher"
					+ "\n15. To see the class Strength\n16. To see the Details of the classRoom");
			System.err.println("Enter 0 to END the SERVICE");

			int key = scanner.nextInt();

			switch (key) {
			case 1:
				app.addStudent();
				break;
			case 2:
				app.removeStudent();
				break;
			case 3:
				app.feesPayment();
				break;
			case 4:
				app.modifyStudentDetails();
				break;
			case 5:
				System.out.println("Enter the Student Id :");
				int sid=scanner.nextInt();
				Student student=app.getStudent(sid);
				if(student!=null) {
					System.out.println(student.toString());
				}
				else {
					System.err.println("Student Not Found......");
				}
				break;
			case 6:
				app.addStaff();
				break;
			case 7:
				app.staffRemove();
				break;
			case 8:
				app.modifyStaffDetails();
				break;
			case 9:
				app.addAttendance();
				break;
			case 10:
				app.changeClasses();
				break;
			case 11:
				System.out.println("Enter the Staff Id :");
				int staffId=scanner.nextInt();
				Staff staff=app.getStaff(staffId);
				if(staff!=null) {
					System.out.println(staff.toString());
				}
				else {
					System.err.println("Staff Id Was NOt found in the database......");
				}
				break;
			case 12:
				System.out.println("Enter the ClassRoom Name :");
				String cid=scanner.next();
				app.addClassroom(cid);
				break;
			case 13:
				app.removeClassroom();
				break;
			case 14:
				app.changeClassTeacher();
				break;
			case 15:
				int c=app.strength();
				if(c!=-1) 
					System.out.println(c);
				break;
			case 16:
				System.out.println("Enter the classroom Name :");
				String classroomName=scanner.next();
				Classroom cls=app.getClassroom(classroomName);
				if(cls!=null) {
					System.out.println(cls.toString());
				}
				else {
					System.err.println("Classroom Was Not in the Database......");
				}
			case 101:
				basicEntry();
				break;
			default:
				flag = false;
				break;
			}
		}

		scanner.close();
	}
}
