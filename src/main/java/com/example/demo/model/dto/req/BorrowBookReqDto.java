package com.example.demo.model.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BorrowBookReqDto {
    @NotNull
    private int inventoryId;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String borrowFlag;
}
