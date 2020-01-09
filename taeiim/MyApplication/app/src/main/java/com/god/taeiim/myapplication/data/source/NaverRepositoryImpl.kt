package com.god.taeiim.myapplication.data.source

import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.data.SearchHistory

class NaverRepositoryImpl private constructor(
    private val naverRemote: NaverDataSource.RemoteDataSource,
    private val naverLocal: NaverDataSource.LocalDataSource
) : NaverRepository {

    override fun getResultData(
        searchType: Tabs,
        query: String,
        success: (results: SearchResult) -> Unit,
        fail: (t: Throwable) -> Unit
    ) {
        naverRemote.getResultData(searchType.name, query, success, fail)
    }

    override fun getLastSearchResultData(searchType: Tabs): SearchHistory =
        naverLocal.getLastSearchResultData(searchType.name)

    override fun saveSearchResult(searchHistory: SearchHistory) {
        naverLocal.saveSearchResult(searchHistory)
    }

    companion object {

        private var INSTANCE: NaverRepositoryImpl? = null

        fun getInstance(
            tasksRemoteDataSource: NaverDataSource.RemoteDataSource,
            tasksLocalDataSource: NaverDataSource.LocalDataSource
        ): NaverRepositoryImpl {
            return INSTANCE ?: NaverRepositoryImpl(tasksRemoteDataSource, tasksLocalDataSource)
                .apply { INSTANCE = this }
        }

    }

}