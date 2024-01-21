package com.cjimenezro.superheroes.features.list.di

import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class SuperHeroeProvides {

    @Provides
    fun provideSuperHeroeApiService(retrofit: Retrofit): SuperHeroeApiService =
        retrofit.create(SuperHeroeApiService::class.java)

}