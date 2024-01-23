package com.cjimenezro.superheroes.app.di

import android.content.Context
import androidx.room.Room
import com.cjimenezro.superheroes.app.data.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    const val TIME_CACHE = 10 * 60000

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java, "superHeroes"
    )
        .fallbackToDestructiveMigration()
        .build()
}