package com.masai.dao;

import java.util.List;

import com.masai.dto.DepartmentDTO;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWrongException;

public interface DepartmentDAO {

	public void addDepartment(DepartmentDTO department) throws SomeThingWrongException;

	public void updateDepartment(String dept_id,String dept_name) throws SomeThingWrongException;

	public List<DepartmentDTO> viewAllDepartment() throws SomeThingWrongException,NoRecordFoundException;


}
