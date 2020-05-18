package com.project.architecturestudy.data.source.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movielist")
class MovieLocal(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "link") var link: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "subtitle") var subtitle: String,
    @ColumnInfo(name = "pubDate") var pubDate: String,
    @ColumnInfo(name = "director") var director: String,
    @ColumnInfo(name = "actor") var actor: String,
    @ColumnInfo(name = "userRating") var userRating: Double?
) {
    constructor() : this(0, "", "", "", "", "", "", "", null)
}