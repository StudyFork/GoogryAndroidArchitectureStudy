package wooooooak.com.studyapp.model.response.movie

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("items")
    val movies: List<Movie>
)

data class Movie(
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
    @SerializedName("userRating")
    val userRating: Float
)
