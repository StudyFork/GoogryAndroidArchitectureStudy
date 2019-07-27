package study.architecture.mainjob

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import study.architecture.model.repository.Repository
import study.architecture.model.vo.ProcessingTicker
import study.architecture.ui.MainFragment
import study.architecture.util.TextUtil
import java.util.concurrent.TimeUnit

/**
 * 1. 업비트 데이터를 가져와 View에게 알려준다.
 * 2. Observable 생명주기를 관리한다.
 */
class MainPresenter(private val view: MainContract.View, index: MainFragment.FragIndex) :
    MainContract.Presenter {
    private val dispose: Disposable
    private lateinit var list: String
    private val compositeDisposable = CompositeDisposable()

    init {
        dispose =
            Repository.getMarketList(index)
                .observeOn(AndroidSchedulers.mainThread())
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

                with(mutableListOf<ProcessingTicker>()) {
                    for (data in lists) {
                        add(
                            ProcessingTicker(
                                TextUtil.getMarketName(data.market),
                                TextUtil.getTradePrice(data.tradePrice),
                                TextUtil.getChangeRate(data.signedChangeRate),
                                TextUtil.getAccTradePrice24h(data.accTradePrice24h),
                                TextUtil.getColorState(data.signedChangeRate)
                            )
                        )
                    }
                    return@map this
                }

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