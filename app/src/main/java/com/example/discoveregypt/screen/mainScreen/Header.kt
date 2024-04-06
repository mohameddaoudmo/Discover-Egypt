package com.example.discoveregypt.screen.mainScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.discoveregypt.R
import com.example.discoveregypt.ui.theme.Gray
import com.example.discoveregypt.ui.theme.Yellow
import com.example.discoveregypt.ui.theme.firaSansFamily
import com.example.discoveregypt.ui.theme.screenmain
import com.example.discoveregypt.ui.theme.textYellow
import kotlinx.coroutines.delay
import kotlin.math.max
import kotlin.math.min

data class HeaderData(val Image: String, val title: String)

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun Header(scrollState: ScrollState ,ondrawerClick :()->Unit
) {
    var page by remember {
        mutableStateOf(0)
    }
    val bannersState = remember {
        mutableStateListOf(
            HeaderData(
                "https://images.pexels.com/photos/262780/pexels-photo-262780.jpeg",
                "sphinx"
            ),
            HeaderData(
                "https://images.pexels.com/photos/1755390/pexels-photo-1755390.jpeg",
                "Nile "
            ),
            HeaderData(
                "https://cdn.britannica.com/47/194547-050-52813FB0/aerial-view-Cairo-Egypt.jpg",
                "Great Pyramid of Giza"
            )


        )
    }
    var bannerIndex by remember { mutableStateOf(0) }

    val pagerState = rememberPagerState(pageCount = {
        bannersState.size
    })
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            bannerIndex = page
        }
    }

    /// auto scroll
    LaunchedEffect(Unit) {
        while (true) {
            delay(10_000)

            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % pagerState.pageCount,
                animationSpec = tween(durationMillis = 1500)
            )
        }
    }
    Box(
        modifier = Modifier
            .height(400.dp)
            .clip(shape = RoundedCornerShape(bottomEnd = 18.dp, bottomStart = 18.dp))
    ) {
        HorizontalPager(state = pagerState) {
            SideEffect {
                page = it

            }
            Box(modifier = Modifier.fillMaxSize()) {
                val height = 400.dp

                AsyncImage(
                    model = bannersState[it].Image,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().graphicsLayer {
                        alpha = min(
                            1f,
                            max(
                                0.0f,
                                1 - (scrollState.value / ((height.value * 2) + (height.value / 1.5f)))
                            )
                        )
                    },

                    contentDescription = null,
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxSize()
//                        .fillMaxWidth()
//                        .height(100.dp)
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.6f, Color.Transparent), Pair(1f, screenmain)
                                )
                            )
                        )

                )
                LazyLazyGrid@
                AnimatedContent(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp),

                    targetState = bannersState, transitionSpec = {
                        fadeIn(animationSpec = tween(durationMillis = 500)) with fadeOut(
                            animationSpec = tween(
                                durationMillis = 500
                            )
                        )
                    }, contentAlignment = Alignment.Center
                ) { targetCount ->
                    Text(
                        text = targetCount[it].title,
                        color = textYellow,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(start = 8.dp, top = 250.dp),
                        fontSize = 28.sp,
                        fontFamily = firaSansFamily,
                        fontWeight = FontWeight.Bold
                    )
                }


            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(top = 100.dp)
        ) {
            repeat(bannersState.size) { index ->
                val width = 12.dp
                val height = if (index == bannerIndex) 28.dp else 12.dp
                val color = if (index == bannerIndex) Yellow else Gray

                Surface(
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .size(width, height)
                        .clip(RoundedCornerShape(20.dp)),
                    color = color,
                ) {}
            }
        }



        textfieldPart({})
        TapRow(ondrawerClick)
        Spacer(modifier = Modifier.height(height = 12.dp))


    }

}