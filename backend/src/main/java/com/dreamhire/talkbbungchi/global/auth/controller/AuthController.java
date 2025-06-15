package com.dreamhire.talkbbungchi.global.auth.controller;

import com.dreamhire.talkbbungchi.global.auth.dto.model.KakaoUserInfo;
import com.dreamhire.talkbbungchi.global.auth.dto.request.KakaoSignupRequest;
import com.dreamhire.talkbbungchi.global.auth.service.KakaoAuthService;
import com.dreamhire.talkbbungchi.domain.user.entity.User;
import com.dreamhire.talkbbungchi.domain.user.entity.User.UserStatus;
import com.dreamhire.talkbbungchi.domain.user.repository.UserRepository;
import com.dreamhire.talkbbungchi.global.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/auth/kakao")
@RequiredArgsConstructor
public class AuthController {

    private final KakaoAuthService kakaoAuthService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> kakaoLogin(@RequestHeader("Authorization") String bearerToken) {
        String accessToken = bearerToken.replace("Bearer ", "");
        KakaoUserInfo kakaoUserInfo = kakaoAuthService.getUserInfo(accessToken);

        Optional<User> userOpt = userRepository.findByKakaoId(kakaoUserInfo.getId());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setLastLoginAt(LocalDateTime.now());
            userRepository.save(user);
            String jwt = jwtTokenProvider.createToken(user.getUserId());
            return ResponseEntity.ok().body(jwt);
        } else {
            return ResponseEntity.status(202).body(kakaoUserInfo);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> kakaoSignup(@RequestHeader("Authorization") String bearerToken,
                                         @RequestBody KakaoSignupRequest request) {
        String accessToken = bearerToken.replace("Bearer ", "");
        KakaoUserInfo kakaoUserInfo = kakaoAuthService.getUserInfo(accessToken);

        if (userRepository.findByKakaoId(kakaoUserInfo.getId()).isPresent()) {
            return ResponseEntity.badRequest().body("이미 가입된 사용자입니다.");
        }

        User user = User.builder()
                .kakaoId(kakaoUserInfo.getId())
                .nickname(request.getNickname())
                .phoneNumber(request.getPhoneNumber())
                .profileImageUrl(kakaoUserInfo.getKakao_account().getProfile().getProfile_image_url())
                .isActive(UserStatus.ACTIVE)
                .build();

        userRepository.save(user);
        String jwt = jwtTokenProvider.createToken(user.getUserId());
        return ResponseEntity.ok().body(jwt);
    }
}
