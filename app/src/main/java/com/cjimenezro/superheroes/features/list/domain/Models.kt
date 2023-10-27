package com.cjimenezro.superheroes.features.list.domain

data class SuperHeroe (
    val principalData:SuperHeroePrincipalData,
    val biography: SuperHeroeBiography,
    val work: SuperHeroeWork
)

data class SuperHeroePrincipalData (
    val id:Int,
    val name:String,
    val imageUrl:String
)

data class SuperHeroeBiography (
    val fullName:String
)

data class SuperHeroeWork (
    val occupation:String
)