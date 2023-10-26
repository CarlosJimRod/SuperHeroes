package com.cjimenezro.superheroes.features.list.data.remote

import com.google.gson.annotations.SerializedName

class SuperHeroePincipalDataApiModel (
    @SerializedName("id") val id:Int,
    @SerializedName("name")val name:String,
    @SerializedName("images") val urlImages: ImagesUrl
)

class ImagesUrl(
    @SerializedName("sm") val imageUrl:String
)

class SuperHeroeBiographyApiModel(
    @SerializedName("fullName") val fullName:String
)

class SuperHeroeWorkApiModel(
    @SerializedName("occupation") val occupation:String
)