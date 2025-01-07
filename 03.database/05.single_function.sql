-- dual 테이블 : 이 테이블은 dummy라는 단 하나의 컬러에 한개의 row만을 저장하고 
-- 있기 때문에 이 값은 아무런 의미가 없다. 쿼리의 수행결과가 하나의 값으로 임시로
-- 실행하고자 할 때 임시테이블로서의 역할을 제공한다.
select * from dual;
select sysdate from dual;
select 2 * 222 from dual;

/* 단일행 함수 */
--    1) upper   : 소문자를 대문자로 변환 : upper('aBcdE') -> ABCDE
--    2) lower   : 대문자를 소문자로 변환 : lower('aBcdE') -> abcde

--    3) initcap : 첫글자를 대문자, 나머지는 소문자로 변환 : initcap('aBcdE') -> Abcde
select ename, initcap(ename) from emp;
select name, initcap(name) from professor;
select initcap('hello world!!') from dual;

--    4) length  : 문자길이를 리턴(한글인 경우 1byte) : length('한글') -> 2
--    5) lengthb : 문자길이를 리턴(한글인 경우 2byte) : lengthb('한글') -> 4  
select ename, length(ename), lengthb(ename) from emp where deptno = 10;
select length('소향'), lengthb('소향') from dual;

--    6) concat  : 문자의 값을 연결 (||와 동일) : concat('A', 'B') -> AB
select ename || job, concat(ename, job), concat('소향의 직업은 ', '가수입니다!') from emp;
select 'A' || 'B' || 'C' from dual;
select concat(concat('A', 'B'), 'C') from dual;

--    7) substr  : 주어진 문자에서 특정 문자만 추출 : substr('ABC',1,2) -> AB
-- substr(값, from, 갯수) / substrb()
select 'abcde'
     , substr('abcde', 3, 2) -- cd
     , substr('abcde', -2, 2) -- de	
		 , substr('abcde', -3, 3) -- cde
		 , substr('abcde', 3, 3) -- cde	
		 , substr('abcde', 3, 3000000) -- cde		
		 , substr('abcde', 3, -1) -- null				 
  from dual;

-- 실습.
-- ex01) student 테이블의 주민등록번호 에서 성별만 추출
select * from student;
select jumin, substr(jumin, 7, 1) as gender from student;

-- ex02) student 테이블의 주민등록번호 에서 월일만 추출
select jumin, substr(jumin, 3, 4) as 월일 from student;

-- 8월에 태어난 학생에게 선물 
select name, jumin, substr(jumin, 3, 2) as 월 
  from student
 where substr(jumin, 3, 2) = '08';

-- ex03) student 테이블에서 jumin컬럼을 사용, 1전공이 101번인 학생들의
--       이름과 태어난월일, 생일 하루 전 날짜를 출력
select name, jumin, substr(jumin, 3, 4) as 월일, substr(jumin, 3, 4) - 1 from student;

select name, jumin, substr(jumin, 3, 4) as 월일, substr(jumin, 5, 2) - 1 
  from student
	where substr(jumin, 3, 2) = '08'
	  and (substr(jumin, 5, 2) - 1) = '18';

--    8) substrb : 주어진 문자에서 특정 위치의 바이트만 추출 : substrb('한글',1,2) -> 한
select '홍길동' name
	   , substr('홍길동', 1, 2)
     , substrb('홍길동', 1, 2) -- 홍(3바이트)에서 2바이트만 가져옴(한글이 깨짐)
		 , substrb('홍길동', 1, 3)
  from dual;

--    9) instr   : 주어진 문자에서 특정문자의 위치를 리턴 : instr("A*B#C#D", '#') -> 4
-- instr(문자열, 찾는글자, 시작위치, 몇번째(기본값 1)) : 찾는 문자의 위치를 리턴
select 'A*B*C*D'
     , instr('A*B*C*D', '*')       -- 2
     , instr('A*B*C*D', '*', 1, 3) -- 6	
     , instr('A*B*C*D', '*', 3, 1) -- 4			
		 , instr('A*B*C*D', '*', 3, 2) -- 6
		 , instr('A*B*C*D', '*', -8, 2) -- 0, 음수값은 뒤에서 부터 검색	
		 , instr('A*B*C*D', '*', -1, 2) -- 4		
		 , instr('A#B#C*D', '#', -1, 2) -- 2
	   , instr('A#B#C*D', '#', -1, 1) -- 4	 
  from dual;

select instr('HELLO World', 'O')
     , instr('HELLO World', 'o')
from dual;

--   10) instrb  : 주어진 문자에서 특정문자의 위치 바이트를 리턴 : instrb("한글로", "로") -> 7
select instrb('한글로', '한') -- 1 
     , instrb('한글로', '글') -- 4 
		 , instrb('한글로', '로') -- 7 		 
from dual;

--   11) lpad    : 주어진 문자열에서 왼쪽으로 특정 문자를 채움 : lpad('love', 6, '*') -> **love
--   12) rpad    : 주어진 문자열에서 왼쪽으로 특정 문자를 채움 : rpad('love', 6, '*') -> love**
-- lpad(문자열, 자리수, 채울문자), rpad(문자열, 자리수, 채울문자)
-- 991118-1*******
select '991118-1' || '******' from dual;
select name
     , jumin
		 , substr(jumin, 1, 6) || '-' || substr(jumin, 7, 1) || '******'  
	from student;

-- id가 10자리를 기준으로 *를 채우기
-- 75true****
-- Dreyfus***
select name, id, length(id)
  from student
 where deptno1 = 101;	

select name, id, length(id)
     , lpad(id, 20, '*')
		 , rpad(id, 20, '*')
  from student
 where deptno1 = 101;	
 
select '**hong##'
     , lpad('**hong##', 10, '*')
		 , rpad('**hong##', 10, '#')
 from dual; 
 
-- ex01) lpad() emp.ename : 1234CLARK, 12345KING, 123MILLER -> 길이가 9인 것을 전제 
select ename 
     , length(ename)
		 , lpad(ename, 9, '123456789')
  from emp;
	
-- ex02) rpad() emp.ename : CLARK1234, KING12345, MILLER123 -> 길이가 9인 것을 전제 
select ename 
     , length(ename)
		 , rpad(ename, 9, '123456789')
  from emp;

-- ex03) rpad() emp.ename : CLARK5678, KING45678, MILLER678 -> 길이가 9인 것을 전제 
-- lengthb, substr함수를 이용

select ename 
     , length(ename)
		 , rpad(ename, 9, '123456789')
		 , lengthb(ename)
		 , substr('123456789', lengthb(ename))
		 , rpad(ename, 9, substr('123456789', lengthb(ename)))
  from emp;

--   13) ltrim   : 주어진 문자열에서 왼쪽의 특정 문자를 삭제 : ltrim('*love', '*') -> love
--   15) rtrim   : 주어진 문자열에서 오른쪽의 특정 문자를 삭제 : ltrim('love*', '*') -> love
-- ltrim(문자열, 제거할 문자), rtrim(문자열, 제거할 문자)
select ename
	   , ltrim(ename, 'C')
		 , rtrim(ename, 'R')
	   , ltrim(ename, 'CLAR')
		 , rtrim(ename, 'LLER')		
		 , ltrim('*love', '*')	
		 , rtrim('love*', '*')
		 , ltrim('*!@#love', '*')	
		 , rtrim('love*!@#', '*')		 
		 , ltrim('*!@#love', '*!@#')	
		 , rtrim('love*!@#', '*!@#')		 		 
  from emp
 where deptno = 10;

--   16) replace : 주어진 문자열에서 A를 B로 치환 : replace("AB", "A", "E") -> EB 
-- replace(문자열, 변경할문자, 대체할문자)
select ename
	 	 , 'KING -> **NG'
	   , replace(ename, 'KI', '**')
	   , replace(ename, 'CL', '**')	
		 , replace(ename, 'MI', '**')
		-- substr, **NG, **ARK, **LLER
		 , substr(ename, 1, 2)
		 , replace(ename, substr(ename, 1, 2), '**')
  from emp
 where deptno = 10;
 
-- 실습. replace, substr, instr
-- ex01) emp에서 20부서에 사원들의 이름과 3~4번째글자를 '-' 변경 SMITH --> SM--H
select ename
		 , replace(ename, substr(ename, 3, 2), '--')
  from emp;
	
-- ex02) student에서 deptno1=101 학생들의 이름과, 주민번호를 출력하는데
--       주민번호 7자리는 '-'와 '/'로 표시되게 123456-/-/-/- 
select name
     , jumin
		 , replace(jumin, substr(jumin, 7, 7), '-/-/-/-')
		 , replace(jumin, substr(jumin, 7, 7), '-******')
  from student;


-- ex03) student에서 deptno1=102 학생들의 이름과 전화번호에서 
--       국번만 '*'처리 (조건 모든 국번은 3자리) 051)999-9999 --> 051)***-9999 
select name
		 , tel
		 , instr(tel, ')', 1)+1  -- ) 바로 뒤
		 , replace(tel, substr(tel, instr(tel, ')', 1)+1, 3), '***')
  from student
 where deptno1 = 102;
 
-- 051)999-****
select name
		 , tel
		 , instr(tel, '-', 1)+1  -- ) 바로 뒤
		 , replace(tel, substr(tel, instr(tel, '-', 1)+1, 4), '****')
  from student
 where deptno1 = 102; 

/* B. 숫자함수 */
	
-- 	1. round : 주어진 실수를 반올림
-- round(값, 반올림자리)
select 987.654
     , round(987.654)     -- 0
		 , round(987.654, 1)  -- 1
		 , round(987.654, 2)  -- 2
		 , round(987.654, -1) -- -1
		 , round(987.654, -2) -- -2
  from dual;

-- 	2. trunc : 주어진 실수를 버림
-- trunc(값, 버릴자리)
select 987.654
     , trunc(987.654, 0)     -- 0
		 , trunc(987.654, 1)     -- 1
		 , trunc(987.654, 2)     -- 1
		 , trunc(987.654, -1)    -- 단단위
		 , trunc(987.654, -2)    -- 십단위
  from dual;

select 'round='
     , 987.654
     , round(987.654)     -- 0
		 , round(987.654, 1)  -- 1
		 , round(987.654, 2)  -- 2
		 , round(987.654, -1) -- -1
		 , round(987.654, -2) -- -2
  from dual
union all
select 'trunc='
     , 987.654
     , trunc(987.654, 0)     -- 0
		 , trunc(987.654, 1)     -- 1
		 , trunc(987.654, 2)     -- 1
		 , trunc(987.654, -1)    -- 단단위
		 , trunc(987.654, -2)    -- 십단위
  from dual;
	
-- 	3. mod   : 나누기연산후 나머지값을 리턴
-- 	4. ceil  : 주어진 실수값에서 가장 큰 정수값을 리턴
-- 	5. floor : 주어진 실수값에서 가장 작은 정수값을 리턴
-- mod / ceil / floor
select 121
     , mod(121,10)  -- 120 / 10의 나머지
		 , ceil(123.45) -- 값에서 가장 큰 정수
		 , floor(123.45) -- 값에서 가장 작은 정수
  from dual;

-- 	6. power : 주어진 값을 주어진 승수를 리턴 power(3,3) -> 3 x 3 x 3 = 27
-- power(값, 승수)
select power(2, 3) from dual;
select power(3, 3) from dual;

-- 	7. rownum : 오라클에서만 사용되는 속서으로 모든 객체에 제공된다.
-- 	   ... rownum은 전체열 즉, *와 같이 사용할 수 없다.
-- 		 ... rownum은 행번호를 의미
select * from student;
select rownum, * from student; -- NG
select rownum, name, jumin from student;

select rownum, name, jumin 
  from student
 where deptno1 = 101; 
 
select rownum, name, jumin 
  from student
 where deptno1 = 101;

select rownum, name, jumin 
  from student
 where deptno1 = 101
 order by name;

select rownum, name
  from (select rownum, name, jumin 
					from student
				 where deptno1 = 101
				 order by name);

select rownum, name, jumin 
					from student
				 where deptno1 = 101
				 order by rownum;
				 
				 
/* C. 날짜함수 */	
	
-- 	1. sysdate : 시스템의 현재일자 : 날짜형으로 리턴
select sysdate from dual;

-- 	2. months_between : 두 날짜 사이의 개월수를 리턴 : 숫자형으로 리턴
select MONTHS_BETWEEN(sysdate, '20210516') from dual;

-- emp에서 각 사원의 근속월수는?
select ename
     , hiredate
		 , round(months_between(sysdate, hiredate), 2)             -- 근속월수
		 , round(round(months_between(sysdate, hiredate), 2) / 12) -- 근속년수
  from emp;

-- 	3. add_months : 주어진 일자에 개월수를 더한 결과를 리턴 : 날짜형
select sysdate
     , ADD_MONTHS(SYSDATE, 5)
		 , ADD_MONTHS(SYSDATE, -5)
  from dual;

-- 	4. next_day : 주어진 일자를 기준으로 다음 요일 리턴 : 날짜형
-- 1~7 : 일요일~토요일
select sysdate
     , NEXT_DAY(sysdate, 1) -- 현재일에서 다음번의 일요일
     , NEXT_DAY(sysdate, 2) -- 현재일에서 다음번의 월요일
     , NEXT_DAY(sysdate, 3) -- 현재일에서 다음번의 화요일
     , NEXT_DAY(sysdate, 4) -- 현재일에서 다음번의 수요일
     , NEXT_DAY(sysdate, 5) -- 현재일에서 다음번의 목요일
     , NEXT_DAY(sysdate, 6) -- 현재일에서 다음번의 금요일
     , NEXT_DAY(sysdate, 7) -- 현재일에서 다음번의 토요일
  from dual;

-- 	5. last_day : 주어진 일자에 해당하는 월의 마지막일자를 리턴
select sysdate
		 , last_day(sysdate)
		 , last_day('20240910')
		 , last_day('2024-09-10')
		 , last_day('2024.09.10')
		 , last_day('2024/09/10')
  from dual;
	
-- 	6. round : 주어진 날짜를 반올림
-- 	7. trunc : 주어진 날짜를 버림
select sysdate
		 , round(sysdate)
		 , round(to_date('20240830'))  -- to_date() 날짜형태의 문자열을 날짜형으로 변환함수
		 , trunc(sysdate)
		 , trunc(to_date('20240830'))
  from dual;

/* D. 형변환함수 
-- 자동형변환(암묵적, 묵시적)
-- 수동형변환(명시적)

	1. to_char()  : 날짜 or 숫자를 문자로 변환함수
	2. to_number(): 문자형숫자를 숫자로 변환(단, 숫자형식에 맞아야 함)
	3. to_date()  : 문자형을 날짜로 변환(단, 날짜형식에 맞아야 함)
*/	

-- 1. to_number()
-- 1) 자동(묵시적, 암묵적)형변환
select '2' + 2 from dual; -- 22가 아니라 4
select 2 + '2' from dual; -- 22가 아니라 4

-- 2) 수동(명시적)형변환
select to_number('2') + 2 from dual; 
select 2 + to_number('2') from dual; 

-- 형변환에러
select '2a' + 2 from dual; --> ORA-01722: invalid number
select 'A' + 2 from dual;

-- 2. to_char()
-- 1) 날짜를 문자로 변환
select sysdate
     , to_char(sysdate)
		 , to_char(sysdate, 'YYYY') -- 년도
		 , to_char(sysdate, 'RRRR') -- 년도
		 , to_char(sysdate, 'YY') -- 년도
		 , to_char(sysdate, 'rr') -- 년도
		 , to_char(sysdate, 'yy') -- 년도
		 , to_char(sysdate, 'YEAR') -- 년도
		 , to_char(sysdate, 'year') -- 년도
  from dual;
		 
select sysdate
     , to_char(sysdate)
		 , to_char(sysdate, 'MM') -- 월
		 , to_char(sysdate, 'MON') -- 월
		 , to_char(sysdate, 'MONTH') -- 월
		 , to_char(sysdate, 'mm') -- 월
		 , to_char(sysdate, 'mon') -- 월
		 , to_char(sysdate, 'month') -- 월
  from dual;

select sysdate
     , to_char(sysdate)
		 , to_char(sysdate, 'DD') -- 일
		 , to_char(sysdate, 'DAY') -- 일
		 , to_char(sysdate, 'DDTH') -- 일
		 , to_char(sysdate, 'dd') -- 일
		 , to_char(sysdate, 'day') -- 일
		 , to_char(sysdate, 'ddth') -- 일
  from dual;
	
-- 날짜형식설정
select sysdate
     , to_char(sysdate, 'YYYY.MM.DD')	
     , to_char(sysdate, 'yyyy.mm.dd')	
     , to_char(sysdate, 'yyyy.mm.dd hh:mi')	
     , to_char(sysdate, 'yyyy.mm.dd hh:mi:ss')	
     , to_char(sysdate, 'yyyy.mm.dd hh24:mi:ss')	
     , to_char(sysdate, 'MON.DD.YY hh:mi:ss')	
     , to_char(sysdate, 'mm.DD.YYYY hh:mi:ss')	
  from dual;	

-- 2) 숫자를 문자로 변환
-- 12345 -> 천단위(12,345), 소수점(12345.00)형태로 변환
select 1234
     , to_char(1234, '9999')
     , to_char(1234, '9999999999')
     , to_char(1234, '0999999999')
     , to_char(1234, '$999999999')
     , to_char(1234, '$999999999.99')
     , to_char(1234, '$9,999.99')
     , to_char(12345, '$9,999.99')  -- 자리수가 안 맞을 경우
     , to_char(12345, '$99,999.99')
     , to_char(123456789, '$9,999') -- 자리수가 안 맞을 경우
     , to_char(123456789, '$9,999,999,999') 
  from dual; 

/*
	E. 기타함수
	
	1. nvl()    : null값을 다른 값으로 치환하는 함수 nvl(comm, 0)
	2. nvl2()   : null값을 다른 값으로 치환하는 함수 nvl2(comm, true, false)
	3. decode() : 오라클에서만 사용하는 함수 if~else
	4. case     : decode대신에 일반적으로 사용되는 문장
				
				case 조건 when 결과1 then 출력1,
				         [when 결과n then 출력n]
				 end as 별칭
*/	

-- 1. nvl()
select ename, sal, comm, sal + comm from emp where ename = 'SMITH'
union all
select ename, sal, nvl(comm, 0), sal + nvl(comm, 0) from emp where ename = 'SMITH';

select name, pay, bonus, nvl(bonus, 0)
     , pay + nvl(bonus, 0) 총급여
		 , to_char(pay + nvl(bonus, 0), '$9,999.99')
  from professor;

-- 2. nvl(값, true(null아니면), false(null))
select ename, sal, comm, nvl2(comm, 'null값이 아닙니다!', 'null값입니다!') 
  from emp; -- where ename = 'SMITH';
	
select ename, sal, comm
     , nvl2(comm, ename||'의 커미션은 '||comm||'원 입니다!', ename||'의 커미션은 없습니다!') 
  from emp;


-- 실습문제(제출)
-- ex01) emp테이블에서 ename, hiredate, 근속년, 근속월, 근속일수 출력, deptno = 10;
-- 근속년
-- months_between, round, turnc, 개월수계산(/12), 일수계산(/365, /12)
select ename
     , hiredate
		 , months_between(sysdate, hiredate)
		 , round(months_between(sysdate, hiredate), 0) 
		 , trunc(months_between(sysdate, hiredate), 0) 근속월수
		 , trunc(trunc(months_between(sysdate, hiredate),0 ) / 12, 0 ) 근속년수
		 , trunc(trunc(months_between(sysdate, hiredate),0 ) / 12, 0 ) * 12 근속개월수
  from emp;

-- 근속월,일
select ename
     , hiredate
		 , trunc(trunc(months_between(sysdate, hiredate),0 ) / 12, 0 ) 근속년수
		 , trunc(mod(months_between(sysdate, hiredate) / 12, 1) * 12, 0) 근속월
		 , round(mod(mod(months_between(sysdate, hiredate) / 12, 1) * (365/12), 0)) 근속일
  from emp;	
	

-- ex02) student에서 birthday중 생일 1월의 학생의 이름과 생일을 출력(YYYY-MM-DD)
select studno
     , name
		 , birthday
		 , to_char(birthday, 'YYYY-MM-DD')
		 , to_char(birthday, 'MM')
  from student
 where to_char(birthday, 'MM') = '01';
	
-- ex03) emp에서 hiredate가 1,2,3월인 사람들의 사번, 이름, 입사일을 출력
select empno 
     , ename
		 , hiredate
  from emp
 where to_char(hiredate, 'MM') = '01'
    or to_char(hiredate, 'MM') = '02'
	  or to_char(hiredate, 'MM') = '03';
		
select empno 
     , ename
		 , hiredate
  from emp
 where to_char(hiredate, 'MM') in ('01', '02', '03');		

-- ex04) emp 테이블을 조회하여 이름이 'ALLEN' 인 사원의 사번과 이름과 연봉을 
--       출력하세요. 단 연봉은 (sal * 12)+comm 로 계산하고 천 단위 구분기호로 표시하세요.
--       7499 ALLEN 1600 300 19,500    
select ename
     , sal
		 , comm
		 , sal * 12 + comm
		 , to_char(sal*12+comm, '999,999') as 년봉
  from emp
 where ename = 'ALLEN';	 

-- ex05) professor 테이블을 조회하여 201 번 학과에 근무하는 교수들의 이름과 
--       급여, 보너스, 연봉을 아래와 같이 출력하세요. 단 연봉은 (pay*12)+bonus
--       로 계산합니다.
--       name pay bonus 6,970
select name, pay, bonus
     , to_char(pay * 12 + bonus, '999,999') 년봉
		 , to_char(pay * 12 + nvl(bonus,0), '999,999') 년봉
  from professor
 where deptno = 201;


-- ex06) emp 테이블을 조회하여 comm 값을 가지고 있는 사람들의 empno , ename , hiredate ,
--       총연봉,15% 인상 후 연봉을 아래 화면처럼 출력하세요. 단 총연봉은 (sal*12)+comm 으로 계산하고 
--       15% 인상한 값은 총연봉의 15% 인상 값입니다.
--      (HIREDATE 컬럼의 날짜 형식과 SAL 컬럼 , 15% UP 컬럼의 $ 표시와 , 기호 나오게 하세요)
select empno
     , ename
		 , to_char(hiredate, 'YYYY.MM.DD')
		 , to_char(sal * 1.15, '$999,999') as "15% 인상급여"
  from emp;


-- ex07) Professor 테이블에서 201번 학과 교수들의 이름과 급여, bonus , 총 연봉을 출력하세요. 
--       단 총 연봉은 (pay*12+bonus) 로 계산하고 bonus 가 없는 교수는 0으로 계산하세요
select profno
     , name
		 , pay
		 , nvl(bonus, 0)
		 , pay + nvl(bonus, 0) 총급여
  from professor;
	 
-- ex08) 아래 화면과 같이 emp 테이블에서 deptno 가 30 번인 사원들을 조회하여 comm 값이 있을 경우
--       'Exist' 을 출력하고 comm 값이 null 일 경우 'NULL' 을 출력하세요 
select empno
     , ename
		 , comm
		 , nvl2(comm, 'EXIT', 'NULL')
  from emp
 where deptno = 30;
 
-- ex09)student 테이블에서 deptno1 = 101인 학생들의 이름과 전화번호와 블라인딩처리
--       예) 055)381-****, 02)7777-**** 의 형식으로 출력
-- instr, substr, replace
select name
     , tel
		 , instr(tel, '-', 1, 1)
		 , substr(tel, instr(tel, '-', 1, 1)+1, 4)
		 , replace(tel, substr(tel, instr(tel, '-', 1, 1)+1, 4), '****')
  from student
 where deptno1 = 101;
 
/*
	DECODE 함수
	1) 통상적으로 if~else문을 decode로 표현한 함수로 오라클에서만 사용되는 함수
	2) 오라클에서 자주 사용하는 중요한 함수이다
	3) decode(col, true, false) 즉, col결과(값)가 true일경우 true실행문을 실행, 아니면 fasle실행문을 실행
	4) decode(deptno, 101, true,
										102, true,
										103, true, false) -> if~else if~else
	5) decode(deptno, 101, decode()....) 중첩if문	
*/ 

-- 학과정보
select * from department;
select * from student;

-- 1. if ~  / if ~ else ~
-- 101 컴퓨터공학, 아니면 기타학과로 출력
select name
		 , deptno1 
		 , decode(deptno1, 101, '컴퓨터공학')   -- if(true) {}
		 , decode(deptno1, 101, '컴퓨터공학', '기타학과') -- if ~ else ~
	from student;
	
	
-- 2. if ~ else if ~ else
-- 101 컴퓨터공학, 102 미디어융합, 103 소프트공학, 아니면 기타학과
select name
		 , deptno1 	
		 , decode(deptno1, 101, '컴퓨터공학' 
		                 , 102, '미디어융합'
										 , 103, '소프트공학'
										 , '기타학과')
	from student;

-- 중첩decode if { if {} }
-- 101학과교수중에 Audie Murphy교수는 'Best Professor' 101이외의 담당교수 'N/A'로 출력하기
select name
     , deptno
		 , decode(deptno, 101, 'Best Professor')
		 , decode(deptno, 101, decode(name, 'Audie Murphy',  'Best Professor', 'Good Professor'), 'N/A')
	from professor;

-- 실습
-- ex01) student에서 전공(deptno1)이 101인 학생들 중에서 jumin에서
-- 1 or 3이면 '남자', 2 or 4면 '여자'로 출력
-- substr, decode
select name
     , jumin
		 , substr(jumin, 7, 1)
		 , decode(substr(jumin, 7, 1), '1', '남자', '2', '여자')
		 , decode(substr(jumin, 7, 1), '1', '남자', '2', '여자', '3', '남자', '4', '여자')
  from student
 where deptno1 = 101;
 
-- ex02) Student 테이블에서 1 전공이 (deptno1) 101번인 학생의 이름과 
--       연락처와 지역을 출력하세요. 단,지역번호가 02 는 "SEOUL" , 
--       031 은 "GYEONGGI" , 051 은 "BUSAN" , 052 는 "ULSAN" , 
--       055 는 "GYEONGNAM"입니다.
-- substr, instr, decode 
select name
     , tel
		 , instr(tel, ')', 1) - 1
		 , substr(tel, 1, instr(tel, ')', 1) - 1) 지역번호 
		 , decode(substr(tel, 1, instr(tel, ')', 1) - 1)
			        , '02',  '서울'
							, '031', '경기'
							, '051', '부산'
							, '052', '울산'
							, '053', '대구'
							, '055', '경남', '기타')
  from student
 where deptno1 = 101;

/*
	CASE 함수
	1) case 조건 when 결과 then true else false end as 별칭;
	2) case when between 값1 and 값2 then true else false end as 별칭;
*/
-- 1.  case 조건 when
select name
     , tel
		 , instr(tel, ')', 1) - 1
		 , substr(tel, 1, instr(tel, ')', 1) - 1) 지역번호 
		 , decode(substr(tel, 1, instr(tel, ')', 1) - 1)
			        , '02',  '서울'
							, '031', '경기'
							, '051', '부산'
							, '052', '울산'
							, '053', '대구'
							, '055', '경남', '기타')
		  , case substr(tel, 1, instr(tel, ')', 1) - 1)
			       when '02'  then '서울'
						 when '031' then '경기'
						 when '051' then '부산'
						 when '052' then '울산'
						 when '053' then '대구'
						 when '055' then '경남'
						 else '기타'
				 end as 지역번호2
  from student
 where deptno1 = 101;
 
-- 2. case when 값(or 표현식) between and true
-- emp테이블에서 sal 1~1000 1등급, 1001~2000 2등급, 2001~3000 3등급, 3001~4000 4등급
-- 4001보다 크면 5등급
select empno
     , ename
		 , sal
		 , case when sal between    1 and 1000 then '1등급'
		        when sal between 1001 and 2000 then '2등급'
		        when sal between 2001 and 3000 then '3등급'
		        when sal between 3001 and 4000 then '4등급'
		        when sal > 4001 then '5등급'		 
		    end 급여등급
  from emp;

-- ex01) student에서 jumin에 월참조해서 해당월의 분기를 출력(1Q, 2Q, 3Q, 4Q)
-- name, jumin, 분기
select name
     , jumin
		 , substr(jumin, 3, 2) month
		 , case when substr(jumin, 3, 2) between '01' and '03' then '1Q'
		        when substr(jumin, 3, 2) between '04' and '06' then '2Q'
						when substr(jumin, 3, 2) between '07' and '09' then '3Q'
						when substr(jumin, 3, 2) between '10' and '12' then '4Q'
		    end 분기
  from student;

-- ex02) dept에서 10=회계부, 20=연구실, 30=영업부, 40=전산실
-- 1) decode
-- 2) case
-- deptno, 부서명
select deptno
     , decode(deptno, 10, '회계부'
		                , 20, '연구실'
										, 30, '영업부'
										, 40, '전산실') as 부서명
		 , case deptno when 10 then '회계부'
						       when 20 then '연구실'
									 when 30 then '영업부'
									 when 40 then '전산실'
			  end as 부서명2
  from emp;



-- ex03) 급여인상율을 다르게 적용하기
-- emp에서 sal < 1000 0.8%인상, 1000~2000 0.5%, 2001~3000 0.3%
-- 그 이상은 0.1% 인상분 출력
-- ename, sal(인상전급여), 인상후급여 
-- 1) decode
-- 2) case
-- sign(sal - 1000) 함수
select sign(-1), sign(0), sign(1) from dual;
-- JavaScript에서 객체 or 값을 비교할 때 -1 0 1
select ename
     , sal
		 , decode(sign(sal-1000)
		        , -1, sal * 1.08                  -- sal < 1000
		        ,  0, sal * 1.05	                 -- sal = 1000
						,  decode(sign(sal - 2000)
						        , -1, sal * 1.05 -- sal < 2000
										,  0, sal * 1.05 -- sal = 2000
										,  decode(sign(sal - 3000)
										         , -1, sal * 1.03 -- sal < 3000
														 ,  0, sal * 1.03 -- sal = 2001
														 ,  1, sal * 1.01))) as 인상후급여 -- sal > 3000
											 
		 , case when sal between    1 and 1000 then sal * 1.08
	          when sal between 1001 and 2000 then sal * 1.05
						when sal between 2001 and 3000 then sal * 1.03
						when sal > 3000 then sal * 1.01
				end 인상후급여														
  from emp;