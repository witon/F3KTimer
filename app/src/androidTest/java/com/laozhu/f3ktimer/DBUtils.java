package com.laozhu.f3ktimer;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.laozhu.f3kdb.DbCompetition;
import com.laozhu.f3kdb.DbManager;
import com.laozhu.f3krule.Competition;

public class DBUtils {
    static public void addCompetitions(String prefix, int startIndex, int count) {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        DbCompetition dbCompetition = dbManager.getDbCompetition();
        for(int i=startIndex; i<count + startIndex; i++) {
            String name = prefix + String.valueOf(i);
            Competition competition = new Competition(name);
            dbCompetition.addCompetition(competition);
        }
        dbManager.closeDB();
    }
    static public void clearDb() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        dbManager.delDB();
    }

}
