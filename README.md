# 스터디 모집 시스템

Spring Boot와 Spring Security를 활용한 스터디 모집 및 신청 시스템입니다.

## 🚀 주요 기능

### 회원 기능
- 회원가입 및 로그인/로그아웃 (Spring Security 세션 기반 인증)
- BCrypt를 통한 비밀번호 암호화
- 마이페이지에서 개설/신청한 스터디 목록 확인

### 스터디 모집 게시판
- 스터디 개설 (제목, 설명, 모집 인원, 마감일 설정)
- 전체 스터디 목록 조회 (페이징 처리)
- 제목 및 작성자 기준 검색 기능
- 스터디 상세 정보 조회

### 스터디 신청 기능
- 로그인한 사용자만 신청 가능
- 동일 스터디 중복 신청 방지
- 모집 정원 초과 시 신청 불가
- 마감일 이후 신청 불가

## 📋 요구사항

- Java 17 이상
- MySQL 8.0 이상
- Gradle 7.0 이상

## 🔧 설치 및 실행 방법

### 1. 프로젝트 클론
\`\`\`bash
git clone https://github.com/ohjihwan/study-system.git
cd study-system
\`\`\`

### 2. 데이터베이스 설정
MySQL에 접속하여 database.sql 파일을 실행합니다.

\`\`\`bash
mysql -u root -p < database.sql
\`\`\`

### 3. 애플리케이션 설정
src/main/resources/application.properties 파일에서 데이터베이스 연결 정보를 수정합니다.

\`\`\`properties
spring.datasource.url=jdbc:mysql://localhost:3306/study_system?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=1234
\`\`\`

### 4. 애플리케이션 실행
\`\`\`bash
./gradlew bootRun
\`\`\`

### 5. 브라우저에서 접속
http://localhost:8080 으로 접속하여 애플리케이션을 사용할 수 있습니다.

## 👤 테스트 계정

데이터베이스 초기화 시 생성되는 테스트 계정입니다. (비밀번호: password)

- **관리자**: admin / password
- **사용자1**: user1 / password  
- **사용자2**: user2 / password

## 📁 프로젝트 구조

\`\`\`
src/
├── main/
│   ├── java/com/study/
│   │   ├── config/          # Spring Security 설정
│   │   ├── controller/      # 컨트롤러
│   │   ├── mapper/          # MyBatis 매퍼 인터페이스
│   │   ├── model/           # 도메인 모델
│   │   ├── service/         # 비즈니스 로직
│   │   └── StudySystemApplication.java
│   └── resources/
│       ├── mybatis/mapper/  # MyBatis XML 매퍼
│       ├── templates/       # Thymeleaf 템플릿
│       └── application.properties
├── database.sql             # 데이터베이스 스키마 및 초기 데이터
├── build.gradle            # Gradle 빌드 설정
└── README.md
\`\`\`

## 🔐 보안 기능

- Spring Security를 통한 세션 기반 인증
- BCrypt를 사용한 비밀번호 암호화
- 로그인하지 않은 사용자의 보호된 페이지 접근 시 로그인 페이지로 리다이렉트
- CSRF 보호 (기본 활성화)

## 📝 주요 페이지

- `/` - 메인 페이지 (스터디 목록으로 리다이렉트)
- `/login` - 로그인 페이지
- `/register` - 회원가입 페이지
- `/studies` - 스터디 목록 페이지
- `/studies/create` - 스터디 개설 페이지 (로그인 필요)
- `/studies/{id}` - 스터디 상세 페이지
- `/studies/search` - 스터디 검색 결과 페이지
- `/mypage` - 마이페이지 (로그인 필요)