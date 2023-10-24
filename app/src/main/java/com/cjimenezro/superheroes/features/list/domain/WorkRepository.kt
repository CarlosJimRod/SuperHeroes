package com.cjimenezro.superheroes.features.list.domain

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp

interface WorkRepository {

    suspend fun getWork(id:String):Either<ErrorApp,SuperHeroeWork>

}