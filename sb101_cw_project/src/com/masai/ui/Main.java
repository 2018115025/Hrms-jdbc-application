package com.masai.ui;
import java.util.Scanner;

import com.masai.custom.ConsoleColors;
import com.masai.dao.LoggedInUser;



public class Main {

	static void displayAdminMenu() {
		
		System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT+
				"+----------------------------------------+"+"\n"
			   +"| 1. Add new Department                  |"+"\n"
			   +"| 2. View all Departments                |"+"\n"
		       +"| 3. Update a department name            |"+"\n" 
		       +"| 4. Add new Employee                    |"+"\n" 
		       +"| 5. Change department of employee       |"+"\n" 
		       +"| 6. view all employee leave request     |"+"\n" 
		       +"| 7. Approve or deny Leave request       |"+"\n" 
		       +"| 8. fire an employee                    |"+"\n" 
		       +"| 9. view all users                      |"+"\n" 
		       +"| 0. Logout                              |"+"\n" 
			   +"+----------------------------------------+"+ConsoleColors.RESET);
	}
	
	static void adminMenu(Scanner sc) {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.print(ConsoleColors.CYAN_BOLD_BRIGHT+"Enter selection "+ConsoleColors.RESET);
			choice = sc.nextInt();
			switch(choice) {
				case 0:
					System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT+"admin logout successfully"+ConsoleColors.RESET);
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
					UserUI.viewAllLeaveRequest();
					break;	
				case 7:
					UserUI.approveLeaveRequest(sc);
					break;
				case 8:
					UserUI.deleteEmployeeByAdmin(sc);
					break;
				case 9:
					UserUI.viewAllUser();
					break;	
				default:
					System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT+"Invalid Selection, try again"+ConsoleColors.RESET);
			}
		}while(choice != 0);
	}
	
	static void adminLogin(Scanner sc) {
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT+"Please Login to your account");
		System.out.print("Enter username :  ");
		String username = sc.next();
		System.out.print("Enter password : ");
		String password = sc.next();
		
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT+"!! Welcome Admin !!"+ConsoleColors.RESET);
			adminMenu(sc);
		}else {
			System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT+"Invalid Username and Password"+ConsoleColors.RESET);
		}
	}
	
	static void displayEmployeeMenu() {
		System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT+
				"+----------------------------------------+"+"\n"
			   +"| 1. update employee details             |"+"\n"
			   +"| 2. change password                     |"+"\n"
		       +"| 3. apply for leave                     |"+"\n" 
		       +"| 4. history of the leave                |"+"\n" 
		       +"| 5. status of the leave                 |"+"\n" 
		       +"| 6. total salary of the month           |"+"\n" 
		       +"| 7. Annual salary of the financial year |"+"\n" 
		       +"| 8. Delete account                      |"+"\n" 
		       +"| 0. Logout                              |"+"\n" 
			   +"+----------------------------------------+"+ConsoleColors.RESET);
		
	}
	
	static void employeeLogin(Scanner sc) {
		if(!UserUI.login(sc))
			return;

		//you are here means login is successful
		int choice = 0;
		do {
			displayEmployeeMenu();
			System.out.print(ConsoleColors.CYAN_BOLD_BRIGHT+"Enter selection : ");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					UserUI.updateNameOfEmployee(sc);
					break;
				case 2:
					UserUI.changePassword(sc);
					break;
				case 3:
					UserUI.applyLeave(sc);
					break;
				case 4:
					UserUI.historyOfLeave();
					break;
				case 5:
					UserUI.statusOfLeave(sc);
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
					System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT+"Invalid Selection, try again"+ConsoleColors.RESET);
			}
		}while(LoggedInUser.LoggedInUserId != 0);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		int choice = 0;
		do {
			System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT+
					"+----------------------+"+"\n"
				   +"| 1. Admin Login       |"+"\n"
				   +"| 2. Employee Login    |"+"\n"
				   +"| 0. Exit              |"+"\n" 
				   +"+----------------------+"+ConsoleColors.RESET);
			
			choice = sc.nextInt();
			switch(choice) {
				case 0:
					System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT+"Thank you, Visit again"+ConsoleColors.RESET);
					break;
				case 1:
					adminLogin(sc);
					break;
				case 2:
					employeeLogin(sc);
					break;
				default:
					System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT+"Invalid Selection, try again"+ConsoleColors.RESET);
			}
		}while(choice != 0);
		sc.close();
	}
}
