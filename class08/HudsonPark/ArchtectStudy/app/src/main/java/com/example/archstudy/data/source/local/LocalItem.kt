package com.example.archstudy.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localItem")
data class LocalItem(
    @PrimaryKey var searchWord: SearchWord,
    @ColumnInfo(name = "movieData") var movieData: MutableList<MovieData> = mutableListOf()
)
