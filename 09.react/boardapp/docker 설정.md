# Docker 설정
* 더블슬래쉬인 경우는 git bash terminal에서 실행하면 한 개의 /는 윈도우의 폴더로 인식한다.
* 따라서, git bash에서 docker exec 로 실행할 경우 도커컨테이너 내부폴더에 접근하려면 2개의 슬래쉬로 실행한다.

## 1. docker step by step

### 1. docker network
```bash
docker network ls
docker network create fullstack-network
```

### 2. docker volume
```bash
# device경로는 절대 경로 지정
docker volume create \
  --driver local \
  --opt type=none \
  --opt device="d:/mysql/boarddb" \  
  --opt o=bind \
  boarddb-volume
```

### 3. docker mysql
```bash
docker run -d \
  --name mysql-container \
  --network fullstack-network \
  -v boarddb-volume:/var/lib/mysql \
  -p 3307:3306 \
  -e MYSQL_ROOT_PASSWORD=12345 \
  mysql:latest
```

### 4. docker frontend
```bash
docker run -dit \
  --name frontend-container \
  --network fullstack-network \
  -p 80:80 \
  ubuntu:latest

docker exec frontend-container apt update

docker exec frontend-container apt install nginx -y

docker exec frontend-container service nginx start
# nginx 연결확인 : http://localhost:80/

# 1. frontend 설정
docker exec frontend-container ls etc/nginx
docker exec frontend-container ls var/www/html 

# 2. frontend build
npm run build

#  와일드카드를 컨테이너 내부에서 처리하도록 전체 명령을 인용하거나 bash -c를 사용해야 합니다.
docker exec frontend-container bash -c "rm -rf /var/www/html/*.html"

# /var/www/html일 경우 MSYS (Git Bash)가 /… 로 시작하는 경로를
# C:/Program Files/Git/... 등 자동으로 Windows 경로로 변환하기 떄문에
# MSYS가 자동 변환을 건너뛰도록, POSIX 루트를 명시 또는
# Git Bash 의 경로 변환 기능을 비활성화하고 실행하기 위해
# MSYS_NO_PATHCONV=1 docker exec frontend-container ls /var/www/html 처럼 실행행
docker exec frontend-container ls //var/www/html 

# 3. front app(build폴더) 복사
docker cp ./build/. frontend-container:/var/www/html
docker exec frontend-container service nginx restart
```

### 5. docker backend
```bash
# 1. docker 이미지생성
docker run -dit \
  --name backend-container \
  --network fullstack-network \
  -p 8090:8090 \
ubuntu:latest

# 2. jdk 설치
docker exec backend-container apt update
docker exec backend-container apt install openjdk-17-jdk -y
docker exec backend-container java --version

# 3. backend app 빌드
# application.yml에 mysql url을 mysql 컨테이너명으로 지정
# url: jdbc:mysql://mysql-container:3307/boarddb?createDatabaseIfNotExist=true

# 4. jar파일 생성
# windows command 명령
./gradlew clean
./gradlew build

# 5. jar파일 backend-container에 복사
docker exec backend-container ls home/ubuntu

# 폴더를 생성하지 않아도 백앤드프로그램실행시 자동생성하기 때문에 별도로 폴더를 생성하지 않아도 된다.
docker exec backend-container mkdir -p root/app_uploads

# ./build/libs/. 는 backend 루트폴더에서 실행할 것
# 도커컨테이너에 app.jar가 있을 경우 삭제
docker exec backend-container rm //home/ubuntu/app.jar
docker exec board-back ps -ef
docker exec backend-container kill -9 [프로세스아이디]

docker cp "$(ls ../board-back/build/libs/*.jar | head -n1)" backend-container:/home/ubuntu/app.jar
docker exec backend-container ls home/ubuntu

# 6. jar파일 실행
# application.yml에 jpa.hibernate.ddl-auto: create로 실행한 후에 update로 변경 후에 재빌드(app.jar 생성)
# board_list_view가 테이블로 생성되니 drop후 view로 생성할 것것
docker exec  backend-container java -jar //home/ubuntu/app.jar

# 7. app.jar 실행터미널 종료
# git bash에서 ctrl+c로 종료하더레도 도커컨테이너에서는 app.jar가 종료되지 않고 실행상태로 유지된다.
# 직접 도커컨테이너 내부에서 실행할 경우 터미널을 종료해도 프로세스가 실행하는 방법은 nohup 명령을 사용하면 된다.
# docker exec  backend-container nohup java -jar //home/ubuntu/app.jar

```

## 2. dockerfile

### Dockerfile for Backend (board-back)
* board-back 루트폴더에 "Dockerfile.backend"로 저장
```Dockerfile
# Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Create app directory
WORKDIR /home/app

# Copy application JAR into container

COPY ./build/libs/board-back-0.0.1-SNAPSHOT.jar ./app.jar

# Expose application port
EXPOSE 8090

# Run the jar
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]
```

```bash
# 1. 이미지빌드 - # board-back 루트폴더에서 실행행
docker build \
  -f Dockerfile.backend \
  -t board-backend:latest \
  .

# 2. 컨테이너실행
docker run -d \
  --name backend-container_1 \
  --network fullstack-network \
  -p 8090:8090 \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://mysql-container:3306/boarddb?createDatabaseIfNotExist=true" \
  -e SPRING_DATASOURCE_USERNAME="root" \
  -e SPRING_DATASOURCE_PASSWORD="12345" \
  board-backend:latest
```

---

### Dockerfile for Frontend (nginx)
* board-front 루트폴더에 "Dockerfile.frontend"로 저장
```Dockerfile
# Use official NGINX image
FROM nginx:alpine

# Remove default content
RUN rm -rf /usr/share/nginx/html/*

# Copy build output
COPY build/. /usr/share/nginx/html

# Expose HTTP port
EXPOSE 80
```

```bash
# 1. 이미지빌드 - # board-front 루트폴더에서 실행행
docker build \
  -f Dockerfile.frontend \
  -t board-frontend:latest \
  .

# 2. 컨테이너시행
docker run -d \
  --name frontend-container_1 \
  --network fullstack-network \
  -p 80:80 \
  board-frontend:latest
```

## 3. docker compose
* 전체 프로젝트 루트에 아래 파일들을 배치:
  1. Dockerfile.backend
  2. Dockerfile.frontend
  3. docker-compose.yml

* compose를 실행기 전에 ./mysql/boarddb 는 프로젝트 루트에 사전에 생성할 것
* mkdir -p ./mysql/boarddb
* Windows Git Bash 환경에서 상대경로는 git bash환경에 맞는 폴더로 변환되기 때문에 절대 경로를 사용
* ./는 /run/desktop/mnt/host/d/20240809_green/09.react/boardapp/mysql/boarddb로 변경된다.
* 따라서 bash 터미널이 아니라 cmd or powershell에서 실행할 것

```bash
# 경로에러발생
# Error response from daemon: failed to populate volume: error while mounting volume 
# '/var/lib/docker/volumes/boarddb-volume/_data': failed to mount local volume: 
# mount /run/desktop/mnt/host/d/20240809_green/09.react/boardapp/mysql/boarddb:/var/lib/docker/volumes/boarddb-volume/_data, 
# flags: 0x1000: no such device
# 이 에러는 Docker가 Windows 경로(특히 d: 드라이브)를 리눅스 컨테이너에 마운트하려고 할 때 발생합니다.
# 주요 원인은 다음과 같습니다:
# Windows 경로 마운트 문제
# Docker Desktop for Windows는 WSL2(리눅스 커널) 위에서 동작합니다.
# Windows의 드라이브(D:, C: 등)를 컨테이너에 마운트하려면, 해당 드라이브가 Docker Desktop에서 공유되어 있어야 합니다.
# 경로 표기법이 리눅스와 달라서, d:/... 경로가 올바르게 인식되지 않을 수 있습니다.
# device: "d:/20240809_green/09.react/boardapp/mysql/boarddb"

# device에는 호스트 절대 경로(Windows 스타일)만 지정해야 되지만 
# 윈도우폴더명이 linux(도커내부에 경로로 변환)폴더명으로 인식되기 때문에
# 에러가 발생한다. 자료준비시점에서는 해결을 못해서 나중에 해결할 것
# 정의된 볼륨은 도커 Docker Desktop (WSL 2 백엔드)이있는 Windows 11에서는 
# 이름이 지정된 volume의 데이터가 실제로 포함 된 WSL2 VM 내부 
# "C:\Users\gilba\AppData\Local\Docker\wsl\disk\"에 있다.
# docker volume inspect board-volume로 확인("Mountpoint": "/var/lib/docker/volumes/board-volume/_data")
```

### docker-compose.yml
```yaml
version: 
  '3.9'

networks:
  board-network:
    name: board-network
    driver: bridge

volumes:
  board-volume:
    name: board-volume
    # driver: local
    # driver_opts:
    #   type: none
    #   o: bind
    # device: "${PWD_WIN}/mysql/boarddb"     

services:
  mysql:
    image: mysql:latest
    container_name: mysql-container
    networks:
      - board-network
    ports:
      - "3307:3306"
    environment:
      MYSQL_ROOT_PASSWORD: "12345"
    volumes:
      - board-volume:/var/lib/mysql
    restart: always

  backend:
    build:
      context: ./board-back
      dockerfile: Dockerfile.backend
    container_name: backend-container
    networks:
      - board-network
    ports:
      - "8090:8090"
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: "jdbc:mysql://mysql-container:3306/boarddb?createDatabaseIfNotExist=true"
      SPRING_DATASOURCE_USERNAME: "root"
      SPRING_DATASOURCE_PASSWORD: "12345"

  frontend:
    build:
      context: ./board-front
      dockerfile: Dockerfile.frontend
    container_name: frontend-container
    networks:
      - board-network
    ports:
      - "80:80"
    depends_on:
      - backend 
```

```bash
# 1. 빌드+실행 (백그라운드)
docker-compose up --build -d
# --build : 변경된 Dockerfile 이나 소스가 있으면 이미지를 다시 빌드
# -d : 백그라운드(Detached) 모드로 실행

# 도커컴포즈실행후 backend는 시작시도후에 중지된 상태로 된다.
docker-compose logs -f # 명령으로로 backend로 로그를 확인 해보면 JPA관련에러가
# 발생하는데 원인은 데이터베이스 및 테이블이 정상적으로 생성되지 않았기 때문에
# 재시작을 하면 테이블이 정상적으로 생성된다.
# 테이블생성후 board_list_view가 테이블로 생성되어 있는데 이를 drop하고
# create or replace view board_list_view 명령을 실행하여 view를 생성하면 된다.

#------------------------------------------------------------------------------
# 2. 로그 확인
docker-compose logs -f
# -f : 실시간(팔로우) 로그 출력

#------------------------------------------------------------------------------
# 3. 특정 서비스만 보고 싶으면:
docker-compose logs -f backend
docker-compose logs -f frontend
docker-compose logs -f mysql

#------------------------------------------------------------------------------
# 4. 중지 및 정리
docker-compose down
# 실행 중인 컨테이너를 중지하고 네트워크, 익명 볼륨도 함께 삭제
# 필요한 옵션:
# docker-compose down --volumes : 볼륨까지 삭제
# docker-compose down --rmi all : 사용된 이미지까지 삭제
```

> **Usage:**
> 1. Place `Dockerfile.backend` and `Dockerfile.frontend` in project root alongside your `build/` and `mysql/` directories.
> 2. Adjust `COPY` paths if necessary.
> 3. Run:
>    ```bash
>    docker-compose up --build -d
>    ```


### 일괄로 실행하는 방법 : run_board_compose.sh
> 1. 환경파일작성 : .env
>```bash
>PWD_WIN=D:/20240809_green/09.react/boardapp
>```

>2. sh 스크립트트
>```bash
>#!/usr/bin/env bash
>set -e
>
># 1) Windows 스타일 절대 경로 얻어와 export
># 경로문제는 미해결 상태태
>export PWD_WIN=$(pwd -W)
>
># 2) 도커 컴포즈 띄우기
>docker-compose up --build -d
>```
>
> 3. 스크립트실행
>```bash
>sh run_board_compose.sh
>```