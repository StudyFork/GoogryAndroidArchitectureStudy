package com.example.androidarchitecturestudy.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchedQuery")
class MovieSearchEntity(
    @PrimaryKey(autoGenerate = true)
    val idx: Int? =0,
    val searchedQuery: String
)