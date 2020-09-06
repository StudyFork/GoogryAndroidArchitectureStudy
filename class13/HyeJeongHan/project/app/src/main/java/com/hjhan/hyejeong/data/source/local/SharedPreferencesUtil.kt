package com.hjhan.hyejeong.data.source.local

import android.content.SharedPreferences
import com.hjhan.hyejeong.GlobalApplication
import org.json.JSONArray

object SharedPreferencesUtil {

    private const val PREFS_KEY = "prefs"
    private const val PREF_NAVER_MOVIE_QUERY_LIST = "_naver_movie_query_list"

    private val prefs: SharedPreferences =
        GlobalApplication.INSTANCE.getSharedPreferences(PREFS_KEY, 0)

    //저장된 쿼리 리스트 가져옴
    fun getQueryList(): List<String> {

        val str = prefs.getString(PREF_NAVER_MOVIE_QUERY_LIST, "") ?: ""
        val queryList = mutableListOf<String>()

        if (str.isNotBlank()) {
            val jsonArray = JSONArray(str)
            for (i in 0 until jsonArray.length()) {
                queryList.add(jsonArray[i].toString())
            }
        }

        return queryList

    }

    //새로운 쿼리 저장
    fun setQueryList(query: String) {
        val list = getQueryList().toMutableList()

        if (query.isNotBlank()) {
            list.add(query)
        }

        //리스트가 5개 이상일때 삭제
        if (list.size > 5) {
            //가장 오래된 첫번째 값 삭제
            list.removeAt(0)
        }

        val jsonArray = JSONArray(list)
        prefs.edit().putString(PREF_NAVER_MOVIE_QUERY_LIST, jsonArray.toString()).apply()

    }


}