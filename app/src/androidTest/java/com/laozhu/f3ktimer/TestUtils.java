package com.laozhu.f3ktimer;

import android.view.View;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class TestUtils {
    public static boolean waitUntilTextAppear(String text, int tryTimes, int interval) {
        boolean found = false;
        for(int i=0; i<tryTimes; i++) {
            try {
                org.hamcrest.Matcher<View> matcher = withText(text);
                onView(matcher).check(matches(isDisplayed()));
                found = true;
            } catch (Exception e) {

            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return found;
    }
}
