package com.example.study.data.source.local

import com.example.study.data.source.local.model.SearchResult

class NaverSearchLocalDataSourceImpl(
    private val naverResultDao: SearchResultDao
) : NaverSearchLocalDataSource {

    override fun addSearchResult(searchResult: SearchResult) {
        naverResultDao.addSearchResult(searchResult)
    }

    override fun getRecentSearchResult(): SearchResult? {
        return naverResultDao.getRecentSearchResult()
    }


}