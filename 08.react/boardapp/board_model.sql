CREATE TABLE `board`  (
  `board_number` int NOT NULL AUTO_INCREMENT COMMENT '게시글번호',
  `title` text NOT NULL COMMENT '게시글제목',
  `content` text NOT NULL COMMENT '게시글내용',
  `write_datetime` datetime NOT NULL COMMENT '게시글작성일시',
  `favorite_count` int NOT NULL DEFAULT 0 COMMENT '좋아요수',
  `comment_count` int NOT NULL DEFAULT 0 COMMENT '댓글수',
  `view_count` int NOT NULL DEFAULT 0 COMMENT '조회수',
  `writer_email` varchar(50) NULL COMMENT '작성자이메일',
  PRIMARY KEY (`board_number`)
) COMMENT = '게시글테이블';

CREATE TABLE `comment`  (
  `comment_number` int NOT NULL AUTO_INCREMENT COMMENT '댓글번호',
  `content` text NOT NULL COMMENT '댓글내용',
  `write_datetime` datetime NOT NULL COMMENT '작성일시',
  `user_email` varchar(255) NOT NULL COMMENT '사용자이메일',
  `board_number` int NOT NULL COMMENT '게시글번호',
  PRIMARY KEY (`comment_number`)
) COMMENT = '댓글테이블';

CREATE TABLE `favorite`  (
  `user_email` varchar(50) COMMENT '사용자이메일' NOT NULL,
  `board_number` int NOT NULL COMMENT '게시글번호',
  PRIMARY KEY (`user_email`, `board_number`)
) COMMENT = '좋아요테이블';

CREATE TABLE `image`  (
  `board_number` int NOT NULL AUTO_INCREMENT COMMENT '게시글번호',
  `image` text NULL COMMENT '게시글이미지URL',
  PRIMARY KEY (`board_number`)
) COMMENT = '이미지테이블';

CREATE TABLE `search_log`  (
  `sequence` int NOT NULL COMMENT '시퀀스',
  `search_word` text NOT NULL COMMENT '검색어',
  `relation_word` text NULL COMMENT '관련 검색어',
  `relation` tinyblob NULL COMMENT '관련 검색어 여부',
  PRIMARY KEY (`sequence`)
) COMMENT = '검색기록';

CREATE TABLE `user`  (
  `email` varchar(50) NOT NULL COMMENT '사용자 이메일',
  `password` varchar(100) NOT NULL COMMENT '사용자 비밀번호',
  `nickname` varchar(20) NOT NULL COMMENT '사용자 닉네임',
  `tel_number` int NOT NULL COMMENT '사용자 휴대전화번호',
  `address` text NULL COMMENT '사용자 주소',
  `address_detail` text NULL COMMENT '사용자 상세주소',
  `profile_image` text NULL COMMENT '사용자 프로필 사진',
  PRIMARY KEY (`email`),
  INDEX(`nickname`)
) COMMENT = '사용자테이블';

ALTER TABLE `board` ADD CONSTRAINT `fk_board_user_1` FOREIGN KEY (`writer_email`) REFERENCES `user` (`email`);
ALTER TABLE `comment` ADD CONSTRAINT `fk_comment_board_1` FOREIGN KEY (`board_number`) REFERENCES `board` (`board_number`);
ALTER TABLE `comment` ADD CONSTRAINT `fk_comment_user_1` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`);
ALTER TABLE `favorite` ADD FOREIGN KEY (`user_email`) REFERENCES `user` (`email`);
ALTER TABLE `favorite` ADD CONSTRAINT `fk_favorite_board_1` FOREIGN KEY (`board_number`) REFERENCES `board` (`board_number`);
ALTER TABLE `image` ADD CONSTRAINT `fk_image_board_1` FOREIGN KEY (`board_number`) REFERENCES `board` (`board_number`);