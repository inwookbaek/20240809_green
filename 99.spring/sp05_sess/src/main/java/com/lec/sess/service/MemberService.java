package com.lec.sess.service;

import org.modelmapper.ModelMapper;

import com.lec.sess.dao.MemberDAO;
import com.lec.sess.domain.MemberVO;
import com.lec.sess.dto.MemberDTO;
import com.lec.sess.util.MapperUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService() {

        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();

    }


    public MemberDTO login(String mid, String mpw)throws Exception {

        MemberVO vo = dao.getWithPassword(mid, mpw);

        MemberDTO memberDTO = modelMapper.map(vo, MemberDTO.class);

        return memberDTO;
    }

    public void updateUuid(String mid, String uuid)throws Exception {

        dao.updateUuid(mid, uuid);

    }

    public MemberDTO getByUUID(String uuid) throws  Exception {

        MemberVO vo = dao.selectUUID(uuid);

        MemberDTO memberDTO = modelMapper.map(vo, MemberDTO.class);

        return memberDTO;
    }


}
