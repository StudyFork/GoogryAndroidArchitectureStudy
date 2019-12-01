package wooooooak.com.studyapp.model.response.image

import com.google.gson.annotations.SerializedName

data class ImageList(
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val images: List<Image>
)

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