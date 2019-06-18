package com.nanamare.mac.sample.presenter

import com.nanamare.mac.sample.api.DisposableManager
import com.nanamare.mac.sample.api.upbit.UpBitServiceManager
import com.nanamare.mac.sample.contract.CoinContract
import io.reactivex.android.schedulers.AndroidSchedulers

class CoinPresenter(private val view: CoinContract.CoinView) :
    CoinContract.CoinPresenter {

    private var disposableManager : DisposableManager = DisposableManager()

    override fun getCoins(ticketList: MutableList<String>) {
        disposableManager.add(
            UpBitServiceManager.getTickerList(ticketList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showCoins(it.body())
                }, {
                    view.hideLoadingDialog()
                })
        )

    }

    override fun networkDispose() {
        disposableManager.dispose()
    }

}