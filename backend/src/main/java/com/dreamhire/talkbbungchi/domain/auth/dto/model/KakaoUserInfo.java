package com.dreamhire.talkbbungchi.domain.auth.dto.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KakaoUserInfo {
    private Long id;
    private KakaoAccount kakao_account;

    @Getter
    @ToString
    public static class KakaoAccount {
        private Profile profile;
        private String phone_number;
        private String email;

        @Getter
        @ToString
        public static class Profile {
            private String nickname;
            private String profile_image_url;
        }
    }
}
