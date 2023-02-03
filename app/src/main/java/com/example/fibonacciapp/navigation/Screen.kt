package com.example.fibonacciapp.navigation

import com.example.fibonacciapp.R

sealed class Screen(val route: String, val icon: Int, val title: String) {
    object FibonacciNumberScreen : Screen("fibonacci_number_screen", R.drawable.ic_fibonacci, "Fibonacci number")
    object RequestHistoryScreen : Screen("history_screen", R.drawable.ic_history, "History")

}

