package com.example.discoveregypt.screen

import android.icu.text.CaseMap.Title
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.discoveregypt.R
import com.example.discoveregypt.ui.theme.Gray
import com.example.discoveregypt.ui.theme.Yellow
import com.example.discoveregypt.ui.theme.YellowButton
import com.example.discoveregypt.ui.theme.firaSansFamily
import com.example.discoveregypt.ui.theme.screenmain
import com.example.discoveregypt.ui.theme.textYellow
import kotlinx.coroutines.delay

data class HeaderData(val Image: Int, val title: String)

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun Header() {
    var page by remember {
        mutableStateOf(0)
    }
    val banners = listOf(
        HeaderData(R.drawable.ramasis, "Ramsis"),
        HeaderData(R.drawable.tempeldandara, "Dendera "),
        HeaderData(
            R.drawable.pytmaids, "Great Pyramid of Giza"
        )


    )
    var bannerIndex by remember { mutableStateOf(0) }

    val pagerState = rememberPagerState(pageCount = {
        banners.size
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
            tween<Float>(1500)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % pagerState.pageCount
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
                Image(
                    painter = painterResource(id = banners[it].Image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
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
                AnimatedContent(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 10.dp),

                    targetState = banners, transitionSpec = {
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
            repeat(banners.size) { index ->
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


        TapRow()
        textfieldPart({})

        Spacer(modifier = Modifier.height(height = 12.dp))


    }

}


