package io.github.sooakim.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "movie")
internal data class SAMovieEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "link")
    val link: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "subtitle")
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