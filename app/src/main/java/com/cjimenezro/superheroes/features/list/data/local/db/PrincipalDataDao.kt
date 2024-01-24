package com.cjimenezro.superheroes.features.list.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PrincipalDataDao {
    @Query("SELECT * FROM principalDataEntity")
    suspend fun getAll(): List<PrincipalDataEntity>

    @Query("SELECT * FROM principalDataEntity WHERE id IN (:id)")
    suspend fun getById(id: String): PrincipalDataEntity?

    @Insert
    suspend fun insertAll(vararg principalData: PrincipalDataEntity)

    @Query("DELETE FROM principalDataEntity")
    suspend fun deleteAll()
}