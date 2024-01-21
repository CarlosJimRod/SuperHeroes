package com.cjimenezro.superheroes.features.list.data.remote

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.data.remote.apiCall
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork
import javax.inject.Inject

class SuperHeroesRemoteDataSource @Inject constructor(
    private val apiService: SuperHeroeApiService
) {

    suspend fun getSuperHeroe(): Either<ErrorApp, List<SuperHeroePrincipalData>> {
        return apiCall {
            apiService.getPrincipaData()
        }.map { superHeroesApiModel ->
            superHeroesApiModel.map { superHeroeApiModel ->
                superHeroeApiModel.toModel()
            }
        }
    }

    suspend fun getSuperHeroeById(id: String): Either<ErrorApp, SuperHeroePrincipalData> {
        return apiCall {
            apiService.getPrincipaDataById(id)
        }.map { superHeroeApiModel ->
            superHeroeApiModel.toModel()
        }
    }

    suspend fun getBiography(id: String): Either<ErrorApp, SuperHeroeBiography> {
        return apiCall {
            apiService.getBiography(id)
        }.map { biographyApiModel ->
            biographyApiModel.toModel()
        }
    }

    suspend fun getWork(id: String): Either<ErrorApp, SuperHeroeWork> {
        return apiCall {
            apiService.getWork(id)
        }.map { workApiModel ->
            workApiModel.toModel()
        }
    }

}