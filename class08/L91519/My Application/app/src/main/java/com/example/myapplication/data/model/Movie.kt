package com.example.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class Movie(
    @ColumnInfo(name = "result_items")
    val results: List<MovieResult.Item>,

    @ColumnInfo(name = "query")
    val query: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0
)