package com.masai.dto;

import java.time.LocalDate;

public class UserDTOImpl implements UserDTO {
	private String name;
	private String username;
	private String password;
	private double salary;
	private LocalDate date;
	private int dept_id;
	public UserDTOImpl(String name, String username, String password, double salary, LocalDate date, int dept_id) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.salary = salary;
		this.date = date;
		this.dept_id = dept_id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public double getSalary() {
		return salary;
	}
	@Override
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public LocalDate getDate() {
		return date;
	}
	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public int getDept_id() {
		return dept_id;
	}
	@Override
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	@Override
	public String toString() {
		return "name= " + name + ", username= " + username + ", password= " + password + ", salary= " + salary
				+ ", date= " + date + ", dept_id= " + dept_id + "\n";
	}
	
}
