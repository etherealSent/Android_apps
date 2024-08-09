package com.example.exercise202

import android.content.Context
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.math.BigInteger

@RunWith(MockitoJUnitRunner::class)
class TextFormatterTest {
    @InjectMocks
    lateinit var textFormatter: TextFormatter
    @Mock
    lateinit var numberAdder: NumberAdder
    @Mock
    lateinit var context: Context
    @Test
    fun getSumResult_success() {
        val n = 10
        val sumResult = BigInteger.TEN
        val expected = "expected"

        whenever(numberAdder.sum(eq(n),
            any())).thenAnswer {
            (it.arguments[1] as (BigInteger) ->
            Unit).invoke(sumResult)
        }

        whenever(context.getString(
            R.string.the_sum_of_numbers_from_1_to_is,
            n, sumResult.toString())
        ).thenReturn(expected)

        val callback = mock<(String) -> Unit>()

        textFormatter.getSumResult(n, callback)
        verify(callback).invoke(expected)
    }

    //Purpose This test helps ensure that:
//TextFormatter correctly calls NumberAdder.sum with the right input.
//TextFormatter correctly uses the Context to format the result string.
//TextFormatter invokes the callback with the formatted string.


    @Test
    fun getSumResult_error() {
        val n = 10
        val expected = "expected"

        whenever(numberAdder.sum(eq(n),
            any())).thenThrow(NumberAdder
            .InvalidNumberException)

        whenever(context.getString(
            R.string.error_invalid_number))
            .thenReturn(expected)

        val callback = mock<(String) -> Unit>()

        textFormatter.getSumResult(n, callback)
        verify(callback).invoke(expected)
    }

}


