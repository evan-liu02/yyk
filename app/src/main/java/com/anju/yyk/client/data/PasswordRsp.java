package com.anju.yyk.client.data;

public class PasswordRsp {
    private int status;
    private String msg;

    public PasswordRsp() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "PasswordRsp{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
