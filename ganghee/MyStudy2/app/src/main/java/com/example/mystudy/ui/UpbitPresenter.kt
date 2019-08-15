package com.example.mystudy.ui

import android.annotation.SuppressLint
import com.example.mystudy.data.FormatTickers
import com.example.mystudy.data.UpbitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UpbitPresenter(private val repository: UpbitRepository, private val tickerView: UpbitContract.View) :
    UpbitContract.Presenter {

    override fun start() {
        //TODO: start UI
    }

    @SuppressLint("CheckResult")
    override fun getTicker(firstMarket: String?) {
        repository.getMarket()
            .observeOn(Schedulers.newThread())
            .subscribe { it ->
                repository.getTicker(it)
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {
                        it.filter { TickerResponse ->
                            TickerResponse.market.split("-")[0] == firstMarket
                        }
                    }
                    .subscribe({
                        tickerView.showUpbitTickerList(it.map { it.toTicker() })

                    }, {
                        tickerView.showFailedUpbitTickerList()
                    })
            }
    }
}