
package com.example.SystemAssistance.DTO;

import com.example.SystemAssistance.Entity.Role;

public class UserDetailsDTO {
	private int userId;
	private String username;
	private String emailID;
	private Role role;


	public UserDetailsDTO(int userId, String name, String email, Role role) {
		super();
		this.userId = userId;
		this.username = name;
		this.emailID = email;
		this.role = role;

	}

	public UserDetailsDTO() {

	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getEmail() {
		return emailID;
	}

	public void setEmail(String email) {
		this.emailID = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
