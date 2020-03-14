package com.anju.yyk.client.data;

public class FeedbackRsp {
    private int status;
    private String msg;

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

    public FeedbackRsp() {
    }

    @Override
    public String toString() {
        return "FeedbackRsp{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
