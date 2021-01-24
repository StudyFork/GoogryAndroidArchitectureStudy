package com.example.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.RecentSearch
import java.util.*

@Entity
data class DatabaseRecentSearch(
    @PrimaryKey
    val searchDate: Date,
    val query: String
)

fun List<DatabaseRecentSearch>.asDomainModel(): List<RecentSearch> {
    return map {
        RecentSearch(
            searchDate = it.searchDate,
            query = it.query
        )
    }
}

fun RecentSearch.asDatabaseModel(): DatabaseRecentSearch =
    DatabaseRecentSearch(
        searchDate = searchDate,
        query = query
    )