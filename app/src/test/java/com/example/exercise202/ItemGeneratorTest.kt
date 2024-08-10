package com.example.exercise202

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.Timer
import java.util.TimerTask

@RunWith(MockitoJUnitRunner::class)
class ItemGeneratorTest {
    private lateinit var itemGenerator: ItemGeneratorImpl

    @Mock
    lateinit var timer: Timer

    @Mock
    lateinit var stringProvider: StringProvider

    private val initialDelay = 5L

    @Before
    fun setUp() {
        itemGenerator = ItemGeneratorImpl(timer, stringProvider, initialDelay)
    }

    @Test
    fun generateItemsAsync() {
        val spy = spy(itemGenerator)
        val callback = mock<(List<Item>) -> Unit>()
        val itemCount = 10
        val items = listOf(Item("1"), Item("2"))

        doReturn(items).whenever(spy).generateItems(itemCount)

        whenever(timer.schedule(any(), eq(initialDelay))).thenAnswer {
            (it.arguments[0] as TimerTask).run()
        }

        spy.generateItemsAsync(itemCount, callback)

        verify(callback).invoke(items)
    }

    @Test
    fun generateItems() {
        val itemCount = 10
        val expected = mutableListOf<Item>()
        for (i in 1..itemCount) {
            val itemText = "itemText$i"
            whenever(stringProvider.provideItemString(i)).thenReturn(itemText)
            expected.add(Item(itemText))
        }

        val result = itemGenerator.generateItems(itemCount)

        assertEquals(expected, result)
    }


}