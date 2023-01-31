package com.example.fibonacciapp.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarNavigation(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.Gray,
            ) {
                bottomNavItems.forEach { item ->
                    val selected = item.route == backStackEntry.value?.destination?.route

                    NavigationBarItem(
                        selected = selected,
                        onClick = { navController.navigate(item.route) },
                        label = {
                            Text(
                                text = item.name,
                                fontWeight = FontWeight.SemiBold,
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = "${item.name} Icon",
                            )
                        }
                    )
                }
            }
        },
        content = {
            //Your UI Content
        }
    )
}