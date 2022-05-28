package com.example.dogadoptapp.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dogadoptapp.R.drawable
import com.example.dogadoptapp.navigation.Screen
import com.example.dogadoptapp.ui.theme.DarkBrown
import com.example.dogadoptapp.ui.theme.DeepOrange

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {

    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()

    val degrees = remember { Animatable(0f) }
    val size = remember { Animatable(0f) }
    //Animate only first time
    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            ),
        )
    }
    LaunchedEffect(key1 = true) {
        size.animateTo(
            targetValue = 2f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted) {
            navController.navigate(Screen.Home.route)
        } else {
            navController.navigate(Screen.Welcome.route)
        }
    }


    Splash(degrees = degrees.value, size = size.value)
}

@Composable
fun Splash(degrees: Float, size: Float) {
    if (isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .rotate(degrees = degrees)
                    .scale(size),
                painter = painterResource(
                    id = drawable.ic_paw2
                ),
                contentDescription = "App logo"
            )
        }
    } else {
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(DeepOrange, DarkBrown)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .rotate(degrees = degrees)
                    .scale(size),
                painter = painterResource(
                    id = drawable.ic_paw2
                ),
                contentDescription = "App logo"
            )
        }
    }

}


@Composable
@Preview
fun SplashPrev() {
    Splash(0f, 3f)
}