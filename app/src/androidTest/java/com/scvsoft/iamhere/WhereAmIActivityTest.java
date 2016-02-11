package com.scvsoft.iamhere;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class WhereAmIActivityTest {

    @Rule
    public ActivityTestRule<WhereAmIActivity> mActivityRule = new ActivityTestRule<>(
            WhereAmIActivity.class);

    @Test
    public void testMapExists() {
        onView(ViewMatchers.withId(R.id.map)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
