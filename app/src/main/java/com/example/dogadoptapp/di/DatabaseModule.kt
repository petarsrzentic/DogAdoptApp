package com.example.dogadoptapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogadoptapp.data.local.DogDatabase
import com.example.dogadoptapp.data.repository.LocalDataSourceImpl
import com.example.dogadoptapp.domain.repository.LocalDataSource
import com.example.dogadoptapp.util.Constants.DOG_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : DogDatabase {
        return Room.databaseBuilder(
            context,
            DogDatabase::class.java,
            DOG_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDatasource(
        database: DogDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            dogDatabase = database
        )
    }

}