package com.anju.yyk.client.data;

import java.util.List;

public class CheckingRecordRsp {
    private int status;
    private String msg;
    private List<CheckingRecordData> list;

    public CheckingRecordRsp() {
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

    public List<CheckingRecordData> getList() {
        return list;
    }

    public void setList(List<CheckingRecordData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "CheckingRecordRsp{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", list=" + list +
                '}';
    }

    public static class CheckingRecordData {
        private String content;
        private String time;
        private List<RecordsData> records;

        public CheckingRecordData() {
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<RecordsData> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsData> records) {
            this.records = records;
        }

        @Override
        public String toString() {
            return "CheckingRecordData{" +
                    "content='" + content + '\'' +
                    ", time='" + time + '\'' +
                    ", records=" + records +
                    '}';
        }
    }

    public static class RecordsData {
        private String title;
        private String xiangqing;

        public RecordsData() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getXiangqing() {
            return xiangqing;
        }

        public void setXiangqing(String xiangqing) {
            this.xiangqing = xiangqing;
        }

        @Override
        public String toString() {
            return "CheckingRecordsData{" +
                    "title='" + title + '\'' +
                    ", xiangqing='" + xiangqing + '\'' +
                    '}';
        }
    }
}
