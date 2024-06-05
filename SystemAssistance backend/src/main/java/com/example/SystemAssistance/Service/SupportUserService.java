package com.example.SystemAssistance.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SystemAssistance.DTO.AssignSupportDTO;
import com.example.SystemAssistance.DTO.SupportUserToWebsocketDTO;
import com.example.SystemAssistance.Entity.Conversation;
import com.example.SystemAssistance.Entity.SupportUser;
import com.example.SystemAssistance.Entity.User;
import com.example.SystemAssistance.Repository.ConversationRepository;
import com.example.SystemAssistance.Repository.SupportUserRepository;
import com.example.SystemAssistance.Repository.UserRepository;

@Service
public class SupportUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ConversationRepository conversationRepository;

	@Autowired
	SupportUserRepository supportUserRepository;

	public String checkUserAndGetMessage(SupportUserToWebsocketDTO supportUsertoWebSocketDTO) {

		User user = userRepository.findByEmail(supportUsertoWebSocketDTO.getEmail());
		// Check if the user is null
		if (user == null) {
			return "No user found with the given email.";
		}
		String userName = user.getUsername();
		Conversation conversation = conversationRepository.findBySupportUserUserNameAndAvailability(userName, false);
		if (conversation != null) {
			String NormalUserUsername = conversation.getNormalUserUserName();
			// conversation.setAvailability(true);
			return "You have been assigned an user (" + NormalUserUsername + ") to answer his query.  ";
		}
		return "No user has been assigned to you.";
	}

	// For makng the support user available once he clicks "end" button.
	public void changeAvailability(AssignSupportDTO assignSupportDTO) {
		Optional<SupportUser> supportUser = supportUserRepository.findByUserUserId(assignSupportDTO.getUserId());
		if (supportUser.isPresent()) {
			SupportUser supportUserfound = supportUser.get();
			supportUserfound.setAvailability(true);
			String supportUserUsername = supportUserfound.getUsername();
			Conversation conversation = conversationRepository
					.findBySupportUserUserNameAndAvailability(supportUserUsername);
			if (conversation != null) {
				conversation.setAvailability(true);
				conversationRepository.save(conversation); // Save the updated conversation entity
				supportUserRepository.save(supportUserfound); // Save the updated support user entity
			}
		}
	}
}
