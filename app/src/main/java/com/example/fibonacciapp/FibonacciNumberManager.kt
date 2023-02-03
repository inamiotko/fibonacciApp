package com.example.fibonacciapp

import android.util.Log

class FibonacciNumberManager() {
    fun getFibonacciNumber(input: Int): Int?{
        var fibonacciValue: Int? = null
        if(input<=0) Log.e("Error", "incorrect input")
        else if(input == 1) fibonacciValue = 0
        else if(input == 2) fibonacciValue = 1
        else{
            fibonacciValue = getFibonacciNumber(input-2)?.let { getFibonacciNumber(input-1)?.plus(it) }
        }
        return fibonacciValue
    }
}