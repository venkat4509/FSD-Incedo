package com.example.SystemAssistance.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.SystemAssistance.DTO.AssignSupportDTO;
import com.example.SystemAssistance.DTO.ChatMessage;
import com.example.SystemAssistance.DTO.adminPageDisplayDTO;
import com.example.SystemAssistance.Entity.Conversation;
import com.example.SystemAssistance.Entity.SupportUser;
import com.example.SystemAssistance.Entity.User;
import com.example.SystemAssistance.Repository.ConversationRepository;
import com.example.SystemAssistance.Repository.SupportUserRepository;
import com.example.SystemAssistance.Repository.UserRepository;

@Service
public class ConversationService {

	@Autowired
	private SupportUserRepository supportUserRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Transactional
	public void initializeConversation(Set<String> usernames) throws Exception {
		List<String> userList = new ArrayList<>(usernames);
		String user1 = userList.get(0);
		String user2 = userList.get(1);

		Optional<Conversation> existingConversation = conversationRepository
				.findBySupportUserUserNameAndNormalUserUserNameAndAvailability(user1, user2, false);
		if (!existingConversation.isPresent()) {
			Conversation conversation = new Conversation();
			conversation.setBlobFile(new byte[0]);
			conversation.setTimestamp(LocalDateTime.now());
			conversation.setSupportUserUserName(user1);
			conversation.setNormalUserUserName(user2);
			conversationRepository.save(conversation);
		}
	}

	@Transactional
	public void addMessage(ChatMessage chatMessage, Set<String> usernames) throws Exception {
		List<String> userList = new ArrayList<>(usernames);
		String user1 = userList.get(0);
		String user2 = userList.get(1);
		Optional<Conversation> existingConversation = conversationRepository
				.findBySupportUserUserNameAndNormalUserUserNameAndAvailability(user1, user2, false);
		if (!existingConversation.isPresent()) {
			throw new IllegalArgumentException("Conversation does not exist");
		}
		Conversation conversation = existingConversation.get();
		List<ChatMessage> messages;
		if (conversation.getBlobFile() == null || conversation.getBlobFile().length == 0) {
			messages = new ArrayList<>();
		} else {
			messages = objectMapper.readValue(conversation.getBlobFile(), new TypeReference<List<ChatMessage>>() {
			});
		}
		messages.add(chatMessage);
		String json = objectMapper.writeValueAsString(messages);
		conversation.setBlobFile(json.getBytes());
		conversation.setTimestamp(LocalDateTime.now());
		conversationRepository.save(conversation);
	}

	
	@Transactional
	public String ConversationDetails(AssignSupportDTO assignSupportDTO) {
		Optional<User> optionalUser = userRepository.findById(assignSupportDTO.getUserId());
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			SupportUser supportUser = supportUserRepository
				.findFirstByAvailabilityTrueAndRole(assignSupportDTO.getRole());
			
			// Getting the emailId of the assigned support user to display to user
			User SupportUser = supportUser.getUser();
			String AssignedSU_email = SupportUser.getEmail();
			supportUser.setAvailability(false);
			
			Conversation conversationTable = new Conversation();
			String supportUserUserName = supportUser.getUsername();
			String normalUserUserName = user.getUsername();
			conversationTable.setSupportUser(supportUser);
			conversationTable.setSupportUserUserName(supportUserUserName);
			conversationTable.setNormalUserUserName(normalUserUserName);
			conversationTable.setNormalUser(user);
			conversationTable.setAvailability(false);
			conversationTable.setTimestamp(LocalDateTime.now());
			conversationRepository.save(conversationTable);
			// AssignedSU_email ;
			return "You have been assigned to the requested support user ( " + AssignedSU_email + " ).  ";
		} else {
			return "User not found.";
		}
	}

	public List<adminPageDisplayDTO> getAllConversations() {
		List<Conversation> conversations = conversationRepository.findAll();
		return conversations.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private adminPageDisplayDTO convertToDTO(Conversation conversation) {
		adminPageDisplayDTO dto = new adminPageDisplayDTO();
		dto.setNormalUserUserName(conversation.getNormalUserUserName());
		dto.setSupportUserUserName(conversation.getSupportUserUserName());
		dto.setTimestamp(conversation.getTimestamp());
		dto.setBlobFile(conversation.getBlobFile());
		return dto;
	}

}
