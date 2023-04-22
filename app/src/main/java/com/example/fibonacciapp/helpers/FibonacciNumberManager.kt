package com.example.fibonacciapp.helpers

import java.math.BigInteger

class FibonacciNumberManager {
    fun getFibonacciNumber(input: Int): BigInteger? {
        val mapOfNumbers = HashMap<Int, BigInteger>()
        mapOfNumbers[0] = BigInteger("0")
        mapOfNumbers[1] = BigInteger("1")
        mapOfNumbers[2] = BigInteger("1")
        for (x in 3..input) {
            mapOfNumbers[x] = mapOfNumbers[x - 1]!! + mapOfNumbers[x - 2]!!
        }
        
        return mapOfNumbers[input]
    }
}