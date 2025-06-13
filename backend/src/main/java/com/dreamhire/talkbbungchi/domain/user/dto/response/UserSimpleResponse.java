package com.dreamhire.talkbbungchi.domain.user.dto.response;

import com.dreamhire.talkbbungchi.domain.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleResponse {
    private Integer userId;
    private String nickname;
    private String profileImageUrl;

    public static UserSimpleResponse from(User user) {
        return new UserSimpleResponse(
                user.getUserId(),
                user.getNickname(),
                user.getProfileImageUrl()
        );
    }
}