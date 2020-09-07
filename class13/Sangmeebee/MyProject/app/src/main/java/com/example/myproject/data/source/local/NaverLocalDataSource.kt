package com.example.myproject.data.source.local

interface NaverLocalDataSource {
    fun saveData(title: String)
    fun readData(): ArrayList<String>
}
