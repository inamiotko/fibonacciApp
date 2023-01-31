package com.example.fibonacciapp.navigation

sealed class Screen (val route: String){
    object FibonacciNumberScreen : Screen("fibonacci_number_screen")
    object RequestHistoryScreen : Screen("history_screen")

}

