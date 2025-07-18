-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS study_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE study_system;

-- 사용자 테이블
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_email (email)
);

-- 스터디 테이블
CREATE TABLE studies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    max_participants INT NOT NULL,
    current_participants INT DEFAULT 0,
    deadline DATETIME NOT NULL,
    creator_id BIGINT NOT NULL,
    creator_name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_creator_id (creator_id),
    INDEX idx_deadline (deadline),
    INDEX idx_created_at (created_at),
    FULLTEXT idx_title_creator (title, creator_name)
);

-- 스터디 신청 테이블
CREATE TABLE study_applications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    study_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (study_id) REFERENCES studies(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE KEY unique_application (study_id, user_id),
    INDEX idx_study_id (study_id),
    INDEX idx_user_id (user_id),
    INDEX idx_applied_at (applied_at)
);

-- 샘플 데이터 삽입
INSERT INTO users (username, password, email, name) VALUES
('admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'admin@example.com', '관리자'),
('user1', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'user1@example.com', '사용자1'),
('user2', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'user2@example.com', '사용자2');

INSERT INTO studies (title, description, max_participants, deadline, creator_id, creator_name) VALUES
('Java 스터디', 'Java 기초부터 심화까지 함께 공부할 스터디원을 모집합니다.', 5, '2024-02-28 23:59:59', 1, '관리자'),
('Spring Boot 프로젝트', 'Spring Boot를 활용한 실전 프로젝트를 진행할 팀원을 찾습니다.', 4, '2024-03-15 18:00:00', 2, '사용자1'),
('알고리즘 스터디', '코딩테스트 대비 알고리즘 문제를 함께 풀어보는 스터디입니다.', 6, '2024-03-01 12:00:00', 1, '관리자');

INSERT INTO study_applications (study_id, user_id) VALUES
(1, 2),
(1, 3),
(2, 1),
(3, 2);
