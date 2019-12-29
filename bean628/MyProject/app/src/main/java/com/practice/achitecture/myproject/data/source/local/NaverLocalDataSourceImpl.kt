package com.practice.achitecture.myproject.data.source.local

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.data.source.local.model.HistoryOfSearch
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.model.SearchedItem
import com.practice.achitecture.myproject.util.AppExecutors
import java.io.File

class NaverLocalDataSourceImpl private constructor(
    val appExcutors: AppExecutors,
    val naverDao: NaverDao,
    val cacheFilePath: String
) : NaverDataSource {

    override fun loadHistoryOfSearch(
        searchType: SearchType,
        callback: NaverDataSource.LoadHistoryOfSearchCallback
    ) {
        appExcutors.diskIO.execute {
            //            val historyOfSearch = naverDao.getHistoryOfSearchCache(searchType.value)
            val historyOfSearchList = naverDao.getHistoryOfSearchList(searchType.value)
            val resultList: ArrayList<SearchedItem> = arrayListOf()

            if (historyOfSearchList != null && historyOfSearchList.isNotEmpty()) {
                for (item in historyOfSearchList) {
                    if (item.searchedItemList.isNotEmpty()) {
                        resultList.addAll(item.searchedItemList)
                    }
                }
            }

            appExcutors.mainThread.execute {
                if (resultList == null || resultList.isEmpty()) {
                    callback.onEmptyData()
                } else {
                    callback.onLoadSuccess(items = resultList)
                }
            }
        }
    }

    override fun searchWordByNaver(
        searchType: SearchType,
        word: String,
        callback: NaverDataSource.GettingResultOfSearchingCallback
    ) {
        // local에서는 쓰이지 않음
    }

    fun saveSearchedListInRoom(searchType: SearchType, word: String, list: List<SearchedItem>) {
        appExcutors.diskIO.execute {
            naverDao.insertResultOfSearch(HistoryOfSearch(searchType.value, word, list))
        }
    }


    // 앱 첫 실행 시 가장 마지막으로 검색한 리스트를 불러옵니다.
    fun getLastSearchType(): SearchType? {
        var lastSearchType: SearchType? = null
        var lastTime: Long? = null
        var cacheFile: File

        for (searchType in SearchType.values()) {
            cacheFile = File(cacheFilePath + searchType.value + ".json")
            if (cacheFile.exists()) {
                if (lastTime == null) {
                    lastTime = cacheFile.lastModified()
                    lastSearchType = searchType
                } else {
                    val temp = cacheFile.lastModified()
                    if (temp > lastTime) {
                        lastTime = temp
                        lastSearchType = searchType
                    }
                }
            }
        }
        return lastSearchType
    }


    fun getCache(searchType: SearchType): List<SearchedItem> {
        val cacheFile = File(cacheFilePath + searchType.value + ".json")
        if (cacheFile.exists()) {
            val gson = Gson()
            val arrayTutorialType = object : TypeToken<List<SearchedItem>>() {}.type
            return gson.fromJson(cacheFile.readText(), arrayTutorialType)
        } else {
            return emptyList()
        }
    }

    @SuppressLint("SdCardPath")
    fun setCache(searchType: SearchType, list: List<SearchedItem>) {
        val cachePath = File(cacheFilePath)
        if (!cachePath.exists()) {
            cachePath.mkdirs()
        }
        val cacheFile = File(cacheFilePath + searchType.value + ".json")
        cacheFile.writeText(Gson().toJson(list))
    }

    companion object {
        private var INSTANCE: NaverLocalDataSourceImpl? = null

        fun getInstance(
            appExecutors: AppExecutors,
            naverDao: NaverDao,
            cacheFilePath: String
        ): NaverLocalDataSourceImpl {
            if (INSTANCE == null) {
                synchronized(NaverLocalDataSourceImpl::javaClass) {
                    INSTANCE = NaverLocalDataSourceImpl(appExecutors, naverDao, cacheFilePath)
                }
            }
            return INSTANCE!!
        }
    }

}