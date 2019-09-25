package study.architecture.coinjob

import android.util.Log
import androidx.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import study.architecture.data.entity.ProcessingTicker
import study.architecture.data.repository.Repository
import study.architecture.util.TextUtil
import java.util.concurrent.TimeUnit

class CoinViewModel(
    private val index: CoinFragment.FragIndex,
    private val repository: Repository
) {
    private lateinit var marketName: String

    private val compositeDisposable = CompositeDisposable()
    private val dispose: Disposable

    val lists = ObservableField<List<ProcessingTicker>>()
    val loadingState = ObservableField<Boolean>()

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
            .doOnSubscribe { loadingState.set(true) }
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
        dispose.dispose()
        repository.getTickers(marketName)
            .repeatWhen { it.delay(8, TimeUnit.SECONDS) }
            .doOnRequest { loadingState.set(false) }
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
                { pLists ->
                    if (!repository.checkNetwork()) Log.e("데이터 연결x", "데이터 최신화 필요")
                    lists.set(pLists)
                },
                { e ->
                    Log.e("CoinViewModel", e.message)
                }
            ).also { compositeDisposable.add(it) }
    }

    fun onResume() {
        if (dispose.isDisposed) {
            tickerRequest()
        }
    }

    fun onPause() {
        dispose.dispose()
        compositeDisposable.clear()
    }
}
