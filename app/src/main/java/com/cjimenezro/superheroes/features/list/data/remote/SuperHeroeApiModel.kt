package com.cjimenezro.superheroes.features.list.data.remote

import com.google.gson.annotations.SerializedName

class SuperHeroePincipalDataApiModel (
    @SerializedName("id") val id:Int,
    @SerializedName("name")val name:String,
    @SerializedName("images") val urlImages: ImagesUrl,
    @SerializedName("powerstats") val stats: Powerstats
)

class ImagesUrl(
    @SerializedName("sm") val imageUrl1:String,
    @SerializedName("xs") val imageUrl2:String,
    @SerializedName("md") val imageUrl3:String,
    @SerializedName("lg") val imageUrl4:String,
)

class Powerstats(
    @SerializedName("intelligence") val intelligence:String,
    @SerializedName("speed") val speed:String,
    @SerializedName("combat") val combat:String
)

class SuperHeroeBiographyApiModel(
    @SerializedName("fullName") val fullName:String,
    @SerializedName("alignment") val alignment:String
)

class SuperHeroeWorkApiModel(
    @SerializedName("occupation") val occupation:String
)