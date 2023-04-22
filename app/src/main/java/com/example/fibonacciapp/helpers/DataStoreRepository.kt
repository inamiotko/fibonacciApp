package com.example.fibonacciapp.helpers

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import java.math.BigInteger


interface DataStoreRepository {

    fun getData(key: Preferences.Key<String>): Flow<String?>

    fun saveToDataStore(
        dates: List<String>,
        numbers: List<Int>,
        results: List<BigInteger>,
        scope: CoroutineScope
    )
}