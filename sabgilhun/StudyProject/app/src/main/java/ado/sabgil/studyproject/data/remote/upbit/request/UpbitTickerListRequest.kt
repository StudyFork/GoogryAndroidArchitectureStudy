package ado.sabgil.studyproject.data.remote.upbit.request

import ado.sabgil.studyproject.data.remote.upbit.response.UpbitMarketCodeResponse
import ado.sabgil.studyproject.enums.BaseCurrency

class UpbitTickerListRequest private constructor(
    val marketCodeQuery: String
) {

    companion object {
        fun of(marketCodeList: List<UpbitMarketCodeResponse>, base: BaseCurrency): UpbitTickerListRequest {
            val regex = Regex("""^$base""")

            return UpbitTickerListRequest(
                marketCodeList
                    .map { it.market }
                    .filter { regex.containsMatchIn(it) }
                    .joinToString(", "))
        }
    }
}