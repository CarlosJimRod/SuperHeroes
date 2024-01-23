package com.cjimenezro.superheroes.features.list.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "ocupation") val ocupation: String
)