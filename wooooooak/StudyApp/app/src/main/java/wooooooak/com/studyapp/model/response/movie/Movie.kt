package wooooooak.com.studyapp.model.response.movie

import com.google.gson.annotations.SerializedName


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
    @SerializedName("director")
    val director: String,
    @SerializedName("actor")
    val actor: String,
    @SerializedName("userRating")
    val _userRating: Float
) {
    val userRating: Float
        get() = _userRating / 2
}
