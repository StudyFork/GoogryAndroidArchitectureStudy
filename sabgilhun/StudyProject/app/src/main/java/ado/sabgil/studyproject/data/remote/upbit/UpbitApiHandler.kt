package ado.sabgil.studyproject.data.remote.upbit

import ado.sabgil.studyproject.data.remote.upbit.request.UpbitTickerListRequest
import ado.sabgil.studyproject.data.remote.upbit.response.UpbitTickerResponse
import ado.sabgil.studyproject.enums.BaseCurrency

interface UpbitApiHandler {

    fun getAllTickers(
        base: BaseCurrency,
        onResponse: (List<UpbitTickerResponse>) -> Unit,
        onFail: (Exception) -> Unit
    )
}