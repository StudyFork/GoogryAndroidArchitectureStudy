package com.example.dkarch.domain.repositoryImpl

import com.example.dkarch.MyApplication
import com.example.dkarch.domain.globalconsts.Consts
import com.example.dkarch.domain.repository.LocalDataSourceRepository
import com.example.dkarch.domain.repository.SharedPrefRepository
import org.json.JSONArray
import org.json.JSONException

class LocalDataSourceRepositoryImpl : LocalDataSourceRepository {
    private val sharedPrefRepository: SharedPrefRepository =
        SharedPrefRepositoryImpl(MyApplication.appContext)


    override fun saveQuery(query: String) {
        val savedQueryList = getSavedQueryList().toMutableList()
        if (query.isNotBlank()) {
            if (savedQueryList.contains(query)) {
                savedQueryList.remove(query)
            }
            if (savedQueryList.size >= 5) {
                savedQueryList.removeFirst()
            }
            savedQueryList.add(query)
        }
        sharedPrefRepository.writePrefs(
            Consts.RECENT_QUERY_LIST,
            JSONArray(savedQueryList).toString()
        )
    }

    override fun getSavedQueryList(): List<String> {
        val queryList = mutableListOf<String>()
        val jsonString = sharedPrefRepository.getPrefsStringValue(Consts.RECENT_QUERY_LIST)

        if (jsonString != null && jsonString.isNotBlank()) {
            try {
                val arr = JSONArray(jsonString)
                for (i in 0 until arr.length()) {
                    queryList.add(arr.getString(i))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return queryList
    }

}
