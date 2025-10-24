package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testActivitySwitch() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Toronto"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());
        onView(withText("Toronto")).perform(click());
        onView(withId(R.id.city_name_text)).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameConsistency() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Vancouver"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());
        onView(withText("Vancouver")).perform(click());
        onView(withId(R.id.city_name_text)).check(matches(withText("Vancouver")));
    }

    @Test
    public void testBackButton() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Montreal"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());
        onView(withText("Montreal")).perform(click());
        onView(withId(R.id.back_button)).perform(click());
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}
