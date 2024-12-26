/* 
   Join 문법
	 
	 1. 표준문법
	 
	 select t1.col1, t2.col1
	   from table1 t1, table2 t2
	  where t1.col1 = t2.col1
		
	 select table1.col1, table2.col1
	   from table1 t1, table2 t2
	  where table1.col1 = t07.joinable2.col1	
		
   2. ansi join
	 
	 select table1.col1, table2.col1
		 from table1 a [inner] join table2 b on a.col1 = b.col1
*/

/* A. Inner Join */
-- 1. Cartesian Product(카티션곱)
-- Join대상이 누락되었을 때 모든 테이블들의 내용이 곱으로 출력된다.
-- emp.13 * dept.4 = 52건
select * from emp;
select * from dept;

select *
  from emp, dept;

select *
  from dept, emp;
	
select * 
  from dual, emp;
	
-- 2. equi join(등가조인)
select ename, deptno from emp;
select deptno, dname from dept;

-- 1) 표준 join 문법
select ename
     , dept.deptno
		 , dname
  from emp, dept
 where emp.deptno = dept.deptno;

select emp.ename
     , dept.deptno
		 , dept.dname
  from emp, dept
 where emp.deptno = dept.deptno;

-- 테이블 별칭 부여하기
select emp.ename
     , dept.deptno
		 , dept.dname
  from emp e, dept d
 where emp.deptno = dept.deptno;

-- 테이블별칭을 사용할 경우에 테이블실제명 대신에
-- 테이블별칭을 사용해야 한다.
select e.ename
     , d.deptno
		 , d.dname
  from emp e, dept d
 where e.deptno = d.deptno;

select emp.ename
     , dpt.deptno
		 , dpt.dname
  from emp emp, dept dpt
 where emp.deptno = dpt.deptno;
 
-- 2) ansi join
select emp.ename
     , dpt.deptno
		 , dpt.dname
	from emp emp join dept dpt 
	               on emp.deptno = dpt.deptno;	

select emp.ename
     , dpt.deptno
		 , dpt.dname
	from emp emp inner join dept dpt 
	               on emp.deptno = dpt.deptno;	

select emp.ename
     , dpt.deptno
		 , dpt.dname
	from emp emp left join dept dpt 
	               on emp.deptno = dpt.deptno;	

select emp.ename
     , dpt.deptno
		 , dpt.dname
	from emp emp right join dept dpt 
	               on emp.deptno = dpt.deptno;	
-- full [outer] join								 
select emp.ename
     , dpt.deptno
		 , dpt.dname
	from emp emp full join dept dpt 
	               on emp.deptno = dpt.deptno;		
								
-- 실습1) student와 professor를 join 학생이름과 교수이름을 출력
-- 표준/ansi 조인 각각 실행
-- join col : profno
select * from student;
select * from professor;	

select std.name 학생명
     , pro.name 교수명
	from student std, professor pro
 where std.profno = pro.profno;	

select std.name 학생명
     , pro.name 교수명
	from student std join professor pro
                     on std.profno = pro.profno;			

-- 실습2) student와 department, professor를 join 해서 학생이름과 학과이름 교수이름을 출력
-- 표준일 경우 from 3개의 테이블, ansi일 경우 join이 두번
select * from department;	

-- 표준 join
select std.name  학생명
     , pro.name  교수명
		 , dpt.dname 학과명
	from student    std
	   , professor  pro
		 , department dpt
 where std.profno = pro.profno
   and pro.deptno = dpt.deptno;

select std.name  학생명
     , pro.name  교수명
		 , dpt.dname 학과명
	from student    std
	   , professor  pro
		 , department dpt
 where std.profno  = pro.profno
   and std.deptno1 = dpt.deptno;
	 
-- ansi join 
select std.name  학생명
     , pro.name  교수명
		 , dpt.dname 학과명
	from student    std join professor  pro on std.profno = pro.profno
	                    join department dpt on std.deptno1 = dpt.deptno;

-- 3. non-equi join(비등가조인)
-- between, in, ...
select * from customer;
select * from gift;

-- customer와 gift를 join해서 고객별로 마일리지(point)기준으로 
-- 선물을 받을 수 있는 선물세트를 조회
-- 고객명과 상품명을 조회
select cus.gname  고객명
     , gft.gname  상품명
  from customer cus 
	   , gift     gft
 where cus.point between gft.g_start and gft.g_end;

select cus.gname  고객명
     , to_char(cus.point, '999,999,999')  포인트
     , gft.gname  상품명		 
  from customer cus 
	   , gift     gft
 where cus.point >= gft.g_start 
   and cus.point <= gft.g_end;
	 
-- ansi join?
select cus.gname  고객명
     , gft.gname  상품명
  from customer cus left gift     gft
    on cus.point between gft.g_start and gft.g_end;

-- 실습) student, score, hakjum테이블에서 학생명과 점수와 학점을 출력
-- 표준과 ansi
select * from student;
select * from score;
select * from hakjum;

-- 비등가조인
select std.name  학생명
     , sco.total 총점
		 , hak.grade 학점
  from student std
	   , score   sco
		 , hakjum  hak
 where std.studno = sco.studno 
   and sco.total between hak.min_point and hak.max_point;
	 
select std.name  학생명
     , sco.total 총점
		 , hak.grade 학점
  from student std
	   , score   sco
		 , hakjum  hak
 where  
   and sco.total >= hak.min_point 
	 and sco.total <= hak.max_point;	 
	 
 -- ansi join
select std.name  학생명
     , sco.total 총점
		 , hak.grade 학점
  from student std join score   sco on std.studno = sco.studno 
	                 join hakjum  hak on sco.total between hak.min_point and hak.max_point;
		 
/* B. outer Join */ 
-- 1. oracle
-- '(+)'는 oracle에서만 사용되는 outer join 문법
select std.name 학생명
     , pro.name 교수명
  from student   std
	   , professor pro
 where std.profno = pro.profno;
 
select count(*) from student; 
select name, profno from student where profno is null; 


-- (+)이 왼쪽에 정의 되어 있으면 right join, 오른 쪽에 정의
-- 되어 있으면 left join
select std.name 학생명
     , pro.name 교수명
  from student   std
	   , professor pro
 where std.profno = pro.profno;  -- inner join

select std.name 학생명
     , pro.name 교수명
  from student   std
	   , professor pro
 where std.profno = pro.profno(+); -- left [outer] join

select std.name 학생명
     , pro.name 교수명
  from student   std
	   , professor pro
 where std.profno(+) = pro.profno; -- right [outer] join

-- 2. ansi join
select std.name 학생명
     , pro.name 교수명
  from student   std join professor pro on std.profno = pro.profno; -- inner join

select std.name 학생명
     , pro.name 교수명
  from student   std left join professor pro on std.profno = pro.profno; -- left join
	
select std.name 학생명
     , pro.name 교수명
  from student   std left outer join professor pro on std.profno = pro.profno; 

select std.name 학생명
     , pro.name 교수명
  from student   std right join professor pro on std.profno = pro.profno; -- right join

/* C. Full Join */	 
select std.name 학생명
     , pro.name 교수명
  from student   std full join professor pro on std.profno = pro.profno; -- full join

select std.name 학생명
     , pro.name 교수명
  from student   std full outer join professor pro on std.profno = pro.profno; -- full join
	
		 
/* D. self Join */	
select empno from emp e;
select m.mgr from emp m;

select e.empno
     , e.ename
		 , e.mgr 
		 , m.ename 
  from emp e, emp m
 where e.mgr = m.empno;

/* 연습문제 */
-- ex01) student, department에서 학생이름, 학과번호, 1전공학과명출력
-- ex02) emp2, p_grade에서 현재 직급의 사원명, 직급, 현재 년봉, 해당직급의 하한
--       상한금액 출력 (천단위 ,로 구분)	
-- ex03) emp2, p_grade에서 사원명, 나이, 직급, 예상직급(나이로 계산후 해당 나이의
--       직급), 나이는 오늘날자기준 trunc로 소수점이하 절삭
-- ex04) customer, gift 고객포인트보나 낮은 포인트의 상품중에 Notebook을 선택할
--       수 있는 고객명, 포인트, 상품명을 출력		
-- ex05) professor에서 교수번호, 교수명, 입사일, 자신보다 빠른 사람의 인원수
--       단, 입사일이 빠른 사람수를 오름차순으로
-- ex06) emp에서 사원번호, 사원명, 입사일 자신보다 먼저 입사한 인원수를 출력
--       단, 입사일이 빠른 사람수를 오름차순 정렬