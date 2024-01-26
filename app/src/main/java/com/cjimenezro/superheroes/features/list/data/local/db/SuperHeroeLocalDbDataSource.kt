package com.cjimenezro.superheroes.features.list.data.local.db

import com.cjimenezro.superheroes.app.di.LocalModule.TIME_CACHE
import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp
import com.cjimenezro.superheroes.app.domain.left
import com.cjimenezro.superheroes.app.domain.right
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork
import javax.inject.Inject

class SuperHeroeLocalDbDataSource @Inject constructor(
    private val principalDataDao: PrincipalDataDao,
    private val biographyDao: BiographyDao,
    private val workDao: WorkDao
) {
    suspend fun savePrincipalData(principalData: List<SuperHeroePrincipalData>): Either<ErrorApp, Boolean> {
        val ms = System.currentTimeMillis()
        return try {
            val principaDataEntity = principalData.map { principalData ->
                principalData.toEntity(ms)
            }
            principalDataDao.insertAll(*principaDataEntity.toTypedArray())
            true.right()
        } catch (ex: Exception) {
            return ErrorApp.DataError.left()
        }
    }

    suspend fun saveBiography(
        biography: SuperHeroeBiography,
        id: String
    ): Either<ErrorApp, Boolean> {
        val ms = System.currentTimeMillis()
        return try {
            biographyDao.insert(biography.toEntity(id, ms))
            true.right()
        } catch (ex: Exception) {
            return ErrorApp.DataError.left()
        }
    }

    suspend fun saveWork(work: SuperHeroeWork, id: String): Either<ErrorApp, Boolean> {
        val ms = System.currentTimeMillis()
        return try {
            workDao.insert(work.toEntity(id, ms))
            true.right()
        } catch (ex: Exception) {
            return ErrorApp.DataError.left()
        }
    }

    suspend fun getPrincipalData(): Either<ErrorApp, List<SuperHeroePrincipalData>> {
        return try {
            val principalData = principalDataDao.getAll()
            if (principalData.isEmpty() || principalData.first().createdAt.plus(TIME_CACHE) < System.currentTimeMillis()) {
                listOf<SuperHeroePrincipalData>().right()
            } else {
                principalData.map { principalDataEntity ->
                    principalDataEntity.toModel()
                }.right()
            }
        } catch (ex: Exception) {
            ErrorApp.DataError.left()
        }
    }

    suspend fun getPrincipalDataById(id: String): Either<ErrorApp, SuperHeroePrincipalData?> {
        return try {
            val principalData = principalDataDao.getById(id)
            if (principalData == null || principalData.createdAt.plus(TIME_CACHE) < System.currentTimeMillis()) {
                null.right()
            } else {
                principalData.toModel().right()
            }
        } catch (ex: Exception) {
            ErrorApp.DataError.left()
        }
    }

    suspend fun getBiographyById(id: String): Either<ErrorApp, SuperHeroeBiography?> {
        return try {
            val biography = biographyDao.getById(id)
            if (biography == null || biography.createdAt.plus(TIME_CACHE) < System.currentTimeMillis()) {
                null.right()
            } else {
                biography.toModel().right()
            }
        } catch (ex: Exception) {
            ErrorApp.DataError.left()
        }
    }

    suspend fun getWorkById(id: String): Either<ErrorApp, SuperHeroeWork?> {
        return try {
            val work = workDao.getById(id)
            if (work == null || work.createdAt.plus(TIME_CACHE) < System.currentTimeMillis()) {
                null.right()
            } else {
                work.toModel().right()
            }
        } catch (ex: Exception) {
            ErrorApp.DataError.left()
        }
    }

    suspend fun deletePrincipalData(): Either<ErrorApp, Boolean> {
        return try {
            principalDataDao.deleteAll()
            true.right()
        } catch (ex: Exception) {
            ErrorApp.DataError.left()
        }
    }

    suspend fun deleteBiography(id: String): Either<ErrorApp, Boolean> {
        return try {
            biographyDao.deleteById(id)
            true.right()
        } catch (ex: Exception) {
            ErrorApp.DataError.left()
        }
    }

    suspend fun deletetWork(id: String): Either<ErrorApp, Boolean> {
        return try {
            workDao.deleteById(id)
            true.right()
        } catch (ex: Exception) {
            ErrorApp.DataError.left()
        }
    }
}