package com.example.exercise202

import android.app.Application
import java.util.Timer
import kotlin.random.Random

open class MyApplication : Application() {
    lateinit var synchronizer: Synchronizer

    override fun onCreate() {
        super.onCreate()
        synchronizer = createSynchronizer()
    }

    open fun createRandomizer() : Randomizer = RandomizerImpl(Random)

    open fun createSynchronizer() : Synchronizer = SynchronizerImpl(createRandomizer(), Timer())
}