package com.dreamhire.talkbbungchi.domain.user.service;

import com.dreamhire.talkbbungchi.domain.user.entity.User;
import com.dreamhire.talkbbungchi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // "kakao"
        Map<String, Object> attributes = oAuth2User.getAttributes();
        log.info("OAuth2 attributes: {}", attributes);

        if ("kakao".equals(registrationId)) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            Long kakaoId = ((Number) attributes.get("id")).longValue();
            String nickname = (String) profile.get("nickname");
            String email = (String) kakaoAccount.get("email");
            String profileImageUrl = (String) profile.get("profile_image_url");

            // DB에 사용자 저장 or 조회
            User user = userRepository.findByKakaoId(kakaoId)
                    .orElseGet(() -> userRepository.save(User.builder()
                            .kakaoId(kakaoId)
                            .nickname(nickname)
                            .phoneNumber("010-0000-0000") // 최초엔 기본값, 이후 수정
                            .profileImageUrl(profileImageUrl)
                            .build()));

            // 스프링 시큐리티에서 사용할 OAuth2User 반환
            return new DefaultOAuth2User(
                    Collections.singleton(() -> "ROLE_USER"),
                    attributes,
                    "id" // 주체가 되는 attributeKey
            );
        }

        throw new IllegalArgumentException("지원하지 않는 로그인 방식입니다: " + registrationId);
    }
}
