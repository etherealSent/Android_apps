package com.example.exercise202

import java.math.BigInteger


class NumberAdder {

    @Throws(InvalidNumberException::class)
    fun sum(n: Int, callback: (BigInteger) -> Unit) {

        if (n < 0) {
            throw InvalidNumberException
        }
        callback(n.toBigInteger()
            .times((n.toBigInteger() + BigInteger.ONE))
            .divide(BigInteger.valueOf(2)))
    }

    object InvalidNumberException : Throwable()
}

