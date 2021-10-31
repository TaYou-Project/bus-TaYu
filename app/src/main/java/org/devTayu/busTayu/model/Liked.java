package org.devTayu.busTayu.model;

public class Liked {

    /*기타*/
    private String rtNm; // 노선 명 : 버스 번호
    private String stNm; // 정류소 이름 : 한글
    private String stationNum; // 정류소 고유 번호

    /*공통 정보*/
    private String adirection; // 방면
    private String firstTm; // 첫차 시간
    private String lastTm; // 막차 시간
    private String nxtStn; // 다음 정류장 순번
    private String routType; // 노선유형
    private String sectNm; // 구간 명
    private String term; // 배차간격

    /*첫 번째 도착예정버스*/
    private String arrmsgSec1; // 도착정보 메시지
    private String busType1; // 버스 타입
    private String isLast1; // 막차 여부

    /*두 번째 도착예정버스*/
    private String arrmsgSec2; // 도착정보 메시지
    private String busType2; // 버스 타입
    private String isLast2; // 막차 여부

    public Liked() {
        super();
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

    public String getFirstTm() {
        return firstTm;
    }

    public void setFirstTm(String firstTm) {
        firstTm = firstTm.substring(0, 1) + "시 " + firstTm.substring(2, 3) + "분";
        this.firstTm = firstTm;
    }

    public String getLastTm() {
        return lastTm;
    }

    public void setLastTm(String lastTm) {
        lastTm = lastTm.substring(0, 1) + "시 " + lastTm.substring(2, 3) + "분";
        this.lastTm = lastTm;
    }

    public String getNxtStn() {
        return nxtStn;
    }

    public void setNxtStn(String nxtStn) {
        this.nxtStn = nxtStn;
    }

    public String getRoutType() {
        return routType;
    }

    public void setRoutType(String routType) {
        switch (routType) {
            case "0":
                routType = "공용 노선";
                break;
            case "1":
                routType = "공항 노선";
                break;
            case "2":
                routType = "마을 노선";
                break;
            case "3":
                routType = "간선 노선";
                break;
            case "4":
                routType = "지선 노선";
                break;
            case "5":
                routType = "순환 노선";
                break;
            case "6":
                routType = "광역 노선";
                break;
            case "7":
                routType = "인천 노선";
                break;
            case "8":
                routType = "경기 노선";
                break;
            case "9":
                routType = "폐지 노선";
                break;
            default:
                routType = "노선 정보 없음";
                break;
        }
        this.routType = routType;
    }

    public String getSectNm() {
        return sectNm;
    }

    public void setSectNm(String sectNm) {
        this.sectNm = sectNm;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        term = term + "분";
        this.term = term;
    }

    public String getArrmsgSec1() {
        return arrmsgSec1;
    }

    public void setArrmsgSec1(String arrmsgSec1) {
        this.arrmsgSec1 = arrmsgSec1;
    }

    public String getBusType1() {
        return busType1;
    }

    public void setBusType1(String busType1) {
        switch (busType1) {
            case "0":
                busType1 = "일반버스";
                break;
            case "1":
                busType1 = "저상버스";
                break;
            default:
                busType1 = "버스 정보 없음";
                break;
        }
        this.busType1 = busType1;
    }

    public String getIsLast1() {
        return isLast1;
    }

    public void setIsLast1(String isLast1) {
        switch (isLast1) {
            case "0":
                isLast1 = "일반";
                break;
            case "1":
                isLast1 = "막차";
                break;
            default:
                isLast1 = "버스 정보 없음";
                break;
        }
        this.isLast1 = isLast1;
    }

    public String getArrmsgSec2() {
        return arrmsgSec2;
    }

    public void setArrmsgSec2(String arrmsgSec2) {
        this.arrmsgSec2 = arrmsgSec2;
    }

    public String getBusType2() {
        return busType2;
    }

    public void setBusType2(String busType2) {
        switch (busType2) {
            case "0":
                busType2 = "일반버스";
                break;
            case "1":
                busType2 = "저상버스";
                break;
            default:
                busType2 = "버스 정보 없음";
                break;
        }
        this.busType2 = busType2;
    }

    public String getIsLast2() {
        return isLast2;
    }

    public void setIsLast2(String isLast2) {
        switch (isLast2) {
            case "0":
                isLast2 = "일반";
                break;
            case "1":
                isLast2 = "막차";
                break;
            default:
                isLast2 = "버스 정보 없음";
                break;
        }
        this.isLast2 = isLast2;
    }

    public Liked(String rtNm, String stNm, String stationNum, String adirection, String firstTm, String lastTm, String nxtStn, String routType, String sectNm, String term, String arrmsgSec1, String busType1, String isLast1, String arrmsgSec2, String busType2, String isLast2) {
        this.rtNm = rtNm;
        this.stNm = stNm;
        this.stationNum = stationNum;
        this.adirection = adirection;
        this.firstTm = firstTm;
        this.lastTm = lastTm;
        this.nxtStn = nxtStn;
        this.routType = routType;
        this.sectNm = sectNm;
        this.term = term;
        this.arrmsgSec1 = arrmsgSec1;
        this.busType1 = busType1;
        this.isLast1 = isLast1;
        this.arrmsgSec2 = arrmsgSec2;
        this.busType2 = busType2;
        this.isLast2 = isLast2;
    }
}
