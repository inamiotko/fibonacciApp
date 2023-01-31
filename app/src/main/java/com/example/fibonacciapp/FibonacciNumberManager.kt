package com.example.fibonacciapp

import android.util.Log

class FibonacciNumberManager() {

    fun getFibonaccuNumber(input: Int): Int?{
        var fibonacciValue: Int? = null
        if(input<=0) Log.e("Error", "incorrect input")
        else if(input == 1) fibonacciValue = 0
        else if(input == 2) fibonacciValue = 1
        else{
            fibonacciValue = getFibonaccuNumber(input-2)?.let { getFibonaccuNumber(input-1)?.plus(it) }
        }
        return fibonacciValue
    }
}