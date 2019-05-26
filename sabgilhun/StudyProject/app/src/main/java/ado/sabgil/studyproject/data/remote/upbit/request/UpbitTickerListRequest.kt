package ado.sabgil.studyproject.data.remote.upbit.request

import ado.sabgil.studyproject.data.remote.upbit.response.UpbitMarketCodeResponse

class UpbitTickerListRequest private constructor(
    val marketCodeQuery: String
) {

    companion object {
        fun of(marketCodeList: List<UpbitMarketCodeResponse>): UpbitTickerListRequest {
            return UpbitTickerListRequest(
                marketCodeList.joinToString(",") { it.market }
            )
        }
    }
}