package com.example.discoveregypt.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpaper
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.discoveregypt.R
import com.example.discoveregypt.ui.theme.White
import com.example.discoveregypt.ui.theme.firaSansFamily

@Composable
fun TapRow() {

    Row(
        Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp, vertical = 16.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {},
        ) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
        }
        Text(
            text = "Discover Egypt",
            color = White,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f),
            fontFamily =firaSansFamily
        )
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "profile photo",
            modifier = Modifier
                .height(40.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(22.dp))
        )
    }
}

@Preview(wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE)
@Composable
fun MyComposablePreview() {
    // Preview your composable function here
    TapRow()
}