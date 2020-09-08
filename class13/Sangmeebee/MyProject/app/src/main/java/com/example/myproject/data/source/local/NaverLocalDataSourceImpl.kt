package com.example.myproject.data.source.local

import com.example.myproject.data.App

class NaverLocalDataSourceImpl : NaverLocalDataSource {
    override fun saveData(title: String) {
        App.prefs.saveData(title)
    }

    override fun readData(): ArrayList<String> {
        var temp = arrayListOf<String>()
        val dataList = App.prefs.readData()
        for (i in dataList.size - 1 downTo 0) {
            temp.add(dataList[i])
        }
        return temp
    }
}
