package com.cjimenezro.superheroes.features.list.domain

import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp

interface SuperHeroeRepository {

    suspend fun obratinSuperHeroe(): Either<ErrorApp, List<SuperHeroePrincipalData>>

    suspend fun obtainSuperHeroeById(id: String): Either<ErrorApp, SuperHeroePrincipalData>

}