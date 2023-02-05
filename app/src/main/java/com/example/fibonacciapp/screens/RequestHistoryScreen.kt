package com.example.fibonacciapp.screens

import ListElementItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fibonacciapp.helpers.SaveDates
import com.example.fibonacciapp.helpers.SaveDates.Companion.DATES_KEY
import com.example.fibonacciapp.helpers.SaveDates.Companion.NUMBERS_KEY
import com.example.fibonacciapp.helpers.SaveDates.Companion.RESULTS_KEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun RequestHistoryScreen() {
    val ctx = LocalContext.current
    val dataStore = SaveDates(ctx)
    val dates = dataStore.getData(DATES_KEY).collectAsState(initial = "").value
    val numbers = dataStore.getData(NUMBERS_KEY).collectAsState(initial = "").value
    val results = dataStore.getData(RESULTS_KEY).collectAsState(initial = "").value
    var datesList by remember { mutableStateOf(emptyList<String>()) }
    var numbersList by remember { mutableStateOf(emptyList<Int>()) }
    var resultsList by remember { mutableStateOf(emptyList<Long>()) }

    Column {
        TopAppBar(
            title = { Text(text = "History of requests") },
            backgroundColor = Color.Gray
        )
        if (dates != "") {
            datesList = Gson().fromJson<List<String>?>(
                dates, object : TypeToken<List<String>>() {}.type
            )
            numbersList = Gson().fromJson<List<Int>?>(
                numbers, object : TypeToken<List<Int>>() {}.type
            )
            resultsList = Gson().fromJson<List<Long>?>(
                results, object : TypeToken<List<Long>>() {}.type
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 48.dp
                )
            ) {
                if (datesList.isNotEmpty()) {
                    items(datesList.size) { it ->
                        ListElementItem(date = datesList[it], numbersList[it], resultsList[it])
                    }
                }
            }
        }
    }
}