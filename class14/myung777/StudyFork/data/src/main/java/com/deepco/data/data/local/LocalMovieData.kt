package com.deepco.data.data.local

import com.deepco.data.data.model.RecentSearchData

interface LocalMovieData {
    fun saveQuery(query: String)

    fun getQueryList(): List<RecentSearchData>
}