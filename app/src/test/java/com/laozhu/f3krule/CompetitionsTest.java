package com.laozhu.f3krule;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class CompetitionsTest {

    @Test
    public void appendAndGetCompetion() {
        Competitions competitions = new Competitions();
        CompetitionArrangement competitionArrangement = new CompetitionArrangement();
        Competition competition = competitionArrangement.newCompetition("competition 1");
        competitions.appendCompetion(competition);
        Competition competitionGet = competitions.getCompetion("competition 1");
        assertEquals("competition 1", competitionGet.getName());
    }

    @Test
    public void getCompetionsArrayList() {
        Competitions competitions = new Competitions();
        CompetitionArrangement competitionArrangement = new CompetitionArrangement();
        Competition competition = competitionArrangement.newCompetition("competition 1");
        competitions.appendCompetion(competition);
        competition = competitionArrangement.newCompetition("competition 2");
        competitions.appendCompetion(competition);

    }

    @Test
    public void appendCompetion() {

        Competitions competitions = new Competitions();
        int testNum = 10;
        Competition [] competitionsArray = new Competition[testNum];
        for(int i=0; i<testNum; i++) {
            Competition c = new Competition((new Integer(i).toString()));
            competitionsArray[i] = c;
        }
        for(int i=0; i<testNum; i++){
            competitions.appendCompetion(competitionsArray[i]);
        }
        //append duplicate
        for(int i=0; i<testNum; i++){
            competitions.appendCompetion(competitionsArray[i]);
        }
        ArrayList<Competition> competitionArrayList = competitions.getCompetionsArrayList();
        assertEquals(testNum, competitionArrayList.size());

    }
}