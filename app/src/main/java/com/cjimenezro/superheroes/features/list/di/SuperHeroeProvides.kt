package com.cjimenezro.superheroes.features.list.di

import com.cjimenezro.superheroes.app.data.local.AppDataBase
import com.cjimenezro.superheroes.features.list.data.local.db.BiographyDao
import com.cjimenezro.superheroes.features.list.data.local.db.PrincipalDataDao
import com.cjimenezro.superheroes.features.list.data.local.db.WorkDao
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class SuperHeroeProvides {

    @Provides
    fun provideSuperHeroeApiService(retrofit: Retrofit): SuperHeroeApiService =
        retrofit.create(SuperHeroeApiService::class.java)

    @Provides
    fun providePrincipalDataDao(appDatBase: AppDataBase): PrincipalDataDao = appDatBase.principaDataDao()

    @Provides
    fun provideBiographyDao(appDatBase: AppDataBase): BiographyDao = appDatBase.biographyDao()

    @Provides
    fun provideWorkDao(appDatBase: AppDataBase): WorkDao = appDatBase.workDao()
}