package com.jay.architecturestudy.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blog")
data class BlogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "blogger_link")
    val bloggerLink: String,
    @ColumnInfo(name = "blogger_name")
    val bloggerName: String,
    val description: String,
    val link: String,
    val postdate: String,
    val title: String
)