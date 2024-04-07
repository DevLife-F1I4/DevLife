# Java/Spring-Project-Devlife
자바/스프링을 이용한 회원제 커뮤니티 서비스


## 🌞 프로젝트 소개
개발자들의 라이프스타일과 반려동물 사진을 공유하고 힐링하는 커뮤니티입니다.

## 🕰 개발 기간
* 24.03.25(월) ~ 24.04.08(월)

## 👨‍👧‍👧 프로젝트 팀 구성 및 역할
- 팀장 윤수빈 - 사용자 인증(로그인 및 회원가입), 배포
- 팀원 윤인선 - 글작성 
- 팀원 임채민 - 댓글, 관리자
- 팀원 최은서 - 글목록
- 멘토 유상욱 - 프로젝트 진행 중 질의응답

## ⚙ 개발 환경
- `JAVA 17`
- **IDE** : IntelliJ IDEA
- **Framework** : SpringBoot
- **Database** : PostgreSQL
- **Template** : Thymeleaf

## 📍 주요 기능
#### 로그인
- DB 값 검증
#### 회원가입
- ID 중복 확인
- 닉네임 중복 확인
#### 마이페이지
- 내가 작성한 글, 댓글 조회
- 내 정보 조회/수정(닉네임)
#### 메인 페이지
#### 카테고리별 게시판
- 게시글 작성/수정/삭제
- 댓글 작성/수정/삭제
#### 관리자 페이지
- 사용자 삭제/정보 조회/등급 업데이트
- 특정 댓글, 게시글 삭제
- 관리자 권한 필요


------


## 🌈 Branch
> 브랜치는 main branch, develop branch, feature branch 총 3개의 브랜치를 사용한다.

## 🌞 Commit Message Convention
| Emoticon | Commit Type | Desc |
| --- | --- | --- |
| ✨ | feat | 새로운 기능 추가 |
| 🐛 | fix | 버그 수정 |
| 📝 | docs | 문서 수정 (md 파일) |
| ♻️ | refactor | 코드 리팩토링 |
| 💄 | style | 코드 formatting, 세미콜론 누락, 코드 자체의 변경이 없는 경우 |
| ✅ | test | 테스트 코드, 리팩토링 테스트 코드 추가 |
| 🚀 | chore | 패키지 매니저 수정 (Dockerfile, gradle, sh, yml) |
| 🚑 | !hotfix | 급하게 치명적인 버그를 고쳐야 하는 경우 |
