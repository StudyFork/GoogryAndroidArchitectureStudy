package com.showmiso.architecturestudy.data.local

class LocalDataSourceImpl : LocalDataSource {
    override fun addHistory(query: String) {
    }

    override fun getHistory(): List<String> {
        return listOf()
    }

    override fun removeHistory(query: String) {
    }
}