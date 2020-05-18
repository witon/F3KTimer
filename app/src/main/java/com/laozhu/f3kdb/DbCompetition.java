package com.laozhu.f3kdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.laozhu.f3krule.Competition;
import com.laozhu.f3krule.Competitions;

public class DbCompetition {
    private SQLiteDatabase db = null;
    public static final String COMPETITION_TABLE_NAME = "competition";
    public DbCompetition(SQLiteDatabase db) {
        this.db = db;
    }

    public static final int RET_SUCCESS = 0;
    public static final int RET_DUPLICATE_COMPETITION = -1;
    public static final int RET_OTHER_FAIL = -2;


    public int addCompetition(Competition competition){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", competition.getName());
        try {
            long ret = db.insertOrThrow(COMPETITION_TABLE_NAME, null, contentValues);
            if(ret == -1){
                return RET_OTHER_FAIL;
            }
        }
        catch (SQLiteConstraintException exception){
            return RET_DUPLICATE_COMPETITION;
        }
        return RET_SUCCESS;
    }

    public Competition getCompetition(String name) {
        String [] columnNames = {"name"};
        String [] args = new String[1];
        args[0] = name;
        Cursor cursor = db.query(COMPETITION_TABLE_NAME, columnNames, "name=?", args, null, null, null, null);
        if(cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        if(!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        Competition competition = new Competition(cursor.getString(0));
        cursor.close();
        return competition;
    }
    public boolean removeCompetition(String name) {
        String [] args = new String[1];
        args[0] = name;
        int ret = db.delete(COMPETITION_TABLE_NAME, "name=?", args);
        if(ret != 1) {
            return false;
        }
        return true;
    }
    public Competitions getAllCompetitions() {
        Competitions competitions = new Competitions();
        getAllCompetitions(competitions);
        return competitions;
    }

    public void getAllCompetitions(Competitions competitions) {
        String [] columnNames = {"name"};
        Cursor cursor = db.query(COMPETITION_TABLE_NAME, columnNames, null, null, null, null, null, null);
        competitions.clear();
        while(cursor.moveToNext()) {
            Competition competition = new Competition(cursor.getString(0));
            competitions.appendCompetion(competition);
        }
        cursor.close();
    }
}
