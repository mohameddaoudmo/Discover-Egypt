package com.example.discoveregypt

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.discoveregypt.screen.HomeScreenDrawer
import com.example.discoveregypt.screen.MainDrawer
import com.example.discoveregypt.screen.mainScreen.HomeScreen
import com.example.discoveregypt.ui.theme.DiscoverEgyptTheme
import com.example.discoveregypt.ui.theme.buttongrey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            ), navigationBarStyle = SystemBarStyle.light(Color.LTGRAY, Color.LTGRAY)
        )
        super.onCreate(savedInstanceState)
        setContent {
            DiscoverEgyptTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    val navController = rememberNavController()

//                    AppRoute.GenerateRoute(navController = navController)
                    val scrollState = rememberScrollState()

                    MainDrawer(scrollState, navController)


                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

}