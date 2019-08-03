package study.architecture.model.repository

import io.reactivex.Single
import study.architecture.model.datasource.UpbitDataSource
import study.architecture.model.vo.Market
import study.architecture.model.vo.Ticker

class Repository private constructor(private val remoteDataSource: UpbitDataSource) : UpbitDataSource {

    override fun getMarkets(): Single<List<Market>> =
        remoteDataSource.getMarkets()


    override fun getTickers(markets: String): Single<MutableList<Ticker>> =
        remoteDataSource.getTickers(markets)

    companion object {
        private var INSTANCE: Repository? = null

        fun getInstance(remoteDataSource: UpbitDataSource): Repository {
            if (INSTANCE == null) {
                INSTANCE = Repository(remoteDataSource)
            }
            return INSTANCE!!
        }
    }

}