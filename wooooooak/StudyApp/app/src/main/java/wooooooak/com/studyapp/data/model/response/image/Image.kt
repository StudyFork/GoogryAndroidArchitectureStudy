package wooooooak.com.studyapp.data.model.response.image

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Image(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("sizeheight")
    val height: String,
    @SerializedName("sizewidth")
    val width: String
)