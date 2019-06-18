package com.nanamare.mac.sample.presenter

import android.os.Bundle
import com.google.gson.Gson
import com.nanamare.mac.sample.api.DisposableManager
import com.nanamare.mac.sample.api.upbit.UpBitServiceManager
import com.nanamare.mac.sample.contract.MarketContract
import com.nanamare.mac.sample.ui.MainActivity
import com.nanamare.mac.sample.ui.MarketListFragment
import io.reactivex.android.schedulers.AndroidSchedulers

class MarketPresenter(private val view: MarketContract.MarketView) : MarketContract.Presenter {

    private var disposableManager: DisposableManager = DisposableManager()

    override fun getMarketList() {
        view.showLoadingDialog()
        disposableManager.add(UpBitServiceManager.getAllMarketList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideLoadingDialog()
                val marketMap: LinkedHashMap<String, List<String>> = LinkedHashMap()
                val marketList = listOf<String>().toMutableList()
                it.body()?.map { marketModel ->
                    marketModel.market!!.split("-")[0].let { market ->
                        marketList.add(marketModel.market)
                        marketMap.put(market, marketList)
                    }
                }
                val bundle = Bundle().apply {
                    putString(MainActivity.KET_MARKET_LIST, Gson().toJson(marketMap))
                }
                view.goToFragment(MarketListFragment::class.java, bundle)
            }, {
                view.hideLoadingDialog()
            })
        )
    }

    override fun networkDispose() {
        disposableManager.dispose()
    }
}