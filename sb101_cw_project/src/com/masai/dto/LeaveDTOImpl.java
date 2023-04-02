package com.masai.dto;

public class LeaveDTOImpl implements LeaveDTO{
	private String leave_id;
	private String leave_type;
	private int days;
	private String status;
	private int user_id;
	public LeaveDTOImpl(String leave_id, String leave_type, int days, String status,int user_id) {
		super();
		this.leave_id = leave_id;
		this.leave_type = leave_type;
		this.days = days;
		this.status = status;
		this.user_id=user_id;
	}
	@Override
	public int getUser_id() {
		return user_id;
	}
	@Override
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String getLeave_id() {
		return leave_id;
	}
	@Override
	public void setLeave_id(String leave_id) {
		this.leave_id = leave_id;
	}
	@Override
	public String getLeave_type() {
		return leave_type;
	}
	@Override
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	@Override
	public int getDays() {
		return days;
	}
	@Override
	public void setDays(int days) {
		this.days = days;
	}
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "leave_id= " + leave_id + " leave_type= " + leave_type + " days= " + days + " status= " + status+" user_id= "+user_id
				+ "\n";
	}
	
}
