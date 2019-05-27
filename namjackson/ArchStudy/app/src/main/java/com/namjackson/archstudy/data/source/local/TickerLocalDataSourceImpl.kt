package com.namjackson.archstudy.data.source.local

class TickerLocalDataSourceImpl : TickerLocalDataSource {


    companion object {
        private lateinit var instance: TickerLocalDataSource
        fun getInstance(): TickerLocalDataSource {
            if (!this::instance.isInitialized) {
                instance = TickerLocalDataSourceImpl()
            }
            return instance
        }
    }
}