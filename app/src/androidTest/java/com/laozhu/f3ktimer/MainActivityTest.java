package com.laozhu.f3ktimer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import android.view.View;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.laozhu.f3kdb.DbCompetition;
import com.laozhu.f3kdb.DbManager;
import com.laozhu.f3krule.Competition;
import com.laozhu.f3krule.CompetitionArrangement;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadPoolExecutor;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest{
    static final private String COMPETITION_NAME_PREFIX = "MainCompetition";
    class CompetitionMatcher implements Matcher<Competition> {
        private String name = null;
        public CompetitionMatcher(String name) {
            this.name = name;
        }

        @Override
        public boolean matches(Object item) {
            return ((Competition)item).getName().equals(name);
        }

        @Override
        public void describeMismatch(Object item, Description mismatchDescription) {

        }

        @Override
        public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

        }

        @Override
        public void describeTo(Description description) {

        }

    }

    @Before
    public void clearDB() {
        DBUtils.clearDb();
    }



    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class,false,false);

    @Test
    public void listCompetitions() {
        DBUtils.addCompetitions(COMPETITION_NAME_PREFIX, 0, 30);
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(appContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activityTestRule.launchActivity(intent);
        ViewInteraction vi = onView(withId(R.id.lv_competitions));
        vi.check(matches(isDisplayed()));
        String competionNameDest = COMPETITION_NAME_PREFIX + "1";
        onView(withText(competionNameDest)).check(matches(isDisplayed()));
        competionNameDest = COMPETITION_NAME_PREFIX + "20";
        CompetitionMatcher competitionMatcher = new CompetitionMatcher(competionNameDest);
        DataInteraction di = onData(competitionMatcher);
        di.perform(scrollTo());
        di.check(matches(isDisplayed()));
    }

    @Test
    public void receiveNewCompetitionBroadcast() throws InterruptedException {
        DBUtils.addCompetitions(COMPETITION_NAME_PREFIX, 0, 3);
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(appContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activityTestRule.launchActivity(intent);

        DBUtils.addCompetitions(COMPETITION_NAME_PREFIX, 3, 1);
        String competitionNameDest = COMPETITION_NAME_PREFIX + "3";
        intent = new Intent(MainActivity.ACTION_NEW_COMPETITION);
        intent.putExtra("name",competitionNameDest);
        appContext.sendBroadcast(intent);
        boolean found = TestUtils.waitUntilTextAppear(competitionNameDest, 60, 50);
        assertTrue(found);
    }

    @Test
    public void newCompetition() throws InterruptedException {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(appContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activityTestRule.launchActivity(intent);

        openActionBarOverflowOrOptionsMenu(appContext);
        onView(withText("New Competition")).perform((click()));
        boolean found = TestUtils.waitUntilTextAppear("Competition name:", 60, 50);
        assertTrue(found);
    }
}