package com.lec.kakao.service;

import com.lec.kakao.dto.MemberJoinDTO;

public interface MemberService {
    static class MidExistException extends Exception {}
    void join(MemberJoinDTO memberJoinDTO)throws MidExistException ;

}
