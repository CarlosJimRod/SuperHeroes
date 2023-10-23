package com.cjimenezro.superheroes.data.remote

import com.cjimenezro.superheroes.data.remote.SuperHeroeBiographyApiModel
import com.cjimenezro.superheroes.data.remote.SuperHeroeWorkApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroeApiService {

    @GET("all.json")
    suspend fun getObject1():Response<List<SuperHeroeWorkApiModel>>

    @GET("biography/{superHeoreId}.json")
    suspend fun getObject2(@Path("superHeoreId")superHeoreId:String):Response<SuperHeroeBiographyApiModel>

    @GET("work/{superHeroeId}.json")
    suspend fun getObject3(@Path("superHeroeId") superHeroeId:String):Response<SuperHeroeWorkApiModel>
}