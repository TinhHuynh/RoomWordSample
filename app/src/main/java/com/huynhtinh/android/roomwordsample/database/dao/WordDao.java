package com.huynhtinh.android.roomwordsample.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.huynhtinh.android.roomwordsample.model.Word;

import java.util.List;

/**
 * Created by TINH HUYNH on 2/11/2018.
 */

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    @Query("DELETE FROM word")
    void deleteAll();

    @Query("SELECT * from word ORDER BY value ASC")
    LiveData<List<Word>> getAllWords();

}
