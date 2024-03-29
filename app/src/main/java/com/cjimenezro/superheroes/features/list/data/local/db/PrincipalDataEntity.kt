package com.cjimenezro.superheroes.features.list.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PrincipalDataEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "intelligence") val intelligence: String,
    @ColumnInfo(name = "speed") val speed: String,
    @ColumnInfo(name = "combat") val combat: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
)