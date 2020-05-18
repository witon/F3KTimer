package com.laozhu.f3ktimer;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompetitorListActivityTest {
    @Rule
    public ActivityTestRule<CompetitorListActivity> activityTestRule = new ActivityTestRule<>(CompetitorListActivity.class, false, false);

    @Before
    public void clearDb() {
        DBUtils.clearDb();
    }

    @Test
    public void launchActivity() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(appContext, CompetitorListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activityTestRule.launchActivity(intent);

    }
}