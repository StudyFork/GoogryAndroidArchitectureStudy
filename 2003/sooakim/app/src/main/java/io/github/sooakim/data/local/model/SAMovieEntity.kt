package io.github.sooakim.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "movie")
data class SAMovieEntity(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "link")
    val link: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "subtitle")
    @PrimaryKey(autoGenerate = false)
    val subtitle: String,

    @ColumnInfo(name = "pubDate")
    val pubDate: Date,

    @ColumnInfo(name = "director")
    val director: String,

    @ColumnInfo(name = "actor")
    val actor: String,

    @ColumnInfo(name = "userRating")
    val userRating: Float
) : SAEntity