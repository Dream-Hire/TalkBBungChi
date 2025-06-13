package com.dreamhire.talkbbungchi.domain.user.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @Size(min = 2, max = 30, message = "닉네임은 2-30자 사이여야 합니다.")
    private String nickname;

    @Size(max = 500, message = "프로필 이미지 URL은 500자를 초과할 수 없습니다.")
    private String profileImageUrl;
}