package com.example.dogadoptapp.util

import androidx.compose.ui.unit.dp

object Constants {

//    const val BASE_URL = "http://10.0.2.2:8080"

    const val BASE_URL = "http://dogpas-server.herokuapp.com"

    const val DETAILS_ARGUMENT_KEY = "dogId"

    const val DOG_DATABASE_TABLE = "dog_table"
    const val DOG_REMOTE_KEYS_DATABASE_TABLE = "dog_remote_keys_table"
    const val DOG_DATABASE =  "dog_database"

    const val PREFERENCES_NAME = "dog_preferences"
    const val PREFERENCES_KEY = "on_boarding_completed"

    const val ON_BOARDING_PAGE_COUNT = 3
    const val LAST_ON_BOARDING_PAGE = 2

    const val ITEMS_PER_PAGE = 3
    const val MAX_LINES = 7
    const val MIN_BACKGROUND_IMAGE_HEIGHT = 0.4f
}