# docker nginx image

1. docker run -d --name nginx-container -p 81:8081 nginx:latest

# A. nginx setup
## nginx.conf
- 전체내용삭제후 재작성

1. nginx-container etc/nginx/nginx.conf

http {
  server {
    listen 8081;
    root /home;
  }
}

events {

}

2. docker cp ./index.html nginx-container:/home/

3. docker exec nginx-container nginx -s reload

4. http://localhost:81


# B. mine.typs

1. docker cp nginx-container:/etc/nginx/mime.types ./workspace/

2. docker exec nginx-container rm -f etc/nginx/mime.types

3. style.css
```css
h1 {
  font-size: 24px;
  color: red;
}
```
4. docker cp ./ nginx-container:/home/
  - docker exec -u 0 nginx-container rm -rf //home/ebook
  - root 권한 : -u 0
  - 더블슬래쉬는 git bash에서 실행할 때 윈도우경로로 변환되기 떄문
  - docker exec nginx-container rm -f /home/*.md

5. nginx.conf
  - mime.types가 없다면 css가 적용되지 않는다.(크롬에서는 상관없이 적용된다.)
  - 적용여부를 떠나 types를 설정하는 방법

http {

  types {
    text/css        css;
    text/html       html;
  }

  server {
    listen 8081;
    root /home;
  }
}

events {

} 

6. docker exec nginx-container nginx -s reload

7. nginx.conf

http {
    include       /etc/nginx/mime.types;

  server {
    listen 8081;
    root /home;
  }

}

events {

}

# C. Location Block
- 실행시 주의 : http://localhost:81/fruits/ 처럼 마지막에 /를 붙일 것

1. /fruites/index.html
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="./styles.css">
</head>
<body>
  <ul>
    <li>🥥</li>
    <li>🍇</li>
    <li>🍉</li>
    <li>🍍</li>
    <li>🍎</li>
    <li>🍓</li>
  </ul>
</body>
</html>

2. docker cp ./fruties nginx-container:/home/fruits

3. nginx.conf

http {
    include       /etc/nginx/mime.types;

  server {
    listen 8081;
    root /home;

    location /fruits {
      alias /home/fruits/;
      index index.html index.htm;
    }
  }

}

events {

}

4. nginx.conf

http {

   include       /etc/nginx/mime.types;

  server {
    listen 8081;
    root /home;

    location /fruits {
      alias /home/fruits/;
      index index.html index.htm;
    }

    location /carbs {
      alias /home/fruits/;
    }
  }

}

events {

}

5. foods/foods.html

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="./styles.css">
</head>
<body>
  <h1>Foods</h1>
  <ul>
    <li>🍕</li>
    <li>🍔</li>
    <li>🍟</li>
    <li>🌭</li>
    <li>🍿</li>
  </ul>
</body>
</html>

6. docker cp ./foods nginx-container:/home/foods

7. nginx.conf : try_files
- foods/foods.html을 변경하면서 404일경우 테스트해 볼 것

```nginx
http {
    # MIME 타입 파일을 포함합니다. (파일 확장자별 Content-Type을 정의)
    include       /etc/nginx/mime.types;

    # server 블록: 가상 호스트(사이트) 설정을 정의합니다.
    server {
        # 이 서버가 8081 포트에서 요청을 기다립니다.
        listen 8081;
        
        # 기본 루트 디렉토리를 /home으로 설정합니다.
        # (요청에 해당하는 파일을 이 디렉토리에서 찾습니다)
        root /home;

        # /fruits 경로로 요청이 오면 처리하는 방법
        location /fruits {
            # 실제 파일 경로는 /home/fruits/에서 찾습니다.
            # alias는 location 경로(/fruits)를 지정한 경로(/home/fruits/)로 대체합니다.
            alias /home/fruits/;
            
            # index 파일 우선순위: index.html 먼저 찾고 없으면 index.htm을 찾습니다.
            index index.html index.htm;
        }

        # /carbs 경로로 요청이 오면 처리하는 방법
        location /carbs {
            # /carbs 요청은 실제로 /home/fruits/ 디렉토리에서 파일을 찾습니다.
            # 예: /carbs/apple.jpg 요청 → /home/fruits/apple.jpg 파일 서비스
            alias /home/fruits/;
            
            # ※ 주의: 여기엔 index 지시어가 없어서 기본 root 설정의 index를 사용하거나
            #          직접 파일명을 입력해야 합니다.
        }

        # /foods 경로로 요청이 오면 처리하는 방법
        location /foods {
            # root는 location 경로(/foods)를 추가합니다.
            # 예: /foods/banana.jpg 요청 → /home/foods/foods/banana.jpg
            # ※ 주의: 일반적으로는 root /home; 으로 설정하는 게 더 일반적입니다.
            root /home/foods/;
            
            # 파일 찾는 순서:
            # 1. 먼저 /foods/foods.html 파일을 찾고
            # 2. 없으면 index.html을 찾고
            # 3. 그래도 없으면 404 에러를 반환합니다.
            # ※ 주의: 현재 경로에 ; 세미콜론이 빠져있고, 경로 설정이 조금 이상할 수 있습니다.
            try_files /foods/foods.html index.html =404;
        }
    }
}

# events 블록: Nginx의 연결 처리 방식에 대한 설정 (기본값 사용)
events {
    # 일반적으로 여기엔 worker_connections 등의 설정이 들어갑니다.
    # 비어있어도 기본값으로 동작합니다.
}
```

```nginx
**주요 개념 설명**
# 1. root vs alias: 

# a. root: location 경로를 추가.

location /images {
    root /data;
}
# /images/cat.jpg 요청 → /data/images/cat.jpg 파일 서비스

# b. alias: location 경로를 대체합니다.

location /images {
    alias /data/photos;
}
# /images/cat.jpg 요청 → /data/photos/cat.jpg 파일 서비스

# 2. try_files:
# 파일을 순서대로 찾아보고, 마지막에 대체 동작을 지정합니다.
# 형식: try_files 파일1 파일2 ... =대체동작
# 예: try_files $uri $uri/ /index.html =404

# 3. index:
# 디렉토리 요청 시 자동으로 찾을 파일 목록을 지정합니다.

# 4. 주의할 점:
# /foods 설정에서 root /home/foods/로 하면 실제 경로가 /home/foods/foods/
# try_files 경로도 이에 맞게 조정해야 한다.
# 모든 지시문은 세미콜론(;)으로 끝나야 합니다.

# 5.  /foods 부분을 다음과 같이 수정가능
location /foods {
    root /home;  # 이렇게 하면 /home/foods/...에서 파일을 찾습니다.
    try_files $uri /foods/foods.html /foods/index.html =404;
}
```

8. docker exec nginx-container nginx -s reload  

9. nginx.conf
- 정규식

```nginx
  server {
    listen 8081;
    root /home;

    location ~* /count/[0-9] {
      root /home;
      try_files /index.html =404; 
    }

     location /fruits {
     }   

    ...  생략

# location ~* (정규식식)
# ~*의 의미    
# ~ : 정규식(regular expression) 매칭을 사용하겠다는 표시
# * : 대소문자를 구분하지 않겠다는 표시(case-insensitive)
# 결합된 ~* : 대소문자 구분 없이 정규식 매칭을 수행
```

# D. redirects and rewrites

1. nginx.conf - rewrite

```nginx
  server {
    listen 8081;
    root /home;

    rewrite ^/number/(\w+) /count/$1;

    location ~* /count/[0-9] {}

    ...  생략

    location /crops {
      return 307 /fruits;
    }

```    
2. http://localhost:81/number/[0-9]
- result : /furits/index.html

# E. load balance

1. server  생성

mkdir server
cd server
npm init -y
npm install express

2. index.js
```js
const express = require('express');
const app = express(); 

app.get('/', (req, res) => {
  res.send('I am server. Hello Endpoint!!!');
});

app.listen(3000, () => {
  console.log('Example app listening on port 3000!');
})
```

3. package.json

```json
... 생략
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "start": "node index"
  },
... 생략
```

4. express server 실행
- npm run start
  ... console.log('Example app listening on port 3000!'); 확인
- http://localhost:3000
  ... res.send('Hello World!');  확인

5. Dockerfile

```Dockerfile
FROM node
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD ["npm","run","start"]
```

- docker build . -t myserver
- docker run -p 1111:3000 -d myserver
- docker run -p 2222:3000 -d myserver
- docker run -p 3333:3000 -d myserver
- docker run -p 4444:3000 -d myserver

6. nginx.conf - load balancing

```nginx
# HTTP 블록: 웹 서버의 주요 설정
http {
    # MIME 타입 설정 파일 포함 (파일 확장자별 Content-Type 정의)
    include /etc/nginx/mime.types;

    # 서버 블록: 가상 호스트 설정
    server {
        # 8081 포트에서 연결 수신
        listen 8081;
        
        # 기본 문서 루트 디렉토리 설정
        root /home;

        # 업스트림 서버 그룹 정의 (로드 밸런싱용)
        upstream backendserver {
            # 기본적으로 Round Robin 방식으로 요청 분배
            server 127.0.0.1:1111; # 백엔드 서버 1
            server 127.0.0.1:2222; # 백엔드 서버 2
            server 127.0.0.1:3333; # 백엔드 서버 3
            server 127.0.0.1:4444; # 백엔드 서버 4
            
            # 추가 옵션:
            # weight=2;    # 가중치 (기본값 1)
            # max_fails=3; # 최대 실패 횟수
            # fail_timeout=30s; # 타임아웃
        }

        # URL 재작성 규칙
        # /number/xxx → /count/xxx 로 경로 변경
        rewrite ^/number/(\w+) /count/$1;
        # ^ : 문자열 시작
        # \w+ : 하나 이상의 단어 문자(문자, 숫자, _)
        # $1 : 첫 번째 캡처 그룹

        # 기본 위치 블록 (모든 요청 처리)
        location / {
            # 정의된 백엔드 서버로 요청 전달
            proxy_pass http://backendserver/;
            # 주의: 마지막 슬래시(/)가 있으면 원본 URI가 전달되지 않음
            
            # 프록시 관련 추가 설정 가능:
            # proxy_set_header Host $host;
            # proxy_set_header X-Real-IP $remote_addr;
        }

        # 정규식 위치 블록 (대소문자 구분 없음)
        location ~* /count/[0-9] {
            # 파일 시스템 경로 설정
            root /home;
            
            # 파일 검색 순서:
            # 1. /home/index.html
            # 2. 없으면 404 에러 반환
            try_files /index.html =404;
            
            # 주의: 실제로는 /count/[0-9] 요청이 모두 index.html로 연결됨
            # 더 유용하게 사용하려면:
            # try_files $uri /count/index.html =404;
        }

        # 정적 파일 서비스 - 과일 디렉토리
        location /fruits {
            # 경로 별칭 설정 (location 경로가 /home/fruits/로 대체됨)
            alias /home/fruits/;
            
            # 디렉토리 인덱스 파일 지정
            index index.html index.htm;
            
            # 예: /fruits/apple.jpg → /home/fruits/apple.jpg
        }

        # 정적 파일 서비스 - carbs 디렉토리 (실제로 fruits 디렉토리 사용)
        location /carbs {
            # /carbs 경로로 요청이 오면 /home/fruits/에서 파일 제공
            alias /home/fruits/;
            # 예: /carbs/banana.jpg → /home/fruits/banana.jpg
            
            # index 지시어가 없으므로 명시적 파일명 필요
        }

        # 정적 파일 서비스 - 음식 디렉토리
        location /foods {
            # root 사용 시 location 경로가 추가됨
            root /home;
            # 예: /foods/pizza.jpg → /home/foods/pizza.jpg
            
            # 파일 검색 순서:
            # 1. /home/foods/foods.html
            # 2. /home/index.html
            # 3. 없으면 404 에러
            try_files /foods/foods.html /index.html =404;
        }

        # 리다이렉션 처리
        location /crops {
            # 307 Temporary Redirect (메소드와 본문 유지)
            return 307 /fruits;
            # 예: /crops → /fruits 로 리다이렉트
            
            # 301 Permanent Redirect도 가능:
            # return 301 /fruits;
        }
        
        # 주의: 다음 줄은 잘못된 위치에 있는 URL입니다 (제거해야 함)
        # http://localhost:8081/fruits/
    }
}

# 이벤트 블록: 연결 처리 방식 설정
events {
    # 일반적으로 worker_connections 등의 설정이 위치
    # 예: worker_connections 1024;
}
```

#### 주요 기능 요약
1. 로드 밸런싱: 4개의 백엔드 서버에 Round Robin 방식으로 요청 분배
2. URL 재작성: /number/xxx → /count/xxx로 경로 변경
3. 정적 파일 서빙:
  - /fruits, /carbs, /foods 경로로 정적 파일 제공
  - alias와 root의 차이점 적용
4. 리다이렉션: /crops → /fruits로 307 리다이렉트
5. 정규식 라우팅: /count/[0-9] 패턴 매칭

#### 개선 제안
1. location / 블록에 프록시 헤더 추가:
```nginx
proxy_set_header Host $host;
proxy_set_header X-Real-IP $remote_addr;
```

2. location ~* /count/[0-9] 블록 개선:
```nginx
location ~* ^/count/([0-9]+)$ {
    try_files /count/$1.html /count/index.html =404;
}
```

3. 업스트림 서버에 상태 확인 추가:
```nginx
upstream backendserver {
    server 127.0.0.1:1111 max_fails=3 fail_timeout=30s;
    server 127.0.0.1:2222 weight=2;
    ...
}
```
4. 오류 페이지 설정 추가:
```nginx
error_page 404 /custom_404.html;
error_page 500 502 503 504 /custom_50x.html;
}