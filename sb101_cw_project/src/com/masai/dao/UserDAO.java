package com.masai.dao;

import java.util.List;

import com.masai.dto.UserDTO;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWrongException;

public interface UserDAO {
	public void addUser(UserDTO user) throws SomeThingWrongException;

	public void changeDeptOfEmployee(String user_name,int dept_id) throws SomeThingWrongException;

	public void deleteEmployeeByAdmin(String user_name) throws SomeThingWrongException,NoRecordFoundException;

	public List<UserDTO> viewAllUser()throws SomeThingWrongException,NoRecordFoundException;

	public void Login(String username, String password) throws SomeThingWrongException,NoRecordFoundException;

	public void logout();

	public void changePassword(String oldPassword, String newPassword) throws SomeThingWrongException,NoRecordFoundException;

	public void updateNameOfEmployee(String name) throws SomeThingWrongException,NoRecordFoundException;

	public void deleteEmployee()throws SomeThingWrongException,NoRecordFoundException;

	public Double annualSalary() throws SomeThingWrongException;
	
	public Double monthSalary() throws SomeThingWrongException;
}
