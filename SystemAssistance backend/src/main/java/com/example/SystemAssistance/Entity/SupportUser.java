package com.example.SystemAssistance.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class SupportUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supportUserID;
	
	private boolean availability;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private String username;

	public SupportUser() {
		
	}
	
	public SupportUser(int supportUserID, boolean availability, Role role, User user) {
		super();
		this.supportUserID = supportUserID;
		this.availability = availability;
		this.role = role;
		this.user = user;
	}

	public int getSupportUserID() {
		return supportUserID;
	}

	public void setSupportUserID(int supportUserID) {
		this.supportUserID = supportUserID;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
