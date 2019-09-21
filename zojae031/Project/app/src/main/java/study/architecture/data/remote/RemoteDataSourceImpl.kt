package study.architecture.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.architecture.data.entity.Market
import study.architecture.data.entity.Ticker

/**
 * Data 관리하는 비즈니스로직이 담긴 클래스
 */
class RemoteDataSourceImpl private constructor(private val api: UpbitRemoteDataSource) :
    UpbitRemoteDataSource {






    override fun getMarkets(): Single<List<Market>> =
        api.getMarkets()
            .subscribeOn(Schedulers.io())

    override fun getTickers(markets: String): Single<MutableList<Ticker>> =
        api.getTickers(markets)
            .subscribeOn(Schedulers.io())

    companion object {
        const val url = "https://api.upbit.com/v1/"
        private var INSTANCE: RemoteDataSourceImpl? = null
        fun getInstance(api: UpbitRemoteDataSource): RemoteDataSourceImpl {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSourceImpl(api)
            }
            return INSTANCE!!
        }
    }

}