package wooooooak.com.studyapp.model.response.blog

import com.google.gson.annotations.SerializedName


data class Blog(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("bloggername")
    val bloggerName: String,
    @SerializedName("bloggerlink")
    val bloggerLink: String
)
