package com.cjimenezro.superheroes.features.list.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkDao {
    @Query("SELECT * FROM workentity WHERE id IN (:id)")
    suspend fun getById(id: Int): WorkEntity?

    @Insert
    suspend fun insert(work: WorkEntity)

    @Query("DELETE FROM workentity WHERE id IN (:id)")
    suspend fun deleteById(id: Int)
}