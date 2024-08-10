package com.example.exercise202

import androidx.test.espresso.idling.CountingIdlingResource

class TestMyApplication : MyApplication() {

    val countingIdlingResource = CountingIdlingResource("Timer Resource")

    override fun createItemGenerator(): ItemGenerator {
        return TestItemGenerator(
            ItemGeneratorImpl(timer, StringProvider(this), 0),
            countingIdlingResource
        )
    }
}