package com.cjimenezro.superheroes.features.list.data.remote

import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroeBiographyApiModel
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroeWorkApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroeApiService {

    @GET("heroes.json")
    suspend fun getPrincipaData():Response<List<SuperHeroePincipalDataApiModel>>

    @GET("biography/{superHeoreId}.json")
    suspend fun getBiography(@Path("superHeoreId")superHeoreId : String):Response<SuperHeroeBiographyApiModel>

    @GET("work/{superHeoreId}.json")
    suspend fun getWork(@Path("superHeoreId")superHeoreId : String):Response<SuperHeroeWorkApiModel>
}