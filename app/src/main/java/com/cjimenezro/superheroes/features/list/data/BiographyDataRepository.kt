package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.features.list.data.local.BiographyLocalDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesRemoteDataSource
import com.cjimenezro.superheroes.features.list.domain.BiographyRepository
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography
import javax.inject.Inject

class BiographyDataRepository @Inject constructor(
    private val localDataSource: BiographyLocalDataSource,
    private val remoteDataSource: SuperHeroesRemoteDataSource
) : BiographyRepository {

    override suspend fun obtainBiography(id: String): Either<ErrorApp, SuperHeroeBiography> {
        val local = localDataSource.getBiography(id)
        return if (local.isRight()) {
            local
        } else {
            remoteDataSource.getBiography(id).map { biography ->
                localDataSource.saveBiography(biography, id)
                biography
            }
        }
    }
}