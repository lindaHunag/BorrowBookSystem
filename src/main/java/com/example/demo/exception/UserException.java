package com.example.demo.exception;

import com.example.demo.enums.StatusCodeEnum;
import lombok.Getter;
import lombok.Setter;

public class UserException extends Exception{
    public UserException(StatusCodeEnum status) {
        super(status.getMessage());
        this.status = status;
    }

    public UserException(StatusCodeEnum status, String message) {
        super(status.getMessage());
        this.status = status;
        this.message = message;
    }

    @Setter
    @Getter
    private StatusCodeEnum status;

    @Setter
    @Getter
    private String message;
}
