package com.cjimenezro.superheroes.features.list.domain

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right

class GetSuperHeroeByIdUseCase(
    private val principalDataRepository: SuperHeroeRepository,
    private val biographyRepository: BiographyRepository,
    private val workRepository: WorkRepository
) {

    suspend operator fun invoke(id:String):Either<ErrorApp,SuperHeroe>{
        return try{
            val result=principalDataRepository.obtainSuperHeroeById(id).get()
            val biography=biographyRepository.obtainBiography(id).get()
            val work=workRepository.obtainWork(id).get()
            val superHeroe=SuperHeroe(result,biography,work)
            superHeroe.right()
        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }

}