package com.example.discoveregypt.screen.bottombar


import android.annotation.SuppressLint
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainBottomAppBarWithFab() {
    val content = remember { mutableStateOf("Home Screen") }
    val selectedItem = remember { mutableStateOf("home") }
    val openDialog = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Bottom App Bar with FAB"
            )
        }, navigationIcon = {
            IconButton(onClick = {
                content.value = "Navigation Drawer"
            }) {
                Icon(Icons.Filled.Menu, contentDescription = "")
            }
        }, backgroundColor = Color.Magenta, elevation = AppBarDefaults.TopAppBarElevation
        )
    },

        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(15.dp)
            ) {
                Text(
                    text = content.value,
                    color = Color.Black,
                    fontSize = 25.sp,
                    modifier = Modifier.align(Alignment.Center)
                )

                FloatAlertDialog(openDialog = openDialog)
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openDialog.value = true
                }, shape = RoundedCornerShape(50), backgroundColor = Color.Magenta
            ) {
                Icon(Icons.Filled.Add, tint = Color.White, contentDescription = "Add")
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center, //if its not set, it's show default position

        bottomBar = {
            BottomAppBar(cutoutShape = RoundedCornerShape(50), content = {
                BottomNavigation {
                    BottomNavigationItem(selected = selectedItem.value == "home", onClick = {
                        content.value = "Home Screen"
                        selectedItem.value = "home"
                    }, icon = {
                        Icon(Icons.Filled.Home, contentDescription = "home")
                    }, label = { Text(text = "Home") }, alwaysShowLabel = false
                    )

                    BottomNavigationItem(selected = selectedItem.value == "Setting", onClick = {
                        content.value = "Setting Screen"
                        selectedItem.value = "setting"
                    }, icon = {
                        Icon(Icons.Filled.Settings, contentDescription = "setting")
                    }, label = { Text(text = "Setting") }, alwaysShowLabel = false
                    )
                }
            })
        })
}


@Composable
fun FloatAlertDialog(openDialog: MutableState<Boolean>) {
    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
            openDialog.value = false
        }, title = {
            Text(text = "Floating Action", fontWeight = FontWeight.Bold)
        }, text = {
            Text(text = "Let's Start...")
        }, confirmButton = {
            Button(onClick = {
                openDialog.value = false
            }) {
                Text(text = "Ok")
            }
        })
    }
}











