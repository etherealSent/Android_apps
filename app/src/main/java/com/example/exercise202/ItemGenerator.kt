package com.example.exercise202

import java.util.Timer
import java.util.TimerTask

interface ItemGenerator {
    fun generateItemsAsync(itemCount: Int, callback: (List<Item>) -> Unit)
}

class ItemGeneratorImpl(
    private val timer: Timer,
    private val stringProvider: StringProvider,
    private val initialDelay: Long
) : ItemGenerator {

    override fun generateItemsAsync(itemCount: Int, callback: (List<Item>) -> Unit) {
        timer.schedule(ItemGeneratorTask(itemCount,callback), initialDelay)
    }

    internal fun generateItems(itemCount: Int): List<Item> {
        val result = mutableListOf<Item>()
        for (i in 1..itemCount) {
            result.add(Item(stringProvider.provideItemString(i)))
        }
        return result
    }


    inner class ItemGeneratorTask(
        private val itemCount: Int,
        private val callback: (List<Item>) -> Unit
    ) : TimerTask() {
        override fun run() {
            callback.invoke(generateItems(itemCount))
        }
    }
}