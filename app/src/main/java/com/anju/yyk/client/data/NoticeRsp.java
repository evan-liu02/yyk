package com.anju.yyk.client.data;

import android.os.Parcel;
import android.os.Parcelable;

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

    public static class NoticeData implements Parcelable {
        private String title;
        private String time;
        private String id;
        private String channel_id;

        public NoticeData() {
        }

        protected NoticeData(Parcel in) {
            title = in.readString();
            time = in.readString();
            id = in.readString();
            channel_id = in.readString();
        }

        public static final Creator<NoticeData> CREATOR = new Creator<NoticeData>() {
            @Override
            public NoticeData createFromParcel(Parcel in) {
                return new NoticeData(in);
            }

            @Override
            public NoticeData[] newArray(int size) {
                return new NoticeData[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(title);
            parcel.writeString(time);
            parcel.writeString(id);
            parcel.writeString(channel_id);
        }
    }
}
