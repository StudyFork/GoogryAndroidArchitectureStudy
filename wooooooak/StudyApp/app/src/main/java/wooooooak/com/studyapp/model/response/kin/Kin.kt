package wooooooak.com.studyapp.model.response.kin

import com.google.gson.annotations.SerializedName

data class Kin(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("description")
    val description: String
)