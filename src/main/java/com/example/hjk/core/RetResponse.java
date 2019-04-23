package com.example.hjk.core;

/**
 * @author linmr
 * @date 2018/11/2 9:39
 */
public class RetResponse {

    private final static String SUCCESS = "success";

    public static <T> RetResult<T> success() {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS);
    }

    public static <T> RetResult<T> success(T data) {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> RetResult<T> error(String message) {
        return new RetResult<T>().setCode(RetCode.FAIL).setMsg(SUCCESS);
    }

    public static <T> RetResult<T> success(int code, String msg) {
        return new RetResult<T>().setCode(code).setMsg(msg);
    }

    public static <T> RetResult<T> success(int code, String msg, T data) {
        return new RetResult<T>().setCode(code).setMsg(msg).setData(data);
    }
}
