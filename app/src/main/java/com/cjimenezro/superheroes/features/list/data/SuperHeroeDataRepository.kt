package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right
import com.cjimenezro.superheroes.features.list.data.local.SuperHeroeLocalDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.data.remote.toModel
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeRepository

class SuperHeroeDataRepository(private val localDataSource: SuperHeroeLocalDataSource,
                               private val apiClient:SuperHeroesApiClient
                               ):SuperHeroeRepository {

    override suspend fun obratinSuperHeroe(): Either<ErrorApp, List<SuperHeroePrincipalData>> {
        val superHeroes:MutableList<SuperHeroePrincipalData> = mutableListOf()
        val listaLocal=localDataSource.getSuperHeroe().get()
        try {
            if (listaLocal.size!=0){
                return localDataSource.getSuperHeroe()
            }else{
                val result= apiClient.retrofit.getPrincipaData().body()!!
                for (i in result.indices){
                    superHeroes.add(result[i].toModel())
                    localDataSource.saveSuperHeroe(superHeroes[i])
                }
                return superHeroes.right()
            }

        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }

    override suspend fun obtainSuperHeroeById(id: String): Either<ErrorApp, SuperHeroePrincipalData> {
        try {
            if (localDataSource.getSuperHeroeById(id).isRight()){
                return localDataSource.getSuperHeroeById(id)
            }else{
                val result= apiClient.retrofit.getPrincipaDataById(id).body()!!
                localDataSource.saveSuperHeroe(result.toModel())
                return result.toModel().right()
            }

        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}