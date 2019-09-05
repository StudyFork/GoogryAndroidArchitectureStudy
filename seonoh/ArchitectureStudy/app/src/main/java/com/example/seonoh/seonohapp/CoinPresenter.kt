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
    private val compositeDisposable = CompositeDisposable()

    override fun loadData(marketName: String) {
        compositeDisposable.add(
            coinRepository.sendCurrentPriceInfo(marketName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    translateData(it)

                }, { e ->
                    Log.e("currentPriceInfo", "Network failed!! ${e.message}")
                })
        )
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }

    override fun setData(data: ArrayList<UseCoinModel>) {
        view.initRecyclerView(data)
    }

    override fun translateData(result: List<CurrentPriceInfoModel>) {
        var marketType = ""

        // 데이터 가공후 모델에 넣음.
        // signedChangeRate textcolor 처리때문에 viewholder에서 진행
        if (result.isNotEmpty()) {
            marketType = result[0].market.substringBefore("-")
        }

        val data = result.map {
            UseCoinModel(
                CalculateUtils.setMarketName(it.market),
                CalculateUtils.filterTrade(it.tradePrice),
                CalculateUtils.setTradeDiff(it.signedChangeRate),
                CalculateUtils.setTradeAmount(marketType, it.accTradePrice24h)
            )

        } as ArrayList<UseCoinModel>
        setData(data)
    }
}