package me.hoyuo.myapplication.ui.coinlist

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.hoyuo.myapplication.model.upbit.Market
import me.hoyuo.myapplication.model.upbit.Ticker
import me.hoyuo.myapplication.util.http.HttpClient
import me.hoyuo.myapplication.util.rx.DisposableManager
import timber.log.Timber

class CoinListPresenter(val view: CoinListContract.View) : CoinListContract.Presenter {
    private var httpClient: HttpClient = HttpClient.newInstance()
    private var disposableManager: DisposableManager = DisposableManager()
    private var krwList = ArrayList<String>()

    override fun start() {
        disposableManager.refreshIfNecessary()
    }

    private fun getData(): Disposable {
        val flowable = getMarketListToTicker()

        return flowable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ::getTickersOnNext,
                ::onError
            )
    }

    private fun getMarketListToTicker(): Flowable<List<Ticker>> {
        return Flowable.just(krwList)
            .flatMap {
                if (it.isEmpty()) {
                    return@flatMap getMarket()
                } else {
                    return@flatMap getTickers()
                }
            }
    }

    private fun getMarket(): Flowable<List<Ticker>> {
        return httpClient.getMarketList()
            .flatMap(::getMarketListOnSuccess)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getTickers(): Flowable<List<Ticker>> {
        return httpClient.getTickers(krwList.joinToString(","))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getMarketListOnSuccess(list: List<Market>): Flowable<List<Ticker>> {
        krwList.clear()
        list.filter { market -> market.market.contains("KRW-") }
            .map { krwList.add(it.market) }

        Timber.tag(TAG).d(krwList.joinToString(","))

        return httpClient.getTickers(krwList.joinToString(","))
    }

    private fun getTickersOnNext(list: List<Ticker>) {
        Timber.tag(TAG).d("getTickersOnNext - ${list.size}")
        view.updateData(list)
    }

    private fun onError(e: Throwable) {
        Timber.tag(TAG).e(e)
    }

    override fun onItemClick(ticker: Ticker) {
        Timber.tag(TAG).d("onItemClick")
        view.navigationCoinDetailActivity(ticker)
    }

    override fun subscribe() {
        Timber.tag(TAG).d("subscribe")
        disposableManager.refreshIfNecessary()
        disposableManager.add(getData())
    }

    override fun unsubscribe() {
        Timber.tag(TAG).d("unsubscribe")
        disposableManager.dispose()
    }

    companion object {
        val TAG: String = CoinListPresenter::class.java.simpleName
    }

}
