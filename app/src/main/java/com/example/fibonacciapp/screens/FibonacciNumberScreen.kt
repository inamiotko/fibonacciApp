package com.example.fibonacciapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fibonacciapp.helpers.SaveDates
import com.example.fibonacciapp.viewmodel.FibonacciViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FibonacciNumberScreen() {
    var inputNumber by remember { mutableStateOf("1") }
    val ctx = LocalContext.current
    var result by remember { mutableStateOf(1) }
    var dateTime by remember { mutableStateOf("") }
    var requestedNumber by remember { mutableStateOf(emptyList<Int>()) }
    var requestDate by remember { mutableStateOf(emptyList<String>()) }
    val scope = rememberCoroutineScope()
    val dataStore = SaveDates(ctx)
    val viewModel: FibonacciViewModel = viewModel()
    val resultState = viewModel.resultStateFlow.observeAsState(initial = 0)
    val dates = dataStore.getData(SaveDates.DATES_KEY).collectAsState(initial = "").value
    val numbers = dataStore.getData(SaveDates.NUMBERS_KEY).collectAsState(initial = "").value

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (dates != "") {
            requestDate = Gson().fromJson<List<String>?>(
                dates, object : TypeToken<List<String>>() {}.type
            )
            requestedNumber = Gson().fromJson<List<Int>?>(
                numbers, object : TypeToken<List<Int>>() {}.type
            )
        }
        OutlinedTextField(
            value = inputNumber,
            onValueChange = { newText ->
                inputNumber = newText
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Enter number ") }
        )
        Button(onClick = {
            val input = if (inputNumber != "") inputNumber.toInt() else 1
            dateTime = Calendar.getInstance().time.toString()
            viewModel.calculate(input)
            resultState.value?.let { result = it }
            requestedNumber += input
            requestDate += dateTime
            dataStore.saveToSharedPrefs(requestDate, requestedNumber, scope)
        }) {
            Text(text = "Get value")
        }
        Text(text = "$result $dateTime")
    }
}
