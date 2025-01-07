/*
	그룹함수
	
	1. count() : 조회되는 데이터들의 총 건수
	2. sum()   : 조회되는 데이터들을 총 합계
	3. avg()   : 조회되는 데이터들을 평균
	4. max()   : 조회되는 데이터들중 최대값
	5. min()   : 조회되는 데이터들을 최소값
	6. stddev(): 조회되는 데이터들을 표준편차값
	7. variance(): 조회되는 데이터들을 분산값
	--------------------------------------------------
	8. rollup()
	9. cube()
	10. groupingset()
	11. listagg()
	12. pivot() / unpivot()
	13. lag()
	14. lead()
	15. rank()
	16. dense_rank()
	17. sum() over()
	18. ration_to_report()
*/
-- 1. count : 조건에 맞는 데이터의 건수를 리턴
select count(*) from emp;
select count(ename) from emp;
select count(comm) from emp;
select count(nvl(comm, 0)) from emp;
select count(*) from emp where deptno = 10;
select count(*), count(ename), count(comm), count(nvl(comm, 0)) from emp;

-- 2. sum() - only 숫자타입
select sum(ename) from emp; -- ORA-01722: invalid number
select sum(sal) from emp;
select sum(sal), sum(comm) from emp;
select sum(sal), sum(comm), sum(nvl(comm, 0)) from emp;

-- 3. avg()
select count(ename) as 총사원수
     , sum(sal) as 총급여
		 , round(avg(sal), 2) as 평균급여
		 , round(sum(sal) / count(ename), 2) 평균급여
  from emp;
		 
select count(ename) as 총사원수
     , sum(sal) as 총급여
		 , round(avg(sal), 2) as 평균급여
		 , round(sum(sal) / count(ename), 2) 평균급여
  from emp
 where deptno = 10;
 
 -- 4. min/max
 -- 1) 문자
select min(ename), max(ename) from emp;

-- 2) 숫자
select min(sal), max(sal) from emp; 
select min(comm), max(comm) from emp; 
select min(nvl(comm, 0)), max(nvl(comm, 0)) from emp; 

select min(sal+comm), max(sal+comm) from emp -- null미처리의 결과
union all
select min(sal+nvl(comm, 0)), max(sal+nvl(comm, 0)) from emp -- null 처리결과;

-- 3) 날짜
select min(hiredate) 최초입사일자
     , max(hiredate) 최후입사일자
  from emp;
	
-- 실습. 
-- 1) 최초입사사원과 최후입사사원은?
select ename, hiredate from emp where hiredate = (select min(hiredate) from emp)
union all 
select ename, hiredate from emp where hiredate = (select max(hiredate) from emp);
		
-- 2) 최저년봉자와 최고년봉자 사원은?
select ename, sal, job from emp where sal = (select min(sal) from emp)
union all 
select ename, sal, job from emp where sal = (select max(sal) from emp);

-- 5. stddev() / variance()
select stddev(sal), variance(sal) from emp;

/* B. group by(데이터를 그룹화하기) */
-- select 그룹기준, 그룹함수 from 테이블 group by 그룹기준
-- 주의사항
-- 1. select절에 사용된 그룹함수 이외의 컬럼이나 표현식은 반드시 group by 절에 정의 되어야 한다.
--    select deptno, sum(sal) from emp group by deptno;

--    group by 절에 사용된 컬럼이라도 select절에 사용되지 않아도 된다.
--    select sum(sal) from emp group by deptno;
  
-- 2. group by절에는 반드시 컬럼명이 사용되어야 한다. 즉, 컬럼의 alias는 사용할 수 없다.
--    select deptno 부서번호, sum(sal) from emp group by 부서번호;

-- 3. group by절을 사용한 select문에 order by절로 정렬을 하기 위해서는 order by절은 group by절
--    뒤에 정의해야 한다. order by 절에서는 별칭도 정의가 가능하다. 

select sum(sal) from emp;
select deptno, sum(sal) from emp; -- ORA-00937: not a single-group group function
select sum(sal) from emp group by deptno;
select deptno, sum(sal) from emp group by deptno;

-- group by절에는 별칭을 사용할 수 없다.
select deptno 부서번호, sum(sal) from emp group by 부서번호; -- ORA-00904: "부서번호": invalid identifier


-- order by는 group by를 사용할 경우에는 반드시 group by절 뒤에 위치해야 한다.
select deptno, sum(sal) from emp order by deptno group by deptno; -- ORA-00933: SQL command not properly ended
select deptno 부서번호, sum(sal) from emp group by deptno order by deptno;

-- order by절에는 컬럼순서 or 별칭도 가능하다.
select deptno 부서번호, sum(sal) from emp group by deptno order by 1;
select deptno 부서번호, sum(sal) from emp group by deptno order by 부서번호;


-- 부서별로 총급여를 출력하세요!
select 10 부서번호, sum(sal) from emp where deptno = 10
union all
select 20, sum(sal) from emp where deptno = 20
union all
select 30, sum(sal) from emp where deptno = 30;

select deptno, sum(sal) from emp group by deptno order by 1;

-- 실습. emp테이블에서
-- ex01) 부서별로 사원수와 급여평균, 급여합계를 구하기, 정렬은 부서(deptno)
select deptno
     , count(*)
		 , sum(sal)
		 , round(avg(nvl(comm, 0)), 2)
  from emp
 group by deptno
 order by deptno;
 
-- ex02) 직급별로 인원수, 평균급여, 최고급여, 최소급여, 급여합계, 정렬은 직급(job) 
select job
     , count(*)
		 , sum(sal)
		 , round(avg(nvl(comm, 0)), 2)
		 , min(sal) 최소급여
		 , max(sal) 최대급여
  from emp
 group by job
 order by 1;

select ename, sal
  from emp 
where job = 'ANALYST';

select job
     , count(*)
		 , sum(sal)
		 , round(avg(nvl(comm, 0)), 2)
		 , min(sal) 최소급여
		 , max(sal) 최대급여
  from emp
 group by job
 order by 1
 where job = 'ANALYST';
 
 select job
      , count(*)
		  , sum(sal)
		  , round(avg(nvl(comm, 0)), 2)
		  , min(sal) 최소급여
		  , max(sal) 최대급여
  from emp
 where job = 'ANALYST'	
 group by job
 order by 1;
 
 select * 
   from (select job
							, count(*)
							, sum(sal)
							, round(avg(nvl(comm, 0)), 2)
							, min(sal) 최소급여
							, max(sal) 최대급여
					from emp	
				 group by job
				 order by 1)
  where job = 'ANALYST';	
	
/* C. 그룹결과를 조건별 결과 구하기(having) */
-- 단일행 함수에서 사용했던 where조건문과 동일하다.
-- 즉, 그룹화에서 조건을 주기 위해서는 having절을 사용하고 where절에는 집계함수를
-- 사용할 수 없다. 
-- having절은 집계함수를 가지고 조건을 비교할때 사용되며 having절을 group by절과
-- 함께 사용한다. 즉, having절을 group by절 없이 사용할 수 없다.	
 select job
      , count(*)
		  , sum(sal)
		  , round(avg(nvl(comm, 0)), 2)
		  , min(sal) 최소급여
		  , max(sal) 최대급여
  from emp
 group by job having job = 'ANALYST'
 order by 1;
 
-- 평균급여가 >= 3000  
select job
      , count(*)
		  , sum(sal)
		  , round(avg(nvl(comm, 0)), 2)
		  , min(sal) 최소급여
		  , max(sal) 최대급여
  from emp
 group by job
having round(avg(nvl(comm, 0)), 2) >= 550;

/* D. 소계 및 총계구하기 */
-- 1. rollup()
--    rollup절은 group by절과 같이 사용되며 group by절에 의해서 그룹지어진 집합결과
--    에 대해 좀 더 상세한 정보를 반환한다. 즉, 소계, 총계를 그룹별로 구할 수 있다.
--    select절에 rollup을 사용함으로 보통의 select된 데이터와 그 데이터의 소계, 총계를
--    구할 수 있다.

-- 직급별 급여합계, 총계 구하기
select * from (select job, sum(sal) from emp group by job
							  union all
	             select null, sum(sal) from emp);
 
select job, sum(sal)
  from emp
 group by rollup(job); 
 
-- 실습. 부서별/직급별, 부서별소계, 총계
-- 1. 부서별, 직급별, 사원수, 평균급여
-- 2. 부서별          사원수, 평균급여
-- 3. 총계            사원수, 평균급여
-- 하나의 결과로 출력하기
select * 
from (select deptno, job, count(*), sum(sal) from emp group by deptno, job
			 union all 
			 select deptno, null, count(*), sum(sal) from emp group by deptno
			 union all 
			 select null , null, count(*), sum(sal) from emp)
order by deptno, job; 

select deptno, job, count(*), sum(sal) 
  from emp 
 group by rollup(deptno, job);
 
-- rollup은 자동으로 소계와 합계를 구해주는 함수
-- group by rollup(deptno, job) -> 그룹 3개(M+1)의 그룹이 생성
select deptno, job, count(*), sum(sal) 
  from emp 
 group by deptno, rollup(job); -- 그룹 M+1 -> 2개, 즉 결과는 총계없음

-- 실습
-- professor테이블에서 deptno, position별로 교수인원수, 급여합계(rollup함수 이용)
select deptno
     , position
     , count(*)
		 , sum(pay)
  from professor
 group by rollup(deptno, position);
 
-- 2. cube()
-- 실습. 총계, 부서소계,부서/직급
select null, null, sum(sal) from emp
union all
select null, job, sum(sal) from emp group by job
union all
select deptno, job, sum(sal) from emp group by deptno,job;

select deptno, job, count(*), sum(sal) 
  from emp 
 group by rollup(deptno), rollup(job);
 
select deptno, job, count(*), sum(sal) 
  from emp 
 group by cube(deptno, job);
  
select deptno, job, count(*), sum(sal) 
  from emp 
 group by cube(deptno), cube(job);
 
-- 3. grouping sets()함수
-- 그룹핑조건이 여러개일경우 한번에 sql실행하기

-- 실습. student에서 학년별인원수와 전공별인원수를 한번에 구하기
select grade, count(*) from student group by grade
union all
select deptno1, count(*) from student group by deptno1;

select grade, deptno1, count(*)
  from student 
 group by grade, deptno1; 
 
select grade, deptno1, count(*)
  from student  
 group by grouping sets(grade, deptno1);

/* E. 순위함수 

	 1. rank()함수는 순위를 부여하는 함수로서 동일순위처리가 가능(중복순위 1,2,2,4)
	 2. dense_rank()함수는 동일순서의 처리에 영향이 없다.(중복순위, 1,2,2,3)
	 3. row_numer()함수는 특정순위의 일련번호를 제공하는 함수 동일순위처리 불가(1,2,3,4,)
	 
	 주의사항
	 순위함수 사용시에는 order by절은 필수로 정의해야 한다.
*/

-- 1. rank()
-- 1) 특정자료의 순위 : rank(조건식) within group(order by 조건식 컬럼명 [asc|desc])
-- 2) 전체순위        : rank() over(order by order by 조건식 컬럼명 [asc|desc])
-- 3) 그룹별 순위     : rank() over(partition by 컬럼... order by 컬럼)

-- 1) 특정조건에서의 순위
-- SMITH가 몇번째(이름(알파벳)순)
select rownum, ename from (select ename from emp order by ename);
select rank('SMITH') within group(order by ename) from emp;
select rank('SMITH') within group(order by ename asc) from emp;
select rank('SMITH') within group(order by ename desc) from emp;

-- 2) 전체순위
-- emp에서 사원들의 급여순서?
select empno, ename, sal
     , rank() over(order by sal) 급여내림차순
		 , rank() over(order by sal desc) 급여오름차순
  from emp;
	
-- 3) 전체에서 그룹별 순위
-- 그룹별순위를 구할때 partition by를 사용한다.
-- 부서별 내 급여의 순위?
select deptno, ename, sal
     , rank() over(partition by deptno order by sal)
  from emp;
	
-- 실습. 부서별, 직급 급여순위
select deptno, job, ename, sal
     , rank() over(partition by deptno, job order by sal)
  from emp;

-- 2. dense_rank()
select ename, sal
     , rank()       over(order by sal)
		 , dense_rank() over(order by sal)
  from emp;
	
-- 3. row_number()
select ename, sal
     , rank()       over(order by sal) rank
		 , dense_rank() over(order by sal) dense_rank
		 , row_number() over(order by sal) row_number
  from emp;

/* F. 합계함수
	    1. sum() over        : 누계를 구하는 함수
				... sum(컬럼) over (order by 컬럼(기준열))
			2. ratio_to_report() : 비율을 구하는 함수
*/

-- 1. sum over()
-- 판매일자별 누적판매액
-- 1 1000   1000
-- 2  500   1500
-- 3  300   1800

-- 실습. panmae테이블에서 1000번대리점의 판매누계구하기
-- (판매일자, 제품코드, 수량, 판매량, 누계)
select to_char(to_date(p_date), 'YYYY.MM.DD') 판매일
     , p_code
		 , p_qty
		 , p_total
	from panmae where p_store = 1000;
	
select to_char(to_date(p_date), 'YYYY.MM.DD') 판매일
     , p_code
		 , p_qty
		 , p_total
		 , sum(p_total) 판매누계
	from panmae where p_store = 1000
 group by p_date, p_code, p_qty, p_total;	

select to_char(to_date(p_date), 'YYYY.MM.DD') 판매일
--     , p_code
-- 		 , p_qty
		 , p_total
		 , sum(p_total) over(order by p_date) 판매누계
	from panmae where p_store = 1000; 
	
-- 실습. 
-- 1. 상기예제를 제품코드별로 누계구하기
-- sum() over(partition by order by)
select to_char(to_date(p_date), 'YYYY.MM.DD') 판매일
    , p_code
-- 		 , p_qty
		 , p_total
		 , sum(p_total) over(partition by p_code order by p_date) 판매누계
	from panmae where p_store = 1000; 
	
-- 2. 전체대리점에서 제품코드/대리점별 누계구하기
select to_char(to_date(p_date), 'YYYY.MM.DD') 판매일
     , p_code
		 , p_store
		 , p_total
		 , sum(p_total) over(partition by p_code, p_store order by p_date) 판매누계
	from panmae; 		


-- 2. ratio_to_report() over()
-- 총판매액비율에서 1일 판매액의 비율
-- 100 %  1000
-- 1   100    10%
-- 2.   50    5%
-- 판매비율
select p_code
     , sum(p_qty)   over() tot_qty
		 , sum(p_total) over() tot_amt
		 , p_store
		 , p_total
		 , round(ratio_to_report(sum(p_qty)) over() * 100, 2) "수량(%)"
		 , round(ratio_to_report(sum(p_total)) over() * 100, 2) "금액(%)"
  from panmae
 where p_store = 1000
 group by p_code, p_store, p_qty, p_total;


/* 연습문제 */
-- 1. emp 테이블을 사용하여 사원 중에서 급여(sal)와 보너스(comm)를 합친 금액이 가장 많은 경우와 
--    가장 적은 경우 , 평균 금액을 구하세요. 단 보너스가 없을 경우는 보너스를 0 으로 계산하고 
--    출력 금액은 모두 소수점 첫째 자리까지만 나오게 하세요
-- MAX, MIN, AVG

-- 2. student 테이블의 birthday 컬럼을 참조해서 월별로 생일자수를 출력하세요
-- TOTAL, JAN, ...,  5 DEC
--  20EA   3EA ....

-- 3. Student 테이블의 tel 컬럼을 참고하여 지역별 인원수를 출력하세요.
--    단, 02-SEOUL, 031-GYEONGGI, 051-BUSAN, 052-ULSAN, 053-DAEGU, 055-GYEONGNAM
--    으로 출력하세요

-- 4. emp 테이블을 사용하여 직원들의 급여와 전체 급여의 누적 급여금액을 출력,
-- 단 급여를 오름차순으로 정렬해서 출력하세요.
-- sum() over()

-- 6. student 테이블의 Tel 컬럼을 사용하여 아래와 같이 지역별 인원수와 전체대비 차지하는 비율을 
--    출력하세요.(단, 02-SEOUL, 031-GYEONGGI, 051-BUSAN, 052-ULSAN, 053-DAEGU,055-GYEONGNAM)
		 
-- 7. emp 테이블을 사용하여 부서별로 급여 누적 합계가 나오도록 출력하세요. 
-- ( 단 부서번호로 오름차순 출력하세요. )

-- 8. emp 테이블을 사용하여 각 사원의 급여액이 전체 직원 급여총액에서 몇 %의 비율을 
--    차지하는지 출력하세요. 단 급여 비중이 높은 사람이 먼저 출력되도록 하세요
	
-- 9. emp 테이블을 조회하여 각 직원들의 급여가 해당 부서 합계금액에서 몇 %의 비중을
--     차지하는지를 출력하세요. 단 부서번호를 기준으로 오름차순으로 출력하세요.










