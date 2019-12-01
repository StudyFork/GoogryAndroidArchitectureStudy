package wooooooak.com.studyapp.model.response.kin

import com.google.gson.annotations.SerializedName

data class KinList(
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val kins: List<Kin>
)

data class Kin(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("description")
    val description: String
)