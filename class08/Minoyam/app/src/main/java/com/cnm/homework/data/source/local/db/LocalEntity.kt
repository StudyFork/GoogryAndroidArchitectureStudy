package com.cnm.homework.data.source.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local")
data class LocalEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var title: String = "",
    var actor: String = "",
    var director: String = "",
    var image: String = "",
    var link: String = "",
    var pubDate: String = "",
    var subtitle: String = "",
    var userRating: Float = 0f
)