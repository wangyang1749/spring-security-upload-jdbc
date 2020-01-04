package com.wangyang.pojo;

@Deprecated
public class Result<T> {
    private int code;
    private String msg;
    private T data;


//    public static <T> Result<T> ok(T data) {
//        int okCode = 200;
//        return new Result<>(okCode, "请求成功", data);
//    }
}
