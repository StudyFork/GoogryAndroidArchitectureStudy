package study.architecture.model.datasource


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import study.architecture.model.entity.Market
import study.architecture.model.entity.Ticker


interface UpbitRemoteDataSource {
    @GET("market/all")
    fun getMarkets(): Single<List<Market>>

    @GET("ticker")
    fun getTickers(@Query("markets") markets: String): Single<MutableList<Ticker>>
}