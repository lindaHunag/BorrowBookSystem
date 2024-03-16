package com.example.demo.controller;

import com.example.demo.model.dto.res.ApiResDTO;
import com.example.demo.model.vo.BookListResVo;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "book/")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("getBookList")
    public ApiResDTO<List<BookListResVo>> getBookList() throws Exception {
        List<BookListResVo> result = bookService.getBookList();
        return ApiResDTO.<List<BookListResVo>>builder()
                .data(result)
                .build();
    }

}
