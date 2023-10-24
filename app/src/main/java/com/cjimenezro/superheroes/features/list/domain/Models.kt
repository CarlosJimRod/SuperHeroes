package com.cjimenezro.superheroes.features.list.domain

class SuperHeroe (
    val principalData:SuperHeroePrincipalData,
    val biography: SuperHeroeBiography,
    val work: SuperHeroeWork
)

class SuperHeroePrincipalData (
    val id:Int,
    val name:String,
    val imageUrl:String
)

class SuperHeroeBiography (
    val fullName:String
)

class SuperHeroeWork (
    val occupation:String
)