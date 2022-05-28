package com.example.dogadoptapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val DeepBlue = Color(0xFF23074D)
val DeepOrange = Color(0xFFCC5333)
val DarkBrown = Color(0xFF230C0B)
val LightBeige = Color(0xFFFBF4EC)
val Pink = Color(0xFFF1CBC0)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)

val Colors.statusBarColor
    @Composable
    get() = if (isLight) DeepOrange else Color.Black


val Colors.welcomeScreenBackgroundColorFirst
    @Composable
    get() = if (isLight) LightBeige else Color.Black

val Colors.welcomeScreenBackgroundColorSecond
    @Composable
    get() = if (isLight) Color.White else Color.Black

val Colors.welcomeScreenBackgroundColorThird
    @Composable
    get() = if (isLight) Pink else Color.Black

val Colors.titleColor
    @Composable
    get() = if (isLight) DarkGray else LightGray

val Colors.descriptionColor
    @Composable
    get() = if (isLight) DarkGray.copy(alpha = 0.5f) else LightGray.copy(alpha = 0.5f)

val Colors.activeIndicatorColor
    @Composable
    get() = if (isLight) DeepBlue else LightBeige

val Colors.inactiveIndicatorColor
    @Composable
    get() = if (isLight) LightGray else DarkGray

val Colors.buttonBackGroundColor
    @Composable
    get() = if (isLight) DeepBlue else LightBeige

val Colors.buttonContentColor
    @Composable
    get() = if (isLight) Color.White else DeepBlue

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) Color.White else LightGray

val Colors.topAppBarBackgroundColor
    @Composable
    get() = if (isLight) DeepOrange else Color.Black

val Colors.boxTextColors
    @Composable
    get() = if (isLight) Color.White else DeepBlue

val Colors.boxColors
    @Composable
    get() = if (isLight) DeepBlue else LightBeige




