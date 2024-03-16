package com.example.demo.model.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class UserBorrowingBookListReqDto {
    @NotNull
    private String phoneNumber;
}
