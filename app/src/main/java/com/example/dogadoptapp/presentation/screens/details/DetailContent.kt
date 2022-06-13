package com.example.dogadoptapp.presentation.screens.details


import android.graphics.Color.parseColor
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.dogadoptapp.R
import com.example.dogadoptapp.domain.model.Dog
import com.example.dogadoptapp.navigation.Screen
import com.example.dogadoptapp.ui.theme.*
import com.example.dogadoptapp.util.Constants.BASE_URL
import com.example.dogadoptapp.util.Constants.MAX_LINES
import com.example.dogadoptapp.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun DetailContent(
    navController: NavHostController,
    selectedDog: Dog?,
    colors: Map<String, String>
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = selectedDog) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    // Fixing StatusBar color to change according to image colors
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(parseColor(darkVibrant))
    )


    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPENDED_RADIUS_LEVEL
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedDog?.let {
                BottomSheetContent(
                    selectedDog = it,
                    infoboxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant))
                )
            }
        },
        content = {
            selectedDog?.let { dog ->
                BackgroundContent(
                    dogImage = dog.image,
                    imageFraction = currentSheetFraction,
                    backgroundColor = Color(parseColor(darkVibrant)),
                    onCloseClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}

@Composable
fun BottomSheetContent(
    selectedDog: Dog,
    infoboxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {

    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                modifier = Modifier
                    .size(ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_paw2),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = contentColor
            )
            Text(
                modifier = Modifier.weight(8f),
                text = selectedDog.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
            val context = LocalContext.current
            Box(
                modifier = Modifier
                    .background(
                        color = infoboxIconColor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        Toast.makeText(context, "All information is sent to the owner.", Toast.LENGTH_LONG).show()
                    }
            ) {
                Text(
                    modifier = Modifier.padding(SMALL_PADDING),
                    text = "Adopt me",
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.titleColor
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(end = SMALL_PADDING),
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = stringResource(id = R.string.location_image),
                tint = Color.Red
            )
            Text(
                text = selectedDog.distance,
                color = contentColor
            )
        }
        Text(
            modifier = Modifier.padding(all = SMALL_PADDING),
            text = "About",
            color = contentColor,
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(bottom = MEDIUM_PADDING),
            textAlign = TextAlign.Justify,
            maxLines = MAX_LINES,
            color = contentColor,
            text = selectedDog.about//"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
        )
        Text(
            modifier = Modifier.padding(all = SMALL_PADDING),
            text = "Dog Info",
            color = contentColor,
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .size(80.dp)
                        .background(
                            color = infoboxIconColor,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(
                            top = SMALL_PADDING,
                            start = SMALL_PADDING,
                            end = SMALL_PADDING
                        ),
                        text = "${selectedDog.age}yrs",
                        color = MaterialTheme.colors.boxTextColors,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(bottom = SMALL_PADDING),
                        text = "Age",
                        color = MaterialTheme.colors.boxTextColors.copy(alpha = 0.5f)
                    )
                }
            }
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center,

                ) {
                Column(
                    modifier = Modifier
                        .size(80.dp)
                        .background(
                            color = infoboxIconColor,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(
                            top = SMALL_PADDING,
                            start = SMALL_PADDING,
                            end = SMALL_PADDING
                        ),
                        text = selectedDog.color,
                        color = MaterialTheme.colors.boxTextColors,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(bottom = SMALL_PADDING),
                        text = "Color",
                        color = MaterialTheme.colors.boxTextColors.copy(alpha = 0.5f)
                    )
                }
            }
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center,

                ) {
                Column(
                    modifier = Modifier
                        .size(80.dp)
                        .background(
                            color = infoboxIconColor,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(
                            top = SMALL_PADDING,
                            start = SMALL_PADDING,
                            end = SMALL_PADDING
                        ),
                        text = "${selectedDog.weight}Kg",
                        color = MaterialTheme.colors.boxTextColors,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(bottom = SMALL_PADDING),
                        text = "Weight",
                        color = MaterialTheme.colors.boxTextColors.copy(alpha = 0.5f)
                    )
                }
            }
        }
//        Text(
//            modifier = Modifier.padding(all = SMALL_PADDING),
//            text = "Dog Owner",
//            color = contentColor,
//            fontSize = MaterialTheme.typography.h6.fontSize,
//            fontWeight = FontWeight.Bold
//        )
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.petar_image),
//                contentDescription = "owner image",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .padding(
//                        start = SMALL_PADDING,
//                        end = LARGE_PADDING
//                    )
//                    .size(64.dp)
//                    .clip(CircleShape)                       // clip to the circle shape
//                    .border(2.dp, Color.Gray, CircleShape)
//            )
//            Column(modifier = Modifier.padding(end = 30.dp)) {
//                Text(
//                    text = "Petar Srzentic",
//                    color = MaterialTheme.colors.titleColor,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = "Developer & Pet Lover",
//                    color = MaterialTheme.colors.titleColor.copy(alpha = 0.5f)
//                )
//            }
//            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
//                Image(modifier = Modifier.size(30.dp),
//                    painter = painterResource(id = R.drawable.messenger_logo),
//                    contentDescription = "Viber Logo")
//                Image(modifier = Modifier.size(30.dp),
//                    painter = painterResource(id = R.drawable.whatsapp_logo),
//                    contentDescription = "Whatsapp Logo")
////                Image(modifier = Modifier.size(30.dp),
////                    painter = painterResource(id = R.drawable.viber_logo),
////                    contentDescription = "Messenger Logo")
//            }
//        }
    }
}

@Composable
fun BackgroundContent(
    dogImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {

    val imageUrl = "$BASE_URL${dogImage}"
    val painter = rememberAsyncImagePainter(imageUrl)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    )
    {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MIN_BACKGROUND_IMAGE_HEIGHT)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = "Dog image",
            contentScale = ContentScale.Crop,

            )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        )
        {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() })
            {
                Icon(
                    modifier = Modifier.size(ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close button",
                    tint = Color.White
                )
            }
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Composable
@Preview()
fun BottomSheetContentPrev() {

}