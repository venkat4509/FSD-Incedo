package com.example.SystemAssistance.DTO;

import com.example.SystemAssistance.Entity.Role;

public class AssignSupportDTO {
	
	//Normal_user user id
	private int userId;
	
	//support he wants
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public AssignSupportDTO() {
		
	}
	
	public AssignSupportDTO(int userID) {
		super();
		this.userId = userID;
	}

	
	

}
