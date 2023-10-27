package com.cjimenezro.superheroes.features.list.data.local

import android.content.Context
import com.cjimenezro.superheroes.app.Either
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.left
import com.cjimenezro.superheroes.app.right
import com.cjimenezro.superheroes.app.serialization.JsonSerialization
import com.cjimenezro.superheroes.features.list.domain.SuperHeroeWork

class WorkLocalDataSource(private val context: Context, private val jsonSerialization: JsonSerialization) {

    private val sharedPref=context.getSharedPreferences("Work",Context.MODE_PRIVATE)

    fun saveWork(work:SuperHeroeWork,id:String):Either<ErrorApp,Boolean> {
        return try {
            with(sharedPref.edit()) {
                val jsonWork = jsonSerialization.toJson(work, SuperHeroeWork::class.java)
                putString(id, jsonWork)
                apply()
            }
            true.right()
        }catch (ex:Exception){
            return ErrorApp.UnknowError.left()
        }
    }

    fun getWork(id:String):Either<ErrorApp,SuperHeroeWork>{
        return try {
            val jsonWork=sharedPref.getString(id,null)
            if (jsonWork.isNullOrBlank()){
                ErrorApp.UnknowError.left()
            }else{
                val work=jsonSerialization.fromJson(jsonWork!!,SuperHeroeWork::class.java)
                work.right()
            }
        }catch (ex:Exception){
            ErrorApp.UnknowError.left()
        }

    }

}