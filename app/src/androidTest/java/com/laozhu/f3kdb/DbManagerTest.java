package com.laozhu.f3kdb;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.laozhu.f3krule.Competition;

import org.junit.Test;
import org.junit.runner.RunWith;
import android.os.Process;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DbManagerTest {
    @Test
    public void testCreateAndDeleteDB() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String competitionName = "testCompetition";
        DbManager dbManager = new DbManager(appContext);
        Competition competition = new Competition(competitionName);
        DbCompetition dbCompetition = dbManager.getDbCompetition();
        int ret = dbCompetition.addCompetition(competition);
        assertEquals(ret, DbCompetition.RET_SUCCESS);
        competition = dbCompetition.getCompetition(competitionName);
        assertNotNull(competition);
        boolean booleanRet = dbManager.delDB();
        assertTrue(booleanRet);
        dbManager = new DbManager(appContext);
        dbCompetition = dbManager.getDbCompetition();
        competition = dbCompetition.getCompetition(competitionName);
        assertNull(competition);
        dbManager.delDB();
    }

}
