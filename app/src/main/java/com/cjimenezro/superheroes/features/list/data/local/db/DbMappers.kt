package com.cjimenezro.superheroes.features.list.data.local.db

import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork

fun WorkEntity.toModel(): SuperHeroeWork = SuperHeroeWork(this.ocupation)
fun SuperHeroeWork.toEntity(id: String, ms: Long): WorkEntity = WorkEntity(id, this.occupation, ms)

fun BiographyEntity.toModel(): SuperHeroeBiography =
    SuperHeroeBiography(this.fullName, this.alignment)

fun SuperHeroeBiography.toEntity(id: String, ms: Long): BiographyEntity =
    BiographyEntity(id, this.fullName, this.alignment, ms)

fun PrincipalDataEntity.toModel(): SuperHeroePrincipalData =
    SuperHeroePrincipalData(this.id, this.name, mutableListOf(this.imageUrl), mutableListOf(this.intelligence,this.speed,this.combat))

fun SuperHeroePrincipalData.toEntity(ms: Long): PrincipalDataEntity =
    PrincipalDataEntity(this.id, this.name, this.imageUrl[0], this.stats[0],this.stats[1],this.stats[2], ms)