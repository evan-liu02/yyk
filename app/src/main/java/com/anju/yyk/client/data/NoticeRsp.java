package com.anju.yyk.client.data;

import java.util.List;

public class NoticeRsp {
    private int status;
    private String msg;
    private List<NoticeData> data;

    public NoticeRsp() {
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

    public List<NoticeData> getData() {
        return data;
    }

    public void setData(List<NoticeData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NoticeRsp{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class NoticeData {
        private String title;
        private String time;
        private String id;
        private String channel_id;

        public NoticeData() {
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(String channel_id) {
            this.channel_id = channel_id;
        }

        @Override
        public String toString() {
            return "NoticeData{" +
                    "title='" + title + '\'' +
                    ", time='" + time + '\'' +
                    ", id='" + id + '\'' +
                    ", channel_id='" + channel_id + '\'' +
                    '}';
        }
    }
}
