/*
	A. SQL문의 종류
	
	1. DML(데이터조작어, Data Manipulation Language)
		a. select : 자료를 조회		
		b. insert : 자료를 추가
		c. update : 자료를 수정
		d. delete : 자료를 삭제
		
	2. DDL(데이터정의어, Data Definition Language)
		a. create   : 객체(table, view, index....)를 생성
		b. drop     : 객체를 삭제(완전삭제)
		c. truncate : 객체의 데이터만 삭제(객체는 보존)
		d. alter    : 객체를 수정
		
	3. DCL(데이터관리어, Data Control Language)
		a. grant  : 권한을 부여
		b. revoke : 권한을 취소
	
	4. DTL(데이터처리어, Data Transaction Language)
		a. commit   : 자료를 추가/삭제/수정했을 경우 최종적으로 확정
		b. rollback : 자료를 추가/삭제/수정했을 경우 해당작업을 취소
*/
/*
	[select 문법]
	
	select 컬럼...
	  from 테이블...
	 where 조건절
	
	select [distinct] [* | col1 [as][별칭], .... ]  
	  from 테이블명(view명..) [별칭]
	[where 조건절...]
	[order by 열이름 [asc | desc], 표현식 .... 
	
	1. distinct    : 중복되는 행을 제거
	2. *           : from절에 있는 테이블(뷰)의 모든 열을 전부 선택
	3. 별칭(alias) : 컬럼명 or 테이블(뷰)명의 별칭을 지정
	4. 조건절      : 비교연산(<,>=,<>, =), between, like, in 등
	5. order by    : 컬럼명[select절의 순서] [asc 오름차순(기본값) | desc], 질의한 결과를 정렬 
*/

-- 1. 특정테이블의 자료 조회
-- 문법 : select col1, ... from 테이블명; -> 지정된 테이블의 해당 col들을 조회
---   or  select * from 테이블명 --> 테이블의 전체 열을 조회
select * from emp;
--select *, empno, ename from emp; -- 에러, *와 개별컬럼은 동시에 정의할 수 없다.
select empno, ename from emp;
-- ename, job, sal만 조회하기
select ename, job, sal from emp;

-- 2. 표현식
-- 리터럴상수(문자) -> 표현식은 작은 따옴표로 정의해야 한다.
-- 큰 따옴표는 identifier를 정의할 때 사용
-- 출력 : 이름 = ename 
select '이름 =', ename from emp;  -- OK
select "이름 =", ename from emp;  -- NG
select '이름 =', "ename" from emp;  -- NG, 문자열은 작은 따옴표, 열명, 테이블명등은 큰 따옴표(대소문자구분)
select '이름 =', "ENAME" from emp;  -- OK
select '이름 =', ENAME from emp;  -- OK
-- 별칭
select '이름 =', ename as 사원이름 from emp; -- OK 
select '이름 =', ename 사원이름 from emp; -- OK 
select '이름 =', ename 사원 이름 from emp; -- NG, ename 사원, 이름
select '이름 =', ename "사원 이름" from emp; -- OK
select '이름 =' 한글이름, ename "사원 이름" from emp; -- OK
select '이름 =' "한글 이름", ename "사원 이름" from emp; -- OK
select '이름 =' "이름 =", ename "사원 이름" from emp; -- OK

-- 작은(큰) 따옴표 사용시 주의
select '한글 이름' from emp; -- OK
select '한글 이름', from emp; -- NG, 컴마뒤에는 열명 or 표현식이 있어야 한다.
select '한글 이름', 'xxx' from emp; -- OK,
--select '한글' 이름' from emp; -- NG
select '한글'' 이름' from emp; -- OK
select '한글', '이름' from emp; -- OK

-- 3. 별칭사용하기 : as
select ename from emp;
select ename 사원명 from emp;
select ename "사원명" from emp;
select ename '사원명' from emp; -- NG
select ename as '사원명' from emp; -- NG
select ename as 사원명 from emp;
select ename as 사원명 from emp;

-- 4. distinct : 중복된 값을 제거
-- distinct는 맨앞에 한번만 정의해야 한다.
select * from dept;
select * from emp;
select deptno from emp;
-- 중복제거
select distinct deptno from emp;

-- 주의
select deptno distinct from emp; -- NG
select distinct deptno, mgr from emp;
select distinct deptno, distinct mgr from emp; -- NG

-- 5. 컬럼, 문자열등 연결하기 : concat(), ||
select '이름 = ', ename from emp; 
select '이름 = ' || ename from emp;

select concat('이름 = ', ename) from emp;
select concat('이름 = ', ename) as 사원이름 from emp;

select ename, deptno from emp;
-- 실습. 이름(부서번호) 사원명(부서) 형태로 출력
-- 1) SMITH(20) as 사원명(부서)
select ename||deptno from emp;
select ename||(deptno) from emp;
select ename||(||deptno||) from emp; -- NG
select ename || '(' || deptno || ')' from emp;
select ename || '(' || deptno || ')' as 사원명(부서번호) from emp; -- NG
select ename || '(' || deptno || ')' as "사원명(부서번호)" from emp; 

-- 2) concat함수
select concat(ename, deptno) from emp;
select concat(ename, '(', deptno,')') "사원명(부서번호)" from emp; -- NG
select concat(concat(ename, '('), deptno)  from emp;
select concat(concat(concat(ename, '('), deptno), ')') "사원명(부서번호)" from emp;

/* 실습문제 */
-- 1."smith의 부서는 20입니다"형태로 출력하기
select ename, deptno from emp;
select ename || '의 부서는 ' || deptno || '입니다!' as "사원명 and 부서번호" from emp;
select concat(concat(concat(ename, '의 부서는 '), deptno) , '입니다!') as "사원명 and 부서번호" from emp;

-- 2. Student 에서 학생들의 정보를 이용해서 "Id and Weight" 형식으로 출력하세요
select * from student;
select id, weight from student;
select id || ' and ' || weight as "Id and Weight" from student;
select concat(concat(id, ' and '), weight) as "Id and Weight" from student;

-- 3. emp에서 "Name and Job"형식으로 출력
select ename || ' and ' || job as "Name and Job" from emp;
select concat(concat(ename, ' and '), job) as "Name and Job" from emp;

-- 4. emp에서 "Name and Sal"
select ename || ' and ' || sal as "Name and Sal" from emp;
select concat(concat(ename, ' and '), sal) as "Name and Sal" from emp;