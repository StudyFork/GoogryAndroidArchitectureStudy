package com.example.seonoh.seonohapp

import android.util.Log
import com.example.seonoh.seonohapp.contract.CoinMainContract
import com.example.seonoh.seonohapp.model.Market
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CoinMainPresenter(
    private val view: CoinMainContract.View
) : CoinMainContract.Presenter {

    private val coinRepository = CoinRepositoryImpl()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun loadMarketData() {
        compositeDisposable.addAll(coinRepository.sendMarket().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.setPager(it)
            }, { e ->
                if (e is HttpException) {
                    Log.e("market", "Market Network failed !! ${e.message()}")
                }
            }))
    }

    override fun classifyMarketData(marketData: List<Market>): ArrayList<String> {
        val conMarketNameList = marketData.map {
            it.market.substringBefore("-")
        }.distinct()

        val marketDataList = ArrayList<String>()

        conMarketNameList.forEach { title ->

            marketDataList += (marketData.filter {
                it.market.substringBefore("-") == title
            }.joinToString(",") {
                it.market
            })
        }

        return marketDataList
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }
}