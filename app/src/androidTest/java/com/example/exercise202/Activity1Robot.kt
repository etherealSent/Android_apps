package com.example.exercise202

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId

// Robot 1 will contain the interaction with EditText and the button
class Activity1Robot {
    fun editText(text: String) : Activity1Robot {
        onView(withId(R.id.numberEdit)).perform(replaceText(text))
        return this
    }

    fun pressButton() : Activity1Robot {
        onView(withId(R.id.activity1_button)).perform(click())
        return this
    }
}