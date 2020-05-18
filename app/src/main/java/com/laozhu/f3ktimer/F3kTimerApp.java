package com.laozhu.f3ktimer;

import android.app.Application;

import com.laozhu.f3kdb.DbCompetition;
import com.laozhu.f3kdb.DbManager;
import com.laozhu.f3krule.Competition;
import com.laozhu.f3krule.CompetitionArrangement;
import com.laozhu.f3krule.Competitions;

public class F3kTimerApp extends Application {

    public Long timeStamp = System.currentTimeMillis();

    CompetitionArrangement competitionArrangement = new CompetitionArrangement();
    Competitions competitions = new Competitions();
    @Override
    public void onCreate()
    {
        super.onCreate();
    }
    public void getCompetitionsFromDb(){
        DbManager dbManager = new DbManager(this);
        DbCompetition dbCompetition = dbManager.getDbCompetition();
        dbCompetition.getAllCompetitions(competitions);
    }
}
