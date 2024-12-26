/* Index

   1. 인덱스란?
	 
	    인덱스란 어떤 특정의 데이터가 HDD(하드디스크)의 어느 위치에 저장되어 있는지에
		  대한 정보를 가진 주속록과 같은 개념이다.
		
	    인덱스는 데이터와 위치주소(rowid)정보를 key와 value의 형태로 한 쌍으로 저장
		  되어 관리가 된다. 인덱스의 가장 큰 목적은 데이터를 보다 빠르게 검색 또는 조작
		  할 수 있게 하기 위함이다.
	 
	 2. rowid구조
	 
	    rowid는 데이터의 위치정보 즉 저장된 HDD의 메모리주소로서 오라클에서는 총 10bytes크기의
			rowid라는 정보를 제공한다.
			
			rowid 구조 : AAAE5jAAEAAAAFbAAA
			1) AAAE5j : 데이터오브젝트번호(emp, dept,...)
			2) AAE    : 파일번호
			3) AAAAFb : block번호
			4) AAA    : row번호
	    
	 3. Index를 사용하는 이유
	 
		  a. 데이터를 보다 신속하게 조회할 수 있게 하기 위해 사용(조회속도를 향상)
			b. 보통 index테이블의 특정컬럼에 한개 이상을 주게 되면 index table이 따로
			   만들어지는데 이 index테이블에는 인덱스컬럼의 row값과 rowid값이 저장되고
				 로우의 값은 정렬된 b-tree구조로 저장시켜두어 검색시에 좀 더 빠르게 해당
				 데이터를 검색할 수 있게 한다.
		  c. 하지만 update, delete, insert시에는 속도가 느려지는 단점이 있다.
	 
	 4. Index가 필요한 경우
	 
	    a. 데이터가 대용량일 경우
		  b. where 조건절에 자주 사용되는 컬럼들인 경우
		  c. 조회결과가 전체 데이터수의 3~5%미만일 경우 인덱스 검색이 효율적이고
			   보다 적은 비용으로 빠르게 감색할 수 있다.
			
	 5. Index가 필요하지 않은 경우
	   
		  a. 데이터가 적을 경우(수천건미만)경우에는 인덱스를 설정하지 않는게 오히려 성능이 좋다.
		  b. 조회보다 insert, delete, update처리가 빈번하게 처리되는 테이블에는 없는게 좋다.
		  c. 조회결과가 전체행의 15%이상일 경우에는 사용하지 않는게 좋다.	
					
	 6. Index의 자동생성
	 
	    인덱스가 자동생성될 때는 테이블 정의시에 PK, UK의 제약조건을 정의할 때 자동으로 
			생성된다.

	 7. Index문법
	 
	    a. 생성 : create index 인덱스명 on 테이블명(컬럼1....컬럼n)
			b. 삭제 : drop index 인덱스명 
			   (인덱스는 테이블에 종속되어 있는 객체로서 테이블이 삭제가 될 때 자동으로 
				  인덱스도 삭제가 된다.)
*/

-- 1. rowid 조회, 조회가 되지 않을 경우 rowidtochar(rowid)함수를 사용
select rowid, empno, ename from emp;
select rowid, rowidtochar(rowid), length(rowid), length(rowidtochar(rowid)), empno, ename from emp;
-- 7369가 HDD에 저장된 메모리주소 = AAAE5eAAEAAAAFfAAA

-- 2. data dictionary
-- 인덱스조회하기
select * from dba_indexes; -- sysdba권한일 경우에만 가능
select * from all_indexes;
select * from user_indexes;
select * from user_indexes where table_name = 'DEPT2';
select * from all_indexes where owner = 'SCOTT';

-- 3. 인덱스생성하기 
-- a. unique index
create unique index idx_dept2_dname on dept2(dname);
select * from user_indexes where table_name = 'DEPT2';

-- b. non-unique index
select * from dept2;
create index idx_dept2_area on dept2(area);
select * from user_indexes where table_name = 'DEPT2';
select * from user_ind_columns where table_name = 'DEPT2';

-- 4. 결합(복합)인덱스
select ename, sal, job 
  from emp
 where ename = 'SMITH'
   and job = 'CLERK';
	 
select ename, sal, job 
  from emp
 where job = 'CLERK'
   and ename = 'SMITH';

select count(*) from emp where job = 'CLERK';

create index idx_emp_ename_job on emp(ename, job);
select * from user_indexes where table_name = 'EMP';
select * from user_ind_columns where table_name = 'EMP';

-- 5. Index rebuliding하기 
-- 1) 인덱스생성
create table idx_test(no number);
select * from idx_test;

delete from idx_test;

-- begin
-- 	for i in 1..10000 loop
-- 		insert into idx_test values(i);
-- 	end loop;
-- 	commit;
-- end;

select * from idx_test order by 1; -- 0.014s

create index idx_test_no on idx_test(no);
select * from idx_test order by 1  -- 0.013s

/* 연습 문제 */
-- ex01) new_emp4를 생성, no(number), name()
create table new_emp4(
			no			number
		, name		varchar2(10)
		, Sal     number 
);
-- ex02) 데이터를 insert
--    1000, 'SMITH', 300
--    1001, 'ALLEN', 250
--    1002, 'KING', 430
--    1003, 'BLAKE', 220
--    1004, 'JAMES', 620
--    1005, 'MILLER', 810

insert into new_emp4 values(1000, 'SMITH', 300);
insert into new_emp4 values(1001, 'ALLEN', 250);
insert into new_emp4 values(1002, 'KING', 430);
insert into new_emp4 values(1003, 'BLAKE', 220);
insert into new_emp4 values(1004, 'JAMES', 620);
insert into new_emp4 values(1005, 'MILLER', 810);
select * from new_emp4

-- ex03) name컬럼에 인덱스 생성
create index idx_newemp4_name on new_emp4(name)

-- 인덱스를 사용하지 않는 일반적인 SQL
select * from new_emp4

-- 인덱스를 사용하는 SQL
select * from new_emp4 where name = 'KING'





