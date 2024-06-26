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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.discoveregypt.R
import com.example.discoveregypt.ui.theme.Gold
import com.example.discoveregypt.ui.theme.firaSansFamily
import com.example.discoveregypt.ui.theme.textYellow

data class Cites(val name: String, val photo: Int?)

val cites = listOf<Cites>(
    Cites(name = "Cario", photo = R.drawable.tempeldandara,),
    Cites(name = "Giza", photo = R.drawable.camal,)
)

@Composable
fun Cites() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(cites) {
            CardCites(city =it.name , modifier = Modifier
                .fillMaxSize()
                .width(220.dp)
                .height(270.dp)
                .shadow(10.dp, RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(6.dp)))
        }
    }
}

@Composable
fun CardCites(modifier: Modifier = Modifier, city :String) {

    Card(
        modifier = modifier

    ) {


        Box(


        ) {

            AsyncImage(
                model = "https://d3rr2gvhjw0wwy.cloudfront.net/uploads/mandators/49581/file-manager/cairo-city-egypt-2020.jpg",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),

                contentDescription = null,
            )
            descriptionCitesCard(modifier = Modifier.align(Alignment.BottomCenter),city)


        }
    }
}

@Composable
fun descriptionCitesCard(modifier: Modifier = Modifier,city :String) {

    Box(

        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.radialGradient(
                    listOf(
                        Color(0x9FFFFFFF), Color(0x9FFFFFFF), Color(0x9FFFFFFF)

                    ), radius = 100f, center = Offset.Infinite
                )
            )

            .padding(4.dp)
    ) {
        Column {
            Text(
                text = city,
                color =textYellow,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = firaSansFamily, fontStyle = FontStyle.Normal
            )
            Row() {
                Text(
                    text = "",
                    color = textYellow,
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
fun CitesCardpreview() {
    // Preview your composable function here
    categories()
}