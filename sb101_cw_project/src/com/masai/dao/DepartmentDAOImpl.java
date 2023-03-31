package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.dto.DepartmentDTO;
import com.masai.dto.DepartmentDTOImpl;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWrongException;


public class DepartmentDAOImpl implements DepartmentDAO {

	@Override
	public void addDepartment(DepartmentDTO department) throws SomeThingWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "INSERT INTO department (dept_id, dept_name) VALUES (?, ?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, department.getDept_id());
			ps.setString(2, department.getDept_name());
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			throw new SomeThingWrongException("department not added");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
	}

	@Override
	public void updateDepartment(String dept_id,String dept_name) throws SomeThingWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "update department set dept_name= ? where dept_id= ? ";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, dept_name);
			ps.setString(2, dept_id);
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			throw new SomeThingWrongException("department not updated");
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				
			}
		}
		
	}

	@Override
	public List<DepartmentDTO> viewAllDepartment() throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		List<DepartmentDTO> list=new ArrayList<>();
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "select dept_id,dept_name from department";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			ResultSet rs= ps.executeQuery();
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("department table is empty");
			}
			while(rs.next()) {
				list.add(new DepartmentDTOImpl(rs.getString(1),rs.getString(2)));
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
	
}
