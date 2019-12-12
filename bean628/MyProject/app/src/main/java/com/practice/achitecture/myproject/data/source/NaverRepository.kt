package com.practice.achitecture.myproject.data.source

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.practice.achitecture.myproject.data.source.local.NaverLocalDataSourceImpl
import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSourceImpl
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.model.SearchedItem
import com.practice.achitecture.myproject.network.RetrofitClient
import common.NAVER_API_BASE_URL
import java.io.File

object NaverRepository : NaverDataSource {

    private val naverRemoteDataSource = NaverRemoteDataSourceImpl(
        RetrofitClient(NAVER_API_BASE_URL).makeRetrofitServiceForNaver()
    )

    private val naverLocalDataSource = NaverLocalDataSourceImpl()


    var cacheFilePath = ""

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
    private fun setCache(searchType: SearchType, list: List<SearchedItem>) {
        val cachePath = File(cacheFilePath)
        if (!cachePath.exists()) {
            cachePath.mkdirs()
        }
        val cacheFile = File(cacheFilePath + searchType.value + ".json")
        cacheFile.writeText(Gson().toJson(list))
    }


    override fun searchWordByNaver(
        searchType: SearchType,
        word: String,
        callBack: NaverDataSource.GettingResultOfSearchingCallBack
    ) {
        naverRemoteDataSource.searchWordByNaver(
            searchType,
            word,
            object : NaverDataSource.GettingResultOfSearchingCallBack {
                override fun onSuccess(items: List<SearchedItem>) {
                    setCache(searchType, items)
                    callBack.onSuccess(items)
                }

                override fun onFailure(throwable: Throwable) { // 캐시가 없는 경우
                    callBack.onFailure(throwable)
                }

            })

    }


}