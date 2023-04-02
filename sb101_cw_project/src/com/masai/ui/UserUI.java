package com.masai.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.custom.ConsoleColors;
import com.masai.dao.DepartmentDAO;
import com.masai.dao.DepartmentDAOImpl;
import com.masai.dao.LoggedInUser;
import com.masai.dao.UserDAO;
import com.masai.dao.UserDAOImpl;
import com.masai.dto.AllUserDTOImpl;
import com.masai.dto.DepartmentDTO;
import com.masai.dto.LeaveDTO;
import com.masai.dto.LeaveDTOImpl;
import com.masai.dto.UserDTO;
import com.masai.dto.UserDTOImpl;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWrongException;

public class UserUI {
	public static void addUser(Scanner sc) {
		//code to take input product details
		System.out.print(ConsoleColors.CYAN_BOLD_BRIGHT+"Enter name");
		String name = sc.next();
		System.out.print("Enter username");
		String user_name = sc.next();
		System.out.print("Enter salary");
		double salary = sc.nextDouble();
		System.out.print("Enter dept id ");
		int dept_id = sc.nextInt();
		
		//create object for user with all details
		UserDTO user = new UserDTOImpl(name, user_name, "123456",salary,LocalDate.now(),dept_id);
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.addUser(user);
			System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT+"User registered successfully"+ConsoleColors.RESET);
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
	}

	public static void changeDeptOfEmloyee(Scanner sc) {
		System.out.print(ConsoleColors.CYAN_BOLD_BRIGHT+"Enter username");
		System.out.print("Enter username");
		String user_name = sc.next();
		System.out.print("Enter dept id ");
		int dept_id = sc.nextInt();
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.changeDeptOfEmployee(user_name, dept_id);;
			System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT+"department of the employee updated successfully"+ConsoleColors.RESET);
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
	}

	public static void deleteEmployeeByAdmin(Scanner sc) {
		System.out.print(ConsoleColors.CYAN_BOLD_BRIGHT+"Enter username");
		String user_name = sc.next();
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.deleteEmployeeByAdmin(user_name);;
			System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT+user_name+" employee deleted successfully"+ConsoleColors.RESET);
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}

	public static void viewAllUser() {
		UserDAO UserDAO=new UserDAOImpl();
		try {
			List<AllUserDTOImpl> list = UserDAO.viewAllUser();
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
			list.forEach(System.out::println);
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
		
	}

	public static boolean login(Scanner sc) {
		boolean loginSuccessful = false;
		//code to take input username and password
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
		System.out.print("Enter username ");
		String username = sc.next();
		
		System.out.print("Enter password ");
		String password = sc.next();
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.Login(username, password);
			System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT+username+" Login successful your user id is "+LoggedInUser.LoggedInUserId+ConsoleColors.RESET);
			loginSuccessful = true;
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
		return loginSuccessful;
	}

	public static void logout() {
		UserDAO UserDAO=new UserDAOImpl();
		UserDAO.logout();
		System.out.println("logged out successfully");
	}

	public static void changePassword(Scanner sc) {
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);		
			System.out.print("Enter old password ");
			String oldPassword = sc.next();
			
			System.out.print("Enter new password ");
			String newPassword = sc.next();
			
			System.out.print("Re-type new password ");
			String newPasswordAgain = sc.next();
			
			if(newPassword.equals(newPasswordAgain)) {
				UserDAO UserDAO=new UserDAOImpl();
				try {
					UserDAO.changePassword(oldPassword, newPassword);
					System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT+"Password updated successfully"+ConsoleColors.RESET);
				}catch(SomeThingWrongException | NoRecordFoundException ex) {
					System.out.println(ex);
				}			
			}else {
				System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT+"New password mismatched with re-typed new password"+ConsoleColors.RESET);
			}
	}

	public static void updateNameOfEmployee(Scanner sc) {
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
		System.out.print("Enter name ");
		String name = sc.next();
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.updateNameOfEmployee(name);
			System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT+"Name updated successfully"+ConsoleColors.RESET);
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}

	public static void deleteEmployee() {
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.deleteEmployee();
			System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT+"You are Logged out.\nDeleted your account"+ConsoleColors.RESET);
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}

	public static void annualSalary() {
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
		UserDAO UserDAO=new UserDAOImpl();
		try {
			 Double annual_sal=UserDAO.annualSalary();
			 System.out.println("Annual salary is : "+annual_sal);
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
	}
	
	public static void monthSalary() {
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
		UserDAO UserDAO=new UserDAOImpl();
		try {
			 Double annual_sal=UserDAO.monthSalary();
			 System.out.println("My salary is : "+annual_sal);
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
	}

	public static void applyLeave(Scanner sc) {
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
		System.out.print("Enter leave id");
		String leave_id = sc.next();
		System.out.print("Enter leave type");
		String leave_type = sc.next();
		System.out.print("Enter number of days of leave");
		int days = sc.nextInt();
		
		//create object for user with all details
		LeaveDTO leaveDTO=new LeaveDTOImpl(leave_id, leave_type, days, "pending",0);
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.applyLeave(leaveDTO);
			System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT+"leave applied successfully for "+LoggedInUser.LoggedInUserId+ConsoleColors.RESET);
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
		
	}

	public static void viewAllLeaveRequest() {
		UserDAO UserDAO=new UserDAOImpl();
		try {
			List<LeaveDTO> list = UserDAO.viewAllLeaveRequest();
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
			list.forEach(System.out::println);
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}

	public static void approveLeaveRequest(Scanner sc) {
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
		System.out.print("Enter leave id");
		String leave_id = sc.next();
		System.out.println("enter approve or deny");
		int check=sc.nextInt();
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.approveLeaveRequest(leave_id,check);
			if(check==1) {
				System.out.println("leave request approved");
			}
			else {
				System.out.println("leave request denied");
			}
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}

	public static void historyOfLeave() {
		UserDAO UserDAO=new UserDAOImpl();
		try {
			List<LeaveDTO> list = UserDAO.historyOfLeave();
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
			list.forEach(System.out::println);
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}

	public static void statusOfLeave(Scanner sc) {
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
		System.out.println("enter leave id");
		String leave_id = sc.next();
		
		UserDAO UserDAO=new UserDAOImpl();
		try {
			String ans = UserDAO.statusOfLeave(leave_id);
			System.out.println(leave_id+" is "+ans);
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	
}
