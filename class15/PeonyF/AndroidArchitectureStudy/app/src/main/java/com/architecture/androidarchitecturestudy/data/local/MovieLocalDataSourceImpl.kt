package com.architecture.androidarchitecturestudy.data.local

import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.architecture.androidarchitecturestudy.util.App
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import org.json.JSONException
import javax.inject.Inject
import javax.inject.Singleton


class MovieLocalDataSourceImpl @Inject constructor(
    private val sharedPreferenceUtil: SharedPreferenceUtil
) : MovieLocalDataSource {
    override fun saveSearchHistory(keyword: String) {
        val searchHistoryList = getSearchHistoryList().toMutableList()
        try {
            if ((searchHistoryList.size) > 5) {
                searchHistoryList.removeAt(0)
            }
            searchHistoryList.add(SearchHistoryEntity(keyword))
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        saveSearchHistoryList(searchHistoryList)
    }

    private fun saveSearchHistoryList(keyword: List<SearchHistoryEntity>) {
        sharedPreferenceUtil.saveSearchHistory(keyword)
    }

    override fun getSearchHistoryList(): List<SearchHistoryEntity> =
        sharedPreferenceUtil.getSearchHistoryList()
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindLocalDataSource(localDataSourceImpl: MovieLocalDataSourceImpl): MovieLocalDataSource
}