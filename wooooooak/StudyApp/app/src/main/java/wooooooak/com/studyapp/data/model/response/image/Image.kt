package wooooooak.com.studyapp.data.model.response.image

import com.google.gson.annotations.SerializedName

data class Image(
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