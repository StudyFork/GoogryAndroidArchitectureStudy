package study.architecture.model


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import study.architecture.vo.Market
import study.architecture.vo.Ticker


interface UpbitApi {

    @GET("market/all")
    fun getMarkets(): Single<List<Market>>

    @GET("ticker")
    fun getTickers(@Query("markets") markets: String): Single<List<Ticker>>


}