package com.spacexapidemo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.spacexapidemo.data.db.converters.LinksConverter

@Entity(tableName = "launches_table")
data class LaunchesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")
    val ids: Int,
    val date_utc: String?,
    val date_unix: Int,
    val details: String?,
    @TypeConverters(LinksConverter::class)
    val links: Links,
    val name: String?,
    val success: Boolean?
)
