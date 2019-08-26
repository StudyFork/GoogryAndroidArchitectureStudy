package com.example.seonoh.seonohapp

import android.util.Log
import com.example.seonoh.seonohapp.datasource.CoinDataSourceImpl
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import com.example.seonoh.seonohapp.network.BaseResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CoinRepositoryImpl : CoinRepository {

    private val coinDataSource = CoinDataSourceImpl()

    override fun sendCurrentPriceInfo(listener: BaseResult<List<CurrentPriceInfoModel>>, markets: String?) {
        if(markets == null){
            Log.e("coinrepo","markets is null ")
            return
        }
        val currentPriceInfoData = coinDataSource.getCurrentPriceInfo(markets!!)

        currentPriceInfoData.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { listener.getNetworkSuccess(it) }

            }, { e ->
                if (e is HttpException) {
                    listener.getNetworkFailed(e.message())
                }
            })
    }


    override fun sendMarket(listener: BaseResult<List<Market>>) {
        val marketNameData = coinDataSource.getMarket()
        marketNameData.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { listener.getNetworkSuccess(it) }

            }, { e ->
                if (e is HttpException) {
                    listener.getNetworkFailed(e.message())
                }
            })
    }
}