package com.dreamhire.talkbbungchi.domain.user.dto.response;

import com.dreamhire.talkbbungchi.domain.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer userId;
    private Long kakaoId;
    private String nickname;
    private String phoneNumber;
    private String profileImageUrl;
    private User.UserStatus isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getKakaoId(),
                user.getNickname(),
                user.getPhoneNumber(),
                user.getProfileImageUrl(),
                user.getIsActive(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getLastLoginAt()
        );
    }
}