package com.example.fibonacciapp.helpers

fun String.intoInt(): Int {
    return this.filter(Char::isDigit).toInt()
}

