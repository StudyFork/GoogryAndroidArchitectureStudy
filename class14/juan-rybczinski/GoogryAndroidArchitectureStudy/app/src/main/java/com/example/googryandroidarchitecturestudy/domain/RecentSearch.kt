package com.example.googryandroidarchitecturestudy.domain

import com.example.googryandroidarchitecturestudy.database.DatabaseRecentSearch
import java.util.*

data class RecentSearch(
    val searchDate: Date,
    val query: String
)

fun RecentSearch.asDatabaseModel(): DatabaseRecentSearch =
    DatabaseRecentSearch(
        searchDate = searchDate,
        query = query
    )