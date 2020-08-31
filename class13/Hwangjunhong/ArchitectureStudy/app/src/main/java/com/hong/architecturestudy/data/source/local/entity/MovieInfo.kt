package com.hong.architecturestudy.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieInfo(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "movietitle") var movieTitle: String
) {
    constructor() : this(null, "")
}