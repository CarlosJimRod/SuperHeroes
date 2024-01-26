package com.cjimenezro.superheroes.features.list.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BiographyDao {
    @Query("SELECT * FROM biographyEntity WHERE id IN (:id)")
    suspend fun getById(id: String): BiographyEntity?

    @Insert
    suspend fun insert(biography: BiographyEntity)

    @Query("DELETE FROM biographyEntity WHERE id IN (:id)")
    suspend fun deleteById(id: String)
}