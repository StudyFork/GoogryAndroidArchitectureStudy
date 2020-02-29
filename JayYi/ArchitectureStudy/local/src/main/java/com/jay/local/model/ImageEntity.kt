package com.jay.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val link: String,
    @ColumnInfo(name = "size_height")
    val sizeHeight: String,
    @ColumnInfo(name = "size_width")
    val sizeWidth: String,
    val thumbnail: String,
    val title: String
)

data class ImageLocalData(
    val images: List<ImageEntity>
)
