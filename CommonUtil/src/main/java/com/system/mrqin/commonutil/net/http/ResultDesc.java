package com.system.mrqin.commonutil.net.http;

import java.io.Serializable;

/**
 * @Description 接口返回数据封装
 * @Author 一花一世界
 */

public class ResultDesc implements Serializable {

    private int code;
    private String message ;
    private String data ;

    public ResultDesc(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultDesc{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
