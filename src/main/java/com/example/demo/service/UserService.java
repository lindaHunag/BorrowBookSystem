package com.example.demo.service;

import com.example.demo.model.dto.req.BorrowBookReqDto;
import com.example.demo.model.dto.req.UserBorrowingBookListReqDto;
import com.example.demo.model.vo.UserBorrowingBookListResVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserBorrowingBookListResVo> getUserBorrowingRecord(UserBorrowingBookListReqDto req) throws Exception;
    void borrowBook(BorrowBookReqDto req) throws Exception;
}
