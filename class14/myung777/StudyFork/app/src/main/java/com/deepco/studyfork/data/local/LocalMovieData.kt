package com.deepco.studyfork.data.local

import com.deepco.studyfork.data.model.RecentSearchData

interface LocalMovieData {
    fun saveQuery(query: String)

    fun getQueryList(): List<RecentSearchData>
}