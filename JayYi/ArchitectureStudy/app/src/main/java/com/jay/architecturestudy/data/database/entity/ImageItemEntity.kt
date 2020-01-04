package com.jay.architecturestudy.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class ImageItemEntity(
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