package com.example.fibonacciapp.helpers

class FibonacciNumberManager() {
    fun getFibonacciNumber(input: Int): Long? {
        val mapOfNumbers = HashMap<Int, Long>()
        mapOfNumbers[0] = 0
        mapOfNumbers[1] = 1
        mapOfNumbers[2] = 1
        for (x in 3..input) {
            mapOfNumbers[x] = mapOfNumbers[x - 1]!! + mapOfNumbers[x - 2]!!
        }
        return mapOfNumbers[input]
    }
}