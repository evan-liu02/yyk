package com.anju.yyk.client.data;

import java.util.List;

public class TipsRsp {
    private int status;
    private String msg;
    private List<TipsData> data;

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

    public List<TipsData> getData() {
        return data;
    }

    public void setData(List<TipsData> data) {
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

        public TipsData() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "TipsData{" +
                    "title='" + title + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }
}
