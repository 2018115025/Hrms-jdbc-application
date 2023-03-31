package com.masai.ui;
import java.util.Scanner;

//import com.masaischool.dao.LoggedINUser;

public class Main {
//	private static ProductUI productUI;
//	private static UserUI userUI;

	static void displayAdminMenu() {
		System.out.println("1. Add new Department");
		System.out.println("2. View all Departments");
		System.out.println("3. Update a department name");
		System.out.println("4. Add new Employee");
		System.out.println("5. Change department of employee");
		System.out.println("6.Approve or deny Leave request");
		System.out.println("7.fire an employee");
		System.out.println("0.logout");
	}
	
	static void adminMenu(Scanner sc) {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
			switch(choice) {
				case 0:
					System.out.println("Bye Bye admin");
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
//					categoryUI.deleteCategory();
					break;
				case 5:
//					categoryUI.searchCategoriesByName();
					break;
				case 6:
//					categoryUI.searchCategoryById();
					break;
				case 7:
//					productUI.viewProductsByCategoryId();
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
	
	static void displayCustomerMenu() {
		System.out.println("1. View All Products");
		System.out.println("2. Purchase a Product");
		System.out.println("3. View Order History");
		System.out.println("4. Update My Name");
		System.out.println("5. Update My Password");
		System.out.println("6. Delete My Account");
		System.out.println("0. Logout");
	}
	
//	static void customerLogin(Scanner sc) {
//		if(!userUI.login())
//			return;
//
//		//you are here means login is successful
//		int choice = 0;
//		do {
//			displayCustomerMenu();
//			System.out.print("Enter selection ");
//			choice = sc.nextInt();
//			switch(choice) {
//				case 1:
//					productUI.viewAllProducts();
//					break;
//				case 2:
//					orderUI.purchaseProduct();
//					break;
//				case 3:
//					orderUI.viewOrderDetails();
//					break;
//				case 4:
//					userUI.updateNameOfUser();
//					break;
//				case 5:
//					userUI.changePassword();
//					break;
//				case 6:
//					userUI.deleteUser();
//					try{
//						Thread.sleep(2000);
//					}catch(InterruptedException ex) {
//						
//					}
//					//no break statement here i.e. after deletion of user account, logout will also take place
//				case 0:
//					userUI.logout();
//					break;
//				default:
//					System.out.println("Invalid Selection, try again");
//			}
//		}while(LoggedINUser.loggedInUserId != 0);
//	}
//	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		categoryUI = new CategeryUI(sc);
//		productUI = new ProductUI(sc);
//		userUI = new UserUI(sc);
//		orderUI = new OrderUI(sc);
//		
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
//					customerLogin(sc);
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
		sc.close();
	}
}
