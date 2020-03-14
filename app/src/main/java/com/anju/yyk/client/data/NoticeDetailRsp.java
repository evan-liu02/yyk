package com.anju.yyk.client.data;

public class NoticeDetailRsp {
    private int status;
    private String msg;
    private NoticeDetailData data;

    public NoticeDetailRsp() {
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

    public NoticeDetailData getData() {
        return data;
    }

    public void setData(NoticeDetailData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NoticeDetailRsp{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class NoticeDetailData {
        private String title;
        private String time;
        private String content;

        public NoticeDetailData() {
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "NoticeDetailData{" +
                    "title='" + title + '\'' +
                    ", time='" + time + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
