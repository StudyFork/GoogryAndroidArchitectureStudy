package study.architecture.model.datasource

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.architecture.model.UpbitApi
import study.architecture.vo.Market

/**
 * Data 관리하는 비즈니스로직이 담긴 클래스
 */
object RemoteDataSource {

    private const val url = "https://api.upbit.com/v1/"


    private val client =
        Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val api = client.create(UpbitApi::class.java)


    fun paresMarketList(): Single<List<Market>> =
        api.getMarkets()
            .subscribeOn(Schedulers.io())


    fun parseTickerList(listName: String) =
        api.getTickers(listName)
            .subscribeOn(Schedulers.io())

}