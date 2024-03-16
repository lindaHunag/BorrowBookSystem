package com.example.demo.controller;

import com.example.demo.model.dto.req.LoginReqDto;
import com.example.demo.model.dto.req.RegisterReqDto;
import com.example.demo.model.dto.res.ApiResDTO;
import com.example.demo.model.vo.LoginResVo;
import com.example.demo.model.vo.RegisterResVo;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("login")
    public ApiResDTO<LoginResVo> login(LoginReqDto req) throws Exception {
        LoginResVo resultVo = loginService.login(req);
        ApiResDTO<LoginResVo> res = ApiResDTO.<LoginResVo>builder()
                .data(resultVo)
                .build();
        return res;
    }

    @PostMapping("register")
    public ApiResDTO<RegisterResVo> register(RegisterReqDto req) throws Exception {
        loginService.register(req);
        ApiResDTO<RegisterResVo> res = ApiResDTO.<RegisterResVo>builder().build();
        return res;
    }

}
