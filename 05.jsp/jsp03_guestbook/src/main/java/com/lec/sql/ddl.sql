create database guestbook

create table message(
	  id int not null auto_increment primary key
 	, guest_name varchar(50) not null
 	, password varchar(50) not null
 	, message text not null
);