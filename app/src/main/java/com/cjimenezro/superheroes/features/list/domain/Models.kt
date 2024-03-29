package com.cjimenezro.superheroes.features.list.domain

data class SuperHeroe (
    val principalData:SuperHeroePrincipalData,
    val biography: SuperHeroeBiography,
    val work: SuperHeroeWork,
)

data class SuperHeroePrincipalData (
    val id: String,
    val name: String,
    val imageUrl: MutableList<String>,
    val stats: MutableList<String>
)

data class SuperHeroeBiography (
    val fullName:String,
    val alignment:String
)

data class SuperHeroeWork (
    val occupation:String
)