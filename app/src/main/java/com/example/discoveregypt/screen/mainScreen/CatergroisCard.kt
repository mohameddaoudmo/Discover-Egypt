package com.example.discoveregypt.screen.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.discoveregypt.R
import com.example.discoveregypt.ui.theme.Gold
import com.example.discoveregypt.ui.theme.YellowButton
import com.example.discoveregypt.ui.theme.firaSansFamily
import com.example.discoveregypt.ui.theme.notosan

data class Places(val name: String, val city: String, val photo: Int?)

val popularplaces = listOf<Places>(
    Places(name = "Vallay of Kings", city = "Luxor", photo = R.drawable.tempeldandara,),
        Places(name = "Vallay of Kings", city = "Luxor", photo = R.drawable.camal,)
)

@Composable
fun categories() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(popularplaces) {
            Cardcategories(city = it.name, place = it.city, modifier = Modifier
                .fillMaxSize()
                .width(250.dp)
                .height(230.dp)
                .shadow(10.dp, RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(6.dp)))
        }
    }
}

@Composable
fun Cardcategories(modifier: Modifier= Modifier, place: String,city :String) {

    Card(
        modifier = modifier

    ) {


        Box(


        ) {

            Image(
                painter = painterResource(id = R.drawable.ramasis),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            description(modifier = Modifier.align(Alignment.BottomCenter),place,city)


        }
    }
}

@Composable
fun description(modifier: Modifier = Modifier, place: String,city :String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.radialGradient(
                    listOf(
                        Color(0x12FFFFFF), Color(0xDFFFFFF), Color(0x9FFFFFFF)

                    ), radius = 900f, center = Offset.Infinite
                )
            )

            .padding(4.dp)
    ) {
        Column {
            Text(
                text = city,
                color = YellowButton,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = notosan, fontStyle = FontStyle.Normal
            )
            Row() {
                Text(
                    text = place,
                    color = YellowButton,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = firaSansFamily
                )

            }
        }
    }
}

@Preview(showBackground = true, wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE)
@Composable
fun Cardpreview() {
    // Preview your composable function here
    categories()
}