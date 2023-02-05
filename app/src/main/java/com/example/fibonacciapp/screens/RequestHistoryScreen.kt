package com.example.fibonacciapp.screens

import ListElementItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fibonacciapp.helpers.SaveDates
import com.example.fibonacciapp.helpers.SaveDates.Companion.DATES_KEY
import com.example.fibonacciapp.helpers.SaveDates.Companion.NUMBERS_KEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun RequestHistoryScreen() {
    val ctx = LocalContext.current
    val dataStore = SaveDates(ctx)
    val dates = dataStore.getData(DATES_KEY).collectAsState(initial = "").value
    val numbers = dataStore.getData(NUMBERS_KEY).collectAsState(initial = "").value
    var datesList by remember { mutableStateOf(emptyList<String>()) }
    var numbersList by remember { mutableStateOf(emptyList<Int>()) }

    Column {
        if (dates != "") {
            datesList = Gson().fromJson<List<String>?>(
                dates, object : TypeToken<List<String>>() {}.type
            )
            numbersList = Gson().fromJson<List<Int>?>(
                numbers, object : TypeToken<List<Int>>() {}.type
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                if (dates != null) {
                    items(datesList.size) { it ->
                        ListElementItem(date = datesList[it], numbersList[it])
                    }
                }
            }
        }
    }
}