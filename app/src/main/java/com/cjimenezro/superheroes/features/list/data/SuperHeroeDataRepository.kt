package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.data.remote.toModel
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeRepository

class SuperHeroeDataRepository:SuperHeroeRepository {

    private val superHeroesApiClient=SuperHeroesApiClient().retrofit

    override suspend fun obratinSuperHeroe(): Either<ErrorApp, List<SuperHeroePrincipalData>> {
        return try {
            val superHeroes: MutableList<SuperHeroePrincipalData> = mutableListOf()
            val result=superHeroesApiClient.getPrincipaData().body()!!

            result.map {
                superHeroes.add(it.toModel())
            }

            superHeroes.right()
        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}