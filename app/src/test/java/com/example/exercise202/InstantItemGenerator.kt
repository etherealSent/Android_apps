package com.example.exercise202

import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.annotation.RealObject

@Implements(ItemGenerator::class)
class InstantItemGenerator {

    @RealObject
    lateinit var itemGenerator: ItemGenerator

    @Implementation
    fun generateItemsAsync(itemCount: Int, callback: (List<Item>) -> Unit) {
        callback.invoke((itemGenerator as ItemGeneratorImpl).generateItems(itemCount))
    }
}