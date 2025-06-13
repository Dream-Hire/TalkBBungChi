package com.dreamhire.talkbbungchi.domain.chatmessage.service;

import com.dreamhire.talkbbungchi.domain.chatmessage.dto.request.ChatMessageRequest;
import com.dreamhire.talkbbungchi.domain.chatmessage.dto.response.ChatMessageResponse;
import com.dreamhire.talkbbungchi.domain.chatmessage.dto.mapper.ChatMessageMapper;
import com.dreamhire.talkbbungchi.domain.chatmessage.entity.ChatMessage;
import com.dreamhire.talkbbungchi.domain.chatmessage.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatMessageService {

    private final ChatMessageRepository repository;

    public ChatMessageService(ChatMessageRepository repository) {
        this.repository = repository;
    }

    public ChatMessageResponse save(ChatMessageRequest request) {
        ChatMessage entity = ChatMessageMapper.toEntity(request);
        return ChatMessageMapper.toResponse(repository.save(entity));
    }

    public List<ChatMessageResponse> findAll() {
        return repository.findAll().stream()
                .map(ChatMessageMapper::toResponse)
                .collect(Collectors.toList());
    }
}
