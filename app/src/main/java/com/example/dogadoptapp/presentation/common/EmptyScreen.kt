package com.example.dogadoptapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.dogadoptapp.R
import com.example.dogadoptapp.domain.model.Dog
import com.example.dogadoptapp.ui.theme.DarkGray
import com.example.dogadoptapp.ui.theme.ERROR_IMAGE_SIZE
import com.example.dogadoptapp.ui.theme.LightGray
import com.example.dogadoptapp.ui.theme.SMALL_PADDING
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    dogs: LazyPagingItems<Dog>? = null
) {
    var message by remember {
        mutableStateOf("Find your best friend!")
    }
    var icon by remember {
        mutableStateOf(R.drawable.ic_search_paw)
    }

    if (error != null) {
        message = parseErrorMessage(error = error)
        icon = R.drawable.ic_sync_problem
    }

    var startAnimation by remember { mutableStateOf(false) }

    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1500
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(
        alfaAnim = alphaAnim,
        icon = icon,
        message = message,
        dogs = dogs,
        error = error
    )
}

@Composable
fun EmptyContent(
    alfaAnim: Float,
    icon: Int,
    message: String,
    dogs: LazyPagingItems<Dog>? = null,
    error: LoadState.Error? = null
) {
    var isRefreshing by remember { mutableStateOf(false) }
    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true
            dogs?.refresh()
            isRefreshing = false
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(ERROR_IMAGE_SIZE)
                    .alpha(alpha = alfaAnim),
                painter = painterResource(id = icon),
                contentDescription = "Network Error Icon",
                tint = if (isSystemInDarkTheme()) LightGray else DarkGray,
            )
            Text(
                modifier = Modifier
                    .padding(top = SMALL_PADDING)
                    .alpha(alpha = alfaAnim),
                text = message,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
            )

        }
    }
}


fun parseErrorMessage(error: LoadState.Error): String {
    return when (error.error) {
        is SocketTimeoutException -> {
            "Server Unavailable"
        }
        is ConnectException -> {
            "Internet Unavailable"
        }
        else -> {
            "Unknown Error"
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EmptyScreenPrev() {
    EmptyScreen(error = LoadState.Error(SocketTimeoutException()))
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun EmptyScreenPrevDark() {
    EmptyScreen(error = LoadState.Error(SocketTimeoutException()))
}