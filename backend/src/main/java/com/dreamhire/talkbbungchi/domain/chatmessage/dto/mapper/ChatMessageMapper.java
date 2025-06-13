package com.dreamhire.talkbbungchi.domain.chatmessage.dto.mapper;

import com.dreamhire.talkbbungchi.domain.chatmessage.dto.request.ChatMessageRequest;
import com.dreamhire.talkbbungchi.domain.chatmessage.dto.response.ChatMessageResponse;
import com.dreamhire.talkbbungchi.domain.chatmessage.entity.ChatMessage;

import java.time.LocalDateTime;

public class ChatMessageMapper {

    public static ChatMessage toEntity(ChatMessageRequest request) {
        return ChatMessage.builder()
                .senderId(request.getSenderId())
                .chatRoomId(request.getChatRoomId())
                .content(request.getContent())
                .type(request.getType())
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ChatMessageResponse toResponse(ChatMessage entity) {
        return new ChatMessageResponse(
                entity.getId(),
                entity.getSenderId(),
                entity.getChatRoomId(),
                entity.getContent(),
                entity.getType(),
                entity.isRead(),
                entity.getCreatedAt()
        );
    }
}
