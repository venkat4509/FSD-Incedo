package com.example.SystemAssistance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SystemAssistance.DTO.LoginRequestDTO;
import com.example.SystemAssistance.DTO.UserDTO;
import com.example.SystemAssistance.DTO.UserDetailsDTO;
import com.example.SystemAssistance.DTO.UserRegistrationDTO;
import com.example.SystemAssistance.Entity.User;
import com.example.SystemAssistance.Service.UserService;

@RestController
@RequestMapping("User")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@PostMapping("/register")
    public String RegisterNewUser(@RequestBody UserRegistrationDTO registrationRequest) {
        return userService.RegisterNewUser(registrationRequest);
    }

	
	@PostMapping("/login")
    public String loginUser(@RequestBody LoginRequestDTO loginRequest) {
        return userService.loginUser(loginRequest);
    }
	
	@PostMapping("/getRoleandUserID")
    public UserDetailsDTO roleGetter(@RequestBody  UserDetailsDTO userDetailsDTO) {
      //System.out.println(userDTO.getRole());
	  return userService.roleandUserIDSender(userDetailsDTO);
    }
	
	
	

}
