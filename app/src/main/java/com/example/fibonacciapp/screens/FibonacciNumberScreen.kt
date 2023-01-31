package com.example.fibonacciapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.fibonacciapp.FibonacciNumberManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FibonacciNumberScreen() {
    var inputNumber by remember { mutableStateOf("1") }
    var input = 1
    val fibonacciNumberManager = FibonacciNumberManager()
    Column(Modifier.fillMaxSize()) {
        OutlinedTextField(

            value = inputNumber,
            onValueChange = { newText ->
                inputNumber = newText
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(text = "Enter number ")}

        )
        input = if(inputNumber != "") inputNumber.toInt() else 1
        Text(text = "${fibonacciNumberManager.getFibonaccuNumber(input)}")
    }
}