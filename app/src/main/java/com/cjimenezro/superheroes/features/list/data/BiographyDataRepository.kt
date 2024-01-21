package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right
import com.cjimenezro.superheroes.features.list.data.local.BiographyLocalDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroeApiService
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.data.remote.toModel
import com.cjimenezro.superheroes.features.list.domain.BiographyRepository
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography
import javax.inject.Inject

class BiographyDataRepository @Inject constructor(private val localDataSource: BiographyLocalDataSource,
                              private val apiClient:SuperHeroeApiService
                              ):BiographyRepository {

    override suspend fun obtainBiography(id: String): Either<ErrorApp, SuperHeroeBiography> {
        return try {
            if (localDataSource.getBiography(id).isRight()){
                localDataSource.getBiography(id)
            }else{
                val result= apiClient.getBiography(id).body()!!
                localDataSource.saveBiography(result.toModel(),id)
                result.toModel().right()
            }

        }catch (ex:Exception){
            ErrorApp.UnknowError.left()
        }
    }
}