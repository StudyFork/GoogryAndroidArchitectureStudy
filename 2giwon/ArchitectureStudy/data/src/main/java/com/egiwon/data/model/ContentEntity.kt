package com.egiwon.data.model

data class ContentEntity(
    val query: String,
    val contentItems: List<ContentItem>
)

data class ContentItem(
    val image: String = "",
    val actor: String = "",
    val description: String = "",
    val title: String = "",
    val link: String = ""
)
