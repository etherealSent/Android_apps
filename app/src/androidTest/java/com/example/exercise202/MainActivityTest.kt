package com.example.exercise202

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUiTest {

    @Test
    fun showSumResultInTextView() {
        val scenario = launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.edit_text)).perform(replaceText("5"))
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.text_view)).check(
            matches(
                withText(
                    getApplicationContext<Application>().getString(
                        R.string.the_sum_of_numbers_from_1_to_is,
                        5,
                        "15"
                    )
                )
            )
        )
    }

    @Test
    fun showErrorInTextView() {
        val scenario = launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.edit_text)).perform(replaceText("-5"))
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.text_view)).check(matches(withText(getApplicationContext<Application>().getString(R.string.error_invalid_number))))
    }
}