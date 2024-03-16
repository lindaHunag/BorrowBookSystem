package com.example.demo.service;

import com.example.demo.model.dto.req.LoginReqDto;
import com.example.demo.model.dto.req.RegisterReqDto;
import com.example.demo.model.vo.LoginResVo;

public interface LoginService {

    LoginResVo login(LoginReqDto req) throws Exception;
    void register(RegisterReqDto req) throws Exception;
}
