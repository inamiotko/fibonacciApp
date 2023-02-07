package com.example.fibonacciapp.helpers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.math.BigInteger

fun String.intoInt(): Int {
    return this.filter(Char::isDigit).toInt()
}

fun String?.intoListOfString(): List<String> {
    return Gson().fromJson<List<String>?>(
        this, object : TypeToken<List<String>>() {}.type
    )
}

fun String?.intoListOfBigInt(): List<BigInteger> {
    return Gson().fromJson<List<BigInteger>?>(
        this, object : TypeToken<List<BigInteger>>() {}.type
    )
}

fun String?.intoListOfInt(): List<Int> {
    return Gson().fromJson<List<Int>?>(
        this, object : TypeToken<List<Int>>() {}.type
    )
}