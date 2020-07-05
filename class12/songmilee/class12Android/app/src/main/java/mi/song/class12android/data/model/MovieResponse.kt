package mi.song.class12android.data.model


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<MovieInfo>,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
)