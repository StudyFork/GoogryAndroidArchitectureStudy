package com.example.myapplication.data.source

import com.example.myapplication.data.model.MovieResult

object NaverLocalDataSourceImpl : NaverLocalDataSource {
    override fun getResentData(success: (MovieResult) -> Unit, fail: (Throwable) -> Unit) {
    }

    override fun saveCache() {
    }

}