package com.example.seonoh.seonohapp

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import com.example.seonoh.seonohapp.network.CoinRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CoinRepositoryImpl : CoinRepository {

    private val coinDataSource = CoinDataSourceImpl()



    override fun sendCurrentPriceInfo(listener: CoinRequest.BaseResult<ArrayList<CurrentPriceInfoModel>>, markets: String) {
        val single = coinDataSource.getCurrentPriceInfo(markets)

        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.getNetworkSuccess(it)

            }, { e ->
                if (e is HttpException) {
                    listener.getNetworkFailed(e.toString())
                }
            })
    }


    override fun sendMarket(listener: CoinRequest.BaseResult<ArrayList<Market>>) {
        val single = coinDataSource.getMarket()
        single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.getNetworkSuccess(it)
            }, { e ->
                if (e is HttpException) {
                    listener.getNetworkFailed(e.toString())
                }
            })

    }
}