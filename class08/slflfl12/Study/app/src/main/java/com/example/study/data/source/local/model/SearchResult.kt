package com.example.study.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "searchresult")
data class SearchResult(

    @ColumnInfo(name = "result_items")
    @SerializedName("items")
    val results: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0


)