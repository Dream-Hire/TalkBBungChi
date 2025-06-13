package com.dreamhire.talkbbungchi.domain.user.controller;

import com.dreamhire.talkbbungchi.domain.user.dto.request.UserCreateRequest;
import com.dreamhire.talkbbungchi.domain.user.dto.request.UserUpdateRequest;
import com.dreamhire.talkbbungchi.domain.user.dto.response.UserResponse;
import com.dreamhire.talkbbungchi.domain.user.dto.response.UserSimpleResponse;
import com.dreamhire.talkbbungchi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest request) {
        log.info("POST /api/users - Creating user with kakaoId: {}", request.getKakaoId());
        UserResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("GET /api/users - Getting all users");
        List<UserResponse> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/active")
    public ResponseEntity<List<UserSimpleResponse>> getActiveUsers() {
        log.info("GET /api/users/active - Getting active users");
        List<UserSimpleResponse> users = userService.findActiveUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer userId) {
        log.info("GET /api/users/{} - Getting user by id", userId);
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/kakao/{kakaoId}")
    public ResponseEntity<UserResponse> getUserByKakaoId(@PathVariable Long kakaoId) {
        log.info("GET /api/users/kakao/{} - Getting user by kakaoId", kakaoId);
        return userService.findByKakaoId(kakaoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer userId,
            @RequestBody UserUpdateRequest request) {
        log.info("PUT /api/users/{} - Updating user", userId);
        UserResponse response = userService.updateUser(userId, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}/login")
    public ResponseEntity<Void> updateLastLogin(@PathVariable Integer userId) {
        log.info("PUT /api/users/{}/login - Updating last login", userId);
        userService.updateLastLoginAt(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Integer userId) {
        log.info("PUT /api/users/{}/deactivate - Deactivating user", userId);
        userService.deactivateUser(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> withdrawUser(@PathVariable Integer userId) {
        log.info("DELETE /api/users/{} - Withdrawing user", userId);
        userService.withdrawUser(userId);
        return ResponseEntity.ok().build();
    }
}