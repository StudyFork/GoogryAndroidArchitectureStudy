package study.architecture.data.remote


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import study.architecture.data.entity.Market
import study.architecture.data.entity.Ticker


interface UpbitApi {
    @GET("market/all")
    fun getMarkets(): Single<List<Market>>

    @GET("ticker")
    fun getTickers(@Query("markets") markets: String): Single<MutableList<Ticker>>
}