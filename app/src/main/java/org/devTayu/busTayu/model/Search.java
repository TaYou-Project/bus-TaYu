package org.devTayu.busTayu.model;

public class Search {
    public String stop_nm;
    public String stop_no;
    // 파베에 올린 데이터에 방면이 없어서 생각 필요
    //public String stationWay;
    public String xcode;
    public String ycode;

    public Search() {

    }

    public Search(String stop_nm, String stop_no, String xcode, String ycode) {
        this.stop_nm = stop_nm;
        this.stop_no = stop_no;
        this.xcode = xcode;
        this.ycode = ycode;
    }

    public String getStop_nm() {
        return stop_nm;
    }

    public void setStop_nm(String stop_nm) {
        this.stop_nm = stop_nm;
    }

    public String getStop_no() {
        return stop_no;
    }

    public void setStop_no(String stop_no) {
        this.stop_no = stop_no;
    }

    public String getXcode() {
        return xcode;
    }

    public void setXcode(String xcode) {
        this.xcode = xcode;
    }

    public String getYcode() {
        return ycode;
    }

    public void setYcode(String ycode) {
        this.ycode = ycode;
    }
}
