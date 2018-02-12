package com.huynhtinh.android.roomwordsample.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by TINH HUYNH on 2/11/2018.
 */

@Entity(tableName = "word")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "value")
    @NonNull
    private String mValue;

    @Ignore
    public Word(@NonNull String value) {
        mValue = value;
    }

    public Word(int id, @NonNull String value) {
        mId = id;
        mValue = value;
    }

    public int getId() {
        return mId;
    }

    @NonNull
    public String getValue() {
        return mValue;
    }
}
