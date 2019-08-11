package kr.schoolsharing.coinhelper.tasks

import kr.schoolsharing.coinhelper.data.Repository
import kr.schoolsharing.coinhelper.data.UpbitDataSource
import kr.schoolsharing.coinhelper.model.UpbitItem
import kr.schoolsharing.coinhelper.model.UpbitMarket
import kr.schoolsharing.coinhelper.model.UpbitTicker
import kr.schoolsharing.coinhelper.util.TextEditor

class UpbitPresenter(val repository: Repository, val upbitView: UpbitContract.View) : UpbitContract.Presenter {

    init {
        upbitView.presenter = this
    }

    override fun completeTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start(marketName: String) {
        loadUpbitMarket(marketName)
    }

    private fun loadUpbitMarket(marketName: String) {
        repository.getMarket(object : UpbitDataSource.GetMarketCallback {
            override fun onMarketLoaded(markets: List<UpbitMarket>) {
                val marketList = markets
                    .filter { TextEditor.splitString(it.market, 0) == marketName }
                    .joinToString(",") { it.market }
                loadUpbitTicker(marketList)
            }

            override fun onDataNotAvailable(t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun loadUpbitTicker(marketList: String) {
        Repository.getTicker(marketList, object : UpbitDataSource.GetTickerCallback {

            override fun onTickerLoaded(tickers: List<UpbitTicker>) {

                processUpbit(tickers.map {
                    UpbitItem(
                        TextEditor.splitString(it.market, 1),
                        TextEditor.makeTradePrice(it.tradePrice),
                        it.change,
                        TextEditor.makeSignedChangeRate(it.signedChangePrice),
                        TextEditor.makeAccTradePrice24h(it.accTradePrice24h)
                    )
                })
            }


            override fun onDataNotAvailable(t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun processUpbit(tickerList: List<UpbitItem>) {
        upbitView.showAddTask(tickerList)
    }


}