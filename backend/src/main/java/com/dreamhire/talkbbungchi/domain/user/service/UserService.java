package com.dreamhire.talkbbungchi.domain.user.service;

import com.dreamhire.talkbbungchi.domain.user.dto.request.UserCreateRequest;
import com.dreamhire.talkbbungchi.domain.user.dto.request.UserUpdateRequest;
import com.dreamhire.talkbbungchi.domain.user.dto.response.UserResponse;
import com.dreamhire.talkbbungchi.domain.user.dto.response.UserSimpleResponse;
import com.dreamhire.talkbbungchi.domain.user.dto.mapper.UserMapper;
import com.dreamhire.talkbbungchi.domain.user.entity.User;
import com.dreamhire.talkbbungchi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        log.info("Creating user with kakaoId: {}", request.getKakaoId());

        if (userRepository.existsByKakaoId(request.getKakaoId())) {
            throw new IllegalArgumentException("이미 존재하는 카카오 ID입니다.");
        }

        if (userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        log.info("User created successfully with id: {}", savedUser.getUserId());

        return userMapper.toResponse(savedUser);
    }

    public Optional<UserResponse> findByKakaoId(Long kakaoId) {
        return userRepository.findByKakaoId(kakaoId)
                .map(userMapper::toResponse);
    }

    public Optional<UserResponse> findById(Integer userId) {
        return userRepository.findById(userId)
                .map(userMapper::toResponse);
    }

    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toResponseList(users);
    }

    public List<UserSimpleResponse> findActiveUsers() {
        List<User> users = userRepository.findByIsActive(User.UserStatus.ACTIVE);
        return userMapper.toSimpleResponseList(users);
    }

    @Transactional
    public UserResponse updateUser(Integer userId, UserUpdateRequest request) {
        log.info("Updating user with id: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 닉네임 중복 체크 (자신 제외)
        if (request.getNickname() != null &&
                !user.getNickname().equals(request.getNickname()) &&
                userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        userMapper.updateEntity(user, request);
        User updatedUser = userRepository.save(user);
        log.info("User updated successfully with id: {}", updatedUser.getUserId());

        return userMapper.toResponse(updatedUser);
    }

    @Transactional
    public void updateLastLoginAt(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Transactional
    public void deactivateUser(Integer userId) {
        log.info("Deactivating user with id: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.setIsActive(User.UserStatus.INACTIVE);
        userRepository.save(user);

        log.info("User deactivated successfully with id: {}", userId);
    }

    @Transactional
    public void withdrawUser(Integer userId) {
        log.info("Withdrawing user with id: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.setIsActive(User.UserStatus.WITHDRAWN);
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);

        log.info("User withdrawn successfully with id: {}", userId);
    }
}