package com.example.SystemAssistance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SystemAssistance.DTO.AssignSupportDTO;
import com.example.SystemAssistance.DTO.adminPageDisplayDTO;
import com.example.SystemAssistance.Service.ConversationService;

@RestController
@RequestMapping("conversation")
public class ConversationController {

	@Autowired
	ConversationService conversationService;
	
	@PostMapping("/assign")
	public String AllotEmployee(@RequestBody AssignSupportDTO assignSupportDTO) {
		
		System.out.println(assignSupportDTO.getUserId());
		String response = conversationService.ConversationDetails(assignSupportDTO);
		return(response);
		
	}
	
	@GetMapping("admin")
    public List<adminPageDisplayDTO> getAllConversations() {
        return conversationService.getAllConversations();
    }
	
	
}
