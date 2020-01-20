package com.example.archstudy.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SearchWord")
data class SearchWord(
    @PrimaryKey(autoGenerate = false) var id: Long,
    @ColumnInfo(name = "searchWord") val searchWord: String
)