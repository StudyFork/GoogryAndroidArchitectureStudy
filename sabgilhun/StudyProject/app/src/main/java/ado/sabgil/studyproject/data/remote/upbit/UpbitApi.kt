package ado.sabgil.studyproject.data.remote.upbit

import ado.sabgil.studyproject.data.remote.upbit.response.UpbitMarketCodeResponse
import ado.sabgil.studyproject.data.remote.upbit.response.UpbitTickerResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET(value = "market/all")
    fun getMarketCode(): Single<List<UpbitMarketCodeResponse>>

    @GET(value = "ticker")
    fun getTickerList(@Query("markets") marketCodes: String): Observable<List<UpbitTickerResponse>>
}