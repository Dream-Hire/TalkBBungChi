package com.dreamhire.talkbbungchi.domain.chatmessage.controller;

import com.dreamhire.talkbbungchi.domain.chatmessage.dto.request.ChatMessageRequest;
import com.dreamhire.talkbbungchi.domain.chatmessage.dto.response.ChatMessageResponse;
import com.dreamhire.talkbbungchi.domain.chatmessage.service.ChatMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-messages")
public class ChatMessageController {

    private final ChatMessageService service;

    public ChatMessageController(ChatMessageService service) {
        this.service = service;
    }

    @PostMapping
    public ChatMessageResponse send(@RequestBody ChatMessageRequest request) {
        return service.save(request);
    }

    @GetMapping
    public List<ChatMessageResponse> getAll() {
        return service.findAll();
    }
}
