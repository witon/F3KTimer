package com.laozhu.f3ktimer;

import android.app.Application;

import com.laozhu.f3krule.CompetitionArrangement;

public class F3kTimerApp extends Application {

    CompetitionArrangement competitionArrangement = new CompetitionArrangement();
    @Override
    public void onCreate()
    {
        super.onCreate();
        competitionArrangement.newCompetition("competition 1");
    }
}
