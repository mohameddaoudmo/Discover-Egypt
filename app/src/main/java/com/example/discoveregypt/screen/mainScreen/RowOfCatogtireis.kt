package com.example.discoveregypt.screen.mainScreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.discoveregypt.R
import com.example.discoveregypt.ui.theme.YellowButton
import com.example.discoveregypt.ui.theme.buttongrey
import com.example.discoveregypt.ui.theme.firaSansFamily

data class CatergroisRow(val name: String, val id: Int)

val list = listOf<CatergroisRow>(
    CatergroisRow("Pyramdis", R.drawable.pyramid),
    CatergroisRow("Popular", R.drawable.pyramid),
    CatergroisRow("Beach", R.drawable.pyramid),
    CatergroisRow("Temple", R.drawable.pyramid),
    CatergroisRow("Resort", R.drawable.pyramid),
    CatergroisRow("Pyramdis", R.drawable.pyramid),


    )

@Composable
fun IngredientsHeader() {
    var currentSelectedIndex by remember {
        mutableStateOf(-1)
    }

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),

        contentPadding = PaddingValues(start = 12.dp),
        modifier = Modifier
            .padding( vertical = 16.dp)
            .fillMaxWidth()
            .height(44.dp)
    ) {
        itemsIndexed(list, key = { index, item ->
            index
        }) { index, it ->
            var active = currentSelectedIndex == index
            TabButton(text = it.name, active = active, id = it.id) {
                currentSelectedIndex = index
            }
        }

    }
}

@Composable
fun TabButton(
    text: String,
    active: Boolean,
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    onClick: () -> Unit
) {

    Button(

        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .fillMaxHeight()
            .width(IntrinsicSize.Min),
        elevation = null,
        colors = if (active) ButtonDefaults.buttonColors(
            containerColor = YellowButton, contentColor = Color.White
        ) else ButtonDefaults.buttonColors(
            containerColor = buttongrey, contentColor = DarkGray
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = id), contentDescription = null)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text, fontFamily = firaSansFamily, fontWeight = FontWeight.SemiBold)
        }
    }
}