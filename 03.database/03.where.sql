/*
	A. where 조건절
	
	1. 비교연산자
 
		=, 
		!=, <>
		>
		>=
		<
		<=
		
	2. 기타연산자
		a and b: 논리곱
		a or b : 논리합
		not a  : 부정 
		between A and B : a 와 b사이의 데이를 검색, a는 b보다 작은 값으로 정의
		in(a,b,c...)    : a,b,c..의 값을 가지고 있는 데이터를 검색
		like (%, _와 같이 사용) : 특정 패턴을 가지고 있는 데이터를 검색
				-> '%A' 끝이 A로 끝나는 데이터, 'A%' A로 시작, '%A%' A를 포함
				-> _ : 문자한개 A_B : A1B, AaB, AXB -> OK, A11B -> NG, A__B -> A11B :OK
		is null/ is not null : null값 여부를 가지고 있는 데이터를 검갯		
*/

/* A. 비교연산자 */
-- 1. 급여(sal)가 5000인 사원 조회
select ename, sal from emp;
select ename, sal from emp where sal = 5000;
select ename, sal 
  from emp 
 where sal = 1600;
 
-- 2. 급여가 950보다 큰(작은, 크거나같은, 작거나 같은, 아닌) 사원
select ename, sal from emp where sal > 950; 
select ename, sal from emp where sal < 950; 
select ename, sal from emp where sal >= 950;
select ename, sal from emp where sal <= 950;  
select ename, sal from emp where sal <> 950; 
select ename, sal from emp where sal != 950; 

-- 3. 사원이름이 smith인 사원만 조회
select * from emp where ename = 'smith'; -- NG, 대소문자구분
select * from emp where ename = "SMITH"; -- NG
select * from emp where ename = 'SMITH';

-- 4. 대소문자변환함수
-- upper(), lower()
select ename, lower(ename) from emp where ename = 'SMITH';
select ename, lower(ename), upper(lower(ename))  from emp where ename = 'SMITH'; 
select * from emp where lower(ename) = 'smith'; 
select * from emp where lower(ename) = 'SMITH'; -- NG
select * from emp where upper(lower(ename)) = 'SMITH';

-- 5. 입사일자(hiredate)
-- 입사일자가 1980-12-17인 사원을 조회
-- hint) date타입은 문자열로 비교가능
select ename, hiredate from emp;
select ename, hiredate from emp where hiredate = 1980-12-17; -- NG
select 1980-12-17 from dual; -- orcale에서만 제공하는 dummy table;
select ename, hiredate from emp where hiredate = '1980-12-17'; 
select ename, hiredate from emp where hiredate = '19801217';  -- OK, DB엔진(오라클) date = 문자열(자동형변환)
select ename, hiredate from emp where hiredate = '12171980';  -- NG ORA-01861: literal does not match format string
select * from v$nls_parameters;
select ename, hiredate from emp where hiredate = '1980.12.17'; 
select ename, hiredate from emp where hiredate = '1980/12/17'; 

/* 실습문제 */
-- ex01) 급여가 1000보다 작은 사원만 출력하기(ename/sal/hiredate 만 출력)
select ename, sal, hiredate from emp where sal < 1000;

-- ex02) 부서(dept)테이블에서 부서번호와, 부서명을 별칭으로 한 sql문을 작성
select * from dept;
select deptno 부서번호, dname 부서명 from dept;

-- ex03) 사원테이블에서 직급만 출력하는데 중복되지 않게 출력하는 sql문
select job from emp;
select distinct job from emp;

-- ex04) 급여가 800인 사원만 조회
select * from emp where sal = 800;

-- ex05) 사원명이 BLAKE인 사원만 출력
select * from emp where ename = 'BLAKE';

-- ex06) 사원이름 JAMES~MARTIN사이의 사원을 사원번호, 사원명, 급여를 출력
-- and, between 2가지형태로 작성
select empno, ename, sal
  from emp
 where ename >= 'JAMES';

select empno, ename, sal
  from emp
 where ename <= 'MARTIN';

select empno, ename, sal
  from emp
 where ename >= 'JAMES' and ename <= 'MARTIN';

select empno, ename, sal
  from emp
 where ename between 'JAMES' and 'MARTIN';
 
 
-- 6. between
-- 입사일자가 1980.12.01~19801231까지의 사원 출력
select ename, hiredate from emp where hiredate = '19801217';
select ename, hiredate from emp where hiredate >= '19801201' and hiredate <= '19801231';
select ename, hiredate from emp where hiredate between '19801201' and '19801231';

select * from emp where sal = 800;
select * from emp where sal = '800'; -- sal의 데이터형의 number, '800' -> 숫자로 자동형변환

-- and, or 연산자
select ename, hiredate from emp where hiredate >= '19801201' or hiredate <= '19801231';

-- 7. in : 컬럼명 in(값1,....)
-- 부서가 10, 20인 사원을 출력
select ename, deptno from emp where deptno = 10 or deptno = 20;
-- or 대신에 in
select ename, deptno from emp where deptno in(10, 20);

-- 8. order by 컬럼명[순서] : 정렬
select * from emp t1 where deptno = 30;

-- ename순서로 정렬(정순/역순)
select * from emp t1 where deptno = 30 order by ename;
select * from emp t1 where deptno = 30 order by 1; -- 1순서는 empno 즉 empno로 정렬
select * from emp t1 where deptno = 30 order by 2; --  order by ename와 동일
select ename, empno from emp t1 where deptno = 30 order by 2; -- empno로 정렬

-- 역순
select * from emp t1 where deptno = 30 order by ename asc;
select * from emp t1 where deptno = 30 order by ename desc;
select * from emp t1 where deptno = 30 order by 2 desc;

-- 멀티컬럼 -  입사일자, 이름순
select * from emp t1
 order by 5, 2;
 
select * from emp t1
 order by t1.hiredate, t1.ename;
 
-- 9. like
-- %: 어떤 문자가 와도 상관없고, 글자수에 제한이 없다. 
-- _: 어떤 문자가 와도 상관없고, 한개의 문자를 의미 즉, 한 개의 문자만 허용

-- 1) 사원이름이 A로 시작하는 사원조회
-- and
select * from emp where ename >= 'A' and ename < 'B';
-- like
select * from emp where ename like 'A%'; -- A* => A, AA, AAAAAA, Adskdkdkd
select * from emp where ename like 'S%'; 

-- 2) 사원이름이 N로 끝나는 사원
select * from emp where ename like '%N'; -- *N => ~~N

-- 3) 사원이름이 A를 포함하는 사원
select * from emp where ename like '%N%';

-- 4) sal가 50으로 끝나는 사원
select * from emp where sal like '%50';

-- 5) 입사년도가 1980년인 사원만
-- to_char() : 날짜 or 숫자를 문자로 변환함수
select to_char(hiredate) from emp;
select * from emp where hiredate like '1980%';
select * from emp where hiredate like '80%'; -- RRRR/MM/DD

-- 6) 입사월이 12월 사원만(like, _)
-- DB환경에 따라 YYYY/MM/DD or RR/MM/DD(현재날짜설정)
select * from emp where hiredate like '_____12%';
select * from emp where hiredate like '___12%';
select * from emp where hiredate like '__/12%';
select * from emp where hiredate like '__-12%';

-- 7) 사원이름에 3번째 문자가 L인 사원(like, _)
select * from emp where ename like '__L%';

select * from v$nls_parameters;

-- 10. is null / is not null
select * from emp;
select * from emp where comm = null; -- NG
select * from emp where comm is null;
select * from emp where comm is not null;

-- null데이터시 주의할 점(특히 숫자일 경우)
select ename, sal, comm from emp;
select 100+100, 100-100,100*100, 100/100 from dual;
select 100 + null, 100 + 0 from dual;

select ename, sal, comm, sal+comm as 총급여 from emp;

-- 11. 날짜관련 시스템함수
-- sysdate : 현재일을 리턴
select sysdate from dual;
select '현재일자 =', sysdate from dual;
select sysdate, ename, sal, comm, sal+comm as 총급여 from emp;

-- 12. 집계함수 : sum(), avg(), count()
select sum(sal), sum(comm), avg(sal), avg(comm) from emp;
select 27925/13 from emp;
select count(*) from emp;
select count(sal) from emp;
select sum(comm), avg(comm), count(comm) from emp;
select 2200/13, 2200/4 from emp;


