package com.example.discoveregypt.screen


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.discoveregypt.R
import com.example.discoveregypt.ui.theme.buttongrey
import com.example.discoveregypt.ui.theme.firaSansFamily

sealed class Screen(
    val title: String,
    val activeIcon: Int,
    val inactiveIcon: Int
    ,val route: String
) {
    object Home : Screen("Home", R.drawable.egypt3 , R.drawable.egypt3,"Main/")
    object Create : Screen("Favourite", R.drawable.egypt1, R.drawable.egypt1,"Favourite/")

}

@Composable
fun BottomNavigationAnimation(
    navController: NavController,  screens: List<Screen>,

) {
    var selectedScreen by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
            .shadow(5.dp)
            .background(buttongrey)
            .height(64.dp)
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
//            verticalAlignment = Alignment.CenterVertically
        ) {
            for (screen in screens) {
                val isSelected = screen == screens[selectedScreen]
                val animatedWeight by animateFloatAsState(targetValue = if (isSelected) 1.5f else 1f)

                Box(
                    modifier = Modifier.weight(animatedWeight),
                    contentAlignment = Center
                ) {
                    val interactionSource = remember { MutableInteractionSource() }
                    BottomNavItem(
                        navController = navController,
                        screen = screen,
                        isSelected = isSelected,
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            selectedScreen = screens.indexOf(screen)
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }


                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FlipIcon(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeIcon: Int,
    inactiveIcon: Int,
    contentDescription: String
) {
    val animationRotation by animateFloatAsState(
        targetValue = if (isActive) 180f else 0f,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )
    Box(
        modifier = Modifier
            .graphicsLayer { rotationY = animationRotation },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter =painterResource(id = if (animationRotation > 90f) activeIcon else inactiveIcon),
            contentDescription = contentDescription
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    screen: Screen,
    isSelected: Boolean, navController: NavController

) {
    val animateHeight by animateDpAsState(
        targetValue = if (isSelected) 150.dp else 26.dp
    )
    val animatedElevation by animateDpAsState(targetValue = if (isSelected) 15.dp else 0.dp)
    val animatedAlpha by animateFloatAsState(targetValue = if (isSelected) 1f else .5f)
    val animatedIconSize by animateDpAsState(
        targetValue = if (isSelected) 80.dp else 20.dp,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .height(animateHeight)
                .width(120.dp)
                .shadow(
                    elevation = animatedElevation,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = buttongrey,
                    shape = RoundedCornerShape(20.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            FlipIcon(
                isActive = isSelected,
                activeIcon = screen.activeIcon,
                inactiveIcon = screen.inactiveIcon,
                contentDescription = "Icons",
                modifier = Modifier
                    .align(CenterVertically)
                    .fillMaxHeight()

                    .padding(start = 11.dp)
                    .alpha(animatedAlpha)
                    .size(animatedIconSize)
            )

            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = screen.title,
                    fontFamily = firaSansFamily, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp, end = 10.dp),
                    maxLines = 1
                )
            }
        }
    }
}













































