package com.xiu.exception;

/**
 * @ClassName AServiceException
 * @Desc 自定异常B
 * @Author xieqx
 * @Date 2020/11/24 16:34
 **/
public class BServiceException extends Exception {
    private String code;
    private String msg;

    public BServiceException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
