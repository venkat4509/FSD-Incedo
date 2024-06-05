package com.example.SystemAssistance.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import com.example.SystemAssistance.DTO.ChatMessage;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import com.example.SystemAssistance.Service.ConversationService;


//@Controller
//public class ChatController {
//
//	@MessageMapping("/chat.send")
//	@SendTo("/topic/public")
//	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
//		return chatMessage;
//	}
//	
//	@MessageMapping("/chat.register")
//	@SendTo("/topic/public")
//	public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//		//add username in websocket session
//		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//		return chatMessage;
//	}

@Controller
public class ChatController {

	private final Set<String> usernames = ConcurrentHashMap.newKeySet();
	private final ConversationService conversationService;

	public ChatController(ConversationService conversationService) {
		this.conversationService = conversationService;
	}

	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor)
			throws Exception {
		String username = (String) headerAccessor.getSessionAttributes().get("username");

		if (username == null) {
			throw new IllegalArgumentException("User is not registered");
		}

		conversationService.addMessage(chatMessage, usernames);
		return chatMessage;
	}

	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor)
			throws Exception {
		String username = chatMessage.getSender();

		synchronized (usernames) {
			if (usernames.size() >= 2) {
				throw new IllegalArgumentException("Only two users can register");
			}

			usernames.add(username);
			headerAccessor.getSessionAttributes().put("username", username);

			// If exactly two users are registered, initialize the conversation
			if (usernames.size() == 2) {
				conversationService.initializeConversation(usernames);
			}
		}

		return chatMessage;
	}
}

/*
 * @MessageMapping("/chat.send")
 * 
 * @SendTo("/topic/public") public ChatMessage sendMessage(@Payload ChatMessage
 * chatMessage) { return chatMessage; }
 * 
 * @MessageMapping("/chat.register") public void register(@Payload ChatMessage
 * chatMessage, SimpMessageHeaderAccessor headerAccessor) {
 * headerAccessor.getSessionAttributes().put("username",
 * chatMessage.getSender());
 * //headerAccessor.getSessionAttributes().put("otherUsername",
 * chatMessage.getOtheruser()); // Assuming you have a method to get other
 * user's username }
 * 
 * @MessageMapping("/chat.message") public void addMessage(@Payload ChatMessage
 * chatMessage, SimpMessageHeaderAccessor headerAccessor) { try {
 * System.out.println("invoked"); conversationService.addMessage(chatMessage,
 * headerAccessor); } catch (Exception e) { e.printStackTrace(); // Handle the
 * exception as per your application's error handling strategy } }
 */
