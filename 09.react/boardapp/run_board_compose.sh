#!/usr/bin/env bash
set -e

# 1) Windows 스타일 절대 경로 얻어와 export
# 경로문제는 미해결 상태태
export PWD_WIN=$(pwd -W)

# 2) 도커 컴포즈 띄우기
docker-compose up --build -d
