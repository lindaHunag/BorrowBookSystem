package com.example.demo.util;

import com.example.demo.model.dto.req.LoginReqDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.google.gson.Gson;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class JwtUtil {
    @Autowired
    UserRepository userRepository;

    static final Key key = MacProvider.generateKey(); // 給定一組密鑰，用來解密和加密使用

    // jwt產生方法
    public String addAuthentication(LoginReqDto req) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        Gson gson = new Gson();
        User user = userRepository.findByPhoneNumber(req.getAccount());
        User newUser = new User();
        newUser.setUserId(user.getUserId());
        String tokenData = gson.toJson(newUser);

        byte[] encryptBytes = cipher.doFinal(tokenData.getBytes());
        System.out.println(Base64.getEncoder().encodeToString(encryptBytes));
        return Base64.getEncoder().encodeToString(encryptBytes);
    }


}
