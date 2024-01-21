package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right
import com.cjimenezro.superheroes.features.list.data.local.SuperHeroeLocalDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroeApiService
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.data.remote.toModel
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeRepository
import javax.inject.Inject

class SuperHeroeDataRepository @Inject constructor(private val localDataSource: SuperHeroeLocalDataSource,
                               private val apiClient: SuperHeroeApiService
                               ):SuperHeroeRepository {

    override suspend fun obratinSuperHeroe(): Either<ErrorApp, List<SuperHeroePrincipalData>> {
        val superHeroes:MutableList<SuperHeroePrincipalData> = mutableListOf()
        val listaLocal=localDataSource.getSuperHeroe().get()
        return try {
            if (listaLocal.size!=0){
                localDataSource.getSuperHeroe()
            }else{
                val result= apiClient.getPrincipaData().body()!!
                for (i in result.indices){
                    superHeroes.add(result[i].toModel())
                    localDataSource.saveSuperHeroe(superHeroes[i])
                }
                superHeroes.right()
            }

        }catch (ex:Exception){
            ErrorApp.UnknowError.left()
        }
    }

    override suspend fun obtainSuperHeroeById(id: String): Either<ErrorApp, SuperHeroePrincipalData> {
        return try {
            if (localDataSource.getSuperHeroeById(id).isRight()){
                localDataSource.getSuperHeroeById(id)
            }else{
                val result= apiClient.getPrincipaDataById(id).body()!!
                localDataSource.saveSuperHeroe(result.toModel())
                result.toModel().right()
            }

        }catch (ex:Exception){
            ErrorApp.UnknowError.left()
        }
    }
}