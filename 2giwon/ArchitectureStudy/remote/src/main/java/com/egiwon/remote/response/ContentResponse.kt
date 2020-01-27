package com.egiwon.remote.response

import androidx.annotation.Keep
import com.egiwon.data.model.ContentEntity
import com.egiwon.data.model.ContentItem
import com.google.gson.annotations.SerializedName

data class ContentResponse(
    var query: String,
    @SerializedName("items")
    val contentResponseItems: List<ContentResponseItem>
)

@Keep
data class ContentResponseItem(

    @SerializedName("image")
    val image: String = "",
    @SerializedName("subtitle")
    val subtitle: String = "",
    @SerializedName("pubdate")
    val pubDate: String = "",
    @SerializedName("director")
    val director: String = "",
    @SerializedName("actor")
    val actor: String = "",
    @SerializedName("userRating")
    val userRating: Double = 0.00,

    @SerializedName("bloggerlink")
    val bloggerlink: String = "",
    @SerializedName("bloggername")
    val bloggername: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("link")
    val link: String = "",
    @SerializedName("postdate")
    val postdate: String = "",
    @SerializedName("title")
    val title: String = "",

    @SerializedName("originallink")
    val originallink: String = ""
)


fun ContentResponse.mapToContentEntity(): ContentEntity =
    ContentEntity(
        query = query,
        contentItems = contentResponseItems.map {
            ContentItem(
                image = it.image,
                actor = it.actor,
                description = it.description,
                title = it.title,
                link = it.link
            )
        }
    )