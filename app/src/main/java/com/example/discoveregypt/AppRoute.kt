package com.example.discoveregypt

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.discoveregypt.screen.mainScreen.HomeScreen

object AppRoute {

    @Composable
    fun GenerateRoute(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Screen.Main.route,
        ) {
            composable(Screen.Main.route) {

            }

        }
    }
}