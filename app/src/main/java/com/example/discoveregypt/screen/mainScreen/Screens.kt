@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.discoveregypt.screen.mainScreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.discoveregypt.ui.theme.Gray
import com.example.discoveregypt.ui.theme.screenmain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Scaffold { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .background(screenmain)
                .verticalScroll(scrollState)
                .padding(
                    bottom = paddingValues.calculateBottomPadding() + 24.dp
                )
        ) {
            Header()
            IngredientsHeader()
            categories()

        }

    }
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