/* Sequence

   시퀀스란? 자동으로 순차적으로 증가하는 일련번호를 반환하는 오라클데이터베이스의 객체
	 이다. 보톤 PK값에 중복을 방지하기 위해 종종 사용한다.
	 예를 게시판에 글이 하나가 추가될 때 마다 글번호(PK)가 생겨야 된다면 시퀀스를 사용하면
	 보다 쉽게, 편리하게 PK를 관리할 수가 있다.
	 
	 1. 유일(unique)한 값을 생성해 주는 오라클 객체이다.
	 2. 시퀀스를 생성하면 기본카와 같이 순차적으로 증가하는 컬럼을 자동으로 생성가능하다.
	 3. 보통 PK값을 생성하기 위해 사용한다.
	 4. 시퀀스는 테이블과 독립적으로 저장되고 생성된다.
	 
	 <sequence 문법>
	 
		 1. create sequence 시퀀스이름
					[start with n]
					[increment by n]
					[maxvalue n | nomaxvalue]
					[minvalue n | nominvalue]
					[cycle | nocycle]
					[cache : nocache]
					
					-- start with : 시퀀스의 시작값을 정의 n을 1000으로 지정하면 1000부터 시작
					-- increment by : 자동증가값을 정의, n이 10이면 1010, 1020...형태로 증가
					-- maxvalue : 시퀀스의 최대값
					-- minvalue : 시퀀스의 최소값
					-- cycle : 최대값이 도달할 경우 처음부터(start with)다시 시작할지의 여부
					-- cache : 원하는 숫자만큼 미리 생성해서 메모리에 저장				
		 
		 2. alter sequence 시퀀스이름
					[increment by n]
					[maxvalue n | nomaxvalue]
					[minvalue n | nominvalue]
					[cycle | nocycle]
					[cache : nocache]	 
		 
		 
		 3. drop sequence 시퀀스이름
	 
   <시퀀스를 조회>
	 
	 select * from user_sequences;

*/

-- 1. sequence 생성
create sequence jno_seq1;
select * from user_sequences;

-- 2. 시퀀스삭제
drop sequence jno_seq;

-- 3. 시퀀스생성(옵션)
create sequence jno_seq2
start with 100
increment by 1
maxvalue 110
minvalue 90
cycle
cache 2;

select * from user_sequences;

-- 4. sequence 사용
create table s_order(
		ord_no 			number(4)
	, ord_name 		varchar2(10)
	, p_name   		varchar2(20)
	, p_qty 			number
);

-- 1) 시퀀스접근
-- 현재번호 : currval
-- 다음번호 : nextval
select * from user_sequences;
select jno_seq1.nextval from dual;
select jno_seq1.currval from dual;

insert into s_order values(jno_seq2.nextval, '홍길동', '아이폰', 10);
insert into s_order values(jno_seq2.nextval, '손흥민', '겔럭시', 20);
select jno_seq2.currval from dual;
select * from s_order;

-- 2) min/max sequence
-- begin
-- 	for i in 1..9 loop
-- 		insert into s_order values(jno_seq2.nextval, '이강인', '샤오미', 20);
-- 	end loop;
-- end;

select jno_seq2.currval from dual;
select * from s_order;

-- 3) 별도의 시퀀스 적용하기
create sequence jno_seq3;
select * from user_sequences;

insert into s_order values(jno_seq3.nextval, '김민재', '축구공', 20);
select * from s_order;

-- 4) 감소시퀀스생성하기
drop sequence jno_seq_rev;

create sequence jno_seq_rev
start with 10
increment by -2
minvalue 0
maxvalue 20;

select * from user_sequences;

drop table s_rev1;

create table s_rev1(no number);
insert into s_rev1 values(jno_seq_rev.nextval);
select * from s_rev1;

-- 4) 시퀀스를 table의 PK로 사용하기
create table test_table(no  number primary key);

insert into test_table values(jno_seq4.nextval);
insert into test_table values(jno_seq3.nextval);

select jno_seq3.currval, jno_seq4.currval from dual;

select * from test_table;