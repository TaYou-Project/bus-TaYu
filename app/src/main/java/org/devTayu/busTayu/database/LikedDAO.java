package org.devTayu.busTayu.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.devTayu.busTayu.model.Liked;
import org.devTayu.busTayu.model.LikedDB;

import java.util.List;

@Dao
public interface LikedDAO {
    @Insert
    void insert(LikedDB likedDB);

    @Update
    void update(LikedDB likedDB);

    @Delete
    void delete(LikedDB likedDB);

    @Query("SELECT * FROM TB_Liked")
    LiveData<List<LikedDB>> getAll(); //LiveData
    /*
    List<Memo> getAll(); 메서드를 LiveData로 감싸주어, Observer로 변화를 감지할 수 있습니다.
    그래서 전체 데이터에 변화가 생길때 LiveData Callback을 실행하여 UI를 업데이트 해주면 됩니다
    */

    @Query("DELETE FROM TB_Liked")
    void deleteAll();
