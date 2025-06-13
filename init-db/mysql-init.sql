-- 데이터베이스 사용 선언
USE talkbbungchi;

-- 기존 테이블이 있다면 삭제 (선택사항)
-- DROP TABLE IF EXISTS chat_rooms;
-- DROP TABLE IF EXISTS ai_personas;
-- DROP TABLE IF EXISTS users;

-- users 테이블 생성
CREATE TABLE `users` (
    `user_id` INT NOT NULL AUTO_INCREMENT COMMENT '사용자 고유 식별자',
    `kakao_id` BIGINT NOT NULL COMMENT '카카오 고유 식별자',
    `nickname` VARCHAR(30) NOT NULL,
    `phone_number` VARCHAR(20) NOT NULL,
    `profile_image_url` VARCHAR(500) NULL,
    `is_active` ENUM('ACTIVE', 'INACTIVE', 'WITHDRAWN') NOT NULL DEFAULT 'ACTIVE' COMMENT 'ACTIVE, INACTIVE, WITHDRAWN',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` DATETIME NULL,
    `last_login_at` DATETIME NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `UK_users_kakao_id` (`kakao_id`)
);

-- ai_personas 테이블 생성
CREATE TABLE `ai_personas` (
    `persona_id` INT NOT NULL AUTO_INCREMENT COMMENT 'AI 페르소나 고유 식별자',
    `owner_id` INT NOT NULL COMMENT '페르소나 소유자',
    `persona_name` VARCHAR(30) NOT NULL COMMENT 'AI가 모방할 인물의 이름/별명',
    `profile_image_url` VARCHAR(500) NULL COMMENT '페르소나 프로필 이미지',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '페르소나 생성 일시',
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 일시',
    PRIMARY KEY (`persona_id`),
    FOREIGN KEY (`owner_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE
);

-- chat_rooms 테이블 생성
CREATE TABLE `chat_rooms` (
    `chatroom_id` INT NOT NULL AUTO_INCREMENT COMMENT '채팅방 고유 식별자',
    `room_type` ENUM('RANDOM', 'AI') NOT NULL,
    `owner_id` INT NOT NULL,
    `persona_id` INT NULL,
    `participant_id` INT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`chatroom_id`),
    FOREIGN KEY (`owner_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE,
    FOREIGN KEY (`participant_id`) REFERENCES `users`(`user_id`) ON DELETE SET NULL,
    FOREIGN KEY (`persona_id`) REFERENCES `ai_personas`(`persona_id`) ON DELETE SET NULL
);

-- 인덱스 추가 (성능 최적화)
CREATE INDEX `IDX_chat_rooms_owner_id` ON `chat_rooms`(`owner_id`);
CREATE INDEX `IDX_chat_rooms_participant_id` ON `chat_rooms`(`participant_id`);
CREATE INDEX `IDX_chat_rooms_persona_id` ON `chat_rooms`(`persona_id`);
CREATE INDEX `IDX_ai_personas_owner_id` ON `ai_personas`(`owner_id`);
CREATE INDEX `IDX_users_kakao_id` ON `users`(`kakao_id`);
CREATE INDEX `IDX_users_is_active` ON `users`(`is_active`);