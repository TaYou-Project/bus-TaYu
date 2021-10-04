package org.devTayu.busTayu.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import org.devTayu.busTayu.model.LikedDB_kt

// version은 앱을 업데이트하다가 entity의 구조를 변경해야 하는 일이 생겼을 때 이전 구조와 현재 구조를 구분해주는 역할
/*
* 구조가 바뀌었는데 버전이 같다면 에러가 뜨며 디버깅이 되지 않는다.
처음 데이터베이스를 생성하는 상황이라면 그냥 1을 넣어주면 된다.
*/
@Database(entities = [LikedDB_kt::class], version = 1, exportSchema = false)
abstract class LikedDatabase_kt: RoomDatabase() {
    abstract fun likedDAO(): LikedDAO_kt

    companion object{
        private var INSTANCE: LikedDatabase_kt? = null
        fun getInstance(context: Context): LikedDatabase_kt{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                        context,
                        LikedDatabase_kt::class.java,
                        "liked_database")
                        .build()
            }
            return INSTANCE as LikedDatabase_kt
        }
    }

    private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            Thread {
                val dao: LikedDAO_kt? = likedDAO()
                dao?.deleteAll()
                //데이터 채우기

                dao?.insertItem("1111111","123456")

            }.start()
        }
    }
}