package com.WebSocketNotification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
	private String fromUser;
	private String toUser;
	private String content;


}
