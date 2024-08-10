package com.example.exercise202

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

// Robot 3 will assert the text displayed in TextView
class Activity3Robot {
    private val myApplication = ApplicationProvider.getApplicationContext<MyApplication>()

    fun assertText(expected: String): Activity3Robot {
        onView(withId(R.id.activity3_text)).check(matches(withText(myApplication.getString(R.string.you_clicked_y, expected))))
        return this
    }
}