/* Sub Query

   1. Main Query에 반대되는 개념으로 이름을 부여한 것 임.
	 2. 메인쿼리를 구성하는 소단위 쿼리(종속쿼리)
	 3. subquery안에는 select, insert, delete, update모두 사용 가능
	 4. subquery의 결과값을 메인쿼리가 사용하는 중간값으로 사용된다.
	 5. subquery자체는 일반 쿼리와 다를 바가 없다.
	 
	 서브쿼리의 종류
	 1. 연관성에 따른 분류
	  
		  1) 연관성이 있는 서브쿼리
			2) 연관성이 없는 서브쿼리
	 
	 2. 위치에 따른 분류
	 
	    1) 인라인뷰 : select or from 절에 위치
			2) 중첩쿼리 : where 절에 위치
*/

/* A. 단일행/단일열 서브쿼리 */
-- Sharon Stone의 position과 동일한 교수목록
select * 
  from professor
 where position = (select position from professor where name = 'Sharon Stone');

-- scott에서 hr의 객체를 접근여부?
select * from scott.emp;
select * from hr.employees; -- 접근불가, > ORA-00942: table or view does not exist

-- scott에서 hr에 있는 객체(table, view..)에 접근하려면
-- 접근권한을 부여해야 한다. 
-- sysdba or hr에서 접근권한을 부여하기(hr에서 실행)
grant select on employees to scott; -- 부여
revoke select on employees from scott;  -- 해제

grant select on hr.employees to scott; 
grant select on hr.departments to scott; 
grant select on hr.locations to scott; 
grant select on hr.jobs to scott; 

select * from hr.employees;
select * from hr.departments;

select emp.first_name || '.' || last_name
     , department_id
		 , (select department_name 
		      from hr.departments	dpt
				 where dpt.department_id = emp.department_id) 부서명
  from hr.employees 	emp;

-- 실습) hr계정의 사원테이불에서 평균급여보다 작은 사원만 출력	 
-- 단일행, 단일컬럼일 경우
select employee_id  사원번호
     , first_name || '.' || last_name 사원이름
		 , salary 급여
  from hr.employees 
 where salary < (select round(avg(salary)) from hr.employees);

/* B. 다중행/다중열 서브쿼리 */
select * from hr.departments where location_id in (1700, 1800);
select * from hr.locations where state_province is null;

-- 다중행/단일열
-- 부서에서 state_province가 null 부서를 조회
-- 서브쿼리로 작성(where절에)
select dpt.department_name 부서명
  from hr.departments dpt
 where location_id in (select location_id 
                         from hr.locations 
												where state_province is null);

-- 실습01) 월급이 가장많은 사원	
-- (first_name, last_name, job_title)
-- 직급은 hr.jobs(권한부여)
select * from hr.jobs;
select * from hr.employees;
select max(salary) from hr.employees;

create or replace view v_max_sal
as
select emp.employee_id  사원번호
     , emp.first_name || '.' || emp.last_name 사원이름
		 , emp.salary 급여
		 , job.job_title 직급
  from hr.employees emp
	   , hr.jobs      job
 where emp.salary = (select max(salary) from hr.employees)
   and emp.job_id = job.job_id;

-- 실습02) 월급이 가장적은 사원 
-- (first_name, last_name, job_title)	
create or replace view v_min_sal
as
select emp.employee_id  사원번호
     , emp.first_name || '.' || emp.last_name 사원이름
		 , emp.salary 급여
		 , job.job_title 직급
  from hr.employees emp
	   , hr.jobs      job
 where emp.salary = (select min(salary) from hr.employees)
   and emp.job_id = job.job_id;
	 

create or replace view v_min_max_sal
as
select * from v_min_sal
union all
select * from v_max_sal;

select * 
  from v_min_max_sal
 where 사원이름 like 'TJ%';


-- 실습03) 월급이 평균급여보다 많은 사원	
-- 미국내에서 근무하는 사원들에 대한 평균급여
-- (first_name, last_name, job_title)
-- locations의 country_id = 'US'
-- hint) 서브쿼리안엔 employees, departments, locations
select * from hr.jobs;
select * from hr.employees;
select * from hr.locations where country_id = 'US';

-- 평균급여
select round(avg(emp.salary))
  from hr.departments dpt
		 , hr.locations   loc
	   , hr.employees   emp
 where loc.country_id = 'US'
   and loc.location_id = dpt.location_id
	 and dpt.department_id = emp.department_id;


create or replace view v_미국_평균급여
as
select emp.first_name || '.' || emp.last_name 사원이름
     , emp.salary 급여
		 , job.job_title 직급
  from hr.employees   emp
	   , hr.jobs        job
 where emp.job_id = job.job_id
   and emp.salary > (select round(avg(emp.salary))
											 from hr.departments dpt
												  , hr.locations   loc
												  , hr.employees   emp
										  where loc.country_id = 'US'
											  and loc.location_id = dpt.location_id
											  and dpt.department_id = emp.department_id);

select * from V_미국_평균급여;

/* C. 다중행/다중열 update */
-- data dictionary
-- table, column의 주석 조회하기
select * from user_tab_comments;
select * from user_col_comments;

drop table month_salary;
create table month_salary(       		/* 급여월마감테이블 */
		magam_date   		date not null  	/* 마감월 */
	,	department_id 	number         	/* 부서번호 */
	, emp_count 			number					/* 사원수 */	
	, total_salary		number 					/* 급여총액 */ 
	, average_salary	number 					/* 급여평균 */ 	
);

-- 주석달기
comment on table month_salary is '급여 월 마감 테이블';
comment on column month_salary.magam_date is '마감월 정보입니다!';

-- 실습. 부서별, 총사원수, 급여총액, 평균급여
-- 1) 초기화 : 해당월(sysdate에 마지막일기준)에 데이터 초기화, 생성
-- insert into .... select .... employees group by
-- 2024.09.31 1000 0 0 0
-- 2024.09.31 1001 0 0 0
-- 2024.09.31 1002 0 0 0

-- step1. 기존자료삭제
delete from month_salary 
 where magam_date = to_char(last_day(sysdate), 'YYYY-MM-DD');
 
-- step2. 초기화
insert into month_salary
select to_char(last_day(sysdate), 'YYYY-MM-DD')
     , department_id
		 , 0
		 , 0
		 , 0
  from hr.employees
 where department_id is not null
 group by department_id;
 
select * from month_salary;

-- 2) 급여총액, 급여평균등을 update
-- update month_salary ....
-- 2024.09.31 1000 12 585858 484848
-- 2024.09.31 1001 12 585858 484848
-- 2024.09.31 1002 12 585858 484848

-- step3. 월마감
update month_salary sal
   set sal.emp_count = (select count(*) 
	                        from hr.employees emp 
												 where sal.department_id = emp.department_id)
	   , sal.total_salary = (select sum(salary) 
	                        from hr.employees emp 
												 where sal.department_id = emp.department_id)
     , sal.average_salary = (select round(avg(salary))
	                             from hr.employees emp 
												      where sal.department_id = emp.department_id);
		 
select * from month_salary;

create or replace view v_magam
as 
select department_id
     , count(*) 
	   , sum(salary)   
		 , round(avg(salary))									
	from hr.employees emp 
 where department_id is not null
 group by department_id;
 
/* C. 다중로우, 다중컬럼 연산자 */
-- 1. 비교연산자 : in, between, exists
-- 2. >any : 결과중에 최소값을 리턴
-- 3. <any : 결과중에 최대값을 리턴
-- 4. >all : 결과중에 최대값을 리턴
-- 5. <all : 결과중에 최소값을 리턴

-- a. 비교연산자
select dpt.department_name
  from hr.departments dpt
		 , hr.locations   loc
 where loc.country_id = 'US'
   and loc.location_id = dpt.location_id;

-- b. in 연산자
select dpt.department_name
  from hr.departments dpt
		 , hr.locations   loc
 where loc.location_id in (select location_id 
                             from hr.locations 
														where loc.country_id = 'US');
	 
-- c. any, all 연산자 
-- salary가 부서=30인 최소급여보다 많은 사원목록
select min(salary) from hr.employees where department_id = 30;

select emp.first_name || '.' || emp.last_name 사원이름
  from hr.employees   emp
 where salary > (select min(salary) from hr.employees where department_id = 30);

select emp.first_name || '.' || emp.last_name 사원이름
  from hr.employees   emp
 where salary >any (select salary from hr.employees where department_id = 30);

select emp.first_name || '.' || emp.last_name 사원이름
  from hr.employees   emp
 where salary > (select max(salary) from hr.employees where department_id = 30);

select emp.first_name || '.' || emp.last_name 사원이름
  from hr.employees   emp
 where salary >all (select salary from hr.employees where department_id = 30);

/*
	가상테이블
	
	1회성 테이블처럼 임시로 사용하고자 할 경우에는 'with'절을 사용하여
	가상테이블 처럼 사용할 수 있다.
	
	사용방법
	
	with 가상테이블명 as (
		 sub query
	)
	select 컬럼명 from 가상테이블명;
*/

with temp_table as (
	select employee_id 사원번호
	     , emp.first_name || '.' || emp.last_name 사원이름
			 , emp.salary 사원급여
	  from hr.employees emp
)
select * from temp_table;
select * from temp_table; -- table not found

with aa as(
	select rownum, 'test1' as name, sysdate from dual union all
	select rownum, 'test2' as name, sysdate from dual union all
	select rownum, 'test3' as name, sysdate from dual union all
	select rownum, 'test4' as name, sysdate from dual
),
bb as(
	select rownum, 'test1' as name, sysdate from dual union all
	select rownum, 'test2' as name, sysdate from dual union all
	select rownum, 'test3' as name, sysdate from dual union all
	select rownum, 'test4' as name, sysdate from dual
)
select aa.name
  from aa, bb
 where aa.name = bb.name;