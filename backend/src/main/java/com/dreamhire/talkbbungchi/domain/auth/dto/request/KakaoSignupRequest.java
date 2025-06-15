package com.dreamhire.talkbbungchi.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class KakaoSignupRequest {
    private String nickname;
    private String phoneNumber;
}