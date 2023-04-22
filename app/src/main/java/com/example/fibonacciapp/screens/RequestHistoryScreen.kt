package com.example.fibonacciapp.screens

import AppBar
import ListElementItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fibonacciapp.helpers.intoListOfBigInt
import com.example.fibonacciapp.helpers.intoListOfInt
import com.example.fibonacciapp.helpers.intoListOfString
import com.example.fibonacciapp.viewmodel.FibonacciViewModel
import java.math.BigInteger

@Composable
fun RequestHistoryScreen() {
    val viewModel: FibonacciViewModel = viewModel()
    val dates = viewModel.dates.collectAsState(initial = "").value
    val numbers = viewModel.numbers.collectAsState(initial = "").value
    val results = viewModel.results.collectAsState(initial = "").value
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