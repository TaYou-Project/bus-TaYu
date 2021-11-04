package org.devTayu.busTayu.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reserved_table")
public class ReservedDB {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String busNumber;
    private String stationNumber;
    private String reservedDate;

    public ReservedDB() {

    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(String reservedDate) {
        this.reservedDate = reservedDate;
    }

    public ReservedDB(String busNumber, String stationNumber) {
        this.busNumber = busNumber;
        this.stationNumber = stationNumber;
    }

   /*
   @Override
    public String toString() {
        return "\n busNumber=> " + this.busNumber + " , stationNumber=> " + this.stationNumber;
    }
    */
}
