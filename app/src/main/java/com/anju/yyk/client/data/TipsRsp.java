package com.anju.yyk.client.data;

public class TipsRsp {
    private int status;
    private String msg;
    private TipsData data;

    public TipsRsp() {
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

    public TipsData getData() {
        return data;
    }

    public void setData(TipsData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TipsRsp{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class TipsData {
        private String title;
        private String time;
    }
}
