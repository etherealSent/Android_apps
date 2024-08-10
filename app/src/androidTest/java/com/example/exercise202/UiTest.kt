package com.example.exercise202

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UiTest {

    private val myApplication =
        ApplicationProvider.getApplicationContext<TestMyApplication>()

    @Before
    fun setUp() {
        val scenario = ActivityScenario.launch(Activity1::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
        IdlingRegistry.getInstance().register(myApplication.countingIdlingResource)
    }

    @Test
    fun testMyFlow() {
        val numberOfItems = 5

        Activity1Robot()
            .editText(numberOfItems.toString())
            .pressButton()

        val selectedPosition = 3

        Activity2Robot()
            .assertNumberOfItems(numberOfItems)
            .assertItemText(selectedPosition)
            .clickOnItem(selectedPosition)

        val expectedTest = myApplication.getString(R.string.item_x, (selectedPosition + 1))

        Activity3Robot()
            .assertText(expectedTest)
    }

}