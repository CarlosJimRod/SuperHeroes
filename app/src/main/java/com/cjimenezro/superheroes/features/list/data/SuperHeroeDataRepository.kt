package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp
import com.cjimenezro.superheroes.app.domain.right
import com.cjimenezro.superheroes.features.list.data.local.db.SuperHeroeLocalDbDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesRemoteDataSource
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeRepository
import javax.inject.Inject

class SuperHeroeDataRepository @Inject constructor(
    private val localDataSource: SuperHeroeLocalDbDataSource,
    private val remoteDataSource: SuperHeroesRemoteDataSource
) : SuperHeroeRepository {

    override suspend fun obratinSuperHeroe(): Either<ErrorApp, List<SuperHeroePrincipalData>> {
        localDataSource.getPrincipalData().map { localListPrincipalData ->
            localListPrincipalData.let {
                return it.right()
            }
        }

        //Remote
        return remoteDataSource.getSuperHeroe().map { principalDataList ->
            localDataSource.deletePrincipalData()
            localDataSource.savePrincipalData(principalDataList)
            principalDataList
        }
    }

    override suspend fun obtainSuperHeroeById(id: String): Either<ErrorApp, SuperHeroePrincipalData> {
        localDataSource.getPrincipalDataById(id).map { localPrincipalData ->
            localPrincipalData?.let {
                return it.right()
            }
        }

        //Remote
        return remoteDataSource.getSuperHeroeById(id).map { principalData ->
            localDataSource.deletePrincipalData()
            localDataSource.savePrincipalData(listOf(principalData))
            principalData
        }
    }
}