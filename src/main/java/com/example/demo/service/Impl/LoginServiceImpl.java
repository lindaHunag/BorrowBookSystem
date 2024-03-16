package com.example.demo.service.Impl;

import com.example.demo.enums.StatusCodeEnum;
import com.example.demo.exception.UserException;
import com.example.demo.model.dto.req.LoginReqDto;
import com.example.demo.model.dto.req.RegisterReqDto;
import com.example.demo.model.entity.User;
import com.example.demo.model.vo.LoginResVo;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoginService;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.Utility;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.UUID;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    Utility util;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Override
    public LoginResVo login(LoginReqDto req) throws Exception {
        try {
            String token = "";
            User user = userRepository.findByPhoneNumberAndPazzW0rd(req.getAccount(), util.encryptPazzW0rdWithSalt(req.getPazzWord()));
            if (user != null) {
                token = jwtUtil.addAuthentication(req);
                user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
                userRepository.save(user);
                System.out.println("token");

            } else {
                throw new UserException(StatusCodeEnum.LoginNoUserError);
            }
            LoginResVo res = new LoginResVo();
            res.setToken(token);

            return res;
        } catch (Exception e) {
            throw new UserException(StatusCodeEnum.LoginError);
        }
    }

    @Override
    public void register(RegisterReqDto req) throws Exception {
        if (userRepository.findByPhoneNumber(req.getAccount()) == null) {
            User user = new User();
            user.setPhoneNumber(req.getAccount());
            user.setPazzW0rd(util.encryptPazzW0rdWithSalt(req.getPazzW0rd()));
            user.setUserName(req.getUserName());
            user.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
        } else {
            throw new UserException(StatusCodeEnum.RegisterError);
        }

    }

}
