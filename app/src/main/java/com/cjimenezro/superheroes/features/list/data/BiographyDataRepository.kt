package com.cjimenezro.superheroes.features.list.data

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.data.remote.toModel
import com.cjimenezro.superheroes.features.list.domain.BiographyRepository
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography

class BiographyDataRepository:BiographyRepository {

    private val superHeroesApiClient= SuperHeroesApiClient().retrofit
    override suspend fun getBiography(id: String): Either<ErrorApp, SuperHeroeBiography> {
        return try {
            val result= superHeroesApiClient.getBiography(id).body()!!

            result.toModel().right()
        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}