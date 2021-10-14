/*
package org.devTayu.busTayu.database;

import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns {
        public static final String UID = "uid"; // 자동증가 평 숫자
        public static final String BUSNUM = "busNum"; // 버스번호(6640A) (노선 명 6640A) -> 노선 번호 : 이거 저장
        public static final String STATIONNUM = "stationNum"; // 정류소 고유 번호
        public static final String LIKEDDATETIME = "likedDateTime"; // 즐겨찾기 추가 ex_ 2019-11-06 12:03:00
        public static final String _TABLENAME0 = "_B_Liked";

        public static final String _CREATE0 = "create table if not exists " + _TABLENAME0 + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BUSNUM + " VARCHAR(15) not null , "
                + STATIONNUM + " VARCHAR(15) not null , "
                + LIKEDDATETIME + " DATETIME not null );";
    }
}*/
