package org.devTayu.busTayu.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.devTayu.busTayu.model.LikedDB;

import java.util.concurrent.Executor;

@Database(entities = {LikedDB.class}, version = 1, exportSchema = false)
public abstract class LikedDatabase extends RoomDatabase {

    public abstract LikedDAO likedDAO();

    private static volatile LikedDatabase INSTANCE;

    //싱글톤
    public static LikedDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LikedDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LikedDatabase.class, "liked_database")
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

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            Executor databaseWriteExecutor = null;
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                LikedDAO dao = INSTANCE.likedDAO();
                dao.deleteAll();

                LikedDB likedDB = new LikedDB("memo1","211212");
                dao.insert(likedDB);
            });
        }
    };
}