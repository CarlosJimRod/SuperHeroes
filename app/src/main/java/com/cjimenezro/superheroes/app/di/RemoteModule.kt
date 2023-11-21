package com.cjimenezro.superheroes.app.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val BASE_URL=" https://dam.sitehub.es/api-curso/superheroes/"

    @Singleton
    @Provides
    fun provideGson():Gson = Gson()

    @Singleton
    @Provides
    fun provideHttpLoginInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideHttpClient (httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient
        .Builder().addInterceptor(httpLoggingInterceptor).build()

    @Singleton
    @Provides
    fun provides(okHttpClient: OkHttpClient):Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

}