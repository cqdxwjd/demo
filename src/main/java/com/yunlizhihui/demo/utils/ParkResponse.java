package com.yunlizhihui.demo.utils;

public class ParkResponse {
    int code;
    String msg;

    public ParkResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
