package com.lec.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// controller에서 요청을 보내기 위해 사용할 SignInDto를 작성
// insert into member (member_id, address, nickname, password, phone, username) values (1, '서울시 강남구', '백사장', '12345', '010-1234-5678', 'admin');
// insert into member_roles (member_member_id, roles) values (1, 'USER');
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private String username;
    private String password;
}