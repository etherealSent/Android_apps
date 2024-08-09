package com.example.exercise202

import kotlin.random.Random

class RandomizerImpl(private val random: Random) : Randomizer {
    override fun getTimeToWait(): Int {
        return random.nextInt(5) + 1
    }
}