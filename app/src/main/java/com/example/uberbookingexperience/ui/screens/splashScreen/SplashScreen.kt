package com.example.uberbookingexperience.ui.screens.splashScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uberbookingexperience.R
import com.example.uberbookingexperience.ui.theme.UberBookingExperienceTheme
import com.example.uberbookingexperience.ui.util.systemTween
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SplashScreen(
    onAnimationFinish: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    var circleHeight by remember { mutableStateOf(0f) }
    val animatedCircleHeight by animateFloatAsState(
        targetValue = circleHeight,
        animationSpec = systemTween(durationMillis = 1000),
        finishedListener = { onAnimationFinish() }
    )

    val screenHeight = LocalConfiguration.current.screenHeightDp * 2

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            Color.Transparent,
            darkIcons = true
        )
        circleHeight = screenHeight.toFloat()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .drawWithContent {
                drawContent()
                drawCircle(
                    color = Color.White,
                    radius = animatedCircleHeight,
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.width(288.dp),
            painter = painterResource(id = R.drawable.uber_foreground),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    UberBookingExperienceTheme {
        SplashScreen {}
    }
}