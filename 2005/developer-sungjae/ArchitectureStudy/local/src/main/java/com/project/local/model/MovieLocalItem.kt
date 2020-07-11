package com.project.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.data.model.MovieItem

@Entity(tableName = "movielist")
data class MovieLocalItem(
    @PrimaryKey(autoGenerate = true) var id: Long,
    val list: List<MovieItem>
)