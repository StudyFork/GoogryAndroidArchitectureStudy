package com.hhi.myapplication.data.local

import com.hhi.myapplication.App
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import org.json.JSONArray
import javax.inject.Inject
import javax.inject.Singleton

class NaverLocalDataSourceImpl @Inject constructor() : NaverLocalDataSource {
    override fun saveQuery(query: String) {
        val queryList = getQueryList().toMutableList()
        queryList.add(query)

        if (queryList.size > 5) {
            queryList.removeAt(0)
        }

        App.prefs.setString(JSONArray(queryList).toString(), PREF_QUERY_LIST)

    }

    override fun getQueryList(): List<String> {
        val queryListJSONString = App.prefs.getString(PREF_QUERY_LIST)
        val queryList = mutableListOf<String>()

        queryListJSONString?.let {
            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                queryList.add(jsonArray.getString(i))
            }
        }
        return queryList
    }

    companion object {
        private const val PREF_QUERY_LIST: String = "pref_query_list"
    }
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class NaverLocalDataModule {
    @Binds
    @Singleton
    abstract fun bindNaverLocalData(naverLocalDataSourceImpl: NaverLocalDataSourceImpl): NaverLocalDataSource
}