package com.woniu.k15.result;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse<T> {
    private int code ;
    private String msg ;
    private T data ;
    private String token ;

    public ResultResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
