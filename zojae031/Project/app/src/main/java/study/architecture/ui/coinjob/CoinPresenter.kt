package study.architecture.ui.coinjob

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import study.architecture.data.entity.ProcessingTicker
import study.architecture.data.repository.RepositoryImpl
import study.architecture.util.TextUtil
import java.util.concurrent.TimeUnit

/**
 * 1. 업비트 데이터를 가져와 View에게 알려준다.
 * 2. Observable 생명주기를 관리한다.
 */
class CoinPresenter(
    private val view: CoinContract.View,
    private val index: CoinFragment.FragIndex,
    private val repository: RepositoryImpl
) :
    CoinContract.Presenter {
    private lateinit var marketName: String

    private val dispose: Disposable
    private val compositeDisposable = CompositeDisposable()

    private lateinit var adapterView: CoinAdapterContract.View
    private lateinit var adapterModel: CoinAdapterContract.Model

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
                    view.showError(it.message)
                }).also { dispose = it }
    }

    override fun setAdapterView(adapterView: CoinAdapterContract.View) {
        this.adapterView = adapterView
    }

    override fun setAdapterModel(adapterModel: CoinAdapterContract.Model) {
        this.adapterModel = adapterModel
    }

    override fun onResume() {
        if (dispose.isDisposed) {
            tickerRequest()
        }
    }


    override fun onPause() {
        dispose.dispose()
        compositeDisposable.clear()
    }

    @SuppressLint("CheckResult")
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
                    adapterView.clearList()
                    adapterView.updateList(lists)
                    adapterModel.notifyDataChange()
                },
                { e ->
                    view.showError(e.message)
                }
            ).also { compositeDisposable.add(it) }

    }


}