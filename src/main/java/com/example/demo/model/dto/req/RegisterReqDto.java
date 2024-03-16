package com.example.demo.model.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterReqDto {
    @NotNull(message = "請輸入帳號")
    private String account;

    @NotNull(message = "請輸入密碼")
    private String pazzW0rd;

    @NotNull(message = "請輸入使用者名稱")
    private String userName;
}
