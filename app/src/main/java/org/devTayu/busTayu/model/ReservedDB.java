package org.devTayu.busTayu.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reserved_table")
public class ReservedDB {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String busNumber;
    private String stationNumber;
    /*private String reservedDate;*/
    private String state;
    // state 칼럼 : R(예약하면 디폴트로 들어감), D(예약취소-사용자), Y(탑승-버스기사), N(미탑승-버스기사), Z(기사님이 잊었거나-> 일정시간 지나면 Z로 변경처리 (추가예정), 기타 다른 이유)
    /*
    state >
    예약 : R
    예약 취소 : C
    탑승 : Y
    미탑승 : N
    기타 : Z
    */

    /*private String division;*/
    // division 칼럼 : 1(첫 번째 도착 예정 버스), 2(두 번째 도착 예정 버스)
    /*
        처음에는 division으로 구분하려고 했음
        문제 발생 : 예약 시에 두 번째 도착 예정 버스 였다가 화면에 정보 뿌려주려고 갔을 때
        예약 할 때 첫 번째 버스였던 게 지나가서 예약한 버스가 순서상 첫 번째 도착 예정으로 바뀐경우
        계속 도착하지 않는 두 번째 버스의 정보만을 보여지게 되는 상태 발생

        해결 : 고민 중 - 시간을 포함시켜서 같이 저장을 해서 대략적으로 되었다 싶으면 순번을 넘겨서 가져오게 할지,
        버스를 좀더 구체적으로 구분할 수 있는 (버스 번호,순번 등등)을 이용해서 해당 버스가 예약한 버스가 맞는지 구분을 다시 시켜서 예약에 뿌려줄 때 그 정보를 가져오도록 할지
        - 추가 정보가 있다면, 두 번째가 괜찮은 듯
        - 두 번째로 가면, 칼럼 필요없을 듯

        현재 상태 : 예약은 첫 번째, 두 번째 구분하지만, 뿌려줄 때 무조건 첫 번째 버스 정보만을 가져오게 함
    */


    public ReservedDB() {

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

/*    public String getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(String reservedDate) {
        this.reservedDate = reservedDate;
    }*/

    public ReservedDB(String busNumber, String stationNumber, String state) {
        this.busNumber = busNumber;
        this.stationNumber = stationNumber;
        this.state = state;
    }

   /*
   @Override
    public String toString() {
        return "\n busNumber=> " + this.busNumber + " , stationNumber=> " + this.stationNumber;
    }
    */
}
