package com.masai.ui;

import java.util.List;
import java.util.Scanner;

import com.masai.dao.DepartmentDAO;
import com.masai.dao.DepartmentDAOImpl;
import com.masai.dto.DepartmentDTO;
import com.masai.dto.DepartmentDTOImpl;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWrongException;


public class DepartmentUI {
	public static void addDepartment(Scanner sc) {
		
		System.out.print("Enter dept id ");
		String dept_id = sc.next();
		System.out.print("Enter dept name ");
		String dept_name = sc.next();
		
		
		DepartmentDTO department = new DepartmentDTOImpl(dept_id, dept_name);
		DepartmentDAO departmentDAO=new DepartmentDAOImpl();
		try {
			departmentDAO.addDepartment(department);
			System.out.println("department added successfully");
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
	}

	public static void viewAllDepartment() {
		DepartmentDAO departmentDAO=new DepartmentDAOImpl();
		try {
			List<DepartmentDTO> list = departmentDAO.viewAllDepartment();
			list.forEach(System.out::println);
		}catch(SomeThingWrongException|NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}

	public static void updateDepartment(Scanner sc) {
		System.out.println("enter dept id ");
		String dept_id=sc.next();
		System.out.print("Enter dept name ");
		String dept_name = sc.next();
		
		DepartmentDAO departmentDAO=new DepartmentDAOImpl();
		try {
			departmentDAO.updateDepartment(dept_id,dept_name);
			System.out.println("department updated successfully");
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
		
	}
}
