## 🎬 영화 리뷰 플랫폼 | 리뷰보다

**리뷰보다**  는 선호하는 장르의 영화 리뷰를 자유롭게 공유하고, 추천 영화를 확인할 수 있는 웹 플랫폼입니다. 

자신만의 영화 감상 기록을 쌓으며, 다양한 장르의 영화에 대한 의견을 나눌 수 있는 공간입니다.


![alt text](rm1.png)

<br>

### 📄 목차

1. [개요](#1-개요)
2. [주요기능 소개 및 설명](#2-주요기능-소개-및-설명)
- 회원가입
- 로그인
- 영화 목록 보기
- 영화 리뷰 관리
- 영화 상세 보기
- 회원 관리
3. [페이지 소개](#3-페이지-소개)
4. [기술스택](#4-기술-스택)
5. [팀원](#5-팀원-소개)

<br>
<hr>

### 1) 개요

-프로젝트 이름: 리뷰보다 

-프로젝트 기간: 2025.05.10 ~ 2025.06.07

-사용자들이 영화를 리뷰하고 서로의 평가를 공유하며 선호 장르 기반의 영화 추천과 인기 영화 정보를 제공받을 수 있는 플랫폼을 구축하는 것이 이번 프로젝트의 목표입니다.(본 프로젝트는 2025-1학기 오픈소스sw개론 수업에 기반을 두었습니다.)

<hr>


### 2) 주요기능 소개 및 설명

**회원가입**: 
- 닉네임,아이디,비밀번호,선호하는 장르를 기입합니다. 
- 존재하는 아이디일 경우 회원가입에 실패합니다.

**로그인**: 
- 아이디,비밀번호를 확인합니다. 

**영화 목록 보기**: 
- 평점이 높은 영화를 인기영화로 게시하여 메인화면을 통해 가장 인기 있는 Top 5개의 영화를 보여줍니다.
- 사용자가 선호하는 장르를 기준으로 메인화면을 통해 영화정보를 보여줍니다.

**영화 리뷰 관리**: 
- 영화는 평점, 리뷰, 장르를 가져 사용자는 리뷰를 작성하고 싶은 영화를 선택하여 리뷰를 등록할 수 있습니다. 
- 영화 상세보기 화면을 통해 작성된 리뷰 전체 목록을 확인할 수 있습니다. 

**영화 상세 보기**: 
- 영화에 대한 설명을 확인할 수 있으며, 해당 영화에 모든 리뷰 조회가 가능합니다. 
- 메인화면에서 영화를 클릭하거나 마이페이지에 등록된 리뷰 클릭시 상세보기 페이지를 확인할 수 있습니다.


**회원 관리**: 
- 회원은 작성한 리뷰 목록을 갖습니다.
- 회원은 닉네임 수정이 가능합니다.
- 회원은 선호하는 장르 수정이 가능합니다.

<br>
<hr>

### 3) 페이지 소개

#### 회원가입/로그인

<div style="display: flex; gap: 10px;">
  <img src="rm2.png"/>
  <img src="rm3.png"/>
</div>


#### 메인페이지
<div style="display: flex; gap: 10px;">
  <img src="rm4.png"/>
  <img src="rm5.png"/>
</div>


#### 리뷰 관리 페이지
<div style="display: flex; gap: 10px;">
  <img src="rm6.png"/>
  <img src="rm7.png"/>
</div>

#### 회원 관리 페이지

<div style="display: flex; gap: 10px;">
  <img src="rm8.png"/>
  <img src="rm9.png"/>
  <img src="rm10.png"/>
</div>


#### 영화 상세보기 페이지
<div style="display: flex; gap: 10px;">
  <img src="rm11.png"/>
  <img src="rm12.png"/>
</div>

<br>
<hr>


### 4) 기술 스택

#### 프론트엔드
<p>
  <img src="https://img.shields.io/badge/React-61DAFB?style=flat&logo=react&logoColor=white"/>
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=css3&logoColor=white"/>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=black"/>

</p>

벡엔드

<p>
  <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat&logo=spring&logoColor=white"/>
</p>




<hr>

### 5) 팀원 소개

| 이름   | 역할        | GitHub |
|--------|-------------|--------|
| 김세진 | 백엔드  | [@sejkim2](https://github.com/sejkim2) |
| 김영민 | 백엔드      | [@youngmin-OS](https://github.com/youngmin-OS) |
| 김하연 | 프론트엔드    | [@hayeon00](https://github.com/hayeon00) |
| 황하은 | 프론트엔드    | [@haeun0529](https://github.com/haeun0529) |

<br>
<hr>

<br>
<br>

## Docker 사용 매뉴얼
docker는 어떤 환경에서도 실행 가능한 프로그램을 패키징 하는 기술 입니다.
docker는 리눅스 기반의 경량화된 가상 머신이라고 보시면 되며, 프로그램을 실행하는 데 필요한 모든 요소(언어, 프레임워크, 라이브러리, 운영체제 등)를 하나의 도커 이미지에 담아 실행합니다.
이 이미지를 기반으로 컨테이너 라는 단위로 실행되며 환경 불일치 문제 없이 어느 컴퓨터에서도 동일하게 작동함을 보장합니다. 즉, 개발자는 오로지 개발에만 집중할 수 있고 어느 환경에서도 배포가 가능한 상태가 됩니다.

## Docker 기본 용어
1. 이미지 : 컨테이너를 만들기 위한 설계도(환경 패키지). Dockerfile로 정의
2. 컨테이너 : 이미지로부터 만들어진 실행 중인 인스턴스(프로세스)
3. Dockerfile : 이미지 생성 방법을 정의한 파일
4. docker-compose : 여러 컨테이너를 관리할 수 있는 설정 파일

## Docker 주요 명령어
1. 이미지 빌드
```
docker build -t [image name] .
```
2. 컨테이너 실행
```
docker run -p [port:port] [image name]
```
3. 컨테이너를 백그라운드로 실행
```
docker run -d -p [port:port] [image name]
```
4. 실행 중인 컨테이너 목록 확인
```
docker ps -a
```
5. 컨테이너 중지
```
docker stop [container_ID]
```
6. 컨테이너 내부 접속
```
docker exec -it [container_ID 혹은 container_name] bash
```
7. 컨테이너 로그 확인
```
docker logs [container_ID 혹은 container_name]
```

1, 2, 3, 5 번 항목은 Makefile에 명령어를 단순화 하여 작성했기 때문에 실제로 사용하실 일은 거의 없을 것 같습니다.

## Makefile 사용법

복잡한 docker 명령어를 단순화 하기 위해 Makefile을 만들었습니다. Makefile이란 소프트웨어 빌드 자동화 도구를 뜻하며 컴파일 명령을 자동화하고 의존성 관리를 쉽게 사용하는데 사용합니다. (저희 프로젝트에서는 복잡한 도커 빌드 명령을 단순하게 사용하기 위해 사용하였습니다.)

1. 빌드 (이미지 생성 및 컨테이너 실행)
```
make
```
2. 재빌드 (단, 의존성이 제대로 읽히지 않는 경우가 있어서 재빌드 시 make down - make 순서로 재빌드 할 것을 권장합니다.)
```
make up
```
4. 실행 중인 컨테이너 모두 종료
```
make down
```
6. 실행 중인 컨테이너 모두 종료 및 이미지 파일 삭제 (하루에 삭제 가능한 횟수가 정해져서 있어서 자주 사용하면 일정 시간 동안 도커 빌드가 막힐수도 있습니다)
```
make clean
```

## 서비스 사용 방법
### 1. 도커 데스크탑을 설치합니다. https://www.docker.com/ 사이트에 접속하여 본인의 운영체제를 선택 후 설치 가능합니다.
<img width="1440" alt="스크린샷 2025-05-16 오후 8 33 28" src="https://github.com/user-attachments/assets/74a163fc-b8b3-4ae3-be21-ef28f2b21e44" />

### 2. 저희 프로젝트는 환경에 의존하지 않고 빌드하기 위해 도커가 필요하기 때문에 항상 프로젝트 빌드 전에는 도커 데스크탑을 실행하여 도커가 실행되는 상태를 유지해야 합니다. 도커 데스크탑 어플리케이션을 실행시키면 아래 사진과 같이 나올 것입니다.

<img width="1440" alt="스크린샷 2025-05-20 오후 11 04 16" src="https://github.com/user-attachments/assets/e5467d3c-de0b-4780-b0a7-66b43b2fad4b" />

### 3. Makefile을 실행하기 위해 먼저 make를 설치해줍니다. 리눅스 CLI 창을 연 뒤, 다음 명령을 수행하여 make를 설치합니다. (저는 이미 설치가 되어있어서 설치하는 사진을 구할수가 없네요... 이거 추가해주시면 감사하겠습니다...)
```
apt update
apt install -y make
```
### 4. make 설치가 완료되면 git clone으로 원격 저장소를 로컬 환경에 복사합니다.
### 5. vscode로 해당 프로젝트 폴더를 열어주세요. (폴더를 vscode에 드래그 하면 해당 폴더를 기준으로 vscode가 열립니다.)
### 6. 그 후 프로젝트의 최상단 디렉토리 (Makefile이 존재하는 디렉토리) 에 .env 파일을 추가합니다.
### 7. .env 파일이 추가되었다면 이제 프로젝트를 빌드할 준비가 완료되었습니다. 해당 디렉토리에서 아래 명령을 수행하면 이미지 생성 및 컨테이너 실행이 수행됩니다. 아래 사진과 같이 실행 결과가 나오면 문제 없이 잘 작동하고 있는 것 입니다.
```
make
```

<img width="1440" alt="스크린샷 2025-05-20 오후 11 10 11" src="https://github.com/user-attachments/assets/f24369f0-17c9-4d23-bab5-456c44d62fd4" />

### 6. 웹 브라우저의 검색창에 localhost:80 이라고 입력하면 아래 사진과 같은 주소로 이동합니다.
<img width="1440" alt="스크린샷 2025-05-20 오후 11 15 53" src="https://github.com/user-attachments/assets/a53e4569-27b3-44f5-9dbc-ede532799d65" />

### 6. 컨테이너는 계속 실행되고 있고 해당 명령 수행 시 모든 컨테이너가 종료됩니다. 아래 사진과 같은 결과가 나오면 됩니다.
```
make down
```
<img width="1440" alt="스크린샷 2025-05-20 오후 11 17 43" src="https://github.com/user-attachments/assets/2e5b8345-3218-4cdc-9a32-7e3aafc388b1" />

### 7. 도커 종료 방법은 오른쪽 상단 메뉴바에서 도커 이미지 -> 우클릭 -> Quit Docker Desktop을 누르시면 됩니다.
<img width="294" alt="스크린샷 2025-05-20 오후 11 23 07" src="https://github.com/user-attachments/assets/52d01c96-8e32-4f5b-9284-192bc077597c" />

### 7. frontend 개발자 분들은 requirements/frontend/srcs 디렉토리에서, backend 개발자 분들은 requirements/backend/app 디렉토리에 본인의 코드를 구현하시면 됩니다.

## 프로젝트 룰
.env 파일에는 환경변수로 사용될 중요한 값들이 저장됩니다. 유출되면 안되기 때문에 .gitignore에 등록하여 커밋을 막았습니다. 각 서비스의 빌드 결과물인 실행 파일 역시 해당 서비스 디렉토리 내부에서 .gitignore로 등록하여 커밋을 막았습니다. 

협업 방식은 GitHub Flow를 따르고 있습니다. 따라서 master 브랜치에 지속적인 병합이 이루어집니다. 브랜치 병합 시 팀원 간 리뷰 없이 병합되는 것을 막기 위한 브랜치 룰을 설정하였습니다. 따라서 병합 시 PR이 올라가고 다른 팀원 최소 1명이 검토 후 승인 해주시면 병합이 가능한 상태입니다.

