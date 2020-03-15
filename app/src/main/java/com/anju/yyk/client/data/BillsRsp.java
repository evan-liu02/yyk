package com.anju.yyk.client.data;

public class BillsRsp {
    private int status;
    private String msg;
    private BillsData data;

    public BillsRsp() {
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

    public BillsData getData() {
        return data;
    }

    public void setData(BillsData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BillsRsp{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class BillsData {
        private String chuangwei;
        private String huli;
        private String gexing;
        private String canyin;
        private String xiyang;
        private String kongtiao;
        private String qita;
        private String richang;
        private String chuangshang;
        private String tuoyang;
        private String changhuxian;
        private String bujiao;
        private String yajin;
        private String zonge;

        public BillsData() {
        }

        public String getChuangwei() {
            return chuangwei;
        }

        public void setChuangwei(String chuangwei) {
            this.chuangwei = chuangwei;
        }

        public String getHuli() {
            return huli;
        }

        public void setHuli(String huli) {
            this.huli = huli;
        }

        public String getGexing() {
            return gexing;
        }

        public void setGexing(String gexing) {
            this.gexing = gexing;
        }

        public String getCanyin() {
            return canyin;
        }

        public void setCanyin(String canyin) {
            this.canyin = canyin;
        }

        public String getXiyang() {
            return xiyang;
        }

        public void setXiyang(String xiyang) {
            this.xiyang = xiyang;
        }

        public String getKongtiao() {
            return kongtiao;
        }

        public void setKongtiao(String kongtiao) {
            this.kongtiao = kongtiao;
        }

        public String getQita() {
            return qita;
        }

        public void setQita(String qita) {
            this.qita = qita;
        }

        public String getRichang() {
            return richang;
        }

        public void setRichang(String richang) {
            this.richang = richang;
        }

        public String getChuangshang() {
            return chuangshang;
        }

        public void setChuangshang(String chuangshang) {
            this.chuangshang = chuangshang;
        }

        public String getTuoyang() {
            return tuoyang;
        }

        public void setTuoyang(String tuoyang) {
            this.tuoyang = tuoyang;
        }

        public String getChanghuxian() {
            return changhuxian;
        }

        public void setChanghuxian(String changhuxian) {
            this.changhuxian = changhuxian;
        }

        public String getBujiao() {
            return bujiao;
        }

        public void setBujiao(String bujiao) {
            this.bujiao = bujiao;
        }

        public String getYajin() {
            return yajin;
        }

        public void setYajin(String yajin) {
            this.yajin = yajin;
        }

        public String getZonge() {
            return zonge;
        }

        public void setZonge(String zonge) {
            this.zonge = zonge;
        }

        @Override
        public String toString() {
            return "BillsData{" +
                    "chuangwei='" + chuangwei + '\'' +
                    ", huli='" + huli + '\'' +
                    ", gexing='" + gexing + '\'' +
                    ", canyin='" + canyin + '\'' +
                    ", xiyang='" + xiyang + '\'' +
                    ", kongtiao='" + kongtiao + '\'' +
                    ", qita='" + qita + '\'' +
                    ", richang='" + richang + '\'' +
                    ", chuangshang='" + chuangshang + '\'' +
                    ", tuoyang='" + tuoyang + '\'' +
                    ", changhuxian='" + changhuxian + '\'' +
                    ", bujiao='" + bujiao + '\'' +
                    ", yajin='" + yajin + '\'' +
                    ", zonge='" + zonge + '\'' +
                    '}';
        }
    }
}
