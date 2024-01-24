package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp
import com.cjimenezro.superheroes.app.domain.right
import com.cjimenezro.superheroes.features.list.data.local.db.SuperHeroeLocalDbDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesRemoteDataSource
import com.cjimenezro.superheroes.features.list.domain.BiographyRepository
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography
import javax.inject.Inject

class BiographyDataRepository @Inject constructor(
    private val localDataSource: SuperHeroeLocalDbDataSource,
    private val remoteDataSource: SuperHeroesRemoteDataSource
) : BiographyRepository {

    override suspend fun obtainBiography(id: String): Either<ErrorApp, SuperHeroeBiography> {
        localDataSource.getBiographyById(id).map { localBiography ->
            localBiography?.let {
                return it.right()
            }
        }

        //Remote
        return remoteDataSource.getBiography(id).map { biography ->
            localDataSource.deleteBiography(id)
            localDataSource.saveBiography(biography, id)
            biography
        }
    }
}