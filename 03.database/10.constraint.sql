/* Constraint (제약조건)

	 테이블에 올바른 데이터만 입력이 될 수 있도록 하고 잘못된 데이터는 사전에
	 막을 수 있게 하기 위해 테이블에 제약을 걸어 두는 규칙을 말한다.
	 
	 제약조건의 종류
	 1. not null(NN) : 테이블에 null값이 입력되지 못하도록 하는 제약조건
	 2. unique(UK)   : 이 조건이 설정된 컬럼에는 중복된 값이 허용하지 않는 제약조건
	 3. primary key(PK) : 이 조건이 설정된 컬럼에는 not null이면선 unique인 특징을 갖는다.
	     pk는 테이블당 한개의 pk만 설정할 수 있는데 여러개의 컬럼으로 1개의 pk로 정의가능
   4. forign key(FK) : 다른 테이블의 컬럼을 참조하도록 하는 제약조건
	 5. check(CK) : 설정된 값만 입력이 되도록 하는 제약 조건 
*/

/* A. 제약조건 설정하기 */
-- 1. 테이블생성시 지정하기
-- new_emp1 테이블생성, PK, NN, UK, CK, FK 지정하기
-- constraint [제약명] 제약종류
-- ... FK : constraint [제약명]	references 테이블명(컬럼1,...)
-- ... 제약명을 생략하면 DB서버에서 자동 부여
-- 1) Full
create table new_emp1(
		no 				number(4)     constraint emp1_no_pk  		primary key
	, name  		varchar2(20)  constraint emp1_name_nn  	not null
	, jumin 		varchar2(13)  constraint emp1_jumin_nn	not null
	                          constraint emp1_jumin_uk	unique
	, loc_code 	number(1)     constraint emp1_loc_ck		check(loc_code < 5)
	, deptno    varchar2(6)   constraint emp1_deptno_fk	references dept2(dcode)
);

-- 2) 약식
create table new_emp2(
		no 				number(4)     primary key
	, name  		varchar2(20)  not null
	, jumin 		varchar2(13)  not null unique
	, loc_code 	number(1)     check(loc_code < 5)
	, deptno    varchar2(6)   references dept2(dcode)
);

-- 3) tool을 이용
create table new_emp3(
		no 				number(4)    
	, name  		varchar2(20) 
	, jumin 		varchar2(13) 
	, loc_code 	number(1)     
	, deptno    varchar2(6)  
);

-- data dictionary
select * from all_constraints
 where owner = 'SCOTT'
   and table_name like 'NEW_EMP%';
	 
-- 테이블에 제약사항테스트하기
insert into new_emp1 values(1, '홍길동', '2409041234567', 1, 1000);
insert into new_emp1 values(2, '홍길순', '2409042234567', 4, 1000);
insert into new_emp1 values(3, '홍길자', '2409043234567', 3, 1001);

select * from new_emp1;
select * from dept2;

insert into new_emp1 values(1, 'XXXX', '2410041234567', 1, 1000); -- (SCOTT.EMP1_NO_PK) violated
insert into new_emp1 values(4, '홍길동', '2409041234567', 1, 1000); -- (SCOTT.EMP1_JUMIN_UK) violated
insert into new_emp1 values(4, '손흥민', '2410041234567', 5, 1000); -- (SCOTT.EMP1_LOC_CK) violated
insert into new_emp1 values(4, '손흥민', '2410041234567', 4, 9999); -- (SCOTT.EMP1_DEPTNO_FK) violated - parent key not found

--- 2. 테이블생성후에 제약사항 추가/수정 하기
-- add constraint 제약명 제약사항
create table new_emp5(
		no 				number(4)    
	, name  		varchar2(20) 
	, jumin 		varchar2(13) 
	, loc_code 	number(1)     
	, deptno    varchar2(6)  
);
select * from new_emp5;

-- 1) new_emp5.name 에 UK제약조건 추가
alter table new_emp5 add constraint emp5_name_uk unique(name);

-- 2) new_emp5.loc_code CK(loc_code < 5)제약사항 추가
alter table new_emp5 add constraint emp5_loc_ck check(loc_code < 5);

-- 2) new_emp5.loc_code NN(loc_code < 1000)제약사항 추가
-- 기존에 CK가 추가되어 있는 상태에서 NN추가
alter table new_emp5 modify (loc_code constraint emp5_loc_nn not null);

select * from all_constraints
 where owner = 'SCOTT'
   and table_name = 'NEW_EMP5';

-- 3. PK와 FK 
create table test_1(
		no			number
	, name 		varchar2(6)
	, deptno 	number
);

create table test_2(
		no			number  
	, name 		varchar2(6)
);

select * from test_1;
select * from test_2;

-- PK, FK
-- PK(test_2(no)) 아니면 참조테이블에서 FK로 사용할 수 없다.
-- FK는 참조테이블의 컬럼이 PK or UK인 컬럼만 적용할 수 있다.
alter table test_1 
  add constraint test1_deptno_fk foreign key(deptno) references test_2(no); -- 에러

-- 상기에러를 수정하려면 test_2.no를 PK or UK로 지정해야 한다.
alter table test_2
  add constraint test2_no_uk unique(no);
alter table test_1 
  add constraint test1_deptno_fk foreign key(deptno) references test_2(no);	

select * from all_constraints
 where owner = 'SCOTT'
   and table_name like 'TEST%';

-- 제약사항 테스트
insert into test_2 values(10, '인사');
insert into test_2 values(20, '영업');
insert into test_2 values(30, '회계');

insert into test_1 values(1, '흥민', 10);
insert into test_1 values(2, '강인', 20);
insert into test_1 values(3, '민제', 30);

select * from test_1;
select * from test_2;

-- 1. FK가 없을 경우 insert할 경우 - parent key not found
insert into test_1 values(4, '길동', 40); -- (SCOTT.TEST1_DEPTNO_FK) violated - parent key not found

-- 2. FK로 지정된 부모테이블의 자료를 삭제할 경우 - child record found
delete from test_2 where no = 20; -- (SCOTT.TEST1_DEPTNO_FK) violated - child record found

-- 1) 부모자료를 삭제하려면 자식자료가 없는 상태이어야 한다.
delete from test_1 where deptno = 20;
delete from test_2 where no = 20; 
select * from test_1;
select * from test_2;

-- 3. 부모가 삭제가 될 떄 자식도 삭제가 가능하게 할 경우
-- FK의 제약사항를 정의할 떄 'on delete cascade'라는 옵션을 지정
-- 1) 자식이 완전삭제
--    ... on delete cascade
-- 2) 자식이 삭제되지는 않지만 FK로 지정된 컬럼을 NULL로 수정
--    ... on delete set null
select * from all_constraints
 where owner = 'SCOTT'
   and table_name like 'TEST%';

-- on delete cascade
alter table test_1 drop constraint TEST1_DEPTNO_FK;
alter table test_1 
  add constraint test1_deptno_fk foreign key(deptno) references test_2(no) on delete cascade;	

delete from test_2 where no = 30; 
select * from test_1;
select * from test_2;

-- on delete set null
alter table test_1 drop constraint TEST1_DEPTNO_FK;
alter table test_1 
  add constraint test1_deptno_fk foreign key(deptno) references test_2(no) on delete set null;	
	
delete from test_2 where no = 10; 
select * from test_1;
select * from test_2;

/* B. 제약사항 관리하기 */
-- 1. 제약조건 disable 하기
-- disable옵션은 novalidate와 validate 2가지가 있다.
-- novalidate는 해당 제약조건을 무효화 시킨다는 의미

-- 2. 제약조건 삭제하기
-- alter table 테이블명 drop constraint 제약조건명 cascade;

create table t_novalidate(
		no 			number         primary key
	, name 		varchar2(10)   not null
);

insert into t_novalidate values(1, 'AAA');
insert into t_novalidate values(2, 'BBB');
insert into t_novalidate values(3, 'CCC');
select * from t_novalidate;

create table t_enable(
		no 			number         constraint te_no_pk   primary key
	, name 		varchar2(10)   constraint te_name_nn not null
);

select * from all_constraints
 where owner = 'SCOTT'
   and table_name like 'T_%';
	 
-- 1. 제약조건무효화하기
-- SYS_C007098, SYS_C007099
insert into T_NOVALIDATE values(1, 'DDD'); -- (SCOTT.SYS_C007099) violated
	 
alter table T_NOVALIDATE disable novalidate constraint SYS_C007099;
insert into T_NOVALIDATE values(1, 'DDD'); 
select * from t_novalidate;

-- 2. 제약조건유효화하기
create table t_validate(
		no 			number         constraint tv_no_pk 		primary key
	, name 		varchar2(10)   constraint tv_name_nn 	not null
);

insert into t_validate values(1, 'AAA');
insert into t_validate values(2, 'BBB');
insert into t_validate values(3, 'CCC');
select * from t_validate;

-- a. 무효화
insert into t_validate values(4, null); -- cannot insert NULL into ("SCOTT"."T_VALIDATE"."NAME")
alter table t_validate disable novalidate constraint tv_name_nn;
insert into t_validate values(4, null);

-- b. 유효화
alter table t_validate disable validate constraint tv_name_nn; --  check constraint violated
-- 에러가 발생하는 이유는 기존데이터에 null값을 가지고 있는 자료가 있기 때문이다.
-- 유효화하려면 NULL값이 있는 자료를 전부 삭제하거나 값을 설정한 후에 유효화시킬 수 있다.
alter table t_validate disable validate constraint tv_name_nn; 