package com.example.discoveregypt.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.discoveregypt.R
import com.example.discoveregypt.screen.mainScreen.HomeScreen
import com.example.discoveregypt.screen.mainScreen.MainScreen
import kotlinx.coroutines.launch
import android.content.Context
import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView


@Composable
fun MainDrawer(scrollState: ScrollState,navController: NavHostController){
    val context =LocalContext.current
    HomeScreenDrawer(context.getVideoUri(),scrollState, navController)

}
private fun Context.getVideoUri(): Uri {
    val rawId = resources.getIdentifier("egypt", "raw", packageName)
    val videoUri = "android.resource://$packageName/$rawId"
    return Uri.parse(videoUri)
}




private fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }

private fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        useController = false
        resizeMode = RESIZE_MODE_ZOOM
    }


@Composable
fun DrawerMain(videoUri: Uri,scrollState: ScrollState,navController: NavHostController) {
    val context = LocalContext.current

    val exoPlayer = remember { context.buildExoPlayer(videoUri) }


}










@Composable
fun HomeScreenDrawer(videoUri: Uri,scrollState: ScrollState,navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current

        val exoPlayer = remember { context.buildExoPlayer(videoUri) }
        var drawerState by remember {
            mutableStateOf(DrawerState.Closed)
        }
        var screenState by remember {
            mutableStateOf(ScreenDrawer.Home)
        }

        val translationX = remember {
            Animatable(0f)
        }

        val drawerWidth = with(LocalDensity.current) {
            DrawerWidth.toPx()
        }
        translationX.updateBounds(0f, drawerWidth)

        val coroutineScope = rememberCoroutineScope()

        fun toggleDrawerState() {
            coroutineScope.launch {
                if (drawerState == DrawerState.Open) {
                    translationX.animateTo(0f)
                } else {
                    translationX.animateTo(drawerWidth)
                }
                drawerState = if (drawerState == DrawerState.Open) {
                    DrawerState.Closed
                } else {
                    DrawerState.Open
                }
            }
        }
        DisposableEffect(
            AndroidView(
                factory = { it.buildPlayerView(exoPlayer) },
                modifier = Modifier.fillMaxSize()
            )
        ) {
            onDispose {
                exoPlayer.release()
            }
        }

        ProvideWindowInsets {
            HomeScreenDrawerContents(
                selectedScreen = screenState,
                onScreenSelected = { screen ->
                    screenState = screen
                }
            )        }


        val draggableState = rememberDraggableState(onDelta = { dragAmount ->
            coroutineScope.launch {
                translationX.snapTo(translationX.value + dragAmount)
            }
        })
        val decay = rememberSplineBasedDecay<Float>()
        ScreenContents(
            selectedScreen = screenState,
            onDrawerClicked = ::toggleDrawerState,
            scrollState= scrollState,
            navController = navController,
            modifier = Modifier
                .graphicsLayer {
                    this.translationX = translationX.value
                    val scale = lerp(1f, 0.8f, translationX.value / drawerWidth)
                    this.scaleX = scale
                    this.scaleY = scale
                    val roundedCorners = lerp(0f, 32.dp.toPx(), translationX.value / drawerWidth)
                    this.shape = RoundedCornerShape(roundedCorners)
                    this.clip = true
                    this.shadowElevation = 32f
                }
                // This example is showing how to use draggable with custom logic on stop to snap to the edges
                // You can also use `anchoredDraggable()` to set up anchors and not need to worry about more calculations.
                .draggable(
                    draggableState, Orientation.Horizontal,
                    onDragStopped = { velocity ->
                        val targetOffsetX = decay.calculateTargetValue(
                            translationX.value,
                            velocity
                        )
                        coroutineScope.launch {
                            val actualTargetX = if (targetOffsetX > drawerWidth * 0.5) {
                                drawerWidth
                            } else {
                                0f
                            }
                            // checking if the difference between the target and actual is + or -
                            val targetDifference = (actualTargetX - targetOffsetX)
                            val canReachTargetWithDecay =
                                (
                                        targetOffsetX > actualTargetX && velocity > 0f &&
                                                targetDifference > 0f
                                        ) ||
                                        (
                                                targetOffsetX < actualTargetX && velocity < 0 &&
                                                        targetDifference < 0f
                                                )
                            if (canReachTargetWithDecay) {
                                translationX.animateDecay(
                                    initialVelocity = velocity,
                                    animationSpec = decay
                                )
                            } else {
                                translationX.animateTo(actualTargetX, initialVelocity = velocity)
                            }
                            drawerState = if (actualTargetX == drawerWidth) {
                                DrawerState.Open
                            } else {
                                DrawerState.Closed
                            }
                        }
                    }
                )
        )
    }
}

@Composable
private fun ScreenContents(
    selectedScreen: ScreenDrawer,
    onDrawerClicked: () -> Unit,
    modifier: Modifier = Modifier
    ,scrollState: ScrollState,
    navController: NavHostController
) {
    Box(modifier) {
        when (selectedScreen) {
            ScreenDrawer.Home ->
                HomeScreen(navController ,onDrawerClicked)


            ScreenDrawer.News ->
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                }

            ScreenDrawer.Metro ->
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                }



            else -> {}
        }
    }
}

private enum class DrawerState {
    Open,
    Closed
}

@Composable
private fun HomeScreenDrawerContents(
    selectedScreen: ScreenDrawer,
    onScreenSelected: (ScreenDrawer) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.Transparent),
        verticalArrangement = Arrangement.Center
    ) {
        ScreenDrawer.values().forEach {

            NavigationDrawerItem(
                modifier=Modifier.border(BorderStroke(1.dp, color = Color.Black),
                  shape =   RoundedCornerShape(12.dp)
                ).padding(8.dp),
                label = {
                    Text(it.text)
                },
                icon = {
                    Icon(painter = painterResource(id =it.icon ) , contentDescription = it.text)
                },
                colors =
                NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent, selectedContainerColor =Color.Transparent ),
                selected = selectedScreen == it,
                onClick = {
                    onScreenSelected(it)
                },
            )
            Spacer(modifier = Modifier.height(12.dp))

        }
    }
}

private val DrawerWidth = 300.dp

private enum class ScreenDrawer(val text: String, val icon: Int) {
    Home("Home", R.drawable.home),
    News("Metro",  R.drawable.metro ),
    Metro("News", R.drawable.news )

}
