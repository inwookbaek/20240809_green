/*
	A. PL/SQL?
	
	오라클의 Procedural Language extension to SQL의 약자
	SQL문장에서 변수정의, 조건처리(if), 반복처리(loop, for, while)등을 지원하며
	절차형언어(Procedural Language)라고 한다.
	
	declare문을 이용하여 정의되고 선언문은 사용자가 정의한다.
	PL/SQL문은 블럭구조로 되어 있고 PL/SQL자신이 자체 compile엔진을 가지고 있다.
	
	2. PL/SQL의 장점
	
		1) block구조로 다수의 SQL문을 한번에 oracle db server로 보내서 처리하기 
		   때문에 처리속도가 빠르다.
	  2) PL/SQL의 모든 요소는 하나 또는 두개 이상의 블럭으로 구성하여 모듈화가
		   가능하다.
	  3) 보다 강력한 프로그램을 작성하기 위해 큰 블럭안에 소블럭을 위치 시킬 수
		   있다.
		4) variable(변수), constant(상수), cursor(커서), exception(예외처리)등을
       정의할 수 있고, SQL문장과 Procedural문장에서 사용할 수 있다.
		5) 변수선언은 단순변수선언과 레코드타입변수로 선언할 수 있다.
    6) 변수선언은 테이블의 데이터구조와 컬럼명에 준하여 동적으로 변수를 선언
    5) exception처리를 이용하여 oracle server error처리를 할 수 있다.
    6) 사용자가 에러를 정의할 수 있고 exception처리를 할 수 있다.

	3. PL/SQL의 block구조
	
	   1) PL/SQL은 프로그램을 논리적인 블럭으로 나누는 구조화된 언어
		 2) 선언부(선택), 실행부(필수), 예외(선택)으로 구성되어 있고 
		    begin...end키워드는 반드시 기술해야 한다.
		 3) 문법
		    create or replace procedure 프로시저명() as [is]
				declare
				   - 선택부분
					 - 변수, 상수, 커서, 사용자정의예외처리
			  begin
				   - 필수부분
					 - PL/SQL문장을 기술(select, if, for, while...)
			  exception
				   - 선택부분
					 - 예외처리로직을 작성
				end;
				
	4. PL/SQL의 block의 종류
	  
		 1) anonymous block(익명블럭) : 이름이 없는 블럭으로서 보통 일회성으로 
		    실행되는 block이다.
		 2) procedure : 매개변수를 받을 수 있고 재사용이 가능하며 보통은 연속실행
		    또는 구현이 복잡한 트랜잭션을 수행하는 PL/SQL블럭으로 데이터베이스안에
				저장된다. 이런의미로 stored procedure이라고도 한다.
		 3) function : procedure와 유사하지만 다른 점은 계산결과를 호출한 곳으로
		    반환해 주는 값이 있다는 점이다. 다만 in 파라미터만 사용할 수 있고, 
				반드시 반환될 값의 데이터 타입을 return문에 선언해야 한다. 
				또한, pl/sql블럭내에서 return문을 사용하여 반드시 값을 반환해야 한다.
		 4) package : 패키지는 오라클 데이터베이스에 저장되어 있는 procedure와
		    function의 집합이다. 패키지는 선어부와 본문이 두부분 나누어 관리한다.
		 5) trigger : insert, update, delete문이 특정 table에 수행될 때마다 자동
		    적으로 수행되는 프로시저이다. 트리거는 테이블과 별도로 database에 저
				장 된다. 트리거는 테이블에 대해서만 정의할 수 있다.		 
*/

-- 1. procedure/function 생성 및 실행
drop procedure pro_01;

-- 1) 기본문법
create or replace procedure pro_01 is
begin
	dbms_output.put_line('Hello World!!!');
end;

call pro_01();

-- 2) 예외처리
create or replace procedure pro_02 is
	counter integer;  
begin
	counter := 10;
	counter := counter / 0;
	dbms_output.put_line('현재 카운터는 = ' || counter);
exception when others then
	dbms_output.put_line('나누기 연산시에는 0으로 나눌 수가 없습니다!!');
end;

call pro_02();

-- 3) if문
create or replace procedure pro_03 is
	-- 변수선언 : 선언된 변수는 begin블럭안에서 사용가능 선언하지 않고 사용할 경우 에러
	a boolean := true;
begin
  a := false;
	if a
		then dbms_output.put_line('a의 값은 true입니다!!');
		else dbms_output.put_line('a의 값은 false입니다!!');
	end if;
end;

call pro_03();

/* 
	B. PL/SQL 데이터타입
	
	1. 스칼라 : scalar 데이터타입은 단인 data type과 데이터변수 %type이 있다.
	   
		 일반데이터타입
		 
		 1) 선언방법 : 변수명 [constant] 데이터타입 [not null] [:= 상수값 or 표현식] 
		    예 : counter constant integer not null := 10 + 10;
		 2) 변수명(variable or identifier)의 이름은 SQL명명규칙을 따른다.
		 3) identifier를 상수로 지정하고 싶은 경우에는 constant라는 키워드로 명시적으로 선언하고
			  상수는 반드시 초기값을 할당해야 한다.
		 4) not null로 정의되어 있다면 초기값을 반드시 지정, 정의되어 있지 않을 경우는 생략할 수 있다.
		 5) 초기값은 할당(대입)연산자(:=)를 사용하여 지정
		 6) 일반적으로 한 줄에 한 개의 identifier를 정의한다.
		 7) 일반변수의 선언방법
		 
		    v_pi  	constant number(7,6) := 3.141592;
				v_price constant number(4,2) := 12.34;
				v_name  varchar2(10);
				v_flag  boolean not null := true;
	 
	2. %type 
	
	   1) DB테이블의 컬럼의 데이터타입을 모를 경우에도 사용할 수가 있고 테이블컬럼의 데이터타입이 변경
		    될 경우에도 수정할 필요없이 사용할 수가 있다.
		 2) 이미 선언된 다른 변수나 테이블의 컬럼을 이용히여 선언(참조)할 수가 있다.
		 3) DB테이블과 컬럼 그리고 이미 선언한 변수명이 %type앞에 올 수 있다.
		 4) %type속성을 이용하는 장점은
		    ... table의 column 속성을 정확히 알지 못할 경우에도 사용할 수가 있다.
				... table의 column 속성이 변경이 되어도 pl/sql을 수정할 필요가 없다.
		 5) 선언방법
		    
				v_empno 	emp.empno%type;
				
	
	3. %rowtype 
	
		 하나 이상의 데이터값을 갖는 데이터형으로 배열과 비슷한 역할을 하며 재사용이 가능하다.
		 %rowtype 데이터형과 pl/sql 테이블과 레코드는 복합데이터형에 속한다.
		 
		 1) 테이블이나 뷰 내부컬럼의 데이터형, 크기, 속성등을 그대로 사용할 수 있다.
		 2) %rowtype앞에는 테이블(뷰)명이 온다.
		 3) 지정된 테이블의 구조와 동일한 구조를 갖는 변수를 선언할 수 있다.
		 4) 데이터베이스 컬럼들의 갯수나 datatype을 알지 못할 경우에 사용하면 편리한다.
		 5) 테이블의 컬럼 데이터타입이 변경되어도 pl/sql을 변경할 필요가 없다.
		 6) 선언방법
		 
			  v_emp      emp%rowtype; 
				  --> v_emp.ename;
		 
	4. table타입
	
	   pl/sql에서 table타입은 db에서의 table과 성격이 다르다. pl/sql에서 table은 1차원 배열이다.
		 table은 크기에 제한이 없으며 row의 수는 데이터가 추가 되면 자동으로 증가된다.
		 binary_integer타입의 인덱스번호로 순서가 정해 진다. 하나의 테이블에는 한개의 컬럼데이터를
		 저장할 수 있다.
		 
		 선언방법
		 
		 1) 데이터타입(테이블)선언
		    
				type 테이블타입명 is table of varchar2(20) index by binary_integer; -> 사용자가 데이터타입을 새로 만든 것
		 
		 2) 변수선언
		    
				v_emp_name_tab   테이블타입명;  --> 사용자가 만든 새로운 데이터타입(테이블타입)으로 변수를 선언
				즉, 변수선언의 의미는 테이블타입으로 변수를 선언한다는 의미이다.
		 
		 3) %rowtpye으로 table타입을 선언
		    
			  type 테이블타입명 is table of emp%rowtype index by binary_integer;
				
				v_emp_tab 테이블타입명	
				
	5. record타입 
	
	   1) record 데이터타입은 여러개의 데이터타입을 갖는 변수들의 집합
		 2) 스칼라, 테이블 or 레코드타입중 하나 이상의 요소로 구성
		 3) 논리적 단위로 컬럼들의 집합을 처리할 수 있도록 한다.
		 4) pl/sql table과는 다르게 개별 필드의 이름을 부여, 선언시에 초기화가 가능하다.
		 5) 선언방법
		 
		    type 레코드타입명 is record(
					col1   데이터타입 [not null {:= 값 | 표현식 }], 
					...
					coln   데이터타입 [not null {:= 값 | 표현식 }],
				);
*/

-- 1. %type
create or replace procedure pro_04 is
	v_empno   emp.empno%type;
	v_ename   emp.ename%type;
	v_sal   	emp.sal%type;
begin

	select empno, ename, sal
	  into v_empno, v_ename, v_sal
	  from emp where empno = 7369;
		
  dbms_output.put_line('사원번호는 = ' || v_empno || '입니다.');
  dbms_output.put_line('사원이름은 = ' || v_ename || '입니다.');
  dbms_output.put_line('사원급여는 = ' || v_sal || '입니다.');

end;

call pro_04();

-- 2. %rowtype
create or replace procedure pro_05 is
	v_emp  emp%rowtype;
begin

	-- row 전체를 v_emp 저장
	select * into v_emp
	  from emp where empno = 7369;
		
  dbms_output.put_line('사원번호는 = ' || v_emp.empno    || '입니다.');
  dbms_output.put_line('사원이름은 = ' || v_emp.ename    || '입니다.');
  dbms_output.put_line('사원급여는 = ' || v_emp.sal      || '입니다.');
  dbms_output.put_line('사원입사일 = ' || v_emp.hiredate || '입니다.');
	
	-- 정의된 컬럼의 정보를 v_emp.컬럼들에 저장
	select empno, ename, sal
	  into v_emp.empno, v_emp.ename, v_emp.sal
	  from emp where ename = 'KING';	
		
  dbms_output.put_line('사원번호는 = ' || v_emp.empno    || '입니다.');
  dbms_output.put_line('사원이름은 = ' || v_emp.ename    || '입니다.');
  dbms_output.put_line('사원급여는 = ' || v_emp.sal      || '입니다.');
	dbms_output.put_line('사원입사일 = ' || v_emp.hiredate || '입니다.');		

end;

call pro_05();

-- 3. record 타입
-- 개발자가 임의로 선언한 procedure or function에서만 정의된 데이터타입이다.
create or replace procedure pro_06 is

	type emp_record is record (
		  v_empno   number
		, v_ename		varchar2(30)
		, v_sal			number);
		
	v_emp_rec emp_record; -- 개발자가 선언한 데이터타입으로 변수를 선언
begin
	select empno, ename, sal 
-- 	  into v_emp_rec.v_empno, v_emp_rec.v_ename, v_emp_rec.v_sal
    into v_emp_rec
	  from emp
	 where empno = 7369;
	
  dbms_output.put_line('사원번호는 = ' || v_emp_rec.v_empno    || '입니다.');
  dbms_output.put_line('사원이름은 = ' || v_emp_rec.v_ename    || '입니다.');
  dbms_output.put_line('사원급여는 = ' || v_emp_rec.v_sal      || '입니다.');
		
end;

call pro_06();


/* 
  1. 오라클 전형적인 데이터타입
	   number, varchar2, date, boolean,....
		 binary_xxxx.... : 검색에 특화된 데이터타입
		 
	2. PL/SQL에서의 데이터타입 
		
	   a. 오라클의 데이터타입
		 
		    1) 한 개의 열의 데이터타입 : emp.empno%type
	      2) 열 전체의 데이터 타입   : emp%rowtype
		 
		 b. 개발자가 PL/SQL내에서 사용하기위한 임의의 데이터 타입
	   
		    1) 한 행의 데이터입 즉, 레코드 타입 : record(v1 d1, v2 d2,....)
		    2) 테이블(표)형태의 데이터 타입     : table of scott.emp.ename%type by binary_integer;
*/

-- 4. table 타입 (%type)
-- 1) 한 건(즉, 단일열)
create or replace procedure pro_07 is
	-- 1. table 데이터타입
	type tbl_emp_name is table of hr.employees.first_name%type index by binary_integer;
	
	-- 2. 변수선언
	t_name 	varchar2(20);
	v_name  tbl_emp_name;
	
begin
	select first_name || '.' || last_name 
	  into t_name
	  from hr.employees 
	 where employee_id = 180;
	 
	 dbms_output.put_line('사원이름은 = ' || t_name || ' 입니다.');
	 
	 v_name(0) := t_name;
	 dbms_output.put_line('사원이름은 = ' || v_name(0) || ' 입니다.');
	 
	 v_name(1) := '홍길동';
	 dbms_output.put_line('사원이름은 = ' || v_name(1) || ' 입니다.');
end;

call pro_07();

-- 2) 여러 건(즉, 다중행, 단일열)
create or replace procedure pro_08 is
	type e_table_type is table of hr.employees.first_name%type index by binary_integer;
	
	tab_type    e_table_type;
	i           binary_integer := 0;	
begin

	for emp in (select first_name || '.' || last_name as name from hr.employees order by 1) loop		
		tab_type(i) := emp.name;
		i := i + 1;
	end loop;
	
	dbms_output.put_line('총 사원수는 ' || i || '명 입니다.');
	
	dbms_output.put_line('-----------------------------------------------');
	for j in 1..i loop
	  dbms_output.put_line('사원이름은 = ' || tab_type(j-1) || ' 입니다.');
	end loop;
	
end;

call pro_08();

-- 3) 여러 건(즉, 다중행, 다중열)
-- 사원명과 직급을 출력
-- table타입을 2개 선언(JavaScript배열 2개선언과 동일의미)
create or replace procedure pro_09 is
	type name_table_type is table of emp.ename%type index by binary_integer;
	type job_table_type  is table of emp.job%type index by binary_integer;
	
	i 							binary_integer := 0;
	v_ename_table 	name_table_type;
	v_job_table    	job_table_type;
begin

	for emp in (select ename, job from emp order by 1, 2) loop
		v_ename_table(i) := emp.ename;
		v_job_table(i)   := emp.job;
		i := i + 1;
	end loop;
	
	dbms_output.put_line('-----------------------------------------------');
	dbms_output.put_line('사원이름' || chr(9) || '사원직급');
	dbms_output.put_line('-----------------------------------------------');
	
	for j in 1..i loop
		-- dbms_output.put_line(v_ename_table(j-1) || chr(9) || v_job_table(j-1));
		dbms_output.put_line(rpad(v_ename_table(j-1), 12) || chr(9) || rpad(v_job_table(j-1),9));
	end loop;
	
end;

call pro_09();

-- 5. table 타입 (%rowtype)
create or replace procedure pro_10 is
	type dept_table_type is table of dept%rowtype index by binary_integer;
	
	v_dept_table  dept_table_type;
	i             binary_integer := 0;
begin
	
	for dept_list in (select * from dept) loop
-- 		v_dept_table(i).deptno := dept_list.deptno;
-- 		v_dept_table(i).dname  := dept_list.dname;
-- 		v_dept_table(i).loc    := dept_list.loc;
			v_dept_table(i) := dept_list;
		i := i + 1;
	end loop;
	
	for j in 1..i loop
		dbms_output.put_line('부서번호=' || v_dept_table(j-1).deptno || chr(9) || 
		                     '부서이름=' || v_dept_table(j-1).dname  || chr(9) || 
												 '부서위치=' || v_dept_table(j-1).loc);
	end loop;
end;

call pro_10();

/* C. 제어문 */

-- 1. 단순조건(if) : if ... end if;
-- hr.employees에서 id = 203인 사원의 정보(id, first_name || last_name, department_id)
-- department_id가 10일 경우 Administration, 20=Marketing 30=Purchasing, 40=Human Resources
select * from hr.departments;

create or replace procedure pro_11 is
	v_emp_id     hr.employees.employee_id%type;
	v_emp_name   hr.employees.first_name%type;
	v_dept_id    hr.employees.department_id%type;
	v_dept_name  hr.departments.department_name%type;
begin 

	select employee_id
	     , first_name || '.' || last_name emp_name 
			 , department_id 
	  into v_emp_id, v_emp_name, v_dept_id 
		from hr.employees 
	 where employee_id = 203;
	 
	
	if(v_dept_id =10) 
		then v_dept_name := 'Administration';
	end if;

	if(v_dept_id =20) 
		then v_dept_name := 'Marketing';
	end if;
	
	if(v_dept_id =30) 
		then v_dept_name := 'Purchasing';
	end if;
		
	if(v_dept_id =40) 
		then v_dept_name := 'Human Resources';
	end if;
	
	dbms_output.put_line('부서번호=' || chr(9) || v_dept_id  || chr(9) ||
		                   '사원이름=' || chr(9) || v_emp_name  || chr(9) ||
											 '부서이름=' || chr(9) || v_dept_name);
	
end;

call pro_11();

-- 2. if then ... else ... end if;
-- 148사원의 bouns가 있다면 보너스출력 없다면 보너가 없습니다. 출력
create or replace procedure pro_12 is
	v_emp_id     hr.employees.employee_id%type;
	v_name       hr.employees.first_name%type;
	v_sal        hr.employees.salary%type;
	v_comm       hr.employees.commission_pct%type;
	v_bonus      number;
begin 
	
	select employee_id
	     , first_name || '.' || last_name emp_name 
			 , salary
			 , nvl(commission_pct, 0)
			 , salary * nvl(commission_pct, 0)
	  into v_emp_id, v_name, v_sal, v_comm, v_bonus 
		from hr.employees 
	 where employee_id = 203;

	if v_comm > 0 
		then dbms_output.put_line(v_name || ' 사원의 보너스는 ' || v_bonus || ' 원 입니다.' ); 
		else dbms_output.put_line(v_name || ' 사원의 보너스는 없습니다.');
	end if;
end;

call pro_12();

-- 3. if ... elsif ... end if;
create or replace procedure pro_13 is
	v_emp_id     hr.employees.employee_id%type;
	v_emp_name   hr.employees.first_name%type;
	v_dept_id    hr.employees.department_id%type;
	v_dept_name  hr.departments.department_name%type;
begin 

	select employee_id
	     , first_name || '.' || last_name emp_name 
			 , department_id 
	  into v_emp_id, v_emp_name, v_dept_id 
		from hr.employees 
	 where employee_id = 203;
	 
	if(v_dept_id =10)    then v_dept_name := 'Administration';
	elsif(v_dept_id =20) then v_dept_name := 'Marketing';
	elsif(v_dept_id =30) then v_dept_name := 'Purchasing';
	elsif(v_dept_id =40) then v_dept_name := 'Human Resources';
	else v_dept_name := '부서번호가 없습니다!!!';
	end if;
	
	dbms_output.put_line('부서번호=' || chr(9) || v_dept_id  || chr(9) ||
		                   '사원이름=' || chr(9) || v_emp_name  || chr(9) ||
											 '부서이름=' || chr(9) || v_dept_name);	 
end;

call pro_13();

-- 4. case
create or replace procedure pro_14 is
	v_emp_id     hr.employees.employee_id%type;
	v_emp_name   hr.employees.first_name%type;
	v_dept_id    hr.employees.department_id%type;
	v_dept_name  hr.departments.department_name%type;
begin 

	select employee_id
	     , first_name || '.' || last_name emp_name 
			 , department_id 
	  into v_emp_id, v_emp_name, v_dept_id 
		from hr.employees 
	 where employee_id = 203;
	 	
	v_dept_name :=
		case v_dept_id 
			when 10 then 'Administration'
			when 20 then 'Marketing'
			when 30 then 'Purchasing'
			when 40 then 'Human Resources'
			else '부서번호가 없습니다!!!'
		end;
	 
	dbms_output.put_line('부서번호=' || chr(9) || v_dept_id  || chr(9) ||
		                   '사원이름=' || chr(9) || v_emp_name  || chr(9) ||
											 '부서이름=' || chr(9) || v_dept_name);	 												
end;

call pro_14();

/* D. 반복문 */
-- 1. 단순 loop : loop ... end loop;
--    JavaScript의 do while과 동일한 실행
create or replace procedure pro_15 is
	result 		number := 0;
begin
	
	loop 
		result := result + 1;
		dbms_output.put_line('result = ' || result);
		exit when result >= 10;
	end loop;
	
end;

call pro_15();

-- 2. while
create or replace procedure pro_16 is
	result 		number := 0;
begin

	while result < 10 loop
		result := result + 1;
		dbms_output.put_line('result = ' || result);
	end loop;

end;

call pro_16();


-- 3. for
-- for 카운터(or 객체list) in [reverse] 시작..종료 loop 
--   실행문... 
-- end loop;
create or replace procedure pro_17 is
	result 		number := 0;
begin

	for i in 1..10 loop
		result := result + 1;
		dbms_output.put_line('result = ' || result);	
	end loop;
end pro_17;

call pro_17();

/* E. 매개변수가 있는 procedure 
	create or replace procedure 프로시져명(변수명 in 데이터타입,...)
	is [as]
	begin
	end 프로시져명;
*/
-- 실습1. 사원번호를 전달 받아서 사원급열를 10% 인상하는 프로시져
create or replace procedure update_emp_sal(p_empno in number, p_percent in number)
is
	v_bef_sal  number;
	v_aft_sal  number;	
begin
	select sal 
	  into v_bef_sal 
		from emp 
	 where empno = p_empno;
	
	dbms_output.put_line('현재사원의 급여는 ' || v_bef_sal || '입니다!');
	
	update emp
	   set sal = sal * p_percent
	 where empno = p_empno;
	
	select sal 
	  into v_aft_sal 
		from emp 
	 where empno = p_empno;
	 
	dbms_output.put_line('현재사원의 급여는 ' || v_bef_sal || '에서 ' || 
	                     v_aft_sal || '으로 인상되었습니다.');
		 
end update_emp_sal;

call update_emp_sal(7369, 1.1)

select ename, sal from emp where empno = 7369;

-- 실습2. emp에서 10부서의 급여를 15% 인상후에 
-- 사원번호, 사원명, 인상후급여를 출력하세요!
create or replace procedure pro_sal_raise()
is
begin

		dbms_output.put_line('==============================================');
		dbms_output.put_line('사원번호'|| chr(9) || '사원명' || chr(9) || '인상급여');
		dbms_output.put_line('==============================================');
		
end pro_sal_raise;

call pro_sal_raise(10, 1.5);

-- 실습3. 사번을 입력받아서 급여순서를 출력하는 procedure
create or replace procedure get_emp_rank(
	p_empno in  emp.empno%type,
	o_rank  out number)
is
begin
	select rnk
	  into o_rank
	  from (select empno, rank() over(order by sal desc) as rnk
		        from emp)
	 where empno = p_empno;
	 
exception
	when no_data_found then o_rank := -1;
	when others        then raise_application_error(-20001, '예상치못한 예외가 발생!!! : ' || sqlerrm); 
end get_emp_rank;


-- 익명함수에서는 프로시저에서는 call로 호출하지 않고
-- 직접 프로시저를 호출한다.
declare
	v_rank   number;
begin
	get_emp_rank(7369, v_rank);
	dbms_output.put_line('7369의 급여수위 = ' || v_rank);
end;


/* 연습문제 */
-- view연습문제를 procedure로 만들어 보세요
-- dbms_output.put_line
-- 프로시저이름 ex_01 ~ ex_06
-- ex01) professor, department을 join 교수번호, 교수이름, 소속학과이름 조회 View
-- ex02) inline view를 사용, student, department를 사용 학과별로
-- 학생들의 최대키, 최대몸무게, 학과명을 출력
-- ex03) inline view를 사용, 학과명, 학과별최대키, 학과별로 가장 키가 큰 학생들으;
-- 이름과 키를 출력
-- ex04) student에서 학생키가 동일학년의 평균키보다 큰 학생들의 학년과 이름과 키
-- 해당 학년의 평균키를 출력 단, inline view로
-- ex05) professor에서 교수들의 급여순위와 이름, 급여출력 단, 급여순위 1~5위까지
-- ex06) 교수번호정렬후 3건씩해서 급여합계와 급여평균을 출력