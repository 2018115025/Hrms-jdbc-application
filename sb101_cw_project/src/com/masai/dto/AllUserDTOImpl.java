package com.masai.dto;

public class AllUserDTOImpl {
	private UserDTO UserDTO;
	private DepartmentDTO DepartmentDTO;
	
	public AllUserDTOImpl() {
		
	}
	public AllUserDTOImpl(UserDTO userDTO,DepartmentDTO departmentDTO) {
		super();
		UserDTO = userDTO;
		DepartmentDTO = departmentDTO;
	}
	public UserDTO getUserDTO() {
		return UserDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		UserDTO = userDTO;
	}
	public DepartmentDTO getDepartmentDTO() {
		return DepartmentDTO;
	}
	public void setDepartmentDTO(DepartmentDTO departmentDTO) {
		DepartmentDTO = departmentDTO;
	}
	@Override
	public String toString() {
		return "name= " + UserDTO.getName()+" username= " + UserDTO.getUsername()+" password= " + UserDTO.getPassword() +" salary= " + UserDTO.getSalary()+" joining_date= " + UserDTO.getDate()  + " Department= " + DepartmentDTO.getDept_name() + "\n";
	}
	
}
