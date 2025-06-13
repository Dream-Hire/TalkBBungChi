package com.dreamhire.talkbbungchi.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByKakaoId(Long kakaoId);

    Optional<User> findByNickname(String nickname);

    boolean existsByKakaoId(Long kakaoId);

    boolean existsByNickname(String nickname);
}
