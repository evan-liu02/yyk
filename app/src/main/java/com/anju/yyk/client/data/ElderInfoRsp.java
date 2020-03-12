package com.anju.yyk.client.data;

public class ElderInfoRsp {
    private int status;
    private String msg;

    public ElderInfoRsp() {
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
        return "ElderInfoRsp{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static class ElderData {
        private String id;
        private String name;
        private String sex; // 1 男 2 女

        public ElderData() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "ElderData{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }
}
