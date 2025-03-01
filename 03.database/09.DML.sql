/* DML 
	1. insert : 테이블에 데이터를 추가
	2. update : 테이블에 있는 데이터를 수정
	3. delete : 테이블에 있는 데이터를 삭제
	4. merge  : 두개이상의 테이블을 병합

*/
/* A. insert 

	 1. 테이블에 새로운 로우(레코드)를 추가할 때 사용하는 SQL문
	 2. 테이블에 새로운 데이터를 입력(추가)하기 위해 사용하는 데이터조작어(DML)
	 3. 테이블의 모든 컬럼에 자료를 입력하는 경우에는 컬럼목록을 기술하지 않아도 된다.
	    컬럼목록이 생략되면 values절에 있는 값들이 테이블의 기본컬럼순서대로 입력이 된다.
			
	 4. 문법
	    
			insert into 테이불명[(col1,..., coln)] values(val1,...,valn);
			insert into 테이불명 values(테이블의 모든 컬럼에 해당하는 값들)
			
			** insert명령은 컬럼의 갯수와 값의 갯수가 동수이어야 한다.		
*/

-- 1. 자료추가
-- dept2의 부서번호=9000, 부서명=테스트_1, 상위부서=1006, 지역=기타지역
select * from dept2;
insert into dept2(dcode, dname, pdept, area)
			values(9000, '테스트_1', 1006, '기타지역'); 
insert into dept2	values(9001, '테스트_2', 1006, '기타지역2'); 			
select * from dept2;

-- 실습1) professor테이블에 교수번호=5001, 교수명='홍길동', 교수ID='hong', position=정교수,
--       급여=510, 입사일은 2020-07-01
insert into professor(profno, name, id, position, pay, hiredate)
       values(5001, '홍길동', 'hong', '정교수', 510, '2020-07-01');
			 
insert into professor(profno, name, id, position, pay)
       values(5002, '홍길동', 'hong', '정교수', 510); 
			 -- ORA-01400: cannot insert NULL into 
			 -- ORA-00001: unique constraint (SCOTT.SYS_C007005) violated

-- 제약사항(constrain, NOT NULL(NN), Unique Key(UK), Primary Key(PK), Forgin Key(FK), check);			 
select * from cols where table_name like 'PRO%';
select * from all_tab_columns where owner = 'SCOTT' and table_name like 'PRO%';
							
select * from professor;

-- 실습2) 
-- a. professor에서 테이블의 구조만 복사, 테이블의 professor4
-- b. professor에서 profno가 4000번보다 큰 교수만 추가
-- 서브쿼리를 사용
create table professor4 as select * from professor where 1=2;
insert into professor4 select * from professor where profno > 4000; 
select * from professor4;

drop table professor4;
create table professor4 as select * from professor where profno > 4000; 
select * from professor4;

-- 실습3) 
-- a. professor을 기준으로 prof_3, prof_4를 생성
--    각각 profno number, name varchar2(25)의 2개의 열만 존재
-- b. prof_3는 교수번호가 1000~1999까지의 교수를
--    prof_4는 교수번호가 2000~2999까지의 교수를 복사(추가)하세요!
create table prof_3 (
		profno  number
	, name 		varchar2(25)
);

create table prof_4 as select profno, name from professor where 1=2;

select * from all_tab_columns where owner = 'SCOTT' and table_name like 'PROF_%';

insert into prof_3 select profno, name from professor where profno between 1000 and 1999;
insert into prof_4 select profno, name from professor where profno between 2000 and 2999;

select * from prof_3;
select * from prof_4;

-- insert all(1)
-- 각각의 테이블에 일괄로 추가
delete from prof_3;
delete from prof_4;

insert all
  when profno between 1000 and 1999 then into prof_3 values(profno, name)
  when profno between 2000 and 2999 then into prof_4 values(profno, name)
select profno, name from professor;

select * from prof_3;
select * from prof_4;
	
-- insert all(2)
-- 각각의 테이블에 동일에 데이터를 일괄로 추가
-- 3000대의 교수자료를 일괄로 prof_3와 prof_4에 동시에 추가하기

insert all
  into prof_3 values(profno, name)
  into prof_4 values(profno, name)
select profno, name from professor where profno between 3000 and 3999;

select * from prof_3;
select * from prof_4;

/* B. update 
      
			1. 테이블에 저장된 데이터를 수정하기 위해 사용되는 명령문이다.
			2. 기존의 행을 수정하기 위해 사용한다.
			3. 주의할 점은
			   where조건절을 사용하지 않으면 전체 자료가 업데이트 된다.
      4. update 문법
			
			   update table
				    set col1 = val1,
						    ...
								coln = valn
				  where 조건절 			
*/
create table emp999 as select * from emp;
select * from emp999;

-- 전체자료를 deptno를 10으로 수정하기
update emp999
   set deptno = 10;
select * from emp999;

truncate table emp999;
insert into emp999 select * from emp;
select * from emp999;


/* update 연습문제 */
-- 실습1)  emp999에서 모든 사원의 급여를 10%인상하기(수정)
select sal, sal*1.1 from emp999;
update emp999
   set sal = sal * 1.1;
select sal from emp999;

-- 실습2) emp999에서 모든 사원의 입사일을 현재일(sysdate)로 수정
update emp999
	 set hiredate = sysdate;

-- 실습3) professor에서 직급이 'assistant professor'인 사람의 bonus를 200으로 인상
select * from professor where position = 'assistant professor';
update professor
   set bonus = 200
 where position = 'assistant professor';

-- 실습4) professor에서 'Sharon Stone'과 직급이 동일한 교수들의 급여를 15%인상
--        서브쿼리를 이용 -> where절에 서브쿼리를 지정
--                        -> where position = (서브쿼리 즉, select문을 정의)
select * from professor where name = 'Sharon Stone';
select * from professor where position = 'instructor';

select pay, pay*1.5
  from professor
 where position = 'instructor';
 
select pay, pay*1.5
  from professor
 where position = (select position from professor where name = 'Sharon Stone'); 
 
update professor
   set pay = pay * 1.5
 where position = (select position from professor where name = 'Sharon Stone'); 	
 
select pay
  from professor
 where position = (select position from professor where name = 'Sharon Stone'); 

-- emp
create table emp99 as select * from emp;

-- ex01) job이 'MANAGER'인 사원은 급여를 15%인상하기
select * from emp99 where job = 'MANAGER';
update emp99
   set sal = sal * 1.15
 where job = 'MANAGER';

-- ex02) SCOTT 사원의 부서번호=30번, JOB=MANAGER로 한꺼번에 수정하는 쿼리
update emp99
   set deptno = 30
	   , job = 'MANAGER'
 where ename = 'SCOTT';
select * from emp99;	

-- ex03) professor테이블에서 'Sharon Stone'교수의 bonus를 100만원으로 인상하기
select * from professor where name = 'Sharon Stone';
update professor
   set bonus = nvl(bonus, 0) + 100
 where name = 'Sharon Stone';

-- ex04) professor에서 'Sharon Stone'교수의 직급과 동일한 직급을 가진 교수들중
-- 현재급여가 300보다 작은 교수들의 급여를 15%인상하세요!
select * from professor where name = 'Sharon Stone';
select * from professor where pay < 500 
                          and position = (select position from professor where name = 'Sharon Stone');

update professor
   set pay = pay * 1.5
 where position = (select position from professor where name = 'Sharon Stone')
   and pay < 500; 	

-- 문제풀이사이트 : www.gurubee.net

/* C. delete 
	테이블에서 특정조건에 맞는 자료를 삭제하는 명령
	문법: delete from 테이블명 [where .... ]
*/
select * from dept2 where dcode = 9000;
-- dcode 9000인 자료 삭제
delete from dept2 where dcode = 9000;

-- 실습. 부서번호가 9000~9999사이의 자료를 삭제하기
delete from dept2 where dcode between 9000 and 9999;

/* D. merge */
-- 여러개의 테이블을 한개의 테이블로 병합하는 명령
-- merge into 테이블1
--      using 테이블2 on 병합조건절
--            when     matched then update set 업데이트할 내용
--                                  [delete where 조건절]
--            when not matched then insert values(컬럼이름...)
create table charge_01 (
		u_date   varchar2(6)
	, cust_no  number
	, u_time   number
	, charge   number
);

create table charge_02 (
		u_date   varchar2(6)
	, cust_no  number
	, u_time   number
	, charge   number
);

create table charge_03 (
		u_date   varchar2(6)
	, cust_no  number
	, u_time   number
	, charge   number
);

create table charge_total (
		u_date   varchar2(6)
	, cust_no  number
	, u_time   number
	, charge   number
);

insert into charge_01 values('241001', 1000, 2, 1000);
insert into charge_01 values('241001', 1001, 2, 1000);
insert into charge_01 values('241001', 1002, 1, 1000);

insert into charge_02 values('241002', 1000, 3, 1500);
insert into charge_02 values('241002', 1001, 4, 2000);
insert into charge_02 values('241002', 1003, 2, 500);
insert into charge_02 values('241002', 1003, 20, 5000);

select * from charge_01
union all
select * from charge_02
union all
select * from charge_03;

-- 1. merge : charge_01 + charge_total
merge into charge_total tot
     using charge_01    c01 on (tot.u_date = c01.u_date)
		  when     matched then update set tot.cust_no = c01.cust_no
			when not matched then insert values(c01.u_date, c01.cust_no, c01.u_time, c01.charge);

select * from charge_total;

-- 2. merge : charge_02 + charge_total
merge into charge_total tot
     using charge_02    c02 on (tot.u_date = c02.u_date)
		  when     matched then update set tot.cust_no = c02.cust_no
			when not matched then insert values(c02.u_date, c02.cust_no, c02.u_time, c02.charge);
			
			
-- 3. merge : charge_03 -> update charge_total
insert into charge_03 values('241002', 1003, 10, 8000);
insert into charge_03 values('241003', 1004, 10, 8000);

merge into charge_total tot
     using charge_03    c03 on (tot.u_date = c03.u_date and tot.cust_no = c03.cust_no)
		  when     matched then update set tot.u_time = c03.u_time, tot.charge = c03.charge
			when not matched then insert values(c03.u_date, c03.cust_no, c03.u_time, c03.charge);			

-- 4. merge : update and delete
merge into charge_total tot
     using charge_03    c03 on (tot.u_date = c03.u_date and tot.cust_no = c03.cust_no)
		  when     matched then update set tot.u_time = c03.u_time, tot.charge = c03.charge
													  delete where (tot.u_date = c03.u_date and tot.cust_no = c03.cust_no)
			when not matched then insert values(c03.u_date, c03.cust_no, c03.u_time, c03.charge);

select * from charge_total;












