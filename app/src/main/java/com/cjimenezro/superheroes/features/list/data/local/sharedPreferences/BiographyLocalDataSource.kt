package com.cjimenezro.superheroes.features.list.data.local.sharedPreferences

import android.content.Context
import com.cjimenezro.superheroes.app.data.serialization.JsonSerialization
import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp
import com.cjimenezro.superheroes.app.domain.left
import com.cjimenezro.superheroes.app.domain.right
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeBiography
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BiographyLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonSerialization: JsonSerialization
) {

    private val sharedPref = context.getSharedPreferences("Biography", Context.MODE_PRIVATE)

    fun saveBiography(biography: SuperHeroeBiography, id: String): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()) {
                val jsonBiography =
                    jsonSerialization.toJson(biography, SuperHeroeBiography::class.java)
                putString(id, jsonBiography)
                apply()
            }
            true.right()
        }catch (ex:Exception){
            return ErrorApp.UnknownError.left()
        }
    }

    fun getBiography(id:String): Either<ErrorApp, SuperHeroeBiography> {
        return try {
            val jsonBiography=sharedPref.getString(id,null)
            if (jsonBiography.isNullOrBlank()){
                ErrorApp.UnknownError.left()
            }else{
                val biography=jsonSerialization.fromJson(jsonBiography!!, SuperHeroeBiography::class.java)
                biography.right()
            }
        }catch (ex:Exception){
            ErrorApp.UnknownError.left()
        }

    }

}