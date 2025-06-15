package com.dreamhire.talkbbungchi.domain.auth.service;

import com.dreamhire.talkbbungchi.domain.auth.dto.model.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private static final String KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";
    private final RestTemplate restTemplate = new RestTemplate();

    public KakaoUserInfo getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<KakaoUserInfo> response = restTemplate.exchange(
                KAKAO_USER_INFO_URI,
                HttpMethod.GET,
                entity,
                KakaoUserInfo.class
        );

        return response.getBody();
    }
}