package com.cjimenezro.superheroes.features.list.data.remote

import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork

fun ImagesUrl.toModel():MutableList<String> = mutableListOf(this.imageUrl1,this.imageUrl2,this.imageUrl3,this.imageUrl4)

fun Powerstats.toModel():MutableList<String> = mutableListOf(this.intelligence,this.speed,this.combat)

fun SuperHeroePincipalDataApiModel.toModel(): SuperHeroePrincipalData =
    SuperHeroePrincipalData(this.id,this.name,this.urlImages.toModel(),this.stats.toModel())

fun SuperHeroeBiographyApiModel.toModel(): SuperHeroeBiography = SuperHeroeBiography(this.fullName,this.alignment)

fun SuperHeroeWorkApiModel.toModel(): SuperHeroeWork = SuperHeroeWork(this.occupation)