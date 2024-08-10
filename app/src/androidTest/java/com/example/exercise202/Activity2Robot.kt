package com.example.exercise202

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

// Robot 2 will assert the number of items on the screen and interact with an item in the list
class Activity2Robot {

    private val myApplication = ApplicationProvider.getApplicationContext<MyApplication>()

    fun assertNumberOfItems(expected: Int) : Activity2Robot {
        onView(withId(R.id.recycler_view))
            .check(checkRecyclerViewItems(expected))
        return this
    }

    fun assertItemText(itemPosition: Int) : Activity2Robot {
        onView(withText(myApplication.getString(R.string.item_x, (itemPosition + 1)))).check(matches(
            isDisplayed()
        ))
        return this
    }

    fun clickOnItem(itemPosition: Int): Activity2Robot {
        onView(withId(R.id.recycler_view)).perform(scrollToPosition<RecyclerView.ViewHolder>(itemPosition))
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(itemPosition, click()))
        return this
    }





}