/*
package org.devTayu.busTayu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBOpenHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // DB 처음 생성 시 호출 : 테이블 생성, 초기 처리
        @Override
        public void onCreate(SQLiteDatabase db) {

            // liked_TB 즐겨찾기 테이블
            db.execSQL(DataBases.CreateDB._CREATE0);
            Log.d("유소정", "DBHelper onCreate : liked_TB 생성");
        }

        // DB 업그레이드 필요 시 호출 : version 값에 따라 호출
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DataBases.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public DBOpenHelper(Context context) {
        this.mCtx = context;
    }

    public DBOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create() {
        mDBHelper.onCreate(mDB);
    }

    public void close() {
        mDB.close();
    }

    public long insertColumn(String uid, String busNum, String stationNum , String likedDateTime){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.UID, uid);
        values.put(DataBases.CreateDB.BUSNUM, busNum);
        values.put(DataBases.CreateDB.STATIONNUM, stationNum);
        values.put(DataBases.CreateDB.LIKEDDATETIME, likedDateTime);
        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
    }

    // uid 때문에 블로그 작성자랑 비교해서 나중에 고쳐야할지도
    public Cursor selectColumns(){
        return mDB.query(DataBases.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }

    public boolean updateColumn(String uid, String busNum, String stationNum , String likedDateTime){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.UID, uid);
        values.put(DataBases.CreateDB.BUSNUM, busNum);
        values.put(DataBases.CreateDB.STATIONNUM, stationNum);
        values.put(DataBases.CreateDB.LIKEDDATETIME, likedDateTime);
        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_ID=" + uid, null) > 0;
    }

    // Delete All
    public void deleteAllColumns() {
        mDB.delete(DataBases.CreateDB._TABLENAME0, null, null);
    }

    // Delete Column
    public boolean deleteColumn(String uid){
        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_ID="+uid, null) > 0;
    }
}
*/
