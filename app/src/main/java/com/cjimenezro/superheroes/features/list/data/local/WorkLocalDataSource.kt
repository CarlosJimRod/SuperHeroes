package com.cjimenezro.superheroes.features.list.data.local

import android.content.Context
import com.cjimenezro.superheroes.app.data.serialization.JsonSerialization
import com.cjimenezro.superheroes.app.domain.Either
import com.cjimenezro.superheroes.app.domain.ErrorApp
import com.cjimenezro.superheroes.app.domain.left
import com.cjimenezro.superheroes.app.domain.right
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WorkLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonSerialization: JsonSerialization
) {

    private val sharedPref = context.getSharedPreferences("Work", Context.MODE_PRIVATE)

    fun saveWork(work: SuperHeroeWork, id: String): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()) {
                val jsonWork = jsonSerialization.toJson(work, SuperHeroeWork::class.java)
                putString(id, jsonWork)
                apply()
            }
            true.right()
        } catch (ex: Exception) {
            return ErrorApp.UnknownError.left()
        }
    }

    fun getWork(id: String): Either<ErrorApp, SuperHeroeWork> {
        return try {
            val jsonWork = sharedPref.getString(id, null)
            if (jsonWork.isNullOrBlank()) {
                ErrorApp.UnknownError.left()
            } else {
                val work = jsonSerialization.fromJson(jsonWork!!, SuperHeroeWork::class.java)
                work.right()
            }
        } catch (ex: Exception) {
            ErrorApp.UnknownError.left()
        }

    }

}