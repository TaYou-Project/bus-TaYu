package org.devTayu.busTayu.model;

public class Liked {
    public String stationName;
    public String stationNum;
    public String adirection;
    public String rtNm;
    public String arrmsgSec1;
    public String arrmsgSec2;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationNum() {
        return stationNum;
    }

    public void setStationNum(String stationNum) {
        this.stationNum = stationNum;
    }

    public String getAdirection() {
        return adirection;
    }

    public void setAdirection(String adirection) {
        this.adirection = adirection;
    }

    public String getRtNm() {
        return rtNm;
    }

    public void setRtNm(String rtNm) {
        this.rtNm = rtNm;
    }

    public String getArrmsgSec1() {
        return arrmsgSec1;
    }

    public void setArrmsgSec1(String arrmsgSec1) {
        this.arrmsgSec1 = arrmsgSec1;
    }

    public String getArrmsgSec2() {
        return arrmsgSec2;
    }

    public void setArrmsgSec2(String arrmsgSec2) {
        this.arrmsgSec2 = arrmsgSec2;
    }

    public Liked(String stationName, String stationNum, String adirection, String rtNm, String arrmsgSec1, String arrmsgSec2) {
        this.stationName = stationName;
        this.stationNum = stationNum;
        this.adirection = adirection;
        this.rtNm = rtNm;
        this.arrmsgSec1 = arrmsgSec1;
        this.arrmsgSec2 = arrmsgSec2;
    }
}
