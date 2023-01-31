package com.example.fibonacciapp.navigation

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.fibonacciapp.R

data class BottomNavItem(val name: String,
                         val route: String,
                         val icon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(
        name = "Number Screen",
        route = "home",
        icon = Icons.Rounded.Settings,
    ),
    BottomNavItem(
        name = "History",
        route = "add",
        icon = Icons.Rounded.AddCircle,
    )
)