/*
package org.devTayu.busTayu.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.devTayu.busTayu.model.LikedDB;

import java.util.List;

public class LikedRepository {

    private LikedDAO likedDAO;
    private LiveData<List<LikedDB>> allContacts;
    private int CountLiked = 0;

    public LikedRepository(Application application) {
        TaYuDatabase db = TaYuDatabase.getDatabase(application);
        likedDAO = db.likedDAO();
        allContacts = likedDAO.getAll();
    }

    public LiveData<List<LikedDB>> getAllContacts() {
        return allContacts;
    }

    public void insertLiked(LikedDB likedDB) {
        */
/*
        TaYuDatabase.databaseWriteExecutor.execute(() -> {
            likedDAO.insertLiked(likedDB);
        });
        *//*

        new InsertLikedAsyncTask(likedDAO).execute(likedDB);
    }

    public void Update(LikedDB likedDB) {
        new UpdateAsyncTask(likedDAO).execute(likedDB);
    }

    public void delete(LikedDB likedDB) {
        new DeleteAsyncTask(likedDAO).execute(likedDB);
    }

    public void deleteAll(LikedDB likedDB) {
        new DeleteAllAsyncTask(likedDAO).execute();
    }

    public void deleteLiked(LikedDB likedDB) {
        new DeleteLikedAsyncTask(likedDAO).execute(likedDB);
    }

    public int getCountLiked(LikedDB likedDB){
        */
/*return CountLiked;*//*

        new getCountLikedAsyncTask(likedDAO).execute(likedDB);
    }

    public LiveData<List<LikedDB>> getAll() {
        return allContacts;
    }

    private static class DeleteAsyncTask extends AsyncTask<LikedDB, Void, Void>{
        private LikedDAO likedDAO;

        private DeleteAsyncTask(LikedDAO likedDAO){
            this.likedDAO = likedDAO;
        }

        @Override
        protected Void doInBackground(LikedDB... likeds){
            likedDAO.delete(likeds[0]);
            return null;
        }
    }
    private static class DeleteAllAsyncTask extends AsyncTask<LikedDB, Void, Void>{
        private LikedDAO likedDAO;

        private DeleteAllAsyncTask(LikedDAO likedDAO){
            this.likedDAO = likedDAO;
        }

        @Override
        protected Void doInBackground(LikedDB... likeds){
            likedDAO.deleteAll();
            return null;
        }
    }
    private static class InsertLikedAsyncTask extends AsyncTask<LikedDB, Void, Void>{
        private LikedDAO likedDAO;

        private InsertLikedAsyncTask(LikedDAO likedDAO){
            this.likedDAO = likedDAO;
        }

        @Override
        protected Void doInBackground(LikedDB... likeds){
            likedDAO.insertLiked(likeds[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<LikedDB, Void, Void>{
        private LikedDAO likedDAO;

        private UpdateAsyncTask(LikedDAO likedDAO){
            this.likedDAO = likedDAO;
        }

        @Override
        protected Void doInBackground(LikedDB... likeds){
            likedDAO.update(likeds[0]);
            return null;
        }
    }

    private static class getCountLikedAsyncTask extends AsyncTask<LikedDB, Void, Void>{
        private LikedDAO likedDAO;

        private getCountLikedAsyncTask(LikedDAO likedDAO){
            this.likedDAO = likedDAO;
        }

        @Override
        protected Void doInBackground(LikedDB... likeds){
            likedDAO.getCountLiked();
            return null;
        }
    }

    private static class DeleteLikedAsyncTask extends AsyncTask<LikedDB, Void, Void>{
        private LikedDAO likedDAO;

        private DeleteLikedAsyncTask(LikedDAO likedDAO){
            this.likedDAO = likedDAO;
        }

        @Override
        protected Void doInBackground(LikedDB... likeds){
            likedDAO.insertLiked(likeds[0]);
            return null;
        }
    }

}
*/
