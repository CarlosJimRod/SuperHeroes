package com.cjimenezro.superheroes.features.list.data.local.db

import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import javax.inject.Inject

class SuperHeroeLocalDbDataSource @Inject constructor(
    private val principalDataDao: PrincipalDataDao,
    private val biographyDao: BiographyDao,
    private val workDao: WorkDao
){
    suspend fun savePrincipalData(principalData:List<SuperHeroePrincipalData>):Either<ErrorApp,Boolean>{

    }
}