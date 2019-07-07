package com.architecturestudy.data.upbit.source

interface UpbitDataSource {
    fun getMarketPrice(prefix: String)
}