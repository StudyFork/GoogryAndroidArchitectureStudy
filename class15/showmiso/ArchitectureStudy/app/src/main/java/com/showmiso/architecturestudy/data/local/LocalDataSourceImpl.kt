package com.showmiso.architecturestudy.data.local

import android.content.SharedPreferences
import com.showmiso.architecturestudy.Constants

class LocalDataSourceImpl(
    private val prefs: SharedPreferences?
) : LocalDataSource {

    private var searchHistoryText: String?
        get() = prefs?.getString(Constants.PREF_HISTORY_DATA, null)
        set(value) {
            prefs?.edit()?.putString(Constants.PREF_HISTORY_DATA, value)?.apply()
        }

    private val regex = ","

    override fun addHistory(query: String) {
        searchHistoryText?.let {
            if (!it.contains(query)) {
                searchHistoryText = it + regex + query
            }
        }
    }

    override fun getHistory(): List<String>? {
        return searchHistoryText?.split(regex)?.filter {
            it.isNotEmpty()
        }
    }

    override fun removeHistory(query: String) {
        searchHistoryText = searchHistoryText?.let {
            var result = it
            if (it.contains(query)) {
                result = it.replace(query, "")
            }
            result
        }
    }

    override fun removeAllHistory() {
        searchHistoryText = ""
    }
}