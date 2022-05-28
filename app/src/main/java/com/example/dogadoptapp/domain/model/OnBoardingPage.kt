package com.example.dogadoptapp.domain.model

import androidx.annotation.DrawableRes
import com.example.dogadoptapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        image = R.drawable.welcome,
        title = "Welcome",
        description = "Do you like dogs? If you do, then we have great news for you!"
    )
    object Second: OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Find the dog that you like from our place for adopting!"
    )
    object Third: OnBoardingPage(
        image = R.drawable.dog_girl,
        title = "Adopt",
        description = "The dog is waiting for you to be your best friend!"
    )
}
