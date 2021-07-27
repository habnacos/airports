package com.habnacos.aeropuertos.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBModel extends SQLiteOpenHelper {
    public DBModel(@Nullable Context context, int version) {
        super(context, DB.nameDB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB.create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
