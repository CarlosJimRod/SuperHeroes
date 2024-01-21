package com.cjimenezro.superheroes.features.list.domain

import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp

interface WorkRepository {

    suspend fun obtainWork(id: String): Either<ErrorApp, SuperHeroeWork>

}