/*
package org.devTayu.busTayu.database

import androidx.lifecycle.LiveData
import androidx.room.*
import org.devTayu.busTayu.model.LikedDB

@Dao
interface LikedDAO {
    @Insert
    fun insertLiked(likedDB: LikedDB)

    @Update
    fun update(likedDB: LikedDB)

    @Delete
    fun delete(likedDB: LikedDB)

    @Query("DELETE FROM liked_table")
    fun deleteAll()

    // 즐찾에 있는 데이터 - 버튼 다시 누른 경우 DELETE 처리
    @Query("DELETE FROM liked_table WHERE  busNumber IN(:busNumber) AND stationNumber IN(:stationNumber)")
    fun deleteLiked(busNumber: String, stationNumber: String)

    // 즐찾에 있는지 확인 : WHERE = busNumber, stationNumber AND 확인
    @Query("SELECT COUNT(*) FROM liked_table WHERE busNumber IN(:busNumber) AND stationNumber IN(:stationNumber)")
    fun getCountLiked(busNumber: String, stationNumber: String): Int

    // @Query("SELECT * FROM liked_table")
    // suspend fun get(): LiveData<ArrayList<LikedDB>>
    // suspend fun getAll(): LiveData<List<LikedDB>>

    @Query("SELECT * FROM liked_table")
    suspend fun getAll(): LiveData<List<LikedDB>>

}*/
