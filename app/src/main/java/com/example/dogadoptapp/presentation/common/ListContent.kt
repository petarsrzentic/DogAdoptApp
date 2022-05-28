package com.example.dogadoptapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.dogadoptapp.R.drawable
import com.example.dogadoptapp.R.string
import com.example.dogadoptapp.domain.model.Dog
import com.example.dogadoptapp.navigation.Screen
import com.example.dogadoptapp.presentation.components.ShimmerEffect
import com.example.dogadoptapp.ui.theme.*
import com.example.dogadoptapp.util.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ListContent(
    dogs: LazyPagingItems<Dog>,
    navController: NavHostController
) {

    val result = handlingPaging(dogs = dogs)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            // items - lazy paging items
            items(
                items = dogs,
                key = { dog ->
                    dog.id
                }
            ) { dog ->
                dog?.let {
                    DogItem(dog = it, navController = navController)
                }
            }
        }
    }
}

@Composable
fun handlingPaging(
    dogs: LazyPagingItems<Dog>
): Boolean {
    dogs.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error, dogs = dogs)
                false
            }
            dogs.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun DogItem(
    dog: Dog,
    navController: NavHostController
) {
    /* rememberImagePainter - deprecated */
//    val painter = rememberImagePainter(data = "$BASE_URL${dog.image}") {
//        placeholder(drawable.ic_background_picture)
//        error(drawable.ic_background_picture)
//    }

    Box(
        modifier = Modifier
            .height(DOG_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passDogId(dogId = dog.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {

        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            val painter = AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = "$BASE_URL${dog.image}",
                placeholder = painterResource(drawable.ic_background_picture),
                contentDescription = "Background Loading Picture",
                contentScale = ContentScale.Crop
            )
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(model = painter),
                contentDescription = stringResource(string.dog_image)

            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.35f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.3f),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.weight(0.8f),
                        text = dog.name,
                        color = MaterialTheme.colors.topAppBarContentColor,
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Surface(
                        modifier = Modifier
                            .weight(0.2f)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = dog.gender,
                            color = when (dog.gender) {
                                "male" -> MaterialTheme.colors.activeIndicatorColor
                                else -> Color.Red
                            },
                            fontSize = MaterialTheme.typography.subtitle1.fontSize,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                }

                Text(
                    text = dog.age.toString() + "Kg",
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(SMALL_PADDING))
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = drawable.ic_location),
                        contentDescription = stringResource(string.location_image),
                    )
                    Spacer(modifier = Modifier.width(SMALL_PADDING))
                    Text(
                        text = dog.distance,
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview()
fun DogItemPrev() {
    DogItem(
        dog = Dog(
            id = 1,
            name = "Jackson",
            image = "/Users/macbookpro/Downloads/Marley.jpg",
            about = "Some random text",
            gender = "male",
            distance = "357m away",
            age = 3.5,
            weight = 5.5,
            color = "brown"
        ),
        navController = rememberNavController()
    )
}
