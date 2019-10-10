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
     val view: CoinMainContract.View
) : BasePresenter(){


    private val coinRepository = CoinRepositoryImpl()

    fun loadData() {
        compositeDisposable.add(coinRepository.sendMarket()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.setData(it)
            },{
                Log.e("currentPriceInfo", "Network failed!! ${it.message}")
            }))
    }
}