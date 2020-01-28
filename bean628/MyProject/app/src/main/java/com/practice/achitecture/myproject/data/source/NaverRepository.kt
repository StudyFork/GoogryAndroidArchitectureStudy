package com.practice.achitecture.myproject.data.source

import com.practice.achitecture.myproject.data.source.local.NaverLocalDataSourceImpl
import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSourceImpl
import com.practice.achitecture.myproject.enums.SearchType
import com.practice.achitecture.myproject.model.SearchedItem

class NaverRepository(
    private val naverRemoteDataSource: NaverDataSource,
    private val naverLocalDataSource: NaverDataSource
) : NaverDataSource {


    override fun setCache(searchType: SearchType, word: String, list: List<SearchedItem>) {
        naverLocalDataSource.setCache(searchType, word, list) // 카테고리별 마지막 검색 캐시 저장
    }

    override fun saveSearchedListInRoom(searchType: SearchType, word: String, list: List<SearchedItem>) {
        naverLocalDataSource.setCache(searchType, word, list) // 카테고리별 마지막 검색 캐시 저장
    }

    override fun loadHistoryOfSearch(
        searchType: SearchType,
        callback: NaverDataSource.LoadHistoryOfSearchCallback
    ) {
        naverLocalDataSource.loadHistoryOfSearch(searchType, callback)
    }

    override fun searchWordByNaver(
        searchType: SearchType,
        word: String,
        callback: NaverDataSource.GettingResultOfSearchingCallback
    ) {
        naverRemoteDataSource.searchWordByNaver(
            searchType,
            word,
            object : NaverDataSource.GettingResultOfSearchingCallback {
                override fun onSuccess(items: List<SearchedItem>) {
                    setCache(searchType, word, items)
                    saveSearchedListInRoom(searchType, word, items)
                    naverLocalDataSource.setCache(searchType, word, items) // 카테고리별 마지막 검색 캐시 저장
                    naverLocalDataSource.saveSearchedListInRoom(searchType, word, items) // 검색 기록 저장
                    callback.onSuccess(items)
                }

                override fun onFailure(throwable: Throwable) { // 캐시가 없는 경우
                    callback.onFailure(throwable)
                }

            })
    }


    override fun getLastSearchType(): SearchType {
        return naverLocalDataSource.getLastSearchType()
    }

    override fun getCache(searchType: SearchType): String {
        return naverLocalDataSource.getCache(searchType)
    }

    companion object {

        private var INSTANCE: NaverRepository? = null

        fun getInstance(
            naverRemoteDataSource: NaverRemoteDataSourceImpl,
            naverLocalDataSource: NaverLocalDataSourceImpl
        ): NaverRepository {
            return INSTANCE ?: NaverRepository(
                naverRemoteDataSource,
                naverLocalDataSource
            ).apply { INSTANCE = this }
        }

    }
}