package com.example.dogadoptapp.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dogadoptapp.ui.theme.*

@Composable
fun ShimmerEffect() {
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        items(count = 2) {
            AnimatedShimmer()
        }
    }
}

@Composable
fun AnimatedShimmer() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ))
    ShimmerItem(alpha = alphaAnim)
}


@Composable
fun ShimmerItem(alpha: Float) {
   Surface(
       modifier = Modifier
           .fillMaxWidth()
           .height(DOG_ITEM_HEIGHT),
       color = if (isSystemInDarkTheme()) Color.Black else ShimmerLightGray,
       shape = RoundedCornerShape(topEnd = EXTRA_LARGE_PADDING, bottomStart = EXTRA_LARGE_PADDING)
   ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
                Surface(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .fillMaxWidth(0.5f)
                        .height(NAME_PLACEHOLDER_HEIGHT),
                    color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(SMALL_PADDING)
                ) {}
                Spacer(modifier = Modifier.padding(LARGE_PADDING))
                Surface(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .fillMaxWidth(0.6f)
                        .height(INFO_PLACEHOLDER_HEIGHT),
                    color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(SMALL_PADDING),
                ) {}
            }
            Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
            Surface(modifier = Modifier
                .alpha(alpha = alpha)
                .fillMaxWidth(0.2f)
                .height(INFO_PLACEHOLDER_HEIGHT),
                color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                shape = RoundedCornerShape(SMALL_PADDING)
            ) {}
            Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
            Row(modifier = Modifier) {
                Surface(modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth(0.1f)
                    .height(INFO_PLACEHOLDER_HEIGHT),
                    color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(SMALL_PADDING)
                ) {}
                Spacer(modifier = Modifier.padding(all = LITTLE_PADDING))
                Surface(modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth(0.5f)
                    .height(INFO_PLACEHOLDER_HEIGHT),
                    color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(SMALL_PADDING)
                ) {}
            }


        }
   }
}

@Composable
@Preview
fun ShimmerItemPrev() {
    AnimatedShimmer()
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun ShimmerDarkItemPrev() {
    AnimatedShimmer()
}