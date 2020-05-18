package com.laozhu.f3kdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.laozhu.f3krule.Competition;
import com.laozhu.f3krule.Competitor;

import java.io.File;

public class DbManager extends SQLiteOpenHelper {
    private SQLiteDatabase db = null;
    private DbCompetition dbCompetition = null;
    private DbCompetitor dbCompetitor = null;
    public static final String F3K_DB_NAME = "f3kdb";
    public static final int F3K_DB_VER = 1;
    public DbManager(@Nullable Context context) {
        super(context, F3K_DB_NAME, null, F3K_DB_VER);
    }

    void createTable(SQLiteDatabase db){
        db.execSQL("create table competition(name char(128) primary key)");
        db.execSQL("create table competitor(name char(128) primary key)");
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        createTable(sqLiteDatabase);
    }

    public SQLiteDatabase getDb(){
        if(db != null){
            return db;
        }
        db = getWritableDatabase();
        return db;
    }

    public void closeDB() {
        if(db != null){
            db.close();
            db = null;
        }
        dbCompetition = null;
    }

    public boolean delDB() {
        String path = getDb().getPath();
        closeDB();
        File file = new File(path);
        return file.delete();
    }



    public DbCompetition getDbCompetition() {
        if(dbCompetition == null){
            dbCompetition = new DbCompetition(getDb());
        }
        return dbCompetition;
    }

    public DbCompetitor getDbCompetitor() {
        if(dbCompetitor == null){
            dbCompetitor = new DbCompetitor(getDb());
        }
        return dbCompetitor;
    }


}
