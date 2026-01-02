package com.example.uiproject

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "GetStarted") {
        composable("GetStarted") {
            GetStarted(
                NavigateToHome = { navController.navigate("Home") },
                navController = navController
            )

        }
        composable("Home") {
            Home(onQueryUpdate = { newValue ->

            })
        }
    }
}