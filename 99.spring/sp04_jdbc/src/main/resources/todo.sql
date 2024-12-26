create database todo;

use todo;

create table tbl_todo(
	
	tno int auto_increment primary key,
	title varchar(100) not null,
	dueDate date not null,
	finished tinyint default 0
	
)

insert into tbl_todo(title, dueDate, finished)
	values('Test....', now(), 1),
	('Test....', now(), 1),
	('Test....', now(), 1),
	('Test....', now(), 1),
	('Test....', now(), 1),
	('Test....', now(), 1),
	('Test....', now(), 1),
	('Test....', now(), 1),
	('Test....', now(), 1),
	('Test....', now(), 1);
	
	
select * from tbl_todo;	

create table tbl_member (
	mid varchar(50) primary key,
	mpw varchar(50) not null,
	mname varchar(100) not null
	uuid varchar(100)
);

insert into tbl_member(mid, mpw, mname)
	values
	('user00', '12345', '사용자00'),
	('user01', '12345', '사용자01'),
	('user02', '12345', '사용자02');

