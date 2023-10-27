package com.cjimenezro.superheroes.features.list.data.remote

import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork

fun ImagesUrl.toModel():String=this.imageUrl

fun SuperHeroePincipalDataApiModel.toModel(): SuperHeroePrincipalData =
    SuperHeroePrincipalData(this.id,this.name,this.urlImages.toModel())

fun SuperHeroeBiographyApiModel.toModel(): SuperHeroeBiography = SuperHeroeBiography(this.fullName)

fun SuperHeroeWorkApiModel.toModel(): SuperHeroeWork = SuperHeroeWork(this.occupation)
