package com.cjimenezro.superheroes.features.list.domain

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp

interface SuperHeroeRepository {

    suspend fun obratinSuperHeroe():Either<ErrorApp,List<SuperHeroePrincipalData>>

    suspend fun obtainSuperHeroeById(id:String):Either<ErrorApp,SuperHeroePrincipalData>

}