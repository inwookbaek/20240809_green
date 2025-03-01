/*
	A. 집합연산자
		
	1. union     : 두 집합의 결과를 합쳐서 출력, 단 중복이 있을 경우 중복자료는 제거
	2. union all : 두 집합의 결과를 합쳐서 출력, 중복과 상관없이 전체 자료 조회
	3. intersect : 두 집합의 교집합을 출력(정렬)
	4. minus     : 두 집합의 차집합을 출력(정렬), 선후가 중요
	
	[집합연산자의 조건]
	1. 두 집합의 select절의 컬럼갯수가 동수이어야 한다.
	2. 두 집합의 select절의 같은 위치의 컬럼의 데이터타입은 동일 데이터타입이어야 한다.
	3. 두 집합의 컬럼명이 달라도 상관없다 단, 먼저 정의된 컬럼명으로 정해진다.
*/
select * from student;
select * from professor;

-- 1. union all
select studno 학생번호 from student;
select profno 교수번호 from professor;

select count(studno) from student;
select count(profno) from professor;

-- 학생번호와 교수번호를 합치기
select studno 학생번호 from student
union all
select profno 교수번호 from professor;

select profno 교수번호 from professor
union all
select studno 학생번호 from student;

-- 동일위치의 컬럼의 데이터타입이 다를 경우 에러
select studno from student;
select name from professor;

select studno from student
union all
select name from professor; -- ORA-01790: expression must have same datatype as corresponding expression

select '학생이름 = ', name from student
union all
select '교수이름 = ', name from professor;

-- 컬럼갯수가 동수가 아닐 경우 에러
select studno, name from student;
select profno, name from professor;

select studno, name from student
union all
select profno, name from professor;

select name, studno from student
union all
select profno, name from professor; --> ORA-01790: expression must have same datatype as corresponding expression

-- 2 union vs union all
-- union all 은 중복제거 않함, 정렬을 하지 않음
select studno, name, deptno1 from student
union all
select studno, name, deptno1 from student;

-- union은 중복제거, 정렬을 함
select studno, name, deptno1 from student
union
select studno, name, deptno1 from student;

-- 정렬
select studno, name, deptno1, 1 from student where deptno1 = 101
union
select studno, name, deptno1, 2 from student where deptno1 = 101;

-- 중복제거
select studno, name, deptno1, 1 from student where deptno1 = 101
union
select studno, name, deptno1, 1 from student where deptno1 = 101;

-- 3. 교집합 : intersect
select studno, name, deptno1 from student where deptno1 = 101;
select studno, name, deptno1 from student where deptno1 = 102;
select studno, name, deptno2 from student where deptno2 = 201;

select studno, name, deptno1 from student where deptno1 = 101
intersect
select studno, name, deptno1 from student where deptno1 = 102;


-- 교집합
select studno, name from student where deptno1 = 101
intersect
select studno, name from student where deptno2 = 201;

-- 4. 차집합
select studno, name from student where deptno1 = 101
minus
select studno, name from student where deptno2 = 201;

select studno, name from student where deptno2 = 201
minus
select studno, name from student where deptno1 = 101;

-- subqury
select * from
(
	select studno, name from student where deptno2 = 201
	minus
	select studno, name from student where deptno1 = 101
) t1;

select name from
(
	select studno, name from student where deptno2 = 201
	minus
	select studno, name from student where deptno1 = 101
) t1;

select t1.STUDNO from
(
	select studno, name from student where deptno2 = 201
	minus
	select studno, name from student where deptno1 = 101
) t1;

/* 실습문제 */
-- ex01) 입사일이 1982-01-01 이후이면서 급여가 3000보다 크거나 같은 사원목록
select * from emp where hiredate > '19820101';
select * from emp where hiredate > '1982-01-01';
select * from emp 
 where hiredate > '19820101'
   and sal >= 3000; 
 
-- ex02) 입사일이 1982-01-01 이후이거나 급여가 3000보다 크거나 같은 사원목록
select * from emp 
 where hiredate > '19820101'
    or sal >= 3000; 

-- ex03) 사원이름은 S로 시작되면서 급여가 800인 사원
select * from emp 
 where ename like 'S%'
   and sal = 800; 

-- ex04) 급여가 1000보다 크면서 (comm이 1000보다 작거나 com이 null) 인사원
-- 비교연산할 경우 연산의 우선순위를 주의할 것
select * from emp 
 where sal > 1000
   and comm < 1000
	  or comm is null; 

select * from emp 
 where sal > 1000
   and (comm < 1000 or comm is null); 

-- 혼공의 3장 

-- 대용량의 데이터를 업로드하는 명령
-- 1. oracle에서는 sql loader
-- 2. mysql에서는 load data
-- concat함수









