package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right
import com.cjimenezro.superheroes.features.list.data.local.WorkLocalDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroeApiService
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.data.remote.toModel
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork
import com.cjimenezro.superheroes.features.list.domain.WorkRepository
import javax.inject.Inject

class WorkDataRepository @Inject constructor(private val localDataSource: WorkLocalDataSource,
                         private val apiClient: SuperHeroeApiService
                         ):WorkRepository {

    override suspend fun obtainWork(id: String): Either<ErrorApp, SuperHeroeWork> {
        return try {
            if (localDataSource.getWork(id).isRight()){
                localDataSource.getWork(id)
            }else{
                val result= apiClient.getWork(id).body()!!
                localDataSource.saveWork(result.toModel(),id)
                result.toModel().right()
            }

        }catch (ex:Exception){
            ErrorApp.UnknowError.left()
        }
    }
}