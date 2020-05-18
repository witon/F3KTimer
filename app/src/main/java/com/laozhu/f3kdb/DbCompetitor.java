package com.laozhu.f3kdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.laozhu.f3krule.Competition;
import com.laozhu.f3krule.Competitions;
import com.laozhu.f3krule.Competitor;
import com.laozhu.f3krule.MapArrayContainer;

public class DbCompetitor {
    SQLiteDatabase db = null;
    static final String COMPETITOR_TABLE_NAME = "competitor";
    public DbCompetitor(SQLiteDatabase db){
        this.db = db;
    }
    public boolean addCompetitor(Competitor competitor){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", competitor.getName());
        long ret = db.insert(COMPETITOR_TABLE_NAME, null, contentValues);
        if(ret == -1){
            return false;
        }
        return true;
    }

    public Competitor getCompetitor(String name) {
        String [] columnNames = {"name"};
        String [] args = new String[1];
        args[0] = name;
        Cursor cursor = db.query(COMPETITOR_TABLE_NAME, columnNames, "name=?", args, null, null, null, null);
        if(cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        if(!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        Competitor competitor = new Competitor(cursor.getString(0));
        cursor.close();
        return competitor;
    }
    public boolean removeCompetitor(String name) {
        String [] args = new String[1];
        args[0] = name;
        int ret = db.delete(COMPETITOR_TABLE_NAME, "name=?", args);
        if(ret != 1) {
            return false;
        }
        return true;
    }
    public MapArrayContainer<Competitor> getAllCompetitors() {
        MapArrayContainer<Competitor> competitors = new MapArrayContainer<>();
        getAllCompetitors(competitors);
        return competitors;
    }

    public void getAllCompetitors(MapArrayContainer<Competitor> competitions) {
        String [] columnNames = {"name"};
        Cursor cursor = db.query(COMPETITOR_TABLE_NAME, columnNames, null, null, null, null, null, null);
        competitions.clear();
        while(cursor.moveToNext()) {
            Competitor competitor = new Competitor(cursor.getString(0));
            competitions.append(competitor);
        }
        cursor.close();
    }
}
