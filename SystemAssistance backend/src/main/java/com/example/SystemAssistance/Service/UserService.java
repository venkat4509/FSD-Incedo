package com.example.SystemAssistance.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.SystemAssistance.DTO.LoginRequestDTO;
import com.example.SystemAssistance.DTO.UserDetailsDTO;
import com.example.SystemAssistance.DTO.UserRegistrationDTO;
import com.example.SystemAssistance.Entity.Role;
import com.example.SystemAssistance.Entity.SupportUser;
import com.example.SystemAssistance.Entity.User;
import com.example.SystemAssistance.Exceptions.InvalidUserException;
import com.example.SystemAssistance.Exceptions.UserNotFoundException;
import com.example.SystemAssistance.Repository.SupportUserRepository;
import com.example.SystemAssistance.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SupportUserRepository supportUserRepository;

	public String RegisterNewUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {

		User existingUser = userRepository.findByEmail(userRegistrationDTO.getEmail());
		if (existingUser != null) {
			return "User with this email already exists";
		}

		User user = new User();

		user.setUsername(userRegistrationDTO.getUserName());
		user.setEmail(userRegistrationDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
		user.setRole(userRegistrationDTO.getRole());
		userRepository.save(user);

		if (userRegistrationDTO.getRole() == Role.IT_Support) {
			SupportUser supportUser = new SupportUser();
			supportUser.setUser(user);
			supportUser.setAvailability(true);
			supportUser.setRole(userRegistrationDTO.getRole());
			supportUser.setUsername(user.getUsername());
			supportUserRepository.save(supportUser);
		}
		return "User registered successfully";
	}

	
	
	public String loginUser(LoginRequestDTO loginRequest) {
		// Check if user exists
		User user = userRepository.findByEmail(loginRequest.getEmail());
		if (user != null) {
			if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
				return "Login successful";
			} else {
				throw new InvalidUserException("Invalid email or password");
			}
		} else {
			throw new UserNotFoundException("User not found");
		}
	}

	public UserDetailsDTO roleandUserIDSender(UserDetailsDTO userDetailsDTO) {
		User user = userRepository.findByEmail(userDetailsDTO.getEmail());
		UserDetailsDTO userDetailsDTOtoFrontend = new UserDetailsDTO();
		userDetailsDTOtoFrontend.setUserId(user.getUserId());
		userDetailsDTOtoFrontend.setRole(user.getRole());
		userDetailsDTOtoFrontend.setName(user.getUsername());
		return userDetailsDTOtoFrontend;
	}
}
