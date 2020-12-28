package com.deepco.data.data.repository

import com.deepco.data.data.model.Item
import com.deepco.data.data.model.RecentSearchData

interface RepositoryMovieData {
    fun getMovieList(
        title: String,
        success: (List<Item>) -> Unit,
        failed: (String) -> Unit
    )

    fun getQueryList(): List<RecentSearchData>
}