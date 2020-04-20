package com.mtjin.data.model.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
    @SerializedName("actor")
    val actor: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("title")
    val title: String,
    @SerializedName("userRating")
    val userRating: String
)