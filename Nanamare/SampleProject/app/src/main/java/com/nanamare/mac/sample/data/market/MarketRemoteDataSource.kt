package com.nanamare.mac.sample.data.market

import com.nanamare.mac.sample.api.DisposableManager
import com.nanamare.mac.sample.api.upbit.MarketModel
import com.nanamare.mac.sample.api.upbit.UpBitServiceManager
import io.reactivex.android.schedulers.AndroidSchedulers

class MarketRemoteDataSource(private val upBitServiceManager: UpBitServiceManager) : MarketSource {

    private var disposableManager: DisposableManager = DisposableManager()

    override fun getMarketList(
        success: (List<MarketModel>) -> Unit,
        failed: () -> Unit
    ) {
        disposableManager.add(
            upBitServiceManager.getAllMarketList()
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