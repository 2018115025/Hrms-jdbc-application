package com.masai.dto;

public interface LeaveDTO {
	public String getLeave_id();
	public void setLeave_id(String leave_id);
	public String getLeave_type();
	public void setLeave_type(String leave_type);
	public int getDays();
	public void setDays(int days);
	public String getStatus();
	public void setStatus(String status);
	public int getUser_id();
	public void setUser_id(int user_id);
}
