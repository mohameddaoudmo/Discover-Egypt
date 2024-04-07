@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.discoveregypt.screen.mainScreen

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.discoveregypt.R
import com.example.discoveregypt.Screen
import com.example.discoveregypt.screen.BottomNavigationAnimation
import com.example.discoveregypt.ui.theme.Gray
import com.example.discoveregypt.ui.theme.Juicy
import com.example.discoveregypt.ui.theme.PurpleGrey40
import com.example.discoveregypt.ui.theme.YellowButton
import com.example.discoveregypt.ui.theme.buttongrey
import com.example.discoveregypt.ui.theme.firaSansFamily
import com.example.discoveregypt.ui.theme.screenmain
import com.exyte.animatednavbar.animation.indendshape.Height
import com.jetpack.scrollabletoolbar.viewmodel.ScrollViewModel
import com.juraj.fluid.MainBottom

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController, ondrawerClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val content = remember { mutableStateOf("Home Screen") }
    val selectedItem = remember { mutableStateOf("home") }
    val openDialog = remember { mutableStateOf(false) }

    androidx.compose.material.Scaffold(

        topBar = {  },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openDialog.value = true
                }, shape = RoundedCornerShape(50), backgroundColor = YellowButton
            ) {
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.egypt3),
                    tint = Color.DarkGray,
                    contentDescription = "Add"
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                backgroundColor = buttongrey,
                cutoutShape = RoundedCornerShape(50),
                content = {
                    BottomNavigation(backgroundColor = buttongrey) {
                        BottomNavigationItem(selected = selectedItem.value == "home",
                            onClick = {
                                navController.navigate(Screen.Main.route)
                                selectedItem.value = "home"
                            },
                            icon = {
                                androidx.compose.material.Icon(
                                    Icons.Filled.Home,
                                    contentDescription = "home"
                                )
                            },
                            label = { androidx.compose.material.Text(text = "Home", fontWeight = FontWeight.Medium, fontFamily = firaSansFamily) },
                            alwaysShowLabel = false
                        )

                        BottomNavigationItem(selected = selectedItem.value == "Favorite",
                            onClick = {
                                selectedItem.value = "Favorite"
                                navController.navigate(Screen.Favourite.route)
                            },
                            icon = {
                                androidx.compose.material.Icon(
                                    Icons.Filled.Favorite,
                                    contentDescription = "setting"
                                )
                            },
                            label = { androidx.compose.material.Text(text = "Favourite",  fontWeight = FontWeight.Medium,fontFamily = firaSansFamily) },
                            alwaysShowLabel = false
                        )
                    }
                })
        }) {
        NavHost(
            navController, startDestination = Screen.Main.route,
        ) {

            composable(Screen.Main.route, enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }, exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }) { MainScreen(scrollState, ondrawerClick) }
            composable(Screen.Favourite.route, enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }, exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }) { Favourite() }
        }
    }

}

@Composable
fun Favourite() {
    Box(modifier = Modifier.fillMaxSize()) {}

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(scrollState: ScrollState, ondrawerClick: () -> Unit) {
    Column(

        Modifier
            .fillMaxSize()
            .background(screenmain)
            .verticalScroll(scrollState)


    ) {
        Header(scrollState, ondrawerClick)
        Type()
        categories()
        Text(
            text = "City",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = firaSansFamily,
            color = White,
            modifier = Modifier.padding(top = 24.dp, start = 12.dp, bottom = 24.dp)
        )
        Cites()
        Spacer(modifier = Modifier.height(200.dp))
    }

    @Composable
    fun textfieldPart(onValueChange: () -> Unit) {
        Box(
            modifier = Modifier
                .padding(top = 110.dp, start = 5.dp, end = 5.dp)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            TextField(
                value = "",
                onValueChange = { onValueChange },
                label = { Text(text = "Search", fontSize = 12.sp) },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search, contentDescription = "Search"
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Settings, contentDescription = "setting"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier

                    .fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textfieldPart(onValueChange: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 110.dp, start = 5.dp, end = 5.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        TextField(
            value = "",
            onValueChange = { onValueChange },
            label = { Text(text = "Search", fontSize = 12.sp) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search, contentDescription = "Search"
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Settings, contentDescription = "setting"
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier

                .fillMaxSize()
        )
    }
}

@Composable
fun CircularButton(
    @DrawableRes iconResouce: Int,
    color: Color = Gray,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(contentColor = color, containerColor = Cyan),
        elevation = elevation,
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
    ) {
        Icon(painterResource(id = iconResouce), null)
    }
}


