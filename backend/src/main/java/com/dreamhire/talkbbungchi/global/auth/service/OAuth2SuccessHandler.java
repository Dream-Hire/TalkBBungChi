package com.dreamhire.talkbbungchi.global.auth.service;

import com.dreamhire.talkbbungchi.domain.user.entity.User;
import com.dreamhire.talkbbungchi.domain.user.repository.UserRepository;
import com.dreamhire.talkbbungchi.global.utils.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Long kakaoId = ((Number) oAuth2User.getAttributes().get("id")).longValue();

        Optional<User> userOptional = userRepository.findByKakaoId(kakaoId);
        if (userOptional.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "회원 정보를 찾을 수 없습니다.");
            return;
        }

        User user = userOptional.get();
        String accessToken = jwtTokenProvider.createToken(user.getUserId(), user.getNickname());

        // ✅ 프론트엔드로 리디렉션 (토큰을 쿼리 파라미터로 전달)
        String redirectUrl = "http://localhost:3000/oauth2/callback?token=" + URLEncoder.encode(accessToken, StandardCharsets.UTF_8);
        response.sendRedirect(redirectUrl);
    }
}
