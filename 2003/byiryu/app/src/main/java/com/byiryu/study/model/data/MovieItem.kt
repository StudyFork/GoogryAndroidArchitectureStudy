package com.byiryu.study.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movie")
data class MovieItem(
    @SerializedName("actor")
    @Expose
    val actor: String,

    @SerializedName("director")
    @Expose
    val director: String,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("link")
    @Expose
    val link: String,

    @SerializedName("pubDate")
    @Expose
    val pubDate: String,

    @SerializedName("subtitle")
    @Expose
    val subtitle: String,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("userRating")
    @Expose
    val userRating: Float


)
