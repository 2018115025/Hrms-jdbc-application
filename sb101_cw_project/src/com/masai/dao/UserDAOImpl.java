package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.dto.AllUserDTOImpl;
import com.masai.dto.DepartmentDTO;
import com.masai.dto.DepartmentDTOImpl;
import com.masai.dto.LeaveDTO;
import com.masai.dto.LeaveDTOImpl;
import com.masai.dto.UserDTO;
import com.masai.dto.UserDTOImpl;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWrongException;

public class UserDAOImpl implements UserDAO {

	@Override
	public void addUser(UserDTO user) throws SomeThingWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "INSERT INTO employee (username,salary,dept_id,name) VALUES (?, ?, ?, ?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, user.getUsername());
			ps.setDouble(2, user.getSalary());
			   ps.setInt(3, user.getDept_id());
			ps.setString(4, user.getName());
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			throw new SomeThingWrongException("employee not added");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
	}

	@Override
	public void changeDeptOfEmployee(String user_name,int dept_id) throws SomeThingWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "update employee set dept_id=? where username=?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setInt(1, dept_id);
		    ps.setString(2, user_name);
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			throw new SomeThingWrongException("employee department was not changed");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
	}

	@Override
	public void deleteEmployeeByAdmin(String user_name) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "update employee set is_deleted=1 where username=?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
		    ps.setString(1, user_name);
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			throw new SomeThingWrongException("employee not deleted");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
	}

	@Override
	public List<AllUserDTOImpl> viewAllUser() throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		List<AllUserDTOImpl> list=new ArrayList<>();
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "select name,username,password,salary,joining_date,dept_name from employee e inner join department d on d.id=e.dept_id where e.is_deleted=0";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("employee table is empty");
			}
			while(rs.next()) {
				AllUserDTOImpl AllUserDTO=new AllUserDTOImpl();
				AllUserDTO.setUserDTO(new UserDTOImpl(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5).toLocalDate(), 0));
				AllUserDTO.setDepartmentDTO(new DepartmentDTOImpl(null, rs.getString(6)));
				list.add(AllUserDTO);
			}
		}catch(SQLException ex) {
			throw new SomeThingWrongException("try again later");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
		return list;
	}

	@Override
	public void Login(String username, String password) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String LOGIN_QUERY = "SELECT id FROM employee WHERE username = ? AND password = ? AND is_deleted=0";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(LOGIN_QUERY);
			
			//stuff the data in the query
			ps.setString(1, username);
			ps.setString(2, password);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("Invalid Username and Password");
			}
			
			//you are here means username and password combination is correct
			resultSet.next();
			LoggedInUser.LoggedInUserId = resultSet.getInt("id");
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomeThingWrongException("try again later");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
	}

	@Override
	public void logout() {
		LoggedInUser.LoggedInUserId=0;
	}
	
	private boolean isOldPasswordCorrect(String oldPassword) throws SomeThingWrongException {
		Connection connection = null;
		boolean isPasswordValid = false;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String CHECK_PASSWORD_QUERY = "SELECT count(*) as count FROM employee WHERE password = ? AND id = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(CHECK_PASSWORD_QUERY);
			
			//stuff the data in the query
			ps.setString(1, oldPassword);
			ps.setInt(2, LoggedInUser.LoggedInUserId);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			
			isPasswordValid = resultSet.getInt("count") == 1;
		}catch(SQLException sqlEx) {
			throw new SomeThingWrongException("try again later");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
			}
		}
		return isPasswordValid;
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) throws SomeThingWrongException, NoRecordFoundException {
		if(!isOldPasswordCorrect(oldPassword)) {
			throw new NoRecordFoundException("Invalid old password!");
		}
		
		//you are here means old password matched
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String UPDATE_QUERY = "UPDATE employee SET password = ? WHERE id = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, newPassword);
			ps.setInt(2, LoggedInUser.LoggedInUserId);
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			throw new SomeThingWrongException("password not changed");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {

			}
		}
		
	}

	@Override
	public void updateNameOfEmployee(String name) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String UPDATE_QUERY = "UPDATE employee SET name = ? WHERE id = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, name);
			ps.setInt(2, LoggedInUser.LoggedInUserId);
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			throw new SomeThingWrongException("Name cannot be changed");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
			}
		}
	}

	@Override
	public void deleteEmployee() throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//prepare the query
			String DELETE_QUERY = "update employee set is_deleted=1 where id=?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
			
			//stuff the data in the query
			ps.setInt(1, LoggedInUser.LoggedInUserId);
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomeThingWrongException("employee can not deleted");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
			}
		}
	}

	@Override
	public Double annualSalary() throws SomeThingWrongException {
		Connection connection = null;
		Double annualsal=0.0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//prepare the query
			String DELETE_QUERY = "select salary*12 as sal from employee where id=? AND is_deleted=0";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
			
			//stuff the data in the query
			ps.setInt(1, LoggedInUser.LoggedInUserId);
			
			//execute query
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				annualsal =rs.getDouble("sal");
			}
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomeThingWrongException("try again later");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
			}
		}
		return annualsal;
		
	}
	@Override
	public Double monthSalary() throws SomeThingWrongException {
		Connection connection = null;
		Double sal=0.0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//prepare the query
			String DELETE_QUERY = "select salary as sal from employee where id=? AND is_deleted=0";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
			
			//stuff the data in the query
			ps.setInt(1, LoggedInUser.LoggedInUserId);
			
			//execute query
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				sal =rs.getDouble("sal");
			}
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomeThingWrongException("try again later");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
			}
		}
		return sal;
		
	}

	@Override
	public void applyLeave(LeaveDTO leaveDTO) throws SomeThingWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "insert into leave_table (leave_id,leave_type,no_of_days,user_id) values (?,?,?,?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, leaveDTO.getLeave_id());
			ps.setString(2, leaveDTO.getLeave_type());
			   ps.setInt(3, leaveDTO.getDays());
			ps.setInt(4, LoggedInUser.LoggedInUserId);
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			throw new SomeThingWrongException("leave cannot applied");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
		
	}

	@Override
	public List<LeaveDTO> viewAllLeaveRequest() throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		List<LeaveDTO> list=new ArrayList<>();
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "select * from leave_table";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("leave table is empty");
			}
			while(rs.next()) {
				list.add(new LeaveDTOImpl(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getString(4),rs.getInt(5)));
			}
		}catch(SQLException ex) {
			throw new SomeThingWrongException("try again later");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
		return list;
	}

	@Override
	public void approveLeaveRequest(String leave_id,int check) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			if(check==1) {
				PreparedStatement ps = connection.prepareStatement("update leave_table set status='approved' where leave_id=?");
				ps.setString(1, leave_id);
				ps.executeUpdate();
			}
			else {
				PreparedStatement ps = connection.prepareStatement("update leave_table set status='denied' where leave_id=?");
				ps.setString(1, leave_id);
				ps.executeUpdate();
			}
			
		}catch(SQLException sqlEx) {
			throw new SomeThingWrongException("leave request not done");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
	}

	@Override
	public List<LeaveDTO> historyOfLeave() throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		List<LeaveDTO> list=new ArrayList<>();
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "select * from leave_table where user_id=?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			ps.setInt(1,LoggedInUser.LoggedInUserId);
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("leave table is empty");
			}
			while(rs.next()) {
				list.add(new LeaveDTOImpl(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getString(4),rs.getInt(5)));
			}
		}catch(SQLException ex) {
			throw new SomeThingWrongException("try again later");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
		return list;
	}

	@Override
	public String statusOfLeave(String leave_id) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		String ans=null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "select status from leave_table where user_id=? and leave_id=?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			ps.setInt(1,LoggedInUser.LoggedInUserId);
			ps.setString(2, leave_id);
			
			ResultSet rs= ps.executeQuery();
			
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("enter a valid leave id");
			}
			while(rs.next()) {
				ans=rs.getString(1);
			}
		}catch(SQLException ex) {
			throw new SomeThingWrongException("try again later");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
		return ans;
	}
	
}
