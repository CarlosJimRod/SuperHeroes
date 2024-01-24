package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp
import com.cjimenezro.superheroes.app.domain.right
import com.cjimenezro.superheroes.features.list.data.local.db.SuperHeroeLocalDbDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesRemoteDataSource
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork
import com.cjimenezro.superheroes.features.list.domain.WorkRepository
import javax.inject.Inject

class WorkDataRepository @Inject constructor(
    private val localDataSource: SuperHeroeLocalDbDataSource,
    private val remoteDataSource: SuperHeroesRemoteDataSource
) : WorkRepository {

    override suspend fun obtainWork(id: String): Either<ErrorApp, SuperHeroeWork> {
        localDataSource.getWorkById(id).map { localWork ->
            localWork?.let {
                return it.right()
            }
        }

        //Remote
        return remoteDataSource.getWork(id).map { work ->
            localDataSource.deletetWork(id)
            localDataSource.saveWork(work, id)
            work
        }
    }
}