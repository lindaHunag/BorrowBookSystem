package com.example.demo.service;

import com.example.demo.model.vo.BookListResVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookListResVo> getBookList() throws Exception;
}
