package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandlerImpl
import ado.sabgil.studyproject.enums.BaseCurrency
import java.util.*

class CoinListPresenter(
    private val baseCurrency: BaseCurrency,
    private val coinListView: CoinListContract.View
) : CoinListContract.Presenter {

    companion object {
        private const val SECOND: Long = 1000
        private const val TICKER_REFRESH_PERIOD: Long = 30 * SECOND
    }

    private val dataSource = UpbitApiHandlerImpl

    private val refreshTimer = Timer()

    private var refreshTask: TickerRefreshTask? = null

    init {
        coinListView.presenter = this
    }

    override fun start() {
        if (refreshTask == null) {
            refreshTask = TickerRefreshTask()
            refreshTimer.schedule(refreshTask, 0, TICKER_REFRESH_PERIOD)
        }
    }

    override fun stop() {
        refreshTask?.cancel()
        refreshTask = null
    }

    override fun refreshTickers() {
        dataSource.getAllTickers(
            baseCurrency,
            { result ->
                coinListView.showProgressBar(false)
                coinListView.updateList(result.map { Ticker.from(it) }.toMutableList())
            },
            { exception ->
                coinListView.showToast(exception.message ?: "error")

            })
    }

    private inner class TickerRefreshTask : TimerTask() {
        override fun run() {
            refreshTickers()
        }
    }
}