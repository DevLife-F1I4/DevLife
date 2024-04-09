# Java/Spring-Project-Devlife
자바/스프링을 이용한 회원제 커뮤니티 서비스


## 🌞 프로젝트 소개
개발자들의 라이프스타일과 반려동물 사진을 공유하고 힐링하는 커뮤니티입니다.

## 🕰 개발 기간
* 24.03.25(월) ~ 24.04.08(월)

## 👨‍👧‍👧 프로젝트 팀 구성 및 역할
- 👩‍💻 팀장 [윤수빈](https://github.com/soogoori) - 사용자 인증(로그인 및 회원가입), 글 목록 조회, CI/CD 구축 및 배포
- 👩‍💻 팀원 [윤인선](https://github.com/InseonYoon) - 글작성(등록, 수정, 삭제), 글 상세 조회  
- 👩‍💻 팀원 [임채민](https://github.com/cmleem) - 댓글(등록, 수정, 삭제), 관리자 페이지 개발
- 👩‍💻 팀원 [최은서](https://github.com/eundoechoi) - 글 목록 조회, 문서 작성, 화면 설계
- 👨‍🔧 멘토 유상욱 - 프로젝트 진행 중 질의응답

## 🗺️ ERD
![Oreumi10](https://github.com/DevLife-F1I4/DevLife/assets/81796258/1f7cc4e0-ec48-4584-afe9-173d5892d68b)

## 📝 API 명세서
[API 명세서](https://www.notion.so/oreumi/API-b233c229b77a42df9b320de074333b84)


## ⚙ 개발 환경
- `JAVA 17`
<img width="600" alt="image" src="https://github.com/DevLife-F1I4/DevLife/assets/81796258/e4c41232-0904-4ede-8744-bebc74ba9f12">


## 🏛️ 아키텍처 
<img width="612" alt="image" src="https://github.com/DevLife-F1I4/DevLife/assets/81796258/f6a87953-c6d7-41f5-89d4-37a924b33272">


## 주요 화면
<img width="1132" alt="image" src="https://github.com/DevLife-F1I4/DevLife/assets/81796258/de22e1d6-c099-4057-b0bf-a933094f6ae6">
<img width="1128" alt="image" src="https://github.com/DevLife-F1I4/DevLife/assets/81796258/d13794a1-d93f-4b0d-ac62-64c5a7d5ed3c">
<img width="1130" alt="image" src="https://github.com/DevLife-F1I4/DevLife/assets/155498348/33b1e3a0-4efa-438a-beaf-13892f7d00de">

  
## 📍 주요 기능
#### 로그인
- Spring Security + JWT로 사용자 인증
#### 회원가입
- ID 중복 확인
- 닉네임 중복 확인
- 패스워드 및 ID 생성 시 조건 확인 
#### 마이페이지
- 내가 작성한 글, 댓글 조회
- 내 정보 조회/수정(닉네임)
#### 메인 페이지
#### 카테고리별 게시판
- 게시글 작성/조회/수정/삭제
- 댓글 작성/조회/수정/삭제
#### 관리자 페이지
- 사용자 삭제/정보 조회/등급 업데이트
- 관리자 권한 필요
#### 댓글
- 댓글 등록/수정/삭제
- 댓글 작성된 순서대로 조회

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
