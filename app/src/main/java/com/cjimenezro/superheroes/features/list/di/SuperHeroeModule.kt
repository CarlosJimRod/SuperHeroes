package com.cjimenezro.superheroes.features.list.di

import com.cjimenezro.superheroes.features.list.data.BiographyDataRepository
import com.cjimenezro.superheroes.features.list.data.SuperHeroeDataRepository
import com.cjimenezro.superheroes.features.list.data.WorkDataRepository
import com.cjimenezro.superheroes.features.list.domain.BiographyRepository
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeRepository
import com.cjimenezro.superheroes.features.list.domain.WorkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SuperHeroeModule {

    @Binds
    abstract fun bindPrincipalDataRepository(superHeroeDataRepository: SuperHeroeDataRepository):SuperHeroeRepository
    @Binds
    abstract fun bindBiographyRepository(biographyDataRepository: BiographyDataRepository): BiographyRepository
    @Binds
    abstract fun bindWorkRepository(workDataRepository: WorkDataRepository): WorkRepository

}