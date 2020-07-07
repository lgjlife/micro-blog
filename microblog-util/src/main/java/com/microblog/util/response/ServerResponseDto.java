package com.microblog.util.response;

import lombok.Data;

@Data
public class ServerResponseDto<T> {

    private int code;
    private T data;
    private String msg;

    public ServerResponseDto() {
    }

    public ServerResponseDto(int code) {
        this.code = code;
    }

    public ServerResponseDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServerResponseDto(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
