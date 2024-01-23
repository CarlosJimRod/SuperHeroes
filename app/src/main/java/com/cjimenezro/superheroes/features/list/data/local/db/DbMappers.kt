package com.cjimenezro.superheroes.features.list.data.local.db

import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork

fun WorkEntity.toModel(): SuperHeroeWork = SuperHeroeWork(this.ocupation)
fun SuperHeroeWork.toEntity(id: Int): WorkEntity = WorkEntity(id, this.occupation)

fun BiographyEntity.toModel(): SuperHeroeBiography =
    SuperHeroeBiography(this.fullName, this.alignment)

fun SuperHeroeBiography.toEntity(id: Int): BiographyEntity =
    BiographyEntity(id, this.fullName, this.alignment)

fun PrincipalDataEntity.toModel(): SuperHeroePrincipalData =
    SuperHeroePrincipalData(this.id, this.name, mutableListOf(this.imageUrl), this.stats)

fun SuperHeroePrincipalData.toEntity(): PrincipalDataEntity =
    PrincipalDataEntity(this.id, this.name, this.imageUrl[0], this.stats)