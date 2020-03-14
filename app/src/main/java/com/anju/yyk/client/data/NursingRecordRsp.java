package com.anju.yyk.client.data;

import java.util.List;

public class NursingRecordRsp {
    private int status;
    private String msg;
    private List<NursingRecordData> data;

    public NursingRecordRsp() {
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

    public List<NursingRecordData> getData() {
        return data;
    }

    public void setData(List<NursingRecordData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NursingRecordRsp{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class NursingRecordData {
        private String title;
        private String time;

        public NursingRecordData() {
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
            return "NursingRecordData{" +
                    "title='" + title + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }
}
