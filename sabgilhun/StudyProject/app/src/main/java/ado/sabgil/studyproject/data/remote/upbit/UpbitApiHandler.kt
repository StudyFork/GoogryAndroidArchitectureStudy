package ado.sabgil.studyproject.data.remote.upbit

import ado.sabgil.studyproject.data.remote.upbit.response.UpbitTickerResponse
import ado.sabgil.studyproject.enums.BaseCurrency

interface UpbitApiHandler {

    fun getMarketList(
        onResponse: (List<String>) -> Unit,
        onFail: (Exception) -> Unit
    )

    fun getAllTickers(
        base: BaseCurrency,
        onResponse: (List<UpbitTickerResponse>) -> Unit,
        onFail: (Exception) -> Unit
    )
}