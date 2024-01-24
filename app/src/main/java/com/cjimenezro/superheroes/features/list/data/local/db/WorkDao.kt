package com.cjimenezro.superheroes.features.list.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkDao {
    @Query("SELECT * FROM workEntity WHERE id IN (:id)")
    suspend fun getById(id: String): WorkEntity?

    @Insert
    suspend fun insert(work: WorkEntity)

    @Query("DELETE FROM workEntity WHERE id IN (:id)")
    suspend fun deleteById(id: String)
}