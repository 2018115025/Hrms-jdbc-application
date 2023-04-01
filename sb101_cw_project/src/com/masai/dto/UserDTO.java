package com.masai.dto;

import java.time.LocalDate;

public interface UserDTO {
	public String getName();
	public void setName(String name);
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
	public double getSalary();
	public void setSalary(double salary);
	public LocalDate getDate();
	public void setDate(LocalDate date);
	public int getDept_id();
	public void setDept_id(int dept_id);
}
