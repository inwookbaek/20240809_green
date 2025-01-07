-- 1. 주석
-- 한줄주석   : -- 로 설정
-- 여러줄주석 : /* ... */

-- A. Oracle 기본 날짜형식 확인
-- 1. 오라클 환경변수 조회하기
select * from v$nls_parameters;

-- B. Oracel 사용자 조회하기 - 오라클에서만 사용되는 명령(즉, SQL command line에서만 사용가능)
-- 1. 현재사용자 확인
show user;

-- 2. Oracle 사용자 목록 조회
select * from dba_users;
select username from dba_users;
select username, ACCOUNT_STATUS, LOCK_DATE from dba_users;

-- 3. Oracle 권한 조회
select * from dba_sys_privs;
-- hr사용자의 권한만 조회
select * from dba_sys_privs where grantee = 'HR';
select * from dba_sys_privs where grantee = 'SYS';

-- 4. Oracle Role 조회
select * from dba_role_privs;
select * from dba_role_privs where grantee = 'HR';
select * from dba_role_privs where grantee = 'SYS';

-- 5. locked된 사용자(스키마, DB) 해제하기
select username, ACCOUNT_STATUS, LOCK_DATE from dba_users where username= 'HR';
alter user hr account unlock; -- 사용자잠금해제명령

alter user hr identified by hr; -- 사용자비밀번호변경명령 

-- 6. 사용자 테이블목록 조회
select * from tabs; -- 현재 접속한 사용자의 테이블 목록 조회

-- C. scott 계정(스키마, DB, 사용자) 생성하기
-- 1. 사용자계정생성하기
SELECT * FROM dba_users where username = 'SCOTT';
create user scott identified by tiger;

-- 2. scott에 권한 설정
-- 1) connection 권한
-- 2) resource 권한(select, insert, update, delete, alter....)
select * from dba_sys_privs where grantee = 'SCOTT';
select * from dba_role_privs where grantee = 'SCOTT';
grant connect, resource to scott;

-- 3. scott의 작업장소(tablespace)를 설정
alter user scott default tablespace users;
alter user scott temporary tablespace temp;

-- 4. 실습테이블생성
-- dept, emp, bonus, salgrade;
-- 주의사항) 사용자를 scott로 변경한 후에 작업

CREATE TABLE DEPT
   (DEPTNO NUMBER(2) CONSTRAINT PK_DEPT PRIMARY KEY,
    DNAME VARCHAR2(14) ,
    LOC VARCHAR2(13) ) ;

CREATE TABLE EMP  
   (EMPNO NUMBER(4) CONSTRAINT PK_EMP PRIMARY KEY,  
    ENAME VARCHAR2(10),  
    JOB VARCHAR2(9),  
    MGR NUMBER(4),  
    HIREDATE DATE,  
    SAL NUMBER(7,2),  
    COMM NUMBER(7,2),  
    DEPTNO NUMBER(2) CONSTRAINT FK_DEPTNO REFERENCES DEPT);

INSERT INTO DEPT VALUES (10,'ACCOUNTING','NEW YORK');
INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');   
INSERT INTO DEPT VALUES (30,'SALES','CHICAGO');  
INSERT INTO DEPT VALUES (40,'OPERATIONS','BOSTON');

INSERT INTO EMP VALUES
(7369,'SMITH','CLERK',7902,to_date('17-12-1980','dd-mm-yyyy'),800,NULL,20);
INSERT INTO EMP VALUES
(7499,'ALLEN','SALESMAN',7698,to_date('20-2-1981','dd-mm-yyyy'),1600,300,30);
INSERT INTO EMP VALUES
(7521,'WARD','SALESMAN',7698,to_date('22-2-1981','dd-mm-yyyy'),1250,500,30);
INSERT INTO EMP VALUES
(7566,'JONES','MANAGER',7839,to_date('2-4-1981','dd-mm-yyyy'),2975,NULL,20);
INSERT INTO EMP VALUES
(7654,'MARTIN','SALESMAN',7698,to_date('28-9-1981','dd-mm-yyyy'),1250,1400,30);
INSERT INTO EMP VALUES
(7698,'BLAKE','MANAGER',7839,to_date('1-5-1981','dd-mm-yyyy'),2850,NULL,30);
INSERT INTO EMP VALUES
(7782,'CLARK','MANAGER',7839,to_date('9-6-1981','dd-mm-yyyy'),2450,NULL,10);
INSERT INTO EMP VALUES
(7788,'SCOTT','ANALYST',7566,to_date('13-7-1987','dd-mm-yyyy')-85,3000,NULL,20);
INSERT INTO EMP VALUES
(7839,'KING','PRESIDENT',NULL,to_date('17-11-1981','dd-mm-yyyy'),5000,NULL,10);
INSERT INTO EMP VALUES
(7844,'TURNER','SALESMAN',7698,to_date('8-9-1981','dd-mm-yyyy'),1500,0,30);
INSERT INTO EMP VALUES
(7876,'ADAMS','CLERK',7788,to_date('13-7-1987','dd-mm-yyyy')-51,1100,NULL,20);
INSERT INTO EMP VALUES
(7900,'JAMES','CLERK',7698,to_date('3-12-1981','dd-mm-yyyy'),950,NULL,30);
INSERT INTO EMP VALUES
(7902,'FORD','ANALYST',7566,to_date('3-12-1981','dd-mm-yyyy'),3000,NULL,20);
INSERT INTO EMP VALUES
(7934,'MILLER','CLERK',7782,to_date('23-1-1982','dd-mm-yyyy'),1300,NULL,10);

CREATE TABLE BONUS
    (
    ENAME VARCHAR2(10)  ,
    JOB VARCHAR2(9)  ,
    SAL NUMBER,
    COMM NUMBER
    ) ;
		
CREATE TABLE SALGRADE
      ( GRADE NUMBER,
    LOSAL NUMBER,
    HISAL NUMBER );
	
INSERT INTO SALGRADE VALUES (1,700,1200);
INSERT INTO SALGRADE VALUES (2,1201,1400);
INSERT INTO SALGRADE VALUES (3,1401,2000);
INSERT INTO SALGRADE VALUES (4,2001,3000);
INSERT INTO SALGRADE VALUES (5,3001,9999);	