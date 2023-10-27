package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right
import com.cjimenezro.superheroes.features.list.data.local.BiographyLocalDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.data.remote.toModel
import com.cjimenezro.superheroes.features.list.domain.BiographyRepository
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography

class BiographyDataRepository(private val localDataSource: BiographyLocalDataSource,
                              private val apiClient:SuperHeroesApiClient
                              ):BiographyRepository {

    override suspend fun obtainBiography(id: String): Either<ErrorApp, SuperHeroeBiography> {
        try {
            if (localDataSource.getBiography(id).isRight()){
                return localDataSource.getBiography(id)
            }else{
                val result= apiClient.retrofit.getBiography(id).body()!!
                localDataSource.saveBiography(result.toModel(),id)
                return result.toModel().right()
            }

        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}