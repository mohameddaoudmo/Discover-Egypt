package com.example.discoveregypt

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Main : Screen("Main/", R.string.main)
    object Favourite : Screen("Favourite/", R.string.Favourite)
}