package com.nanamare.mac.sample.data

import com.nanamare.mac.sample.api.DisposableManager
import com.nanamare.mac.sample.api.upbit.TickerModel
import com.nanamare.mac.sample.api.upbit.UpBitServiceManager
import io.reactivex.android.schedulers.AndroidSchedulers

object CoinRemoteDataSource : CoinSource {

    private var disposableManager: DisposableManager = DisposableManager()

    override fun getCoins(ticketList: MutableList<String>, success: (List<TickerModel>) -> Unit, failed: () -> Unit) {
        disposableManager.add(
            UpBitServiceManager.getTickerList(ticketList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    success.invoke(it.body()!!)
                }, {
                    failed.invoke()
                })
        )
    }

    override fun close() {
        disposableManager.dispose()
    }

}