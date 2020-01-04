package com.example.androidarchitecture.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class MovieData(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("actor")
    val actor: String,
    @SerializedName("userRating")
    val origin_userRating: Float
) {
    val userRating: Float
        get() = origin_userRating / 2
}