package com.architecturestudy.common

import com.architecturestudy.model.UpBitTickerResponse

interface UpBitCommunicable {
    fun onSuccessMarketPrice(marketPrice: List<UpBitTickerResponse>)
}