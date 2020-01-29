package com.example.study.data.source.local

import com.example.study.data.source.local.model.SearchResult

class NaverSearchLocalDataSourceImpl private constructor(
    private val naverResultDao: SearchResultDao
) : NaverSearchLocalDataSource {

    override fun addSearchResult(searchResult: SearchResult) {
        naverResultDao.addSearchResult(searchResult)
    }

    override fun getRecentSearchResult(): SearchResult {
        return naverResultDao.getRecentSearchResult()
    }

    companion object {
        private var instance: NaverSearchLocalDataSourceImpl? = null

        fun getInstance(naverResultDao: SearchResultDao): NaverSearchLocalDataSourceImpl {
            return instance ?: synchronized(NaverSearchLocalDataSourceImpl::javaClass) {
                instance ?: NaverSearchLocalDataSourceImpl(naverResultDao).also { instance = it }
            }

        }
    }
}