package com.cjimenezro.superheroes.data.remote

import com.cjimenezro.superheroes.domain.models.SuperHeroeBiography
import com.cjimenezro.superheroes.domain.models.SuperHeroePrincipalData
import com.cjimenezro.superheroes.domain.models.SuperHeroeWork

fun ImagesUrl.toModel():String=this.imageUrl

fun SuperHeroePincipalDataApiModel.toModel(): SuperHeroePrincipalData =
    SuperHeroePrincipalData(this.id,this.name,this.urlImages.toModel())

fun SuperHeroeBiographyApiModel.toModel(): SuperHeroeBiography = SuperHeroeBiography(this.fullName)

fun SuperHeroeWorkApiModel.toModel(): SuperHeroeWork = SuperHeroeWork(this.occupation)
