package com.cjimenezro.superheroes.features.list.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "ocupation") val ocupation: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
)