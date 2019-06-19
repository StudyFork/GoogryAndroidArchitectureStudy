package com.nanamare.mac.sample.data

import com.nanamare.mac.sample.api.DisposableManager
import com.nanamare.mac.sample.api.upbit.MarketModel
import com.nanamare.mac.sample.api.upbit.UpBitServiceManager
import io.reactivex.android.schedulers.AndroidSchedulers

object MarketRemoteDataSource : MarketSource {

    private var disposableManager: DisposableManager = DisposableManager()

    override fun getMarketList(success: (List<MarketModel>) -> Unit, failed: () -> Unit) {
        disposableManager.add(
            UpBitServiceManager.getAllMarketList()
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