package study.architecture.mainjob

import study.architecture.model.DataParser
import study.architecture.vo.Ticker

class MainPresenter(private val view: MainContract.View, index: Int) :
    MainContract.Presenter, DataParser.ResultCallback {

    private val parser = DataParser(index, this@MainPresenter)
    override fun onCreate() {
        parser.paresMarketList()
    }

    override fun successMarketList() {
        parser.parseTickerList()

    }

    override fun successTickerList(list: List<Ticker>) {
        view.notifyAdapter(list)
    }
}