package com.cjimenezro.superheroes.features.list.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BiographyEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "alignment") val alignment: String
)