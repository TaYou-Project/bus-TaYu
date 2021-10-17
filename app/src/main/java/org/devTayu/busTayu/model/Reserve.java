package org.devTayu.busTayu.model;

public class Reserve {

    private String leastTime;
    private String busNumber;
    private String stationName;
    private String expectationTime;
    private String busInfo;

    public String getLeastTime() {
        return leastTime;
    }

    public void setLeastTime(String leastTime) {
        this.leastTime = leastTime;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getExpectationTime() {
        return expectationTime;
    }

    public void setExpectationTime(String expectationTime) {
        this.expectationTime = expectationTime;
    }

    public String getBusInfo() {
        return busInfo;
    }

    public void setBusInfo(String busInfo) {
        this.busInfo = busInfo;
    }

    public Reserve(String leastTime, String busNumber, String stationName, String expectationTime, String busInfo) {
        this.leastTime = leastTime;
        this.busNumber = busNumber;
        this.stationName = stationName;
        this.expectationTime = expectationTime;
        this.busInfo = busInfo;
    }
}
