package com.masai.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.DepartmentDAO;
import com.masai.dao.DepartmentDAOImpl;
import com.masai.dao.LoggedInUser;
import com.masai.dao.UserDAO;
import com.masai.dao.UserDAOImpl;
import com.masai.dto.DepartmentDTO;
import com.masai.dto.UserDTO;
import com.masai.dto.UserDTOImpl;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWrongException;

public class UserUI {
	public static void addUser(Scanner sc) {
		//code to take input product details
		System.out.print("Enter name");
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
			System.out.println("User registered successfully");
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
	}

	public static void changeDeptOfEmloyee(Scanner sc) {
		System.out.print("Enter username");
		String user_name = sc.next();
		System.out.print("Enter dept id ");
		int dept_id = sc.nextInt();
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.changeDeptOfEmployee(user_name, dept_id);;
			System.out.println("department of the employee updated successfully");
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
	}

	public static void deleteEmployeeByAdmin(Scanner sc) {
		System.out.print("Enter username");
		String user_name = sc.next();
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.deleteEmployeeByAdmin(user_name);;
			System.out.println(user_name+" employee deleted successfully");
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}

	public static void viewAllUser() {
		UserDAO UserDAO=new UserDAOImpl();
		try {
			List<UserDTO> list = UserDAO.viewAllUser();
			list.forEach(System.out::println);
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
		
	}

	public static boolean login(Scanner sc) {
		boolean loginSuccessful = false;
		//code to take input username and password
		System.out.print("Enter username ");
		String username = sc.next();
		
		System.out.print("Enter password ");
		String password = sc.next();
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.Login(username, password);
			System.out.println(username+" Login successful your user id is "+LoggedInUser.LoggedInUserId);
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
					System.out.println("Password updated successfully");
				}catch(SomeThingWrongException | NoRecordFoundException ex) {
					System.out.println(ex);
				}			
			}else {
				System.out.println("New password mismatched with re-typed new password");
			}
	}

	public static void updateNameOfEmployee(Scanner sc) {
		System.out.print("Enter name ");
		String name = sc.next();
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.updateNameOfEmployee(name);
			System.out.println("Name updated successfully");
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}

	public static void deleteEmployee() {
		UserDAO UserDAO=new UserDAOImpl();
		try {
			UserDAO.deleteEmployee();
			System.out.println("You are Logged out.\nDeleted your account");
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}

	public static void annualSalary() {
		UserDAO UserDAO=new UserDAOImpl();
		try {
			 Double annual_sal=UserDAO.annualSalary();
			 System.out.println("Annual salary is "+annual_sal);
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
	}
	
	public static void monthSalary() {
		UserDAO UserDAO=new UserDAOImpl();
		try {
			 Double annual_sal=UserDAO.monthSalary();
			 System.out.println("My salary is "+annual_sal);
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
	}
	
}
