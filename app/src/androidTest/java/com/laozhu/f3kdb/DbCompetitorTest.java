package com.laozhu.f3kdb;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.laozhu.f3krule.Competitor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DbCompetitorTest {

    @Before
    public void setUp(){
        ;
    }

    @After
    public void tearDown() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        dbManager.delDB();
    }

    @Test
    public void testAddAndGetAndRemoveCompetitor() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        DbCompetitor dbCompetitor = dbManager.getDbCompetitor();
        String competitorName = "competitor1";
        Competitor competitor = new Competitor(competitorName);
        boolean ret = dbCompetitor.addCompetitor(competitor);
        assertTrue(ret);
        competitor = dbCompetitor.getCompetitor(competitorName);
        assertEquals(competitor.getName(), competitorName);
        ret = dbCompetitor.removeCompetitor(competitorName);
        assertTrue(ret);
        competitor = dbCompetitor.getCompetitor(competitorName);
        assertNull(competitor);
    }
    @Test
    public void testDuplicateAddCompetitor() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        DbCompetitor dbCompetitor = dbManager.getDbCompetitor();
        String competitorName = "competitor1";
        Competitor competitor = new Competitor(competitorName);
        boolean ret = dbCompetitor.addCompetitor(competitor);
        assertTrue(ret);
        ret = dbCompetitor.addCompetitor(competitor);
        assertFalse(ret);
    }

    @Test
    public void testRemoveNotExistCompetitor() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        DbCompetitor dbCompetitor = dbManager.getDbCompetitor();
        boolean ret = dbCompetitor.removeCompetitor("competitor1");
        assertFalse(ret);
    }

    @Test
    public void testGetNotExistCompetitor() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DbManager dbManager = new DbManager(appContext);
        DbCompetitor dbCompetitor = dbManager.getDbCompetitor();
        Competitor competitor = dbCompetitor.getCompetitor("competitor1");
        assertNull(competitor);
    }

}