package com.god.taeiim.myapplication.data.source.local

import com.god.taeiim.myapplication.data.SearchHistory
import com.god.taeiim.myapplication.data.source.NaverDataSource

class NaverLocalDataSourceImpl private constructor(private val searchHistoryDao: SearchHistoryDao) :
    NaverDataSource.LocalDataSource {

    override fun getLastSearchResultData(searchType: String): SearchHistory =
        searchHistoryDao.getLastSearchResult(searchType)


    override fun saveSearchResult(searchHistory: SearchHistory) =
        searchHistoryDao.saveSearchHistory(searchHistory)

    companion object {

        private var INSTANCE: NaverLocalDataSourceImpl? = null

        fun getInstance(searchHistoryDao: SearchHistoryDao): NaverLocalDataSourceImpl {
            if (INSTANCE == null) {
                synchronized(NaverLocalDataSourceImpl::javaClass) {
                    INSTANCE = NaverLocalDataSourceImpl(searchHistoryDao)
                }
            }
            return INSTANCE!!
        }
    }

}
