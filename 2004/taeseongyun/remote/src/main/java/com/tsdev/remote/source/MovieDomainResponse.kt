package com.tsdev.remote.source

import com.tsdev.data.model.EntryItem

data class MovieDomainResponse(
    val display: Int,
    val items: List<EntryItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)