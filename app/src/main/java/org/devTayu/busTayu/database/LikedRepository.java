/*
package org.devTayu.busTayu.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.devTayu.busTayu.model.Liked;
import org.devTayu.busTayu.model.LikedDB;

import java.util.List;

public class LikedRepository {

    private LikedDAO likedDAO;
    private LiveData<List<LikedDB>> allContacts;

    public LikedRepository(Application application) {
        LikedDatabase db = LikedDatabase.getDatabase(application);
        likedDAO = db.likedDAO();
        allContacts = likedDAO.getAll();
    }

    public LiveData<List<LikedDB>> getAllContacts() { return allContacts; }
    public void insert(LikedDB contact) {
        LikedDatabase.databaseWriteExecutor.execute(() -> {
            likedDAO.insert(contact);
        });
    }
}
*/
