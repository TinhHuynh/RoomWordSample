package com.huynhtinh.android.roomwordsample.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.huynhtinh.android.roomwordsample.database.dao.WordDao;
import com.huynhtinh.android.roomwordsample.model.Word;

/**
 * Created by TINH HUYNH on 2/11/2018.
 */

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "words";

    private static WordRoomDatabase sInstance;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (sInstance == null) {
            synchronized (WordRoomDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordRoomDatabase.class,
                            DATABASE_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return sInstance;
    }

    public abstract WordDao wordDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(sInstance).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final WordDao mWordDao;

        PopulateDbAsync(WordRoomDatabase db){
             mWordDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordDao.deleteAll();
            Word word = new Word("Hello");
            mWordDao.insert(word);
            word = new Word("World");
            mWordDao.insert(word);
            return null;
        }
    }


}
