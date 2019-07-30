package study.architecture.ui.coinjob

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import study.architecture.model.repository.Repository
import study.architecture.ui.coinjob.adapter.CoinAdapterContract

/**
 * 1. 업비트 데이터를 가져와 View에게 알려준다.
 * 2. Observable 생명주기를 관리한다.
 */
class CoinPresenter(private val view: CoinContract.View, index: CoinFragment.FragIndex) :
    CoinContract.Presenter {


    private val dispose: Disposable
    private lateinit var marketName: String
    private val compositeDisposable = CompositeDisposable()

    private lateinit var adapterView: CoinAdapterContract.View
    private lateinit var adapterModel: CoinAdapterContract.Model

    init {
        dispose =
            Repository.getMarketList(index)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { marketList ->
                        marketName = marketList
                        tickerRequest()
                    },
                    { e ->
                        Log.e("onErrorMarketList", e.message)
                    })
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
        compositeDisposable.clear()
    }

    @SuppressLint("CheckResult")
    private fun tickerRequest() {
        Repository.getTickerList(marketName)
            .doOnSubscribe { view.showProgress() }
            .doOnRequest { view.hideProgress() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { lists ->
                    adapterView.clearList()
                    adapterView.updateList(lists)
                    adapterModel.notifyDataChange()

                },
                { e ->
                    Log.e("onError", e.message)
                }
            ).also { compositeDisposable.add(it) }

    }


}