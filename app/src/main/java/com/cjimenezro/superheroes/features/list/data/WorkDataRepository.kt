package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right
import com.cjimenezro.superheroes.features.list.data.local.WorkLocalDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.data.remote.toModel
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork
import com.cjimenezro.superheroes.features.list.domain.WorkRepository

class WorkDataRepository(private val localDataSource: WorkLocalDataSource,
                         private val apiClient:SuperHeroesApiClient
                         ):WorkRepository {

    override suspend fun obtainWork(id: String): Either<ErrorApp, SuperHeroeWork> {
        try {
            if (localDataSource.getWork(id).isRight()){
                return localDataSource.getWork(id)
            }else{
                val result= apiClient.retrofit.getWork(id).body()!!
                return result.toModel().right()
            }

        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}