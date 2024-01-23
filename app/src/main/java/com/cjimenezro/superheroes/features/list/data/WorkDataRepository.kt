package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp
import com.cjimenezro.superheroes.features.list.data.local.sharedPreferences.WorkLocalDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesRemoteDataSource
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork
import com.cjimenezro.superheroes.features.list.domain.WorkRepository
import javax.inject.Inject

class WorkDataRepository @Inject constructor(
    private val localDataSource: WorkLocalDataSource,
    private val remoteDataSource: SuperHeroesRemoteDataSource
) : WorkRepository {

    override suspend fun obtainWork(id: String): Either<ErrorApp, SuperHeroeWork> {
        val local = localDataSource.getWork(id)
        return if (local.isRight()) {
            local
        } else {
            remoteDataSource.getWork(id).map { work ->
                localDataSource.saveWork(work, id)
                work
            }
        }
    }
}