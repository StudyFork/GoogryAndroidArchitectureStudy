package study.architecture.data.local

import android.content.Context
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import study.architecture.data.entity.Market
import study.architecture.data.entity.Ticker

class LocalDataSourceImpl private constructor(context: Context) : UpbitLocalDataSource {

    private val db = CoinDatabase.getInstance(context)
    private val marketDao = db!!.marketDao()
    private val tickerDao = db!!.tickerDao()


    override fun getMarkets(): Single<List<Market>> =
        marketDao.selectAll()
            .subscribeOn(Schedulers.io())

    override fun getTickers(markets: String): Single<MutableList<Ticker>> =
        tickerDao.selectTicker("%$markets%")
            .subscribeOn(Schedulers.io())


    override fun insertMarket(market: Market) {
        marketDao.insert(market)
    }

    override fun insertTicker(ticker: Ticker) {
        tickerDao.insert(ticker)
    }


    companion object {
        private var INSTANCE: LocalDataSourceImpl? = null
        fun getInstance(context: Context): LocalDataSourceImpl {
            if (INSTANCE == null) {
                INSTANCE =
                    LocalDataSourceImpl(context)
            }
            return INSTANCE!!
        }
    }


}