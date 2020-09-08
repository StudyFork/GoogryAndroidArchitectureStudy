package com.example.aas.data.source.local

import android.content.Context
import androidx.core.content.edit
import com.example.aas.MyApplication
import io.reactivex.Completable
import io.reactivex.Single
import org.json.JSONArray
import org.json.JSONException

class LocalDataSourceImpl : LocalDataSource {

    override fun saveQuery(query: String): Completable {
        val appContext = MyApplication.appContext

        val queryHistorySharedPreferences =
            appContext.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

        return getSavedQuery()
            .flatMapCompletable {
                val list = it.toMutableList()
                if (!list.remove(query) && it.size > 4) {
                    list.removeFirstOrNull()
                }
                list.add(query)

                queryHistorySharedPreferences.edit {
                    val editArray = JSONArray()
                    list.forEach { query -> editArray.put(query) }
                    putString(sharedPreferencesName, editArray.toString())
                }
                Completable.complete()
            }
    }

    override fun getSavedQuery(): Single<List<String>> {
        val appContext = MyApplication.appContext

        val queryHistorySharedPreferences =
            appContext.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

        val result = arrayListOf<String>()

        queryHistorySharedPreferences.getString(sharedPreferencesName, null)?.let {
            try {
                val arr = JSONArray(it)
                for (i in 0 until arr.length()) {
                    result.add(arr.getString(i))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return Single.just(result)
    }

    companion object {
        const val sharedPreferencesName = "QueryHistory"
    }
}