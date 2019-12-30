package com.example.androidstudy.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchResult")
data class SearchResultEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Long?,
    @ColumnInfo(name="type")
    val type:String,
    @ColumnInfo(name="search")
    val search:String,
    @ColumnInfo(name="result")
    val result:String
)