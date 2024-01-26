package com.cjimenezro.superheroes.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cjimenezro.superheroes.features.list.data.local.db.BiographyDao
import com.cjimenezro.superheroes.features.list.data.local.db.BiographyEntity
import com.cjimenezro.superheroes.features.list.data.local.db.PrincipalDataDao
import com.cjimenezro.superheroes.features.list.data.local.db.PrincipalDataEntity
import com.cjimenezro.superheroes.features.list.data.local.db.WorkDao
import com.cjimenezro.superheroes.features.list.data.local.db.WorkEntity

@Database(
    entities = [PrincipalDataEntity::class, BiographyEntity::class, WorkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun principaDataDao(): PrincipalDataDao
    abstract fun biographyDao(): BiographyDao
    abstract fun workDao(): WorkDao
}