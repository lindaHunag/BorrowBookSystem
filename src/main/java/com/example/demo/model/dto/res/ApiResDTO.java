package com.example.demo.model.dto.res;

import com.example.demo.enums.StatusCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "innerBuilder")
public class ApiResDTO<T> {

    // 預設值
    @Builder.Default
    private String rtCode = "0000";
    @Builder.Default
    private String rtMsg = "Success";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private transient T data;

    public static <T> ApiResDTOBuilder<T> builder() {
        return innerBuilder();
    }

    public static <T> ApiResDTOBuilder<T> builder(StatusCodeEnum statusCode) {
        return (ApiResDTOBuilder<T>) innerBuilder().rtCode(statusCode.getKey()).rtMsg(statusCode.getMessage());
    }

    public static <T> ApiResDTOBuilder<T> builder(StatusCodeEnum statusCode, String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(statusCode.getMessage());
        if (msg != null) {
            sb.append(" (");
            sb.append(msg);
            sb.append(")");
        }
        return (ApiResDTOBuilder<T>) innerBuilder().rtCode(statusCode.getKey()).rtMsg(sb.toString());
    }

}
