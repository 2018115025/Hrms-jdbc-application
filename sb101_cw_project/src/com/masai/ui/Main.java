package com.masai.ui;
import java.util.Scanner;

import com.masai.dao.LoggedInUser;



public class Main {

	static void displayAdminMenu() {
		System.out.println("1. Add new Department");
		System.out.println("2. View all Departments");
		System.out.println("3. Update a department name");
		System.out.println("4. Add new Employee");
		System.out.println("5. Change department of employee");
		System.out.println("6. Approve or deny Leave request");
		System.out.println("7. fire an employee");
		System.out.println("8. view all users");
		System.out.println("0. logout");
	}
	
	static void adminMenu(Scanner sc) {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
			switch(choice) {
				case 0:
					System.out.println("admin logout successfully");
					break;
				case 1:
					DepartmentUI.addDepartment(sc);
					break;
				case 2:
					DepartmentUI.viewAllDepartment();
					break;
				case 3:
					DepartmentUI.updateDepartment(sc);
					break;
				case 4:
					UserUI.addUser(sc);
					break;
				case 5:
					UserUI.changeDeptOfEmloyee(sc);
					break;
				case 6:
//					categoryUI.searchCategoryById();
					break;
				case 7:
					UserUI.deleteEmployeeByAdmin(sc);
					break;
				case 8:
					UserUI.viewAllUser();
					break;	
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
	}
	
	static void adminLogin(Scanner sc) {
		System.out.print("Enter username ");
		String username = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			adminMenu(sc);
		}else {
			System.out.println("Invalid Username and Password");
		}
	}
	
	static void displayEmployeeMenu() {
		System.out.println("1. update employee details");
		System.out.println("2. change password");
		System.out.println("3. apply for leave");
		System.out.println("4. status of the leave");
		System.out.println("5. history of the leave");
		System.out.println("6. total salary of the month");
		System.out.println("7. Annual salary of the financial year");
		System.out.println("8. Delete account");
		System.out.println("0. Logout");
	}
	
	static void employeeLogin(Scanner sc) {
		if(!UserUI.login(sc))
			return;

		//you are here means login is successful
		int choice = 0;
		do {
			displayEmployeeMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					UserUI.updateNameOfEmployee(sc);
					break;
				case 2:
					UserUI.changePassword(sc);
					break;
				case 3:
//					orderUI.viewOrderDetails();
					break;
				case 4:
//					userUI.updateNameOfUser();
					break;
				case 5:
//					userUI.changePassword();
					break;
				case 6:
					UserUI.monthSalary();
					break;
				case 7:
					UserUI.annualSalary();
					break;	
				case 8:
					UserUI.deleteEmployee();
					try{
						Thread.sleep(2000);
					}catch(InterruptedException ex) {
						
					}
					//no break statement here i.e. after deletion of user account, logout will also take place
				case 0:
					UserUI.logout();
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(LoggedInUser.LoggedInUserId != 0);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		int choice = 0;
		do {
			System.out.println("1. Admin Login\n2. Employee Login\n0. Exit");
			choice = sc.nextInt();
			switch(choice) {
				case 0:
					System.out.println("Thank you, Visit again");
					break;
				case 1:
					adminLogin(sc);
					break;
				case 2:
					employeeLogin(sc);
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
		sc.close();
	}
}
