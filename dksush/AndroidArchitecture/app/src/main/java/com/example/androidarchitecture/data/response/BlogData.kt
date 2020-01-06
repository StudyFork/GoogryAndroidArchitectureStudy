package com.example.androidarchitecture.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "blog")
data class BlogData(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @SerializedName("bloggerlink")
    val bloggerLink: String,
    @SerializedName("bloggername")
    val bloggerName: String,
    val description: String,
    val link: String,
    val postdate: String,
    val title: String
)
