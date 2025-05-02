#### 참고사이트 : https://www.youtube.com/watch?v=9t9Mp0BGnyI
# 1. Basic
## 1.1 installing
### docker run -it --name ubuntu-nginx ubuntu /bin/bash
* git bash에서는 실행하면 bash프로그램 실행중이므로 에러
* windows powershell or cmd 에서 실행할 것

```bash
$ apt update
$ apt install -y curl gnupg2 ca-certivicates lsb-release debian-archive-keyring
```
###### Ubuntu 또는 Debian 계열 리눅스에서 NGINX 설치를 준비하기 위한 기초 패키지들을 설치
| 패키지명                 | 설명 |
|--------------------------|------|
| `curl`                   | 웹에서 데이터를 다운로드하거나 API에 요청할 수 있는 **HTTP 클라이언트**입니다. 예: `curl -fsSL https://nginx.org/keys/nginx_signing.key` |
| `gnupg2`                 | GPG 키를 관리하기 위한 도구입니다. **패키지 서명 확인**에 사용됩니다. |
| `ca-certificates`        | 시스템에서 **신뢰하는 인증서 목록**입니다. HTTPS 사이트에서 안전하게 통신할 수 있도록 도와줍니다. |
| `lsb-release`            | 시스템의 배포판 정보를 확인하는 유틸리티입니다. 예: `Ubuntu 20.04`, `Debian 11` 등의 정보를 확인할 수 있습니다. |
| `debian-archive-keyring`| **Debian 저장소의 공식 서명 키**를 포함하는 패키지입니다. Debian 저장소에서 받은 패키지가 신뢰된 것인지 검증할 때 사용됩니다. |

#### NGINX signing key download and install
* NGINX 저장소의 GPG 키를 안전하게 시스템에 등록하는 과정입니다.
* apt 명령으로 nginx 관련 패키지를 설치할 때, 이 키를 사용하여 해당 패키지가 신뢰된 출처에서 왔는지 검증

```bash
# gpg, sud 설치
$ apt update
$ apt install -y gnupg sudo
$ apt install -y curl
$ apt install -y lsb-release

# gpg, sudo 설치후 실행
$ curl https://nginx.org/keys/nginx_signing.key | gpg --dearmor \
| tee /usr/share/keyrings/nginx-archive-keyring.gpg > /dev/null

```

| 명령어 구성 요소 | 설명 |
|------------------|------|
| `curl https://nginx.org/keys/nginx_signing.key` | 📡 NGINX 공식 GPG 서명 키를 다운로드합니다. 패키지 검증에 사용됩니다. |
| `| gpg --dearmor` | 🔐 ASCII 형식의 GPG 키를 바이너리 형식(GPG 형식)으로 변환합니다. APT가 인식 가능하게 만듭니다. |
| `| tee /usr/share/keyrings/nginx-archive-keyring.gpg` | 💾 변환된 키를 `/usr/share/keyrings/nginx-archive-keyring.gpg`에 저장합니다. APT에서 사용할 키링입니다. |
| `> /dev/null` | 🙈 `tee` 명령의 표준 출력을 무시하여 출력 결과를 깔끔하게 처리합니다. |

#### lsb_release를 사용하여 OS 및 릴리스 이름을 정의하는 변수를 설정한 후, apt 소스 파일을 생성

```bash
# 리눅스 배포판의 이름을 모두 소문자로 저장하기 위해 사용
# sb_release -is	리눅스 배포판의 이름(예: Ubuntu, Debian 등)을 출력
# tr '[:upper:]' '[:lower:]'	대문자를 소문자로 변환
# ( ... )	명령어 치환 (결과를 변수 OS에 저장)
$ OS=$(lsb_release -is | tr '[:upper:]' '[:lower:]')

# sb_release -cs	현재 리눅스 배포판의 codename을 출력 (예: focal, buster, bullseye 등)
# $( ... )	명령어 치환으로 결과를 변수 RELEASE에 저장
$ RELEASE=$(lsb_release -cs)

# GINX 공식 저장소를 APT 소스 목록에 추가하는 작업을 수행합니다. (주로 Debian/Ubuntu 계열에서 사용)
# echo :문자열을 출력하는 명령어로, 여기서는 NGINX 저장소 정보를 생성합니다.
# 저장소 URL 구조
# ... deb: 패키지 저장소가 바이너리임을 명시합니다.
# ... [signed-by=...]: 저장소의 GPG 서명 키 경로를 지정하여 보안 검증을 합니다.
#     ... 키 위치: /usr/share/keyrings/nginx-archive-keyring.gpg
# http://nginx.org/packages/${OS}: NGINX 공식 패키지 저장소 주소.
# ... ${OS}: 변수로 배포판 이름 (예: ubuntu, debian)이 들어갑니다.
# ${RELEASE}: 배포판 릴리스 코드명 (예: jammy, bullseye).
#  ... nginx: 저장소에서 사용할 컴포넌트 이름입니다.
# tee : 출력 내용을 동시에 화면과 파일에 저장합니다.
# ... /etc/apt/sources.list.d/nginx.list: APT가 참조하는 새로운 소스 파일 생성.
# ... (일반적으로 시스템 기본 경로인 /etc/apt/sources.list 대신 분리된 관리를 위해 사용)
$ echo "deb [signed-by=/usr/share/keyrings/nginx-archive-keyring.gpg] \
    http://nginx.org/packages/${OS} ${RELEASE} nginx" \
    | tee /etc/apt/sources.list.d/nginx.list

# Update package information once more, then install NGINX:
$ apt update
$ apt install -y nginx # 5. Asia / 68.seoul

# vim 편집기 설치
$ apt install vim
$ vi --version

# 환경설정
$ vi /etc/wsl.conf
# [boot]
# systemd=true
$ cat /etc/wsl.conf

# systemctl은 systemd 시스템 관리 도구의 일부로 제공됩니다. systemctl을 사용하기 위해서는 먼저 systemd를 설치해야 한다.
# systemd 사용여부확인
$ ps -p 1 -o comm=       
$ apt install -y systemd
$ systemctl --version
# systemctl 위치 찾기
$ which systemctl || whereis systemctl
# PATH에 추가 (예: /usr/bin/systemctl)
$ export PATH=$PATH:/usr/bin

# 도커 reboot
$ exit
# 윈도우 cmd
# docker stop 도커ID
# docker start 도커ID
# docker exec -it 도커ID /bin/bash


# nginx 실행
$ systemctl enable nginx
$ nginx -v

# nginx 실행확인인
```
$ ps -ef | grep nginx