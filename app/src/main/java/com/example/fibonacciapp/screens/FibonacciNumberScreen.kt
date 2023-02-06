package com.example.fibonacciapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fibonacciapp.helpers.SaveDates
import com.example.fibonacciapp.helpers.intoInt
import com.example.fibonacciapp.viewmodel.FibonacciViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.math.BigInteger
import java.util.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FibonacciNumberScreen() {
    var inputNumber by remember { mutableStateOf("") }
    val ctx = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var result by remember { mutableStateOf(BigInteger("0")) }
    var dateTime by remember { mutableStateOf("") }
    var requestedNumber by remember { mutableStateOf(emptyList<Int>()) }
    var requestDate by remember { mutableStateOf(emptyList<String>()) }
    var requestedResult by remember { mutableStateOf(emptyList<BigInteger>()) }
    val scope = rememberCoroutineScope()
    val dataStore = SaveDates(ctx)
    val viewModel: FibonacciViewModel = viewModel()
    val resultState = viewModel.resultStateFlow.observeAsState(initial = BigInteger("0"))
    val dates = dataStore.getData(SaveDates.DATES_KEY).collectAsState(initial = "").value
    val numbers = dataStore.getData(SaveDates.NUMBERS_KEY).collectAsState(initial = "").value
    val results = dataStore.getData(SaveDates.RESULTS_KEY).collectAsState(initial = "").value

    TopAppBar(
        title = { androidx.compose.material.Text(text = "Calculate n'th number of Fibonacci series") },
        Modifier.fillMaxWidth(),
        backgroundColor = Color.Gray
    )
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (dates != "" && numbers != "" && results != "") {
            requestDate = Gson().fromJson<List<String>?>(
                dates, object : TypeToken<List<String>>() {}.type
            )
            requestedNumber = Gson().fromJson<List<Int>?>(
                numbers, object : TypeToken<List<Int>>() {}.type
            )
            requestedResult = Gson().fromJson<List<BigInteger>?>(
                results, object : TypeToken<List<BigInteger>>() {}.type
            )
        }
        OutlinedTextField(
            value = inputNumber,
            onValueChange = { newText ->
                inputNumber = newText
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }),
            label = { Text(text = "Enter number ") }
        )
        Button(onClick = {
            keyboardController?.hide()
            val input: Int
            if (inputNumber.isDigitsOnly())
                input = inputNumber.intoInt()
            else {
                input = 1
                Toast.makeText(ctx, "Wrong input! Replaced with 1", Toast.LENGTH_SHORT)
                    .show()
            }
            dateTime = Calendar.getInstance().time.toString()
            viewModel.calculate(input).apply {
                resultState.value?.let { result = it }
            }
            requestedNumber += input
            requestDate += dateTime
            requestedResult += result
            dataStore.saveToDataStore(requestDate, requestedNumber, requestedResult, scope)
        }) {
            Text(text = "Get value")
        }
        Text(text = "Result: $result", modifier = Modifier.padding(16.dp))
    }
}
