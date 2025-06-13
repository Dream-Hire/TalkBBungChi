package com.dreamhire.talkbbungchi.domain.chatmessage.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequest {
    private Long senderId;
    private Long chatRoomId;
    private String content;
    private String type;
}
