package com.example.demo.controller;

import com.example.demo.model.dto.req.BorrowBookReqDto;
import com.example.demo.model.dto.req.UserBorrowingBookListReqDto;
import com.example.demo.model.dto.res.ApiResDTO;
import com.example.demo.model.vo.BorrowBookResVo;
import com.example.demo.model.vo.UserBorrowingBookListResVo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "user/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("getBorrowingRecord")
    public ApiResDTO<List<UserBorrowingBookListResVo>> getUserBorrowingRecord(UserBorrowingBookListReqDto req) throws Exception {
        List<UserBorrowingBookListResVo> res = userService.getUserBorrowingRecord(req);
        return ApiResDTO.<List<UserBorrowingBookListResVo>>builder()
                .data(res)
                .build();
    }

    @PostMapping("borrowBook")
    public ApiResDTO<BorrowBookResVo> borrowBook(BorrowBookReqDto req) throws Exception {
        userService.borrowBook(req);
        return ApiResDTO.<BorrowBookResVo>builder().build();
    }
}
