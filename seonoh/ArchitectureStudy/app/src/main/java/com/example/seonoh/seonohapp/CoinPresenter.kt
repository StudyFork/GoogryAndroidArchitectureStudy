package com.example.seonoh.seonohapp

import android.util.Log
import com.example.seonoh.seonohapp.contract.CoinFragmentContract
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.UseCoinModel
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import com.example.seonoh.seonohapp.util.CalculateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinPresenter(
    private val view: CoinFragmentContract.View
) : CoinFragmentContract.Presenter {

    private val coinRepository = CoinRepositoryImpl()
    override val compositeDisposable by lazy { CompositeDisposable() }

    override fun loadData(marketName: String?) {
        compositeDisposable.add(
            coinRepository.sendCurrentPriceInfo(marketName!!)
                .map {
                    refineData(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.setData(it) }, {
                    Log.e("currentPriceInfo", "Network failed!! ${it.message}")
                })
        )
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }

    private fun refineData(result: List<CurrentPriceInfoModel>): ArrayList<UseCoinModel> {
        // 데이터 가공후 모델에 넣음.
        // signedChangeRate textcolor 처리때문에 viewholder에서 진행
        val marketType = if (result.isNotEmpty()) {
            result[0].market.substringBefore("-")
        } else ""

        val data = result.map {
            UseCoinModel(
                CalculateUtils.setMarketName(it.market),
                CalculateUtils.filterTrade(it.tradePrice),
                CalculateUtils.setTradeDiff(it.signedChangeRate),
                CalculateUtils.setTradeAmount(marketType, it.accTradePrice24h)
            )

        } as ArrayList<UseCoinModel>
        return data
    }
}