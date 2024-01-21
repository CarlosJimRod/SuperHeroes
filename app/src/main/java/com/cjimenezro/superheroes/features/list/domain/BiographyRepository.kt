package com.cjimenezro.superheroes.features.list.domain

import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp

interface BiographyRepository {

    suspend fun obtainBiography(id: String): Either<ErrorApp, SuperHeroeBiography>

}