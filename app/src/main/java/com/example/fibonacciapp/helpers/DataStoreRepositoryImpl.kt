package com.example.fibonacciapp.helpers

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.math.BigInteger

class DataStoreRepositoryImpl(private val context: Context) : DataStoreRepository {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("dates")
        val DATES_KEY = stringPreferencesKey("dates_key")
        val NUMBERS_KEY = stringPreferencesKey("numbers_key")
        val RESULTS_KEY = stringPreferencesKey("results_key")
    }

    override fun getData(key: Preferences.Key<String>): Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[key] ?: ""
        }

    private suspend fun saveInput(listOfDatesNumbers: String, key: Preferences.Key<String>) {
        context.dataStore.edit { preferences ->
            preferences[key] = listOfDatesNumbers
        }
    }

    override fun saveToDataStore(
        dates: List<String>,
        numbers: List<Int>,
        results: List<BigInteger>,
        scope: CoroutineScope
    ) {
        val datesToBeSaved = Gson().toJson(dates)
        val numbersToBeSaved = Gson().toJson(numbers)
        val resultToBeSaved = Gson().toJson(results)
        scope.launch {
            saveInput(datesToBeSaved, DATES_KEY)
            saveInput(numbersToBeSaved, NUMBERS_KEY)
            saveInput(resultToBeSaved, RESULTS_KEY)
        }
    }
}