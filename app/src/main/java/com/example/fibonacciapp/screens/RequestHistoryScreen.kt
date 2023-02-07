package com.example.fibonacciapp.screens

import AppBar
import ListElementItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fibonacciapp.helpers.SaveDates
import com.example.fibonacciapp.helpers.SaveDates.Companion.DATES_KEY
import com.example.fibonacciapp.helpers.SaveDates.Companion.NUMBERS_KEY
import com.example.fibonacciapp.helpers.SaveDates.Companion.RESULTS_KEY
import com.example.fibonacciapp.helpers.intoListOfBigInt
import com.example.fibonacciapp.helpers.intoListOfInt
import com.example.fibonacciapp.helpers.intoListOfString
import java.math.BigInteger

@Composable
fun RequestHistoryScreen() {
    val ctx = LocalContext.current
    val dataStore = SaveDates(ctx)
    val dates = dataStore.getData(DATES_KEY).collectAsState(initial = "").value
    val numbers = dataStore.getData(NUMBERS_KEY).collectAsState(initial = "").value
    val results = dataStore.getData(RESULTS_KEY).collectAsState(initial = "").value
    var datesList by remember { mutableStateOf(emptyList<String>()) }
    var numbersList by remember { mutableStateOf(emptyList<Int>()) }
    var resultsList by remember { mutableStateOf(emptyList<BigInteger>()) }

    Column {
        AppBar(title = "History of requests")
        if (dates != "" && numbers != "" && results != "") {
            datesList = dates.intoListOfString()
            numbersList = numbers.intoListOfInt()
            resultsList = results.intoListOfBigInt()

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
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Request some numbers first!",
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                )
            }

        }
    }
}