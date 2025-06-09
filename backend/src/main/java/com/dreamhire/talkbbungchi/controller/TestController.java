package com.dreamhire.talkbbungchi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "🚀 TalkBBungChi Backend is running successfully!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/test")
    public String test() {
        return "Dream-Hire TalkBBungChi Service - Backend API Test";
    }
}