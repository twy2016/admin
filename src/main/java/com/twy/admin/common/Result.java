package com.twy.admin.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gongpeng
 * @date 2021/5/10 10:45
 */
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private Object data;

    public static Result ok(Object data) {
        return ok(200, "操作成功", data);
    }

    public static Result ok(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
