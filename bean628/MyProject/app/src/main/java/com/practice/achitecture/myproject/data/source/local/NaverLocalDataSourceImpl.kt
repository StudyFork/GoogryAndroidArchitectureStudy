package com.practice.achitecture.myproject.data.source.local

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.model.SearchedItem
import com.practice.achitecture.myproject.util.AppExecutors
import java.io.File

class NaverLocalDataSourceImpl private constructor(
    val appExcutors: AppExecutors,
    val naverDao: NaverDao,
    val cacheFilePath: String
) : NaverDataSource {

    override fun searchWordByNaver(
        searchType: SearchType,
        word: String,
        callBack: NaverDataSource.GettingResultOfSearchingCallBack
    ) {
        // TODO : 4.1 추가 과제 검색했던 결과 히스토리 (검색할때마다 저장해서 히스토리를 남김)
        //         할 때 Room 사용과 함께 쓸 계획입니다.
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