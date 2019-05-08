package ado.sabgil.studyproject.data.remote.upbit

import ado.sabgil.studyproject.data.remote.upbit.request.UpbitTickerListRequest
import ado.sabgil.studyproject.data.remote.upbit.response.UpbitMarketCodeResponse
import ado.sabgil.studyproject.data.remote.upbit.response.UpbitTickerResponse
import ado.sabgil.studyproject.enums.BaseCurrency
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UpbitApiHandlerImpl : UpbitApiHandler {

    private val retrofit: UpbitApi

    private const val baseURL = "https://api.upbit.com/v1/"

    private var cachedMarketCode: List<UpbitMarketCodeResponse>? = null

    init {
        retrofit = run {
            Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UpbitApi::class.java)
        }
    }

    override fun getMarketList(
        onResponse: (List<String>) -> Unit,
        onFail: (Exception) -> Unit
    ) {
        getMarketCodes(
            { response ->
                onResponse.invoke(response.map { it.market.substringBefore('-') }.distinct())
            },
            onFail
        )
    }

    override fun getAllTickers(
        base: BaseCurrency,
        onResponse: (List<UpbitTickerResponse>) -> Unit,
        onFail: (Exception) -> Unit
    ) {
        if (cachedMarketCode == null) {
            getMarketCodes(
                { response ->
                    cachedMarketCode = response
                    val tickerRequest = UpbitTickerListRequest.of(cachedMarketCode!!, base)
                    getTickers(
                        tickerRequest,
                        onResponse,
                        onFail
                    )
                },
                onFail
            )
        } else {
            val tickerRequest = UpbitTickerListRequest.of(cachedMarketCode!!, base)
            getTickers(
                tickerRequest,
                onResponse,
                onFail
            )
        }


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
                    onFail.invoke(RuntimeException("서버에서 데이터를 가져오는데 실패하였습니다."))
                }

                override fun onResponse(
                    call: Call<List<UpbitMarketCodeResponse>>,
                    response: Response<List<UpbitMarketCodeResponse>>
                ) {
                    val marketCodes = response.body()

                    if (marketCodes != null) {
                        onResponse.invoke(marketCodes)
                    } else {
                        onFail.invoke(RuntimeException("서버에서 응답이 없습니다."))
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
                    onFail.invoke(RuntimeException("서버에서 데이터를 가져오는데 실패하였습니다."))
                }

                override fun onResponse(
                    call: Call<List<UpbitTickerResponse>>,
                    response: Response<List<UpbitTickerResponse>>
                ) {
                    val tickers = response.body()

                    if (tickers != null) {
                        onResponse.invoke(tickers)
                    } else {
                        onFail.invoke(RuntimeException("서버에서 응답이 없습니다."))
                    }
                }
            })
    }
}