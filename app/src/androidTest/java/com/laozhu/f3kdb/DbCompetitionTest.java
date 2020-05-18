package com.laozhu.f3kdb;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.laozhu.f3krule.Competition;
import com.laozhu.f3krule.Competitions;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DbCompetitionTest {
    @After
    public void tearDown() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        dbManager.delDB();
    }

    @Test
    public void testAddAndGetAndRemoveCompetition() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        Competition competition = new Competition("testCompetition");
        DbCompetition dbCompetition = dbManager.getDbCompetition();
        int ret = dbCompetition.addCompetition(competition);
        assertEquals(ret, DbCompetition.RET_SUCCESS);
        Competition competitionResult = dbCompetition.getCompetition("testCompetition");
        assertNotNull(competitionResult);
        assertEquals(competition.getName(), competitionResult.getName());
        boolean booleanRet =  dbCompetition.removeCompetition("testCompetition");
        assertTrue(booleanRet);
        competitionResult = dbCompetition.getCompetition("testCompetition");
        assertNull(competitionResult);
    }
    @Test
    public void testAddDuplicateCompetition() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        Competition competition = new Competition("testCompetition");
        DbCompetition dbCompetition = dbManager.getDbCompetition();
        int ret = dbCompetition.addCompetition(competition);
        assertEquals(ret, DbCompetition.RET_SUCCESS);
        ret = dbCompetition.addCompetition(competition);
        assertEquals(ret, DbCompetition.RET_DUPLICATE_COMPETITION);
    }
    @Test
    public void testRemoveNotExistCompetition() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        DbCompetition dbCompetition = dbManager.getDbCompetition();
        boolean ret = dbCompetition.removeCompetition("competition1");
        assertFalse(ret);
    }
   @Test
    public void testGetAllCompetitions() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        DbCompetition dbCompetition = dbManager.getDbCompetition();
        Competition competition = new Competition("competition1");
        int ret = dbCompetition.addCompetition(competition);
        assertEquals(ret, DbCompetition.RET_SUCCESS);
        competition = new Competition("competition2");
        ret = dbCompetition.addCompetition(competition);
        assertEquals(ret, DbCompetition.RET_SUCCESS);
        Competitions competitions = dbCompetition.getAllCompetitions();
        ArrayList<Competition> competitionArraylist = competitions.getCompetionsArrayList();
        ArrayList<String> competitionNames = new ArrayList<>();
        for(Competition c : competitionArraylist) {
            competitionNames.add(c.getName());
        }
        assertThat(competitionNames, hasItems("competition1", "competition2"));

        competitions = new Competitions();
        dbCompetition.getAllCompetitions(competitions);
        competitionNames = new ArrayList<>();
        for(Competition c : competitionArraylist) {
            competitionNames.add(c.getName());
        }
        assertThat(competitionNames, hasItems("competition1", "competition2"));

    }
}