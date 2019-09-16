package study.architecture.ui.coinjob

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import study.architecture.data.entity.ProcessingTicker
import study.architecture.data.repository.RepositoryImpl
import study.architecture.util.TextUtil
import java.util.concurrent.TimeUnit

class CoinViewModel(
    private val index: CoinFragment.FragIndex,
    private val repository: RepositoryImpl,
    private val adapter: CoinDataAdapter
) {
    private lateinit var marketName: String
    private val compositeDisposable = CompositeDisposable()
    private val dispose: Disposable

    init {
        repository.getMarkets()
            .map { list ->
                list.filter { filterData
                    ->
                    filterData.market.startsWith(index.name)
                }
                    .joinToString(",") { separateData ->
                        separateData.market
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    marketName = it
                    tickerRequest()
                },
                {
                    Log.e("CoinViewModelError", it.message)
                }).also { dispose = it }
    }

    private fun tickerRequest() {
        repository.getTickers(marketName)
            .repeatWhen { it.delay(8, TimeUnit.SECONDS) }
            .doOnSubscribe { view.showProgress() }
            .doOnRequest { view.hideProgress() }
            .map { list ->
                list.map { data ->
                    ProcessingTicker(
                        TextUtil.getMarketName(data.market),
                        TextUtil.getTradePrice(data.tradePrice),
                        TextUtil.getChangeRate(data.signedChangeRate),
                        TextUtil.getAccTradePrice24h(data.accTradePrice24h),
                        TextUtil.getColorState(data.signedChangeRate)
                    )
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { lists ->
                    if (!repository.checkNetwork()) view.showError("데이터가 최신이 아닙니다.\n 인터넷에 연결해주세요")

                    adapter.clearList()
                    adapter.updateList(lists)
                    adapter.notifyDataChange()
                },
                { e ->
                    view.showError(e.message)
                }
            ).also { compositeDisposable.add(it) }

    }
}
