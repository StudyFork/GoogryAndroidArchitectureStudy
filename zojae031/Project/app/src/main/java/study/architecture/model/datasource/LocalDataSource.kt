package study.architecture.model.datasource

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import study.architecture.model.CoinDatabase
import study.architecture.model.entity.Market
import study.architecture.model.entity.Ticker

class LocalDataSource private constructor(context: Context) {
    private val db = CoinDatabase.getInstance(context)
    private val marketDao = db!!.marketDao()
    private val tickerDao = db!!.tickerDao()
    private val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun getMarket(): Single<List<Market>> =
        marketDao.selectAll()
            .subscribeOn(Schedulers.io())


    fun insertMarket(market: Market) {
        marketDao.insert(market)
    }

    fun insertTicker(ticker: Ticker) {
        tickerDao.insert(ticker)
    }

    fun getTicker(market: String): Single<MutableList<Ticker>> =
        tickerDao.selectTicker("%$market%")
            .subscribeOn(Schedulers.io())

    fun checkNetwork(): Boolean {
        if (manager.activeNetworkInfo == null) {
            return false
        }
        return manager.activeNetworkInfo.isConnected
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(context: Context): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(context)
            }
            return INSTANCE!!
        }
    }


}