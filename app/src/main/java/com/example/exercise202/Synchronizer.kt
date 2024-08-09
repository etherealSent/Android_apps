package com.example.exercise202

import androidx.test.espresso.idling.CountingIdlingResource
import java.util.Timer
import java.util.TimerTask

interface Synchronizer {
    fun executeAfterDelay(callback: (Int) -> Unit)
}