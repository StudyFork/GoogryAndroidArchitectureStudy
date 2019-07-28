package study.architecture.myarchitecture.data.source.local

import io.reactivex.Single
import study.architecture.myarchitecture.data.model.UpbitMarket
import study.architecture.myarchitecture.data.model.UpbitTicker

class UpbitLocalLocalDataSourceImpl(
    private val dao: UpbitDao
) : UpbitLocalDataSource {

    override fun getMarkets(): Single<List<UpbitMarket>> = dao.getMarkets()

    override fun getTickers(key: String): Single<List<UpbitTicker>> = dao.getTickers(key)

    override fun insertMarket(market: UpbitMarket) = dao.insertMarket(market)

    override fun insertTicker(ticker: UpbitTicker) = dao.insertTicker(ticker)

    override fun updateMarket(market: UpbitMarket) = dao.updateMarket(market)

    override fun updateTicker(ticker: UpbitTicker) = dao.updateTicker(ticker)

    override fun clearTickers(key: String) = dao.clearTickers(key)

    override fun clearMarkets() = dao.clearMarkets()
}