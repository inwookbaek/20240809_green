/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80041
 Source Host           : localhost:3306
 Source Schema         : boarddb

 Target Server Type    : MySQL
 Target Server Version : 80041
 File Encoding         : 65001

 Date: 25/02/2025 14:52:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
use boarddb;

-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board`  (
  `board_number` int NOT NULL AUTO_INCREMENT COMMENT '게시글번호',
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '게시글제목',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '게시글내용',
  `write_datetime` datetime NOT NULL COMMENT '게시글작성일시',
  `favorite_count` int NOT NULL DEFAULT 0 COMMENT '좋아요수',
  `comment_count` int NOT NULL DEFAULT 0 COMMENT '댓글수',
  `view_count` int NOT NULL DEFAULT 0 COMMENT '조회수',
  `writer_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '작성자이메일',
  PRIMARY KEY (`board_number`) USING BTREE,
  INDEX `fk_board_user_1`(`writer_email`) USING BTREE,
  CONSTRAINT `fk_board_user_1` FOREIGN KEY (`writer_email`) REFERENCES `user` (`email`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '게시글테이블' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of board
-- ----------------------------
INSERT INTO `board` VALUES (1, '[수정]제목입니다!!', '[수정]게시글 수정한 내용입니다!', '2025-02-14 09:10:00', 1, 1, 0, 'iwbaek@gmail.com');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_number` int NOT NULL AUTO_INCREMENT COMMENT '댓글번호',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '댓글내용',
  `write_datetime` datetime NOT NULL COMMENT '작성일시',
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '사용자이메일',
  `board_number` int NOT NULL COMMENT '게시글번호',
  PRIMARY KEY (`comment_number`) USING BTREE,
  INDEX `fk_comment_board_1`(`board_number`) USING BTREE,
  INDEX `fk_comment_user_1`(`user_email`) USING BTREE,
  CONSTRAINT `fk_comment_board_1` FOREIGN KEY (`board_number`) REFERENCES `board` (`board_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_comment_user_1` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '댓글테이블' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '댓글입니다', '2025-02-14 09:10:00', 'iwbaek@gmail.com', 1);

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `user_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '사용자이메일',
  `board_number` int NOT NULL COMMENT '게시글번호',
  PRIMARY KEY (`user_email`, `board_number`) USING BTREE,
  INDEX `fk_favorite_board_1`(`board_number`) USING BTREE,
  CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_favorite_board_1` FOREIGN KEY (`board_number`) REFERENCES `board` (`board_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '좋아요테이블' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES ('iwbaek@gmail.com', 1);

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`  (
  `sequence` int NOT NULL AUTO_INCREMENT COMMENT '이미지번호',
  `image` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '게시글이미지URL',
  `board_number` int NOT NULL COMMENT '게시글번호',
  PRIMARY KEY (`sequence`) USING BTREE,
  INDEX `fk_image_board`(`board_number`) USING BTREE,
  CONSTRAINT `fk_image_board` FOREIGN KEY (`board_number`) REFERENCES `board` (`board_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '이미지테이블' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES (2, 'https://localhost:8080/board/images/sample.png', 1);

-- ----------------------------
-- Table structure for search_log
-- ----------------------------
DROP TABLE IF EXISTS `search_log`;
CREATE TABLE `search_log`  (
  `sequence` int NOT NULL COMMENT '시퀀스',
  `search_word` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '검색어',
  `relation_word` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '관련 검색어',
  `relation` tinyblob NULL COMMENT '관련 검색어 여부',
  PRIMARY KEY (`sequence`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '검색기록' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of search_log
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '사용자 이메일',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '사용자 비밀번호',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '사용자 닉네임',
  `tel_number` int NOT NULL COMMENT '사용자 휴대전화번호',
  `address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '사용자 주소',
  `address_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '사용자 상세주소',
  `profile_image` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '사용자 프로필 사진',
  PRIMARY KEY (`email`) USING BTREE,
  INDEX `nickname`(`nickname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '사용자테이블' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('iwbaek@gmail.com', '12345', '[수정]닉네임', 1099998888, '서울 강남구', '이젠아카데미', NULL);

-- ----------------------------
-- View structure for board_list_view
-- ----------------------------
DROP VIEW IF EXISTS `board_list_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `board_list_view` AS select `b`.`board_number` AS `board_number`,`b`.`title` AS `title`,`b`.`content` AS `content`,`i`.`image` AS `title_image`,`b`.`favorite_count` AS `favorite_count`,`b`.`comment_count` AS `comment_count`,`b`.`view_count` AS `view_count`,`b`.`write_datetime` AS `write_datetime`,`u`.`nickname` AS `nickname`,`u`.`profile_image` AS `writer_profile_image` from ((`board` `b` join `user` `u`) join (select `image`.`board_number` AS `board_number`,any_value(`image`.`image`) AS `image` from `image` group by `image`.`board_number`) `i`) where ((`b`.`writer_email` = `u`.`email`) and (`b`.`board_number` = `i`.`board_number`)) order by `b`.`write_datetime`;

SET FOREIGN_KEY_CHECKS = 1;
