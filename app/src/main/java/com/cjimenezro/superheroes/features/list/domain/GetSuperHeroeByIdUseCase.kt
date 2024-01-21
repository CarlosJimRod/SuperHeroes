package com.cjimenezro.superheroes.features.list.domain

import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp
import com.cjimenezro.superheroes.app.domain.left
import com.cjimenezro.superheroes.app.domain.right
import javax.inject.Inject

class GetSuperHeroeByIdUseCase @Inject constructor(
    private val principalDataRepository: SuperHeroeRepository,
    private val biographyRepository: BiographyRepository,
    private val workRepository: WorkRepository
) {

    suspend operator fun invoke(id: String): Either<ErrorApp, SuperHeroe> {
        return try {
            val result = principalDataRepository.obtainSuperHeroeById(id).get()
            val biography = biographyRepository.obtainBiography(id).get()
            val work = workRepository.obtainWork(id).get()
            val superHeroe = SuperHeroe(result, biography, work)
            superHeroe.right()
        } catch (ex: Exception) {
            return ErrorApp.UnknownError.left()
        }
    }

}