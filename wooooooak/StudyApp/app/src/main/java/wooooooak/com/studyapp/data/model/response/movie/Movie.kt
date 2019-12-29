package wooooooak.com.studyapp.data.model.response.movie

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
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
    val rating: Float
) {
    val userRating: Float
        get() = rating / 2
}
