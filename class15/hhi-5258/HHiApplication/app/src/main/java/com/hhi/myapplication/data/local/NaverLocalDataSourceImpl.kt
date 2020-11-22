package com.hhi.myapplication.data.local

import com.hhi.myapplication.App

class NaverLocalDataSourceImpl : NaverLocalDataSource {
    override fun saveQuery(query: String) {
        App.prefs.saveQuery(query)
    }

    override fun getQueryList(): List<String> = App.prefs.getQueryList()
}