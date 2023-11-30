package com.example.discoveregypt

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.discoveregypt.screen.HomeScreen

object AppRoute {

    @Composable
    fun GenerateRoute(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = AppRouteName.Home,
        ) {
            composable(AppRouteName.Home) {
                HomeScreen(navController = navController)
            }

        }
    }
}