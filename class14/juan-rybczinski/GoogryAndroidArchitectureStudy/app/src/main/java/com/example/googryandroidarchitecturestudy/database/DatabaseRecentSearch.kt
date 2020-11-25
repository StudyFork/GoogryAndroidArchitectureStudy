package com.example.googryandroidarchitecturestudy.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
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