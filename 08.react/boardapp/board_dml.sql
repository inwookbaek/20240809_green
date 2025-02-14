create user 'developer'@'*' identified by '12345';
GRANT Alter, Create, Create View, Delete, Drop, Index, Insert, Select, Update ON board.* TO 'developer'@'*';

-- 사용자등록
insert into user values('iwbaek@gmail.com', '12345', '이누기', '01099998888', '서울 강남구', '이젠아카데미', null);
select * from user where email = 'iwbaek@gmail.com';

-- 게시글등록
insert into board (title, content, write_datetime, favorite_count, comment_count, view_count, writer_email)
values('제목입니다!','내용입니다','2025.02.14 09:10:00',0,0,0,'iwbaek@gmail.com');
select * from board;

-- image
insert image values(1, 'https://localhost:8080/board/images/sample.png');
select * from image;

-- comment
insert into comment(content, write_datetime, user_email, board_number)
values ('댓글입니다', '2025.02.14 09:10:00','iwbaek@gmail.com', 1); 
select * from comment;

update board set comment_count = comment_count + 1 where board_number = 1;

-- favority
insert into favorite values('iwbaek@gmail.com', 1); 
select * from favorite;
update board set favorite_count = favorite_count + 1 where board_number = 1;

-- board update
update board set title = '[수정]제목입니다!!', content = '[수정]게시글 수정한 내용입니다!'
 where board_number = 1;
 
-- 게시글조회
select b.board_number
     , b.title
		 , b.content
		 , b.write_datetime
		 , b.writer_email
		 , u.nickname
  from board b, user u
 where b.writer_email = u.email
   and b.board_number = 1;
	 
	 
-- 최신게시물
create view board_list_view as
select b.board_number
	   , b.title
		 , b.content
		 , i.image as title_image
		 , b.favorite_count
		 , b.comment_count
		 , b.view_count
		 , b.write_datetime
     , u.nickname
		 , u.profile_image as writer_profile_image
  from board b, user u, (select board_number, any_value(image) as image from image group by board_number) i
 where b.writer_email = u.email
   and b.board_number = i.board_number
 order by write_datetime;
 
 -- comment count
 select u.nickname
		  , u.profile_image
			, c.write_datetime
			, c.content
   from comment c, user u
  where c.user_email = u.email
	  and c.board_number = 1
  order by write_datetime desc;
	
	-- 인기검색어리스트
	select search_word
	     , count(search_word)
	  from search_log
	 where relation is false
   group by search_word;
	 
	-- 관련어리스트
	select relation_word
	     , count(relation_word) as count
	  from search_log
	 where search_word = '검색어'
   group by relation_word
	 order by count desc
	 limit 13;
	 
-- 사용자정보
select * 
  from user
 where email = 'iwbaek@gmail.com';	 
 
-- 닉네임수정
update user set nickname = '[수정]닉네임' where email = 'iwbaek@gmail.com';	

-- 프로필이미지수정
update user set profie_image = 'localhost:/image/xxx.png' where email = 'iwbaek@gmail.com';	 