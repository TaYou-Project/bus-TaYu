package org.devTayu.busTayu.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.devTayu.busTayu.model.ReservedDB;

import java.util.List;

@Dao
public interface ReservedDAO {

    // state 칼럼 : R(예약하면 디폴트로 들어감), D(예약취소-사용자), Y(탑승-버스기사), N(미탑승-버스기사), Z(기사님이 잊었거나, 기타 다른 이유)
    @Insert
    void insert(ReservedDB reservedDB);

    @Update
    void update(ReservedDB reservedDB);

    @Delete
    void delete(ReservedDB reservedDB);

    @Query("DELETE FROM reserved_table")
    void deleteAll();

    // 예약 삭제 : update 처리 : D로 변경
    // update state='D'처리 해당 버스번호,정류소 번호를 가지며 최신 uid 값을 가진 행 하나만
    // state 칼럼 : R(예약하면 디폴트로 들어감), D(예약취소-사용자), Y(탑승-버스기사), N(미탑승-버스기사), Z(기사님이 잊었거나, 기타 다른 이유)
    @Query("UPDATE RESERVED_TABLE SET state = 'D' WHERE uid IN (SELECT uid FROM RESERVED_TABLE WHERE busNumber IN(:busNumber) AND stationNumber IN(:stationNumber) ORDER BY uid DESC LIMIT 1)")
    void updateReserved(String busNumber, String stationNumber);

    // 아래 실수한 쿼리 : where 조건 칼럼 하나 (같은 버스,정류소)를 구분할 수 있는 걸로 하나 잡아서 넣어줬어야 함
    // @Query("UPDATE RESERVED_TABLE SET state = 'D' WHERE (SELECT state FROM reserved_table WHERE busNumber IN(:busNumber) AND stationNumber IN(:stationNumber) ORDER BY uid DESC LIMIT 1)")


    // 예약에 있는지 확인 : WHERE = busNumber, stationNumber AND 확인 - 다시 예약 눌렀을 때 이미 예약된 버스입니다 알림
    @Query("SELECT COUNT(*) FROM reserved_table WHERE busNumber IN(:busNumber) AND stationNumber IN(:stationNumber) AND state='R'")
    Integer getCountReservedBus(String busNumber, String stationNumber);

    // 살아있는 예약 몇개인지 확인 - 문제없으면 1,0 이어야 함 - 다시 예약 눌렀을 때 예약 중인 버스가 있습니다 알림
    @Query("SELECT COUNT(*) FROM reserved_table WHERE state='R'")
    Integer getCountReserved();

    @Query("SELECT * FROM reserved_table WHERE state='R' ORDER BY uid DESC")
    LiveData<List<ReservedDB>> getAll(); //LiveData
}
