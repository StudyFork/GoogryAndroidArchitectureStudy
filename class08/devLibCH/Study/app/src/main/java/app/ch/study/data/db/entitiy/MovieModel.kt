package app.ch.study.data.db.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieModel")
data class MovieModel(
    @PrimaryKey(autoGenerate = false) var title: String,
    @ColumnInfo(name = "link") var link: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "pubDate") var pubDate: String,
    @ColumnInfo(name = "director") var director: String,
    @ColumnInfo(name = "actor") var actor: String,
    @ColumnInfo(name = "userRating") var userRating: String
)