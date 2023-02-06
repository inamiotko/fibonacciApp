package com.example.fibonacciapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fibonacciapp.helpers.FibonacciNumberManager
import kotlinx.coroutines.launch
import java.math.BigInteger

class FibonacciViewModel() : ViewModel() {
    private val fibonacciNumberManager = FibonacciNumberManager()
    private val _resultStateFlow = MutableLiveData<BigInteger?>()

    val resultStateFlow: LiveData<BigInteger?>
        get() = _resultStateFlow

    fun calculate(input: Int) {
        viewModelScope.launch {
            val res = fibonacciNumberManager.getFibonacciNumber(input)
            _resultStateFlow.value = res
        }
    }
}