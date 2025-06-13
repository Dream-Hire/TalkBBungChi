package com.dreamhire.talkbbungchi.domain.chatmessage.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_messages")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

    @Id
    private String id;

    private Long senderId;
    private Long chatRoomId;
    private String content;
    private String type; // 예: TEXT, IMAGE
    private boolean isRead;
    private LocalDateTime createdAt;
}
