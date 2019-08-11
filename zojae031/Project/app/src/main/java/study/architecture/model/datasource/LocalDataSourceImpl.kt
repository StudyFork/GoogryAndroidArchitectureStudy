package study.architecture.model.datasource

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import study.architecture.model.CoinDatabase
import study.architecture.model.entity.Market
import study.architecture.model.entity.Ticker

class LocalDataSourceImpl private constructor(context: Context) : UpbitLocalDataSource {

    private val db = CoinDatabase.getInstance(context)
    private val marketDao = db!!.marketDao()
    private val tickerDao = db!!.tickerDao()
    private val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


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


    override fun checkNetwork(): Boolean {
        if (manager.activeNetworkInfo == null) {
            return false
        }
        return manager.activeNetworkInfo.isConnected
    }

    companion object {
        private var INSTANCE: LocalDataSourceImpl? = null
        fun getInstance(context: Context): LocalDataSourceImpl {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSourceImpl(context)
            }
            return INSTANCE!!
        }
    }


}