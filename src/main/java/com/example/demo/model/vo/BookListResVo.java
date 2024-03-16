package com.example.demo.model.vo;

import lombok.Data;

@Data
public class BookListResVo {
    private int inventoryId;
    private String ISBN;
    private String bookName;
    private String bookAuthor;
    private String introduction;
    private int status;
}
