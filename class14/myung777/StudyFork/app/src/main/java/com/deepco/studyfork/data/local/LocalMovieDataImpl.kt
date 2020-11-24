package com.deepco.studyfork.data.local

import com.deepco.studyfork.MyApplication

class LocalMovieDataImpl : LocalMovieData {
    override fun saveQuery(query: String) {
        MyApplication.prefs.saveQuery(query)
    }

    override fun getQueryList(): List<String> =
        MyApplication.prefs.getQueryList()
}