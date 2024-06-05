package com.example.SystemAssistance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SystemAssistance.DTO.AssignSupportDTO;
import com.example.SystemAssistance.DTO.SupportUserToWebsocketDTO;
import com.example.SystemAssistance.Service.SupportUserService;

@RestController
@RequestMapping("Supportpage")
public class SupportUserController {

	@Autowired
	SupportUserService supporUserService;

	@PostMapping("checkMessage")
	public String checkMessageForSupportUser(@RequestBody SupportUserToWebsocketDTO supportUsertoWebSocketDTO) {

		System.out.println(supportUsertoWebSocketDTO.getEmail());
		return supporUserService.checkUserAndGetMessage(supportUsertoWebSocketDTO);
	}
	
	@PostMapping("end")
	public void changeAvailability(@RequestBody AssignSupportDTO assignSupportDTO) {
		System.out.println(assignSupportDTO.getUserId());
		supporUserService.changeAvailability(assignSupportDTO);
		return;
	}
}
