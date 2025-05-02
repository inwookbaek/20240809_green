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
```Dockerfile
# Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Create app directory
WORKDIR /home/app

# Copy application JAR into container
COPY build/libs/app.jar ./app.jar

# Expose application port
EXPOSE 8090

# Run the jar
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]
```

---

### Dockerfile for Frontend (nginx)
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


## 3. docker compose
* 전체 프로젝트 루트에 아래 파일들을 배치:
  1. Dockerfile.backend
  2. Dockerfile.frontend
  3. docker-compose.yml

### docker-compose.yml
```yaml
version: '3.8'

networks:
  fullstack-network:
    driver: bridge

volumes:
  boarddb-volume:
    driver: local
    driver_opts:
      type: none
      o: bind
      device: "${PWD}/mysql/boarddb"

services:
  mysql:
    image: mysql:latest
    container_name: mysql-container
    networks:
      - fullstack-network
    ports:
      - "3307:3306"
    environment:
      MYSQL_ROOT_PASSWORD: "12345"
    volumes:
      - boarddb-volume:/var/lib/mysql

  backend:
    build:
      context: .
      dockerfile: Dockerfile.backend
    container_name: backend-container
    networks:
      - fullstack-network
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
      context: .
      dockerfile: Dockerfile.frontend
    container_name: frontend-container
    networks:
      - fullstack-network
    ports:
      - "80:80"
    depends_on:
      - backend
```

> **Usage:**
> 1. Place `Dockerfile.backend` and `Dockerfile.frontend` in project root alongside your `build/` and `mysql/` directories.
> 2. Adjust `COPY` paths if necessary.
> 3. Run:
>    ```bash
>    docker-compose up --build -d
>    ```



