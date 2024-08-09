package com.example.exercise202

import androidx.test.espresso.idling.CountingIdlingResource
import kotlin.random.Random

public class TestMyApplication : MyApplication() {

    val countingIdlingResource =
        CountingIdlingResource("Timer resource")

    override fun createRandomizer(): Randomizer {
        return TestRandomizer()
    }

    override fun createSynchronizer(): Synchronizer {
        return TestSynchronizer(super.createSynchronizer(), countingIdlingResource)
    }
}