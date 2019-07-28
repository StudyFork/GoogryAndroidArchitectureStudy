package study.architecture.myarchitecture.ui.tickerlist

import study.architecture.myarchitecture.data.repository.UpbitRepository

class TickerListPresenter(
    private val upbitRepository: UpbitRepository,
    private val view: TickerListContract.View
) : TickerListContract.Presenter {
}