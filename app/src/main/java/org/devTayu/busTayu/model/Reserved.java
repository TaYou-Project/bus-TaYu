package org.devTayu.busTayu.model;

public class Reserved {

    private String leastTime; // 시간 도착정보 메시지에서 format해서 끊어와야 함
    // private String __Time; 도착 예상 시간도 추가 필요 : leastTime + currentTime

    private String adirection; // 방면
    private String arsId;// 정류소 고유 번호
    private String rtNm; // 노선 명 : 버스 번호
    private String stNm; // 정류소 이름 : 한글
    // 도착 정보 추가 필요함

    private String arrmsgSec1; // 도착정보 메시지
    private String arrmsgSec2; // 도착정보 메시지

    public Reserved() {

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

    public String getLeastTime() {
        return leastTime;
    }

    public void setLeastTime(String leastTime) {
        this.leastTime = leastTime;
    }

    public String getAdirection() {
        return adirection;
    }

    public void setAdirection(String adirection) {
        this.adirection = adirection;
    }

    public String getArsId() {
        return arsId;
    }

    public void setArsId(String arsId) {
        this.arsId = arsId;
    }

    public String getRtNm() {
        return rtNm;
    }

    public void setRtNm(String rtNm) {
        this.rtNm = rtNm;
    }

    public String getStNm() {
        return stNm;
    }

    public void setStNm(String stNm) {
        this.stNm = stNm;
    }

    public Reserved(String leastTime, String adirection, String arsId, String rtNm, String stNm, String arrmsgSec1, String arrmsgSec2) {
        this.leastTime = leastTime;
        this.adirection = adirection;
        this.arsId = arsId;
        this.rtNm = rtNm;
        this.stNm = stNm;
        this.arrmsgSec1 = arrmsgSec1;
        this.arrmsgSec2 = arrmsgSec2;
    }
}
