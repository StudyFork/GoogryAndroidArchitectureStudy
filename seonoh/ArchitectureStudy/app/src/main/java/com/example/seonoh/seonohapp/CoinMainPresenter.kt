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
    override val view: CoinMainContract.View
) : CoinMainContract.Presenter {

    private val coinRepository = CoinRepositoryImpl()
    override val compositeDisposable by lazy { CompositeDisposable() }

    override fun loadData() {
        compositeDisposable.add(coinRepository.sendMarket()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({view.setData(it)},{
                Log.e("currentPriceInfo", "Network failed!! ${it.message}")
            }))
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }
}