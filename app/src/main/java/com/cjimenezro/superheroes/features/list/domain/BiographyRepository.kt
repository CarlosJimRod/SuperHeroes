package com.cjimenezro.superheroes.features.list.domain

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp

interface BiographyRepository {

    suspend fun getBiography(id:String):Either<ErrorApp,SuperHeroeBiography>

}