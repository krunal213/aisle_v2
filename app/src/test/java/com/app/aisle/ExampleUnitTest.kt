package com.app.aisle

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        var phoneNumber = "9890352190"

        phoneNumber?.let {
            it?.trim()?.isNotEmpty() && it?.trim()?.length == 10
        }.ifTrueThen {
            println("True")
        }.orElse {
            println("false")
        }
    }
}


