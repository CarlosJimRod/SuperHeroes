package com.cjimenezro.superheroes.features.list.domain

import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right

class GetSuperHeroeUseCase(
    private val principalDataRepository: SuperHeroeRepository,
    private val biographyRepository: BiographyRepository,
    private val workRepository: WorkRepository
) {

    suspend fun invoke():Either<ErrorApp,List<SuperHeroe>>{
        return try{
            val superHeroesList: MutableList<SuperHeroe> = mutableListOf()
            val result=principalDataRepository.obratinSuperHeroe()
            result.map { superHeroes ->
                superHeroes.map {superHeroe ->
                    val biography=biographyRepository.getBiography(superHeroe.id.toString()).get()
                    val work=workRepository.getWork(superHeroe.id.toString()).get()
                    superHeroesList.add(SuperHeroe(superHeroe,biography,work))
                }
            }
            superHeroesList.right()
        }catch (Ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }

}