package com.cjimenezro.superheroes.app.di

import com.cjimenezro.superheroes.app.serialization.GsonSerialization
import com.cjimenezro.superheroes.app.serialization.JsonSerialization
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindJsonSerialization(gsonSerialization: GsonSerialization) : JsonSerialization

}