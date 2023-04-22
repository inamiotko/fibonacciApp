package com.example.fibonacciapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fibonacciapp.helpers.DataStoreRepository
import com.example.fibonacciapp.helpers.DataStoreRepositoryImpl.Companion.DATES_KEY
import com.example.fibonacciapp.helpers.DataStoreRepositoryImpl.Companion.NUMBERS_KEY
import com.example.fibonacciapp.helpers.DataStoreRepositoryImpl.Companion.RESULTS_KEY
import com.example.fibonacciapp.helpers.FibonacciNumberManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.math.BigInteger

class FibonacciViewModel() : ViewModel(), KoinComponent {
    private val fibonacciNumberManager = FibonacciNumberManager()
    private val _resultStateFlow = MutableLiveData<BigInteger?>()
    private val dataStore: DataStoreRepository by inject()

    val dates = dataStore.getData(DATES_KEY)
    val numbers = dataStore.getData(NUMBERS_KEY)
    val results = dataStore.getData(RESULTS_KEY)

    val resultStateFlow: LiveData<BigInteger?>
        get() = _resultStateFlow

    fun calculate(input: Int) {
        viewModelScope.launch {
            val res = fibonacciNumberManager.getFibonacciNumber(input)
            _resultStateFlow.value = res
        }
    }

    fun saveToDataStore(
        requestDate: List<String>, requestedNumber: List<Int>,
        requestedResult: List<BigInteger>, scope: CoroutineScope
    ) {
        dataStore.saveToDataStore(requestDate, requestedNumber, requestedResult, scope)
    }
}