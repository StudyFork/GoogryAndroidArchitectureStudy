package com.showmiso.architecturestudy.data.local

import android.content.Context
import com.showmiso.architecturestudy.Constants

class LocalDataSourceImpl(context: Context) : LocalDataSource {

    private val prefs =
        context.getSharedPreferences(Constants.PREF_HISTORY_KEY, Context.MODE_PRIVATE)

    private var data: String?
        get() = prefs.getString(Constants.PREF_HISTORY_DATA, null)
        set(value) = prefs.edit().putString(Constants.PREF_HISTORY_DATA, value).apply()

    private val regex = ","

    override fun addHistory(query: String) {
        val temp = data
        temp?.let {
            if (!it.contains(query)) {
                data = it + regex + query
            }
        }
    }

    override fun getHistory(): List<String>? {
        val temp = data
        return temp?.split(regex)?.filter {
            it.isNotEmpty()
        }
    }

    override fun removeHistory(query: String) {
        val temp = data
        data = temp?.let {
            var result = it
            if (it.contains(query)) {
                result = it.replace(query, "")
            }
            result
        }
    }

    override fun removeAll() {
        data = ""
    }
}