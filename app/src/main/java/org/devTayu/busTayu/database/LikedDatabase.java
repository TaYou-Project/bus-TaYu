package org.devTayu.busTayu.database;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import org.devTayu.busTayu.model.LikedDB;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {LikedDB.class}, version = 1, exportSchema = false)
public abstract class LikedDatabase extends RoomDatabase {
    public abstract LikedDAO likedDAO();
    public static final int NUMBER_OF_THREADS = 4;
    private static volatile LikedDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //싱글톤
    public static LikedDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LikedDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LikedDatabase.class, "liked_database")
                            .addCallback(setInitialRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    //DB 객체 제거
    public static void destroyInstance() {
        INSTANCE = null;
    }

    // 처음부터 데이터가 들어가 있길 원한다면, 여기에서 직접 채워줄수 있습니다
    private static RoomDatabase.Callback setInitialRoomDatabaseCallback = new RoomDatabase.Callback() {
        // 현재 시각 - 어떻게 찍히는지 확인 필요
        // Date currentTime = Calendar.getInstance().getTime();

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // dataBaseWriteExecutor에 정의된 onOpen()을 사용하면, database가 열릴때마다 할 행동을 override할 수 있습니다.

            // If you want to keep data through app restarts,
            // comment out the following block
            Executor databaseWriteExecutor = null;
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                LikedDAO dao = INSTANCE.likedDAO();
                dao.deleteAll();
                LikedDB likedDB = new LikedDB("버스번호","정류소번호");
                dao.insert(likedDB);
            });
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // onCraete()를 override하면 database가 생성될때 할 행동을 코딩할 수 있습니다.

        }
    };
}