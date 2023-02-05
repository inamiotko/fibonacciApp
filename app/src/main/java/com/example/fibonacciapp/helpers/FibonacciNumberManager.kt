package com.example.fibonacciapp.helpers

class FibonacciNumberManager() {
    fun getFibonacciNumber(input: Int): Int? {
        val mapOfNumbers = HashMap<Int, Int>()
        mapOfNumbers[1] = 0
        mapOfNumbers[2] = 1
        mapOfNumbers[3] = 1
        for (x in 4..input) {
            mapOfNumbers[x] = mapOfNumbers[x - 1]!! + mapOfNumbers[x - 2]!!
        }
        return mapOfNumbers[input]
    }
}