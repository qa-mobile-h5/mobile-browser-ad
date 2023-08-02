package com.group.chat.entity;

public class ServiceResult<T> {
    private T result;
    private int err_code;
    private String err_msg;

    public void setResult(T result) {
        this.result = result;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public T getResult() {
        return result;
    }
// 构造函数和getter/setter方法省略，可以根据需要自行添加
}
