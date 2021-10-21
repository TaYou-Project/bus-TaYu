package org.devTayu.busTayu.model;

public class Liked {

    private String rtNm; // 노선 명 : 버스 번호
    private String adirection; // 방면
    private String arrmsgSec1; // 첫번째 도착예정버스의 도착정보메시지
    private String arrmsgSec2; // 두번째 도착예정버스의 도착정보메시지
    private String stationNum; // 정류소 고유 번호
    private String stNm; // 정류소 이름 : 한글

    public Liked() {
        super();
    }

    public String getStNm() {
        return stNm;
    }

    public void setStNm(String stNm) {
        this.stNm = stNm;
    }

    public void setRtNm(String rtNm) {
        this.rtNm = rtNm;
    }

    public String getRtNm() {
        return rtNm;
    }

    public String getAdirection() {
        return adirection;
    }

    public void setAdirection(String adirection) {
        this.adirection = adirection;
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

    public String getStationNum() {
        return stationNum;
    }

    public void setStationNum(String stationNum) {
        this.stationNum = stationNum;
    }

    public Liked(String rtNm, String adirection, String arrmsgSec1, String arrmsgSec2, String stationNum, String stNm) {
        this.rtNm = rtNm;
        this.adirection = adirection;
        this.arrmsgSec1 = arrmsgSec1;
        this.arrmsgSec2 = arrmsgSec2;
        this.stationNum = stationNum;
        this.stNm = stNm;
    }
}
