package study.architecture.mainjob

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import study.architecture.model.repository.Repository
import study.architecture.model.vo.ProcessingTicker
import study.architecture.ui.MainFragment
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

/**
 * 1. 업비트 데이터를 가져와 View에게 알려준다.
 * 2. Observable 생명주기를 관리한다.
 */
class MainPresenter(private val view: MainContract.View, index: MainFragment.FragIndex) :
    MainContract.Presenter {
    private val dispose: Disposable
    private var list = ""
    private val compositeDisposable = CompositeDisposable()

    init {
        dispose =
            Repository.getMarketList(index)
                .subscribe(
                    { marketList ->
                        list = marketList
                        tickerRequest()
                    },
                    { e ->
                        Log.e("onErrorMarketList", e.message)
                    })
    }


    override fun onResume() {
        if (dispose.isDisposed) {
            tickerRequest()
        }
    }


    override fun onPause() {
        compositeDisposable.clear()
    }

    @SuppressLint("CheckResult")
    private fun tickerRequest() {
        Repository.getTickerList(list)
            .repeatWhen { t -> t.delay(8, TimeUnit.SECONDS) }
            .map { lists ->
                val processList = mutableListOf<ProcessingTicker>()

                for (data in lists) {
                    val tradePrice = if (data.tradePrice.toInt() > 0) {
                        String.format("%,d", data.tradePrice.toInt())
                    } else {
                        String.format("%,f", data.tradePrice)
                    }

                    val acc = with(DecimalFormat("#,###")) {
                        if (data.accTradePrice24h >= 1000000) {
                            return@with "${this.format(data.accTradePrice24h / 1000000)}M"
                        } else if (data.accTradePrice24h < 1000000 && data.accTradePrice24h >= 1000) {
                            return@with "${this.format(data.accTradePrice24h / 1000)}K"
                        } else {
                            return@with this.format(data.accTradePrice24h)
                        }
                    }

                    with(processList) {
                        add(
                            ProcessingTicker(
                                data.market.substringAfter('-'),
                                tradePrice,
                                String.format("%.2f%%", data.signedChangeRate * 100),
                                acc
                            )
                        )
                    }

                }
                return@map processList
            }
            .doOnSubscribe { view.showProgress() }
            .doOnRequest { view.hideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { lists ->
                    view.notifyAdapter(lists)
                },
                { e ->
                    Log.e("onError", e.message)
                }
            ).also { compositeDisposable.add(it) }

    }


}