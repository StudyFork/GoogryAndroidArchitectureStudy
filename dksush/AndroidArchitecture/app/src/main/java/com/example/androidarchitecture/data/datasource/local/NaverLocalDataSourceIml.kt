package com.example.androidarchitecture.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.example.androidarchitecture.common.StringConst.Companion.PREF_KEY
import com.example.androidarchitecture.common.StringConst.Companion.PREF_KEY_BLOG
import com.example.androidarchitecture.data.datasource.database.SearchHistDatabase
import com.example.androidarchitecture.data.response.BlogData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NaverLocalDataSourceIml(private val context: Context) : NaverLocalDataSourceInterface {

    override val spm: SharedPreferences by lazy {
        context.getSharedPreferences(PREF_KEY, 0)
    }
    override val searchHistyDatabase: SearchHistDatabase by lazy {
        SearchHistDatabase.getInstance(context)
    }


    override fun saveBlogHist(blog: List<BlogData>) {
        CoroutineScope(Dispatchers.IO).launch {
            searchHistyDatabase.blofDao().insertAll(blog)
        }


    }

    override suspend fun getBlogHist(): List<BlogData> = searchHistyDatabase.blofDao().getAll()


    override fun saveBlogKeyword(keyword: String) {
        spm.edit()
            .putString(PREF_KEY_BLOG, keyword)
            .apply()
    }

    override fun getBlogKeyword(): String = spm.getString(PREF_KEY_BLOG, "") ?: ""


}