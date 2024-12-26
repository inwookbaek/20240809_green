/* 객체 생성 create 객체명(테이블, 뷰, 인덱스, ....) 

  [table생성하기]
	
	1. 문법
	create table명 (
		컬럼명1 데이터타입(크기),
		...
		컬럼명n 데이터타입(크기)
	);
	
	2. 데이터타입
	
	   1) 문자형
		    
				a. char(n)     : 고정길이 / 최대 2KB / 기본크기 1byte
				b. varchar2(n) : 가변길이 / 최대 4KB / 기본크기 1byte
				c: long        : 가변길이 / 최대 2GB
				 
		 2) 숫자형
		 
		    a. number(p,s) : 가변숫자 / p(1~38 기본값=38), s(-84~127 기본값=0) / 최대 22bytes
				b. float(p)    : number하위타입 / p(1~128 기본값=128) / 최대 22bytes	 
		 		 
		 3) 날짜형
		 
		    a. date      : bc 1970.01.01 ~ 9999.12.31까지 연,월,시,분,초까지 입력가능
				b. timestamp : 연,월,일,시,분,초,밀리초까기 입력이 가능
							
		 4) lob타입 : LOB란 Large Object의 약자로 대용량 데이터를 저장할 수 있는 데이터
			  타입이아. 일반적으로 그래프, 이미지, 사운드등 비정형 데이터를 저장할 때 LOB
				타입을 사용한다.
				
				문자형 대용량 데이터는 CLOB나 NLOB를 사용하고 그래프,이미지,동영상등의 데이터
				는 주로 BLOB를 주로 사용한다. 
				
				a. clob  : 문자형 대용량 객체, 고정길이와 가변길이 문자형을 지원
				b. blob  : 이진형 대용량 객체 주로 멀티미디어자료를 저장
				c. bfile : 대용량 이진파일에 대한 파일의 위치 및 이름을 저장
	
*/


/* 테이블생성하기 */
-- 1. 객체생성
create table 마이테이블(
		no        number(3,1)
	, name      varchar2(10)
	, hiredate  date
);

create table mytable(
		no        number(3,1) -- 99.9
	, name      varchar2(10)
	, hiredate  date
);

-- 소유테이블 조회
select * from user_tables;

-- 소유한 테이블의 컬럼
select * from cols where table_name = 'mytable'; -- not found 즉, 소문자는 불가
select * from cols where table_name = 'MYTABLE';

-- 식별자는 한글도 가능
create table 마이테이블1(
		번호        number(3,1)
	, 이름      varchar2(10)
	, 입사일  date
);

select * from cols where table_name like '마이테이블%';

-- 2. 객체삭제
-- drop 객체타입 객체명
drop table 마이테이블;
drop table if exists 마이테이블;

/* 테이블생성시 제한사항
	1. 테이블이름은 반드시 문자로 시작해야 한다. 중간에 숫자가 포함되는 것은 가능하다.
	2. 특수문자도 사능하지만 테이블생성시 큰따옴표로 감싸야 한다. 하지만 권장하지는 않는다.
	3. 테이블이름이나 컬럼이름은 최대 30byte까지 가능(즉, 한글은 15자리까지만 가능)
	4. 테이블명은 중복할 수가 없다.
	5. 테이블명이나 컬럼이름에는 오라클키워드를 사용하지 않기를 권장
	   (하지만 절대로 사용하지 마세요!!!!)
*/


select * from cols where table_name = 'MYTABLE2';

-- 3. 객체수정
-- alter 객체명 
-- alter user scott identified by tiger;

-- 4. 객체삭제
-- trunc 객체명
-- drop은 완전삭제, trunc 자료만삭제
-- truncate table mytatble2;

-- 5. 테이블에 자료 추가하기
-- insert into 테이블명(컬럼1,....) values(값1,...)
-- insert명령은 컬럼의 갯수와 밸류의 갯수가 동일해야 한다. 아니면 에러
-- 또한, 컬럼과 값의 데이터타입이 동일함이 원칙이다. 문자형을 제외하고는
-- 데이터타입이 다를 경우에는 에러가 발생한다.
delete from mytable;
select * from mytable order by no;

insert into mytable(no) values(1);
insert into mytable(no, name) values(2, '홍길동');
insert into mytable(no, name, hiredate) values(3, '홍길순', sysdate);

insert into mytable(no) values(99.9);
insert into mytable(no) values(999.9); -- 크기초가에러
insert into mytable(no) values('손흥민'); -- 타입에러
insert into mytable(no, name) values(4, 10000); -- 자동형변환
insert into mytable(no, name, hiredate) values(5, '이강인', 123456); -- 타입에러
insert into mytable(no, name, hiredate) values(5, '이강인', '2024.09.03');
insert into mytable(no, name, hiredate) values(5, '이강인', '20240903');

-- 6. 테이블복사하기
-- emp에서 temptable로 복사
select * from temptable;
-- create table temptable(empno number, ename......)
-- insert into ....
-- select * from temptable;
create table temptable
as select * from emp;

create table temptable1
as select empno, ename from emp;
select * from temptable1;

-- 테이블구조만 복사하기
create table temptable2
as select * from emp where 1=2;
select * from temptable2;

-- 특정조건의 자료만 복사하기
create table temptable3
as select empno, ename, deptno from emp where deptno = 10;
select * from temptable3;

create table temptable4
as select e.empno   사원번호
        , e.ename   사원이름
				, e.deptno  부서번호
				, d.dname   부서이름
     from emp e, dept d
	  where e.deptno = 10
		  and e.deptno = d.deptno;
select * from temptable4;

-- drop table if exists temptable;
drop table temptable1;
drop table temptable2;
drop table temptable3;
drop table temptable4;

/* B. 테이블의 컬럼 수정하기 */
-- 문법 : alter table 테이블명 [add|rename column|modify|drop]
create table dept6 as select * from dept2;
select * from dept6;
select * from cols where table_name = 'DEPT6';

-- 1) 컬럼추가
alter table dept6 add(location varchar2(10));

-- 2) 컬럼삭제
alter table dept6 drop(xxx);

-- 3) 컬럼명수정
alter table dept6 rename column location to loc;
select * from cols where table_name = 'DEPT6';

-- 4) 컬럼속성수정
-- 자료형이 다를 경우에는 해당열에 자료가 하나도 없을 때 수정가능
alter table dept6 modify(loc number);
select * from cols where table_name = 'DEPT6';

alter table dept6 modify(loc date); -- ORA-01439: column to be modified must be empty to change datatype

-- 5) oracle data dictionary
-- oracle 테이블정보조회
select * 
  from all_tab_columns
 where owner = 'SCOTT';

select * from user_tables;	

-- 소유한 테이블목록
select * from tab;

-- 6) default 설정
create table dept7 (
		deptno    number
	, dname     varchar2(20)
	, crtdate   date
);

create table dept8 (
		deptno    number   default 10
	, dname     varchar2(20)
	, crtdate   date     default sysdate
); 

-- defalut값 설정명령
alter table dept7 modify(deptno default 90);

select * from user_tables where table_name = 'DEPT7';	
select * from all_tab_columns where owner = 'SCOTT' and table_name = 'DEPT7';
select * from cols where table_name = 'DEPT7';

-- 컬럼속성(크기수정)
-- 데이터가 있을 경우 모든 데이터의 최대크기보다 작게 변경할 경우에는 에러가 발생
select * from dept7;
alter table dept7 modify(dname varchar2(1)); -- cannot decrease column length because some value is too big
alter table dept7 modify(dname varchar2(10));

/* C. 테이블 수정하기 */
-- 1. rename
alter table dept7 rename to dept77;
select * from all_tab_columns where owner = 'SCOTT' and table_name = 'DEPT7';
select * from all_tab_columns where owner = 'SCOTT' and table_name = 'DEPT77';

-- 2. truncate : 객체는 존재하고 데이터만 삭제
select * from dept77;
truncate table dept77;
select * from all_tab_columns where owner = 'SCOTT' and table_name = 'DEPT77';
select * from dept77;

-- 3. drop : 객체완전삭제
select * from dept6;
select * from all_tab_columns where owner = 'SCOTT' and table_name = 'DEPT6';
drop table dept6;
select * from all_tab_columns where owner = 'SCOTT' and table_name = 'DEPT6';

/* D. 일기 전용 테이블 생성 */
create table t_readonly ( 
		no    number
	, name  varchar2(10)
);

insert into t_readonly(no, name) values(1, 'AAA');
insert into t_readonly values(2, 'BBB');

select * from t_readonly;

-- 읽기전용테이블변경
alter table t_readonly read only;
insert into t_readonly values(3, 'CCC'); -- ORA-12081: update operation not allowed on table

-- 읽기전용 -> 읽기쓰기로 변경
alter table t_readonly read write;
insert into t_readonly values(3, 'CCC'); 
select * from t_readonly;

/* E. Data Dictionary 

   오라클 데이터베이스의 메모리구조와 테이블에 대한 구조정보를 가지고 있다.
	 각 객체(테이블, 뷰등)들이 사용하고 있는 공간들의 정보, 제약조건, 사용자정보 및
	 권한, 프로파일, Role, 감사(Audit)등의 정보를 제공한다.
	 
	 1. 데이터딕셔너리

	    1) 데이터베이스 자원들을 효율적으로 관리하기 위해 다양한 정보를 저장하고 있는 시스템이다.
			2) 사용자가 테이블을 생성하거나 변경하는 등의 작업을 할 때 데이터베이스 서버(엔진)에
			   의해 자동으로 갱신되는 테이블이다.
		  3) 사용자가 데이터딕셔너리의 내용을 수정하거나 삭제할 수 없다.
			4) 사용자가 데이터딕셔너리를 조회할 경우에 시스템이 직접 관리하는 테이블은 암호가 되어
			   있기 때문에 내용을 볼 수가 없다.

   2. 데이터딕셔너리 뷰 : 오라클은 데이터딕셔너리의 내용을 사용자가 이해할 수 있는 내용으로 
	    변환하여 제공한다.
			
	    1) user_xxx
			
			   a. 자신의 계정이 소유핱 객체에 대한 정보를 조회할 수 있다.
				 b. user라는 접두어가 붙은 데이터딕셔너리중에서 자신이 생성한 테이블, 인덱스, 뷰등과
				    같은 자신의 계정이 소유한 객체의 정보를 저장하는 'user_tables'가 있다.
					  -> select * from user_tables;
						
			2) all_xxx
			
			   a. 자신계정소유 또는 권한을 부여 받은 객체등에 대한 정보를 조회할 수 있다.
				 b. 타 계정의 객체는 원칙적으로는 접근이 불가능하지만, 그 객체의 소유자가 접근할 수
				    있도록 권한을 부여하면 타 계정의 객체에도 접근이 가능하다.
						
						-> select * from all_tables;
						-> select owner, table_name from all_tables where owner='SCOTT';
			
			
			3) dba_xxx
			
			   a. 데이터베이스 관리자만 접근이 가능한 객체들의 정보를 조회할 수 있다.
				 b. dba딕셔너리는 DBA권한을 가진 사용자는 모두 접근이 가능하다. 즉, DB에 있는
				    모든 객체에 대한 정보를 조회할 수 있다.
				 c. 따라서 DBA권한을 가진 sys, system계정으로 접속하면 dba_xxx등의 내용을 조회
				    할 수 있다.
*/
SELECT * FROM DICTIONARY;
SELECT * FROM DICTIONARY where table_name like 'USER%';
SELECT * FROM DICTIONARY where table_name like 'V$%';
select * from v$nls_parameters;

select * from dba_tables;








