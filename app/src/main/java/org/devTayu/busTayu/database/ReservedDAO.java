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
    @Insert
    void insert(ReservedDB reservedDB);

    @Update
    void update(ReservedDB reservedDB);

    @Delete
    void delete(ReservedDB reservedDB);

    @Query("DELETE FROM reserved_table")
    void deleteAll();

    // 예약 삭제
    @Query("DELETE FROM reserved_table WHERE  busNumber IN(:busNumber) AND stationNumber IN(:stationNumber)")
    void insertReserved(String busNumber, String stationNumber);

    // 예약에 있는지 확인 : WHERE = busNumber, stationNumber AND 확인 - 다시 예약 눌렀을 때 이미 예약된 버스입니다 알림
    @Query("SELECT COUNT(*) FROM reserved_table WHERE busNumber IN(:busNumber) AND stationNumber IN(:stationNumber)")
    Integer getCountReserved(String busNumber, String stationNumber);

    @Query("SELECT * FROM reserved_table")
    LiveData<List<ReservedDB>> getAll(); //LiveData
}
