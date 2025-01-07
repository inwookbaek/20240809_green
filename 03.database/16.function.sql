/* Function

	 1. Function?
	 
	    보통의 경우 값을 계산하고 그 결과값을 반환하기 위해서 function을 사용한다.
			대부분 프로시저와 유사하지만 
			1) "in 파리미터"만 사용할 수 있다.
			2) 반드시 반환될 값의 데이타입을 return문에 선언해야 한다.
			
	 2. 문법
	 
	    1) pl/sql block에는 적어도 한개의 return문이 있어야 한다.
			2) pl/sql block은 함수가 수행할 내용을 정의한 본문(body)부분이다.
			3) 선언방법
			
			   create or replace function 평션이름
				    (매개변수 in 데이터타입) 
						return 데이터타입 -> return될 값의 데이터타입을 선언 
			   is[as]
						변수선언
				 begin
				 
				 
						return 변수명 --> 리턴문이 반드시 존재해야 한다.
				 end;
*/

-- 실습1. 사원번호를 입력받아서 사원급여를 10% 인상하는 함수
-- 오라클 함수(function)는 기본적으로 update/insert/delete를
-- 수행할 수 없다. 하지만 수행하려면 begin문 바로 위에 
-- 'pragma automous_transaction'을 선언
create or replace function fn_01(p_empno in number) return number
is
	v_sal   number;
	
pragma autonomous_transaction;
begin

	select sal 
		into v_sal
	  from emp
	 where empno = p_empno;
	
	dbms_output.put_line('인상전 급여 = ' || v_sal);	
	
			
	update emp
	   set sal = sal * 1.1
	 where empno = p_empno;
	 
	 commit;
	 -- rollback;
	 
	 select sal 
		 into v_sal
	   from emp
	  where empno = p_empno;	
			
	dbms_output.put_line('인상후 급여 = ' || v_sal);

	return v_sal;
end fn_01;

-- 인상전급여 = 880, 인상후 급여 = 968		
select fn_01(7369) from dual;

select empno, ename, sal, fn_01(empno) 
  from emp
 where empno = 7369;


-- 실습2. 부피를 계산하는 함수(fn_02)
-- 매개변수, length, width, height
-- result = length * width * height
create or replace function fn_02
 (length in number, width  in number, height in number) 
	return number
is
  result number;
begin

	result := length * width * height;
	return result;

end fn_02;

select fn_02(10,20,20) 부피 from dual; -- 4000
select 10*20*20 from dual;        -- 4000

-- 실습3. 현재일을 입력받아서 해당월의 마지막일자를 구하기
create or replace function fn_03
	(p_date in date)
	return date
is
	v_lastdate   date;
begin
	v_lastdate := (add_months(p_date, 1) - to_char(p_date, 'DD'));
	return v_lastdate;
end fn_03;

select (add_months(sysdate, 1) - to_char(sysdate, 'DD')) from dual;
select fn_03(sysdate) from dual;
select fn_03('20240201') from dual;


-- 실습4. '홍길동'문자를 받아서 '길동'값을 리턴하는 함수
create or replace function fn_04
  (p_name  in varchar2)
  return varchar2
is
	result varchar2(10);
begin
	-- result := substr(p_name, 2);
	return substr(p_name, 2);
end fn_04;

select fn_04('홍길동') from dual;
select fn_04('남궁성') from dual;

-- 실습5. 현재일을 입력받아서 '2024년 09월 06일'형태로 리턴하는 함수
create or replace function fn_05(p_date in date) return varchar2
is
begin
	return to_char(p_date, 'YYYY"년" MM"월" DD"일"');
end fn_05;
select fn_05(sysdate) from dual;
select empno, ename, hiredate, fn_05(hiredate) from emp;


-- 실습6. 주민번호를 전달받아서 남자 or 여자를 리턴하는 함수
-- 9911181234567 
create or replace function fn_06(p_ssn in varchar2) return varchar2
is
	v_gender varchar2(10);
begin
	
	v_gender := substr(p_ssn, 7, 1);
	if v_gender in ('1', '3')
		then v_gender := '남자';
		else v_gender := '여자';
	end if;
	
	return v_gender;
end fn_06;
select name, tel, fn_06(jumin) from student;

-- 실습7. emp에서 전사원의 hiredate를 현재일 기준으로 근속년월을 계산하는 함수를
-- 작성한 후에 전사원의 근속년월을 출력
create or replace function fn_07(p_hiredate in date) return varchar2
is
	v_workdays  varchar2(20);
begin
	v_workdays := floor(months_between(sysdate, p_hiredate)/12) || '년 ' ||
									floor(mod(months_between(sysdate, p_hiredate), 12)) ||'개월';
	return v_workdays;			
end fn_07;

select * from hr.employees; 
select first_name || '.' || last_name
     , fn_07(hire_date)
  from hr.employees;

select ename
     , fn_07(hiredate)
  from emp;


/* 실습문제 */ 
-- ex01) 부서의 총 급여를 반환하는 함수
-- get_dept_total_salary(10)
create or replace function get_dept_total_salary(p_deptno in hr.employees.department_id%type) return number
is	
	v_total_salary  number;
begin
	select sum(salary) 
	  into v_total_salary
	  from hr.employees
	 where department_id = p_deptno;
		
		return nvl(v_total_salary, 0);
end;

select get_dept_total_salary(20) from dual;
select get_dept_total_salary(10) from dual;

select deptno, sum(salary)
	from hr.employees
 where department_id = 10; -- group by 에러

select department_id
     , get_dept_total_salary(department_id)
	from hr.employees
 where department_id = 10;
 
select * From hr.employees;

-- ex02) 직원 번호가 주어졌을 때 직원의 관리자 이름을 반환하는 함수
-- get_manager_name
create or replace function get_manager_name(p_emp_id in hr.employees.employee_id%type) return varchar2
is	
	v_mgr_name  hr.employees.first_name%type;
begin
	select first_name || '.' || last_name
	  into v_mgr_name
	  from hr.employees
	 where employee_id = (select manager_id 
	                        from hr.employees 
												 where employee_id = p_emp_id);
												 
	return v_mgr_name;
exception 
	when no_data_found then return '해당 사원(' || p_emp_id ||')의 매니저가 없습니다!';
end;

select get_manager_name(100) from dual;
select get_manager_name(101) from dual;

select employee_id
     , first_name || '.' || last_name 사원이름
		 , get_manager_name(employee_id)  매니저이름
	from hr.employees
 where get_manager_name(employee_id) = 'John.Russell';

-- ex03) 급여와 커미션을 기준으로 직원의 커미션 비율을 계산하는 함수
-- get_commission_pct
create or replace function get_commission_pct(p_emp_id in hr.employees.employee_id%type) return number
is
	v_salary   hr.employees.salary%type;
	v_comm     hr.employees.commission_pct%type;
begin
	select salary, commission_pct
	  into v_salary, v_comm
	  from hr.employees
	 where employee_id = p_emp_id;
	 
  if v_comm is null or v_comm = 0 then
		return 0;
	else
		return v_comm * 100;
	end if;
			
exception 
	when no_data_found then return -1;
end;

select get_commission_pct(100) from dual;
select get_commission_pct(150) from dual;

-- ex04) 부서의 직원 수를 반환하는 함수
-- count_employees_in_dept
select * From hr.employees;
create or replace function count_employees_in_dept(p_deptno in hr.employees.department_id%type) return number
is
	v_count   number;
begin
	select count(*) 
	  into v_count
	  from hr.employees
	 where department_id = p_deptno;
	 
	 return nvl(v_count, 0);
	 
end;

select count_employees_in_dept(90) from dual;

select department_id
    , department_name
	  , count_employees_in_dept(department_id)
  from hr.departments;


-- ex05) 부서에서 가장 높은 급여를 받는 직원의 이름을 반환하는 함수
-- get_highest_paid_emp 
create or replace function get_highest_paid_emp(p_deptno in hr.employees.department_id%type) return varchar2
is
	v_emp_name  hr.employees.first_name%type;
begin
	select emp_name
	  into v_emp_name
	  from (select rownum rn, first_name || '.' || last_name emp_name
						from hr.employees
					 where department_id = p_deptno
					 order by salary desc)
	 where rn = 1;
	 
	 return v_emp_name;
end;

select department_id
    , department_name
	  , get_highest_paid_emp(department_id) 최고급여사원이름
  from hr.departments;
	 
-- ex06) 직원이 관리자인지 확인하는 함수
-- is_manager
create or replace function is_manager(p_empno in hr.employees.employee_id%type) return varchar2
is
	v_count  number;
begin
	select count(*)
	  into v_count
	  from hr.employees
	 where manager_id = p_empno;
	 
	 if v_count > 0
			then return '관리자입니다!!';
			else return '관리자가 아닙니다!!';
	 end if;
end;

select employee_id
     , first_name || '.' || last_name
		 , is_manager(employee_id)
  from hr.employees;

-- ex07) 특정 관리자에게 보고하는 직원의 평균 급여를 구하는 함수
-- get_avg_salary_by_mgr
create or replace function get_avg_salary_by_mgr(p_mgrid in hr.employees.employee_id%type) return number
is
	v_avg_salary  number;
begin
	select avg(salary)
	  into v_avg_salary
    from hr.employees
	 where manager_id = p_mgrid;
	 
	 return nvl(v_avg_salary, 0);
end;

select get_avg_salary_by_mgr(101) from dual;
select get_avg_salary_by_mgr(120) from dual;


-- ex08) 직원의 근속 기간을 계산하는 함수(년수)
-- get_years_of_service
select * from hr.employees;
create or replace function get_years_of_service(p_empid in hr.employees.employee_id%type) return number
is
	v_hiredate   hr.employees.hire_date%type;
	v_years      number;
begin
	select hire_date 
	  into v_hiredate
    from hr.employees
	 where employee_id = p_empid;
	 
	 v_years := floor((sysdate - v_hiredate) / 365);
	 
	 return nvl(v_years, 0);
end;

select employee_id
     , first_name || '.' || last_name  사원이름
		 , get_years_of_service(employee_id) || ' 년' 근속년수
  from hr.employees
 where employee_id = 100;
	
-- ex09) 직원이 속한 부서의 이름을 반환하는 함수
-- get_dept_name_by_emp
create or replace function get_dept_name_by_emp(p_empid in hr.employees.employee_id%type) return varchar2
is
	v_dept_name   hr.departments.department_name%type;
begin
	select dpt.department_name
	  into v_dept_name
	  from hr.employees   emp
		   , hr.departments dpt
	 where emp.employee_id   = p_empid
	   and emp.department_id = dpt.department_id;

	return v_dept_name;
end;

select employee_id
     , first_name || '.' || last_name  사원이름
		 , get_dept_name_by_emp(employee_id) 부서이름
  from hr.employees
 where employee_id = 150;
 

-- ex10) 직원과 관리자 간의 급여 차이를 계산하는 함수
-- get_salary_difference_with_mgr
create or replace function get_salary_difference_with_mgr(p_empid in hr.employees.employee_id%type) return number
is
	v_emp_sal   hr.employees.salary%type;
	v_mgr_sal   hr.employees.salary%type;
	
begin
	select salary
	  into v_emp_sal
	  from hr.employees
	 where employee_id = p_empid;
	 
	select salary
	  into v_mgr_sal
	  from hr.employees
	 where employee_id = (select manager_id 
	                       from hr.employees 
												where employee_id = p_empid);		
												
	return v_emp_sal - v_mgr_sal;

exception
	when no_data_found then return -1; -- 사원 or 관리자를 찾지 못했을 경우
end get_salary_difference_with_mgr;

select employee_id
     , first_name || '.' || last_name  사원이름
		 , get_salary_difference_with_mgr(employee_id) 급여차이
  from hr.employees
 where employee_id = 150;