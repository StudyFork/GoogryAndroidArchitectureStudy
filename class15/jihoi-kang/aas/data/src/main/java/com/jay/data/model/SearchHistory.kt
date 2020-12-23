package com.jay.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchHistory(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "queryText") val queryText: String
)