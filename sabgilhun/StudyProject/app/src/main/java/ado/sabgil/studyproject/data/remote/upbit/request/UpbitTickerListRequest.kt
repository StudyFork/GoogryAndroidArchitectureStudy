package ado.sabgil.studyproject.data.remote.upbit.request

import ado.sabgil.studyproject.data.remote.upbit.response.UpbitMarketCodeResponse

class UpbitTickerListRequest private constructor(val marketCodeQuery: String) {

    companion object {
        fun of(marketCodeList: List<UpbitMarketCodeResponse>, base: Base): UpbitTickerListRequest {
            val stringBuilder = StringBuilder()
            stringBuilder.apply {
                val regex = Regex("""^$base""")
                for (marketCode in marketCodeList) {
                    if (regex.containsMatchIn(marketCode.market)) {
                        append(marketCode.market)
                        append(", ")
                    }
                }
                delete(this.length - 2, this.length - 1)
            }
            return UpbitTickerListRequest(stringBuilder.toString())
        }
    }

    enum class Base(val value: String) {
        KRW("KRW"), BTC("BTC"), ETH("ETH"), USDT("USDT")
    }
}