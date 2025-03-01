/*
	1. Cursor(커서)?
	
	   오라클 서버는 SQL문을 실행하고 처리한 정보를 저장하기 위헤 'Private 
		 SQL Area'라는 메모리 작업공간을 이용한다. 이 영역에 이름을 부여하고
		 저장된 정보를 처리할 수 있게 해주는 데 이를 커서(Cursor)라고 한다.
		 
		 Cursor는 DML문과 select문에 의해 내부적으로 선언되는 묵시적커서와 
		 명시적커서가 있다. 
		 
		 PL/SQL에서 select문은 한개의 row만 검색할 수 있기 때문에 하나 이상의
		 row를 검색하기 위해서는 명시적 커서를 사용해야 한다. 
		 
		 묵시적커서의 경의 pl/sql블럭의 begin..end에 sql문이 있다면 pl/sql은
		 "SQL"이라는 이름의 커서를 자동을 생성한다.
	
	2. Cursor종류
	   
		 1) 묵시적(Implicit)커서
		 
		    묵시적커서는 오라클이나 pl/sql실행 매커니즘에 의해 처리되는 SQL문장이
				처리되는 곳에 대한 익명의 주소이다. 오라클데이터베이스에서 실행되는
				모든 SQL문장은 암시적인 커서가 생성이 되면 커서속성을 사용할 수 있다.
				암시적커서는 SQL문이 실행되는 순간에 자동으로 open과 close를 실행한다.
		 
			  암시적커서의 속성은
				
				a. SQL%rowcount : 해당 sql문에서 영향을 받는 행의 수
				b. SQL%found    : 해당 sql문에서 행의 수가 한개 이상일 때 true를 리턴
				c. SQL%notfound : 해당 sql문에서 행의 수가 없을 경우에 true를 리턴
				d. SQL%isopen   : 항상 false, 암시적커서가 열려있는지 여부를 검색
								
		 2) 명시적(Explicit)커서
  
	      프로그래머에 의해 선언되고 이름이 부여된 커서를 말한다.
				명시적커서의 진행순서는 "선언 -> open -> fetch -> close"로 진행한다.
				
				a. 문법 : cusor 커서명 is select문
				b. 커서명령
				   a) 커서열기(open)
					 
						  ... 커서의 열기는 open문을 사용
							... 커서안의 검색이 실행되면 데이터가 추출되진 않아도 에러는 
							    발생하지 않는다.
						  ... 문법 : open 커서명;
					 
					 b) 커서패치(fetch)
					 
					    ... 커서의 fetch는 현재 데이터행을 output변수에 반환 
							... 커서의 select문의 컬럼수와 output변수의 수가 동일 해야 한다.
							... 커서컬럼의 타입과 output변수의 타입은 동일해야 한다.
						  ... 커서는 한라인씩 데이터를 fetch한다.
						  ... 문법 : fetch 커서명 into 변수1...변수n	
							
					 c) 커서닫기(close)
					 
					    ... 사용이 끝난 커서는 반드시 닫아 주어야 한다.
							... 필요할 경우 커서를 다시 오픈할 수 있다.
							... 커서를 닫은 경우에는 fetch할 수 없다.
							... 문법: close 커서명;
	
*/
-- 1. 묵시적커서 
create or replace procedure pro_18(p_empno in emp.empno%type)
is
	v_sal 				emp.sal%type;
	v_update_row 	number;
begin
	select sal into v_sal
	  from emp
	 where empno = p_empno;
	 
	 -- 1. 검색자료가 있는 경우
	if sql%found 
		 then dbms_output.put_line('자료가 있습니다!! 사원(' || p_empno || ')급여는 ' || v_sal || '원 입니다!');
	end if;
	 
	update emp
	   set sal = 0
	 where empno between p_empno and p_empno;
	
	-- 2. 수정된 데이터의 건수를 변수에 저장
	-- commit 전에 sql커서의 속성을 읽을 수가 있고 commit후에는 sql커서의 정보가 클리어된다.
	v_update_row := sql%rowcount;
	dbms_output.put_line(v_update_row || ' 건의 사원급여가 수정되었습니다!');
	
	commit;
			 
exception
	when no_data_found then dbms_output.put_line('사원(' || p_empno || ') 찾지 못했습니다!');
end pro_18;

call pro_18(7369);

-- 2. 명시적커서 
-- 1) 한 건 : 특정부서의 평균급여와 사원수를 출력
create or replace procedure pro_19(p_deptno in emp.deptno%type)
is
	-- 1) 커서선언
	cursor dept_avg is
	select dpt.dname
	     , count(emp.empno)
			 , round(avg(emp.sal), 2)
	  from emp  emp
		   , dept dpt
	 where emp.deptno = dpt.deptno
	 group by dpt.dname;
	 
 v_dname 		dept.dname%type;
 v_emp_cnt	number;
 v_sal_avg	number;
 
begin
	-- 2) 커서 open
	open dept_avg;
	
	-- 3) 커서 fetch(읽기)	
	fetch dept_avg into v_dname, v_emp_cnt, v_sal_avg;
	
	dbms_output.put_line('부서이름 = ' || v_dname);
	dbms_output.put_line('부서인원 = ' || v_emp_cnt);
	dbms_output.put_line('부서평균 = ' || v_sal_avg);
	
	-- 4) 커서 close
	close dept_avg;

exception 
	when others then dbms_output.put_line(sqlerrm || ' 에러가 발생했습니다!!');
end pro_19;

call pro_19(10);

-- 2) 여러건 : for문안에 커서를 사용하기
-- for문을 사용하면 커서의 open, fetch, close가 자동으로 진행되기 때문에
-- 별도로 기술할 필요가 없고 레코드이름도 자동으로 선언되기 때문에 별도로
-- 선언할 필요가 없다.
-- for 레코드이름 in 커서명 loop
--     실행문
-- end loop;
-- 부서별 인원수와 급여합계
create or replace procedure pro_20
is
	cursor dpet_sum is
	select dpt.dname        부서이름
	     , count(emp.empno) 부서인원
			 , sum(emp.sal)     부서총급여      
	  from emp  emp
		   , dept dpt
	 where emp.deptno = dpt.deptno
	 group by dpt.dname;	
begin

	for emp_list in dpet_sum loop
		dbms_output.put_line('부서이름 = ' || emp_list.부서이름   || ', ' ||
	                       '부서인원 = ' || emp_list.부서인원 || ', ' ||
		                     '부서총급여 = ' || emp_list.부서총급여);	
	end loop;
	
exception 
	when others then dbms_output.put_line(sqlerrm || ' 에러가 발생했습니다!!');
end pro_20;

call pro_20();

-- 3. 명시적커서 - 커서의 속성
-- 커서명%isopen, 커서명%found, 커서명%notfound, 커서명%rowcount
create or replace procedure pro_21
is
	cursor emp_list is select empno, ename, sal from emp;
	
	v_empno  emp.empno%type;
	v_ename  emp.ename%type;
	v_sal    emp.sal%type;
begin

	open emp_list;
	
	loop 
		fetch emp_list into v_empno, v_ename, v_sal;
		exit when emp_list%notfound;
	end loop;
	
	dbms_output.put_line('전체 처리건수 = ' || emp_list%rowcount);
	
	close emp_list;

exception 
	when others then dbms_output.put_line(sqlerrm || ' 에러가 발생했습니다!!');
end pro_21;

call pro_21();

-- 4. 명시적커서 - 파라미터가 있는 커서
-- 커서가 오픈되고 질의가 실행이 되면 매개변수값을 커서에 전달할 수가 있다.
-- 다른 매개변수를 갖는 커서를 사용하고자 할 경우에는 별도의 커서를 선언해야 한다.
-- 프로시저에서 부서번호를 전달받아서 전달받은 부서번호를 커서에 전달
create or replace procedure pro_22(p_deptno emp.deptno%type)
is
	cursor eml_list(v_deptno emp.deptno%type) is
	select ename
	  from emp
	 where deptno = v_deptno;
	 
 v_ename emp.ename%type;
begin

	dbms_output.put_line('--- [' || p_deptno || ' 부서의 사원명부 ] ---');
	
	for e_list in eml_list(p_deptno) loop
		dbms_output.put_line('사원이름 = ' || e_list.ename);
	end loop;

exception 
	when others then dbms_output.put_line(sqlerrm || ' 에러가 발생했습니다!!');
end pro_22;

call pro_22(20);

-- 5. 커서를 이용한 참조된 데이터의 행을 수정하거나 삭제하기
-- where current of 커서명
-- 1) 특정조건을 사용하지 않고 현재 참조된 자료를 수정, 삭제할 수 있다.
-- 2) fetch문으로 최근 사용된 행을 참조하기 위해 사용
-- 3) delete, update문을 사용할 수 있게 한다.
-- 4) where current of를 사용할 경우 참조하는 커서가 있어야 하고
--    for update절이 커서선언절에 선언되어 있어야 한다. 만약에 없으면 에러발생
create or replace procedure pro_23
is
	cursor emp_list is
	select empno, ename
	  from emp
	 where empno = 7934
	   for update;  -- for update는 커서가 오픈될 때 행삭제 or update 수행전에 해당 row를 lock을 건다.
begin

	for e_list in emp_list loop
		
		update emp
		   set job = '사원'
		 where current of emp_list;
	  dbms_output.put_line('성공적으로 자료가 수정이 되었습니다!!');
	end loop;

exception 
	when others then dbms_output.put_line(sqlerrm || ' 에러가 발생했습니다!!');
end pro_23;

call pro_23();

	select empno, ename, job
	  from emp
	 where empno = 7934

-- 실습) 성적관리를 위한 프로시저만들기(with cursor)
-- 성적테이블
create table sungjuk (
			hakbun   varchar2(10)
		, name     varchar2(10)
		, kor      number(3)
		, eng      number(3)
		, mat      number(3)
);
insert into sungjuk values(1001, '홍길동',90,80,70);
insert into sungjuk values(1002, '홍길순',100,100,100);
insert into sungjuk values(1003, '홍길자',90,80,70);
insert into sungjuk values(1004, '홍길녀',70,60,50);
insert into sungjuk values(1005, '홍길영',70,70,70);
select * from sungjuk;

-- 성적결과테이블
create table sungresult(
			hakbun   varchar2(10)
		, name     varchar2(10)
		, kor      number(3)
		, eng      number(3)
		, mat      number(3)		
		, tot      number(3)
		, avg      number(4,1)
		, hak      varchar2(2)	-- A+, ~ F
	  , pass     varchar2(10)
	  , rank     number	
);
haknun
-- 1. sungjuk파일을    커서 c_sungjuk    로 정의
-- 2. sungresult파일을 커서 c_sungresult
-- 3. >=95 A+, >=90 A0, >=85 B+ 5점단위... >= 60 D0 else 'F
-- 4. 성적의 평균이 >= 70 pass= 'pass' else pass='fail';
-- 5. insert into sungresult() value()
-- 6. update sungresult rank = 순위를 update
create or replace procedure pro_24
is
	cursor c_sungjuk is 
	select * from sungjuk;
	
	cursor c_sungresult is 
	select hakbun, tot from sungresult;	
	
	v_hakbun   	sungjuk.hakbun%type;
	v_name     	sungjuk.name%type;
	v_kor     	sungjuk.kor%type;
	v_eng     	sungjuk.eng%type;
	v_mat     	sungjuk.mat%type;

	v_tot     	sungresult.tot%type;
	v_avg     	sungresult.avg%type;
	v_hak     	sungresult.hak%type;
	v_pass     	sungresult.pass%type;
	v_rank     	sungresult.rank%type;

begin
	open c_sungjuk;
	
	loop
		fetch c_sungjuk into 	v_hakbun, v_name, v_kor, v_eng, v_mat;
		exit when c_sungjuk%notfound;
		
		v_tot := v_kor + v_eng +v_mat;
		v_avg := v_tot / 3;
		
		if    v_avg >= 95 then v_hak := 'A+';
		elsif v_avg >= 90 then v_hak := 'A0';
		elsif v_avg >= 85 then v_hak := 'B+';
		elsif v_avg >= 80 then v_hak := 'B0';
		elsif v_avg >= 75 then v_hak := 'C+';
		elsif v_avg >= 70 then v_hak := 'C0';
		elsif v_avg >= 60 then v_hak := 'D0';
		else v_hak := 'F';
		end if;
		
		if v_avg >= 70
			 then v_pass := '합격';
			 else v_pass := '불합격';
		end if;	 
		
		insert into sungresult(hakbun, name, kor, eng, mat, tot, avg, hak, pass) 
				 values(v_hakbun, v_name, v_kor, v_eng, v_mat, v_tot, v_avg, v_hak, v_pass);
		
		commit;
			 
	end loop;
	
	close c_sungjuk;
	
	-- rank 구하기
	open c_sungresult;
	
	loop
		fetch c_sungresult into v_hakbun, v_tot;
		exit when c_sungresult%notfound;

		select count(*) + 1 
			into v_rank
			from sungresult
		 where tot > v_tot;
		 
		 update sungresult
		    set rank = v_rank
			where hakbun = v_hakbun;
			
		commit;
	 
	end loop;	 
	
	close c_sungresult;

end pro_24;

call pro_24();

select * from sungresult;