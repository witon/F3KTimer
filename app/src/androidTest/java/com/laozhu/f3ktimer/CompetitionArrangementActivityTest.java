package com.laozhu.f3ktimer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import android.util.Log;
import android.view.View;


import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.laozhu.f3kdb.DbCompetition;
import com.laozhu.f3kdb.DbManager;
import com.laozhu.f3krule.Competition;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CompetitionArrangementActivityTest {
    private static final String COMPETITION_NAME_PREFIX = "ArrangeCompetition";
    class CompetitionArrangementBroadcastReceiver extends BroadcastReceiver {
        public String destCompetitionName = "";
        public boolean receivedBroadcast = false;
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(MainActivity.ACTION_NEW_COMPETITION)) {
                String competitionNameResult = intent.getStringExtra("name");
                assertEquals(destCompetitionName, competitionNameResult);
                receivedBroadcast = true;
            }
        }
    }
    CompetitionArrangementBroadcastReceiver broadcastReceiver = new CompetitionArrangementBroadcastReceiver();

    @Rule
    public ActivityTestRule<CompetitionArrangementActivity> activityTestRule = new ActivityTestRule<>(CompetitionArrangementActivity.class,false,false);

    @Before
    public void clearDb() {
        DBUtils.clearDb();
    }

    @Test
    public void newCompetition() {
        final String competitionNameDest = COMPETITION_NAME_PREFIX;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        broadcastReceiver.receivedBroadcast = false;
        broadcastReceiver.destCompetitionName = competitionNameDest;
        appContext.registerReceiver(broadcastReceiver, new IntentFilter(MainActivity.ACTION_NEW_COMPETITION));

        Intent intent = new Intent(appContext, CompetitionArrangementActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activityTestRule.launchActivity(intent);

        ViewInteraction vi = onView(withId(R.id.text_view_competition_name));
        vi.check(matches(withText("Competition name:")));
        vi = onView(withId(R.id.edit_text_competition_name));
        vi.perform(clearText());
        vi.perform(typeText(competitionNameDest));
        openActionBarOverflowOrOptionsMenu(appContext);
        onView(withText("Save")).perform((click()));
        DbManager dbManager = new DbManager(appContext);
        DbCompetition dbCompetition = dbManager.getDbCompetition();
        Competition competition = dbCompetition.getCompetition(competitionNameDest);
        assertNotNull(competition);
        assertTrue(broadcastReceiver.receivedBroadcast);
        appContext.unregisterReceiver(broadcastReceiver);
    }

    @Test
    public void duplicateCompetition() {
        DBUtils.addCompetitions(COMPETITION_NAME_PREFIX, 0, 1);
        final String competitionNameDest = COMPETITION_NAME_PREFIX + "0";
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        broadcastReceiver.receivedBroadcast = false;
        appContext.registerReceiver(broadcastReceiver, new IntentFilter(MainActivity.ACTION_NEW_COMPETITION));

        Intent intent = new Intent(appContext, CompetitionArrangementActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activityTestRule.launchActivity(intent);

        ViewInteraction vi = onView(withId(R.id.text_view_competition_name));
        vi.check(matches(withText("Competition name:")));
        vi = onView(withId(R.id.edit_text_competition_name));
        vi.perform(clearText());
        vi.perform(typeText(competitionNameDest));
        openActionBarOverflowOrOptionsMenu(appContext);
        onView(withText("Save")).perform((click()));
        DbManager dbManager = new DbManager(appContext);
        DbCompetition dbCompetition = dbManager.getDbCompetition();
        Competition competition = dbCompetition.getCompetition(competitionNameDest);
        assertNotNull(competition);
        assertFalse(broadcastReceiver.receivedBroadcast);
        appContext.unregisterReceiver(broadcastReceiver);
    }

   @Test
    public void homeButton() throws InterruptedException {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(appContext, CompetitionArrangementActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activityTestRule.launchActivity(intent);
        ViewInteraction vi = onView(withContentDescription("Navigate up"));
        vi.perform(click());
        assertEquals(activityTestRule.getActivityResult().getResultCode(), Activity.RESULT_CANCELED);
        for(int i=0; i<20; i++) {
            Thread.sleep(100);
            if(activityTestRule.getActivity().isDestroyed())
                return;
        }
        assertTrue(false);
    }

}