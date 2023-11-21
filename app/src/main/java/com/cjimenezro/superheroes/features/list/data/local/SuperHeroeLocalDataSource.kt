package com.cjimenezro.superheroes.features.list.data.local

import android.content.Context
import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right
import com.cjimenezro.superheroes.app.serialization.JsonSerialization
import com.cjimenezro.superheroes.features.list.domain.SuperHeroePrincipalData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SuperHeroeLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonSerialization: JsonSerialization) {

    private val sharedPref=context.getSharedPreferences("SuperHeroes",Context.MODE_PRIVATE)

    fun saveSuperHeroe(superHeroe: SuperHeroePrincipalData): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()) {
                val jsonSuperHeroe = jsonSerialization.toJson(superHeroe, SuperHeroePrincipalData::class.java)
                putString(superHeroe.id.toString(), jsonSuperHeroe)
                apply()
            }
            true.right()
        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }

    fun getSuperHeroe(): Either<ErrorApp, List<SuperHeroePrincipalData>> {
        return try {
            val jsonSuperHeroe=sharedPref.all as Map<String,String>

            val hotels=jsonSuperHeroe.values.map {
                jsonSerialization.fromJson(it,SuperHeroePrincipalData::class.java)
            }

            return hotels.right()
        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }

    }

    fun getSuperHeroeById(id:String): Either<ErrorApp, SuperHeroePrincipalData> {
        return try {
            val jsonBiography=sharedPref.getString(id,null)
            if (jsonBiography.isNullOrBlank()){
                ErrorApp.UnknowError.left()
            }else{
                val superHeroe=jsonSerialization.fromJson(jsonBiography!!, SuperHeroePrincipalData::class.java)
                superHeroe.right()
            }
        }catch (ex:Exception){
            ErrorApp.UnknowError.left()
        }

    }
}