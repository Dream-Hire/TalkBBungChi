package com.dreamhire.talkbbungchi.domain.chatmessage.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ChatMessageResponse {
    private String id;
    private Long senderId;
    private Long chatRoomId;
    private String content;
    private String type;
    private boolean isRead;
    private LocalDateTime createdAt;
}
