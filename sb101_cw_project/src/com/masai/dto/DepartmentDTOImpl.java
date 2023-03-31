package com.masai.dto;

public class DepartmentDTOImpl implements DepartmentDTO{
	private String dept_id;
	private String dept_name;
	
	public DepartmentDTOImpl(String dept_id, String dept_name) {
		super();
		this.dept_id = dept_id;
		this.dept_name = dept_name;
	}
	@Override
	public String getDept_id() {
		return dept_id;
	}
	@Override
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	@Override
	public String getDept_name() {
		return dept_name;
	}
	@Override
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	@Override
	public String toString() {
		return "dept_id= " + dept_id + ", dept_name= " + dept_name + "\n";
	}
	
}
