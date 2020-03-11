package com.anju.yyk.client.data;

public class LoginRsp {
    private int status;
    private String msg;

    public LoginRsp() {
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
        return "LoginRsp{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static class LoginData {
        private String user_id;

        public LoginData() {
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        @Override
        public String toString() {
            return "LoginData{" +
                    "user_id='" + user_id + '\'' +
                    '}';
        }
    }
}
