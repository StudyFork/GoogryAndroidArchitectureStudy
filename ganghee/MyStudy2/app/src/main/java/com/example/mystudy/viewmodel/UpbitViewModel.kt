package com.example.mystudy.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.example.mystudy.base.BaseViewModel
import com.example.mystudy.data.FormatTickers
import com.example.mystudy.data.UpbitRepository
import com.example.mystudy.data.remote.UpbitRemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class UpbitViewModel: BaseViewModel() {

    private val repository: UpbitRepository by lazy { UpbitRepository.getInstance(UpbitRemoteDataSource) }
    val tickerList = ObservableField<List<FormatTickers>>()

    fun getTicker(firstMarket: String?) {
        repository.getMarket()
            .repeatWhen { it.delay(5, TimeUnit.SECONDS) }
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
                        tickerList.set(it.map { it.toTicker() })
                    }, {
                        showFailedUpbitTickerList()
                    })
            }.also { compositeDisposable.add(it) }
    }

    private fun showFailedUpbitTickerList() {
        Log.d("TickerFail", "Ticker is not show")
    }

}