package com.example.myproject.data.source.local

import com.example.myproject.data.App

class NaverLocalDataSourceImpl : NaverLocalDataSource {
    override fun saveRecentSearchTitle(title: String) {
        App.prefs.saveRecentSearchTitle(title)
    }

    override fun readRecentSearchTitle(): ArrayList<String> {
        var temp = arrayListOf<String>()
        val dataList = App.prefs.readRecentSearchTitle()
        for (i in dataList.size - 1 downTo 0) {
            temp.add(dataList[i])
        }
        return temp
    }
}
