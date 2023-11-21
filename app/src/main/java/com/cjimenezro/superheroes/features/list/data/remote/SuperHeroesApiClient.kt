package com.cjimenezro.superheroes.features.list.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class SuperHeroesApiClient @Inject constructor(){

    private val urlApi=" https://dam.sitehub.es/api-curso/superheroes/"

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder().addInterceptor(interceptor)
    private val getRetrofit: Retrofit=
        Retrofit.Builder()
            .baseUrl(urlApi)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

    var retrofit=getRetrofit.create(SuperHeroeApiService::class.java)


}