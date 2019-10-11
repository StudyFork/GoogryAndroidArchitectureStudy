package com.example.seonoh.seonohapp

import android.util.Log
import com.example.seonoh.seonohapp.contract.CoinFragmentContract
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.UseCoinModel
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import com.example.seonoh.seonohapp.util.CalculateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CoinPresenter(
     val view: CoinFragmentContract.View
) : BasePresenter() {

    private val coinRepository = CoinRepositoryImpl()

    fun loadData(marketName: String) {
        compositeDisposable.add(
            coinRepository.sendCurrentPriceInfo(marketName)
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

    private fun refineData(result: List<CurrentPriceInfoModel>): List<UseCoinModel> {
        // 데이터 가공후 모델에 넣음.
        // signedChangeRate textcolor 처리때문에 viewholder에서 진행
        val marketType = if (result.isNotEmpty()) {
            result[0].market.substringBefore("-")
        } else ""

        return result.map {
            var rate = CalculateUtils.setTradeDiff(it.signedChangeRate)["rate"].toString()
            var color = CalculateUtils.setTradeDiff(it.signedChangeRate)["color"].toString().toInt()
            var totalTradePrice = CalculateUtils.setTradeAmount(marketType, it.accTradePrice24h)["price"].toString()
            var format = CalculateUtils.setTradeAmount(marketType, it.accTradePrice24h)["format"].toString().toInt()

            UseCoinModel(
                CalculateUtils.setMarketName(it.market),
                CalculateUtils.filterTrade(it.tradePrice),
                rate,
                color,
                totalTradePrice,
                format
            )
        }
    }
}