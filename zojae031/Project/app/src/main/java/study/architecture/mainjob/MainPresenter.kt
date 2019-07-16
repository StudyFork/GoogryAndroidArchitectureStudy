package study.architecture.mainjob

import io.reactivex.disposables.CompositeDisposable
import study.architecture.model.DataParser
import study.architecture.vo.Ticker

class MainPresenter(private val view: MainContract.View, index: Int) :
    MainContract.Presenter, DataParser.ResultCallback {
    private val compositeDisposable = CompositeDisposable()
    private val parser = DataParser(index, this@MainPresenter)
    override fun onCreate() {
        parser.paresMarketList()
    }

    override fun onResume() {
        compositeDisposable.add(parser.parseTickerList())
    }

    override fun successMarketList() {
        onResume()
    }

    override fun successTickerList(list: List<Ticker>) {
        view.notifyAdapter(list)
    }

    override fun onPause() {
        compositeDisposable.clear()
    }
}