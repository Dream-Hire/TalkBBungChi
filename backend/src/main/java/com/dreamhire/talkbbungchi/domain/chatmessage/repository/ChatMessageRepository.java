package com.dreamhire.talkbbungchi.domain.chatmessage.repository;

import com.dreamhire.talkbbungchi.domain.chatmessage.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
