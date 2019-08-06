package com.nanamare.mac.sample.data.coin

import com.nanamare.mac.sample.api.DisposableManager
import com.nanamare.mac.sample.api.upbit.CoinModel
import com.nanamare.mac.sample.api.upbit.UpBitServiceManager
import io.reactivex.android.schedulers.AndroidSchedulers

class CoinRemoteDataSource(private val upBitServiceManager: UpBitServiceManager) : CoinSource {

    private var disposableManager: DisposableManager = DisposableManager()

    override fun getCoins(
        ticketList: MutableList<String>,
        success: (List<CoinModel>) -> Unit,
        failed: () -> Unit
    ) {
        disposableManager.add(
            upBitServiceManager.getTickerList(ticketList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.body()?.let(success)
                }, {
                    failed.invoke()
                })
        )
    }

    override fun close() {
        disposableManager.dispose()
    }

}