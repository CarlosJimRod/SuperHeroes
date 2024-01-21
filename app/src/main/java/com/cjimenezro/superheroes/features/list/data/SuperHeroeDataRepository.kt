package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.features.list.data.local.SuperHeroeLocalDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesRemoteDataSource
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeRepository
import javax.inject.Inject

class SuperHeroeDataRepository @Inject constructor(
    private val localDataSource: SuperHeroeLocalDataSource,
    private val remoteDataSource: SuperHeroesRemoteDataSource
) : SuperHeroeRepository {

    override suspend fun obratinSuperHeroe(): Either<ErrorApp, List<SuperHeroePrincipalData>> {
        val local = localDataSource.getSuperHeroe().get()
        return if (local.size != 0) {
            localDataSource.getSuperHeroe()
        } else {
            remoteDataSource.getSuperHeroe().map { superHeroes ->
                superHeroes.map { superHeroe ->
                    localDataSource.saveSuperHeroe(superHeroe)
                }
                superHeroes
            }

        }
    }

    override suspend fun obtainSuperHeroeById(id: String): Either<ErrorApp, SuperHeroePrincipalData> {
        val local = localDataSource.getSuperHeroeById(id)
        return if (local.isRight()) {
            local
        } else {
            remoteDataSource.getSuperHeroeById(id).map { superHeore ->
                localDataSource.saveSuperHeroe(superHeore)
                superHeore
            }
        }

    }
}