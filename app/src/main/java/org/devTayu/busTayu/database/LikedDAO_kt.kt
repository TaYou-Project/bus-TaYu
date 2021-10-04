package org.devTayu.busTayu.database

import androidx.room.*
import org.devTayu.busTayu.model.LikedDB_kt

@Dao
interface LikedDAO_kt {
    @Insert
    fun insert(user: LikedDB_kt)

    @Update
    fun update(user: LikedDB_kt)

    @Delete
    fun delete(user: LikedDB_kt)

    @Query("DELETE FROM likedTable")
    fun deleteAll()

    @Query("INSERT INTO likedTable(busNumber, stationNumber) VALUES (:busNum, :stationNum)")
    fun insertItem(busNum: String, stationNum: String)

    @Query("SELECT * FROM likedTable") // 테이블의 모든 값을 가져와라
    fun getAll(): List<LikedDB_kt>

    @Query("DELETE FROM likedTable WHERE busNumber = :busNum") // 'name'에 해당하는 유저를 삭제해라
    fun deleteUserByName(busNum: String)
}