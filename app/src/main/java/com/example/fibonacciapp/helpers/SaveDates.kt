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

class SaveDates(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("dates")
        val DATES_KEY = stringPreferencesKey("dates_key")
        val NUMBERS_KEY = stringPreferencesKey("numbers_key")
    }

    fun getData(key: Preferences.Key<String>): Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[key] ?: ""
        }

    private suspend fun saveInput(listOfDatesNumbers: String, key: Preferences.Key<String>) {
        context.dataStore.edit { preferences ->
            preferences[key] = listOfDatesNumbers
        }
    }

    fun saveToSharedPrefs(dates: List<String>, numbers: List<Int>, scope: CoroutineScope) {
        val datesToBeSaved = Gson().toJson(dates)
        val numbersToBeSaved = Gson().toJson(numbers)
        scope.launch {
            saveInput(datesToBeSaved, DATES_KEY)
            saveInput(numbersToBeSaved, NUMBERS_KEY)
        }
    }
}