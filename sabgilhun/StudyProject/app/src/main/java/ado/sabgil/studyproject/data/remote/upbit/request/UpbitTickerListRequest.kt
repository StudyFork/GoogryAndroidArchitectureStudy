package ado.sabgil.studyproject.data.remote.upbit.request

import ado.sabgil.studyproject.data.remote.upbit.response.UpbitMarketCodeResponse

class UpbitTickerListRequest private constructor(
    val marketCodeQuery: String
) {

    companion object {
        fun of(marketCodeList: List<UpbitMarketCodeResponse>, base: Base): UpbitTickerListRequest {
            val stringBuilder = StringBuilder()
            val regex = Regex("""^$base""")

            stringBuilder.apply {
                marketCodeList
                    .filter {
                        regex.containsMatchIn(it.market)
                    }.map {
                        append(it.market)
                        append(", ")
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