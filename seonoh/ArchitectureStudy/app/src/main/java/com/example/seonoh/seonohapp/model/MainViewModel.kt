package com.example.seonoh.seonohapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repo: CoinRepositoryImpl) : BaseViewModel() {
    private val _marketInfo = MutableLiveData<List<String>>()

    val marketInfo: LiveData<List<String>>
        get() = _marketInfo

    init {
        loadData()
    }

    private fun handleError(throwable: Throwable) {
        Log.e("currentPriceInfo", "Main Network failed!! ${throwable.message}")
    }

    private fun loadData() {

        repo.sendMarket()
            .map(::refineData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _marketInfo.value = it
            }, ::handleError)
            .addCompositeDisposable()

    }

    private fun refineData(marketData: List<Market>): List<String> {

        val marketDataList = ArrayList<String>()

        marketData
            .groupBy { it.market.substringBefore("-") }
            .map {
                val market = it.value.joinToString { it.market }
                marketDataList += market
            }


        return marketDataList
    }


}