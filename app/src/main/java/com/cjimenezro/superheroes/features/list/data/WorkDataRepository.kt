package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.data.remote.toModel
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork
import com.cjimenezro.superheroes.features.list.domain.WorkRepository

class WorkDataRepository:WorkRepository {

    private val superHeroesApiClient=SuperHeroesApiClient().retrofit
    override suspend fun getWork(id: String): Either<ErrorApp, SuperHeroeWork> {
        return try {
            val result=superHeroesApiClient.getWork(id).body()!!

            result.toModel().right()
        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}