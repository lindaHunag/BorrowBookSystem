package com.example.demo.model.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserBorrowingBookListResVo {
    private String bookName;
    private Timestamp borrowTime;
    private Timestamp returnTime;
}
