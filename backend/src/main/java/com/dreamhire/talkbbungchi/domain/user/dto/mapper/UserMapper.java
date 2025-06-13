package com.dreamhire.talkbbungchi.domain.user.dto.mapper;

import com.dreamhire.talkbbungchi.domain.user.dto.request.UserCreateRequest;
import com.dreamhire.talkbbungchi.domain.user.dto.request.UserUpdateRequest;
import com.dreamhire.talkbbungchi.domain.user.dto.response.UserResponse;
import com.dreamhire.talkbbungchi.domain.user.dto.response.UserSimpleResponse;
import com.dreamhire.talkbbungchi.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toEntity(UserCreateRequest request) {
        User user = new User();
        user.setKakaoId(request.getKakaoId());
        user.setNickname(request.getNickname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setProfileImageUrl(request.getProfileImageUrl());
        return user;
    }

    public void updateEntity(User user, UserUpdateRequest request) {
        if (request.getNickname() != null) {
            user.setNickname(request.getNickname());
        }
        if (request.getProfileImageUrl() != null) {
            user.setProfileImageUrl(request.getProfileImageUrl());
        }
    }

    public UserResponse toResponse(User user) {
        return UserResponse.from(user);
    }

    public UserSimpleResponse toSimpleResponse(User user) {
        return UserSimpleResponse.from(user);
    }

    public List<UserResponse> toResponseList(List<User> users) {
        return users.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    public List<UserSimpleResponse> toSimpleResponseList(List<User> users) {
        return users.stream()
                .map(UserSimpleResponse::from)
                .collect(Collectors.toList());
    }
}