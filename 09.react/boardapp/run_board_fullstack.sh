#!/usr/bin/env bash
set -e

# Windows Git Bash 환경용 Docker 전체 스택 실행 스크립트
# - fullstack-network 네트워크 생성
# - mysql-container, backend1, backend2, frontend-container 실행

# 1) 네트워크 생성 (이미 존재하면 무시)
if ! docker network ls | grep -q fullstack-network; then
  docker network create fullstack-network
  echo "Created network: fullstack-network"
else
  echo "Network already exists: fullstack-network"
fi

# 2) MySQL용 볼륨 생성 (이미 존재하면 무시)
if ! docker volume ls | grep -q boarddb-volume; then
  docker volume create boarddb-volume
  echo "Created volume: boarddb-volume"
else
  echo "Volume already exists: boarddb-volume"
fi

# 3) mysql-container 실행
if ! docker ps -a --format '{{.Names}}' | grep -q mysql-container; then
  docker run -d \
    --name mysql-container \
    --network fullstack-network \
    -v boarddb-volume:/var/lib/mysql \
    -e MYSQL_ROOT_PASSWORD=12345 \
    mysql:latest
  echo "Started mysql-container"
else
  echo "mysql-container already exists"
fi

# 4) backend1 실행
if ! docker ps -a --format '{{.Names}}' | grep -q backend1; then
  docker run -d \
    --name backend1 \
    --network fullstack-network \
    -e SPRING_DATASOURCE_URL="jdbc:mysql://mysql-container:3306/boarddb?createDatabaseIfNotExist=true" \
    -e SPRING_DATASOURCE_USERNAME="root" \
    -e SPRING_DATASOURCE_PASSWORD="12345" \
    board-backend:latest
  echo "Started backend1"
else
  echo "backend1 already exists"
fi

# 5) backend2 실행
if ! docker ps -a --format '{{.Names}}' | grep -q backend2; then
  docker run -d \
    --name backend2 \
    --network fullstack-network \
    -e SPRING_DATASOURCE_URL="jdbc:mysql://mysql-container:3306/boarddb?createDatabaseIfNotExist=true" \
    -e SPRING_DATASOURCE_USERNAME="root" \
    -e SPRING_DATASOURCE_PASSWORD="12345" \
    board-backend:latest
  echo "Started backend2"
else
  echo "backend2 already exists"
fi

# 6) frontend-container 실행
if ! docker ps -a --format '{{.Names}}' | grep -q frontend-container; then
  docker run -d \
    --name frontend \
    --network fullstack-network \
    -p 80:80 \
    nginx:alpine
  echo "Started frontend-container"
else
  echo "frontend-container already exists"
fi

echo "All containers are up and running."
