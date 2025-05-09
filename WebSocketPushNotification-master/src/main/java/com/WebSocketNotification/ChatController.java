package com.WebSocketNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/chat.send")
	public void sendMessage(@Payload ChatMessage chatMessage) {
		// Send to specific user
		messagingTemplate.convertAndSend("/topic/messages/" + chatMessage.getToUser(), chatMessage);
	}
}
