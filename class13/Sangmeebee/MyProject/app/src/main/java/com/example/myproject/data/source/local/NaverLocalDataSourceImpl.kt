package com.example.myproject.data.source.local

import com.example.myproject.data.App

class NaverLocalDataSourceImpl : NaverLocalDataSource {
    override fun saveData(title: String) {
        App.prefs.saveData(title)
    }

    override fun readData() = App.prefs.readData()
}
