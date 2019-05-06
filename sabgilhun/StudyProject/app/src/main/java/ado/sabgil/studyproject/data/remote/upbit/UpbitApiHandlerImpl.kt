package ado.sabgil.studyproject.data.remote.upbit

import ado.sabgil.studyproject.data.remote.upbit.request.UpbitTickerListRequest
import ado.sabgil.studyproject.data.remote.upbit.response.UpbitMarketCodeResponse
import ado.sabgil.studyproject.data.remote.upbit.response.UpbitTickerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UpbitApiHandlerImpl : UpbitApiHandler {

    private val retrofit: UpbitApi
    private const val baseURL = "https://api.upbit.com/v1/"

    init {
        retrofit = run {
            Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UpbitApi::class.java)
        }
    }

    override fun getAllTickers(
        base: UpbitTickerListRequest.Base,
        onResponse: (List<UpbitTickerResponse>) -> Unit,
        onFail: (Exception) -> Unit
    ) {
        getMarketCodes(
            { response ->
                val tickerRequest = UpbitTickerListRequest.of(response, base)
                getTickers(
                    tickerRequest,
                    onResponse,
                    onFail
                )
            },
            onFail
        )
    }

    private fun getMarketCodes(
        onResponse: (List<UpbitMarketCodeResponse>) -> Unit,
        onFail: (Exception) -> Unit
    ) {
        retrofit.getMarketCode()
            .enqueue(object : Callback<List<UpbitMarketCodeResponse>> {
                override fun onFailure(
                    call: Call<List<UpbitMarketCodeResponse>>,
                    t: Throwable
                ) {
                    onFail.invoke(RuntimeException(t.message))
                }

                override fun onResponse(
                    call: Call<List<UpbitMarketCodeResponse>>,
                    response: Response<List<UpbitMarketCodeResponse>>
                ) {
                    val marketCodes = response.body()

                    if (marketCodes != null) {
                        onResponse.invoke(marketCodes)
                    } else {
                        onFail.invoke(RuntimeException("empty response"))
                    }
                }
            })
    }

    private fun getTickers(
        tickerListRequest: UpbitTickerListRequest,
        onResponse: (List<UpbitTickerResponse>) -> Unit,
        onFail: (Exception) -> Unit
    ) {
        retrofit.getTickerList(tickerListRequest.marketCodeQuery)
            .enqueue(object : Callback<List<UpbitTickerResponse>> {
                override fun onFailure(
                    call: Call<List<UpbitTickerResponse>>,
                    t: Throwable
                ) {
                    onFail.invoke(RuntimeException(t.message))
                }

                override fun onResponse(
                    call: Call<List<UpbitTickerResponse>>,
                    response: Response<List<UpbitTickerResponse>>
                ) {
                    val tickers = response.body()

                    if (tickers != null) {
                        onResponse.invoke(tickers)
                    } else {
                        onFail.invoke(RuntimeException("empty response"))
                    }
                }
            })
    }
}