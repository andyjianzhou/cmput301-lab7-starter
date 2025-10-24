package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Test
    public void testCityNameDisplayed() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ShowActivity.class);
        intent.putExtra("CITY_NAME", "Edmonton");
        ActivityScenario<ShowActivity> scenario = ActivityScenario.launch(intent);

        onView(withId(R.id.city_name_text)).check(matches(withText("Edmonton")));
    }

    @Test
    public void testBackButtonExists() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ShowActivity.class);
        intent.putExtra("CITY_NAME", "Calgary");
        ActivityScenario<ShowActivity> scenario = ActivityScenario.launch(intent);

        onView(withId(R.id.back_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testBackButtonFunctionality() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ShowActivity.class);
        intent.putExtra("CITY_NAME", "Ottawa");
        ActivityScenario<ShowActivity> scenario = ActivityScenario.launch(intent);

        onView(withId(R.id.back_button)).perform(click());
        scenario.onActivity(activity -> {
            assert activity.isFinishing();
        });
    }
}
