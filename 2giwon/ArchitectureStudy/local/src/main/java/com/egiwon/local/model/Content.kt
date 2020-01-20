package com.egiwon.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.egiwon.data.model.ContentItem

@Entity(tableName = "contents")
data class Content(
    @PrimaryKey val id: Long,
    val list: List<ContentItem>,
    val type: String,
    val query: String
) {
    companion object {
        fun empty(type: String, query: String): Content =
            Content(System.currentTimeMillis(), emptyList(), type, query)
    }
}