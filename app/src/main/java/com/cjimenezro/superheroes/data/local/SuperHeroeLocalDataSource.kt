package com.cjimenezro.superheroes.data.local

import android.content.Context
import com.cjimenezro.superheroes.app.serialization.JsonSerialization

class SuperHeroeLocalDataSource(private val context: Context,private val jsonSerialization: JsonSerialization) {

    private val sharedPref=context.getSharedPreferences("SuperHeroes",Context.MODE_PRIVATE)



}