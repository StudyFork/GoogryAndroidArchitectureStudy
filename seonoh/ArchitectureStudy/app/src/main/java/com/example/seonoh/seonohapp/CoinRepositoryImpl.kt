package com.example.seonoh.seonohapp

import com.example.seonoh.seonohapp.datasource.CoinDataSourceImpl
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import com.example.seonoh.seonohapp.network.BaseResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CoinRepositoryImpl : CoinRepository {

    private val coinDataSource = CoinDataSourceImpl()


    override fun sendCurrentPriceInfo(listener: BaseResult<List<CurrentPriceInfoModel>>, markets: String) {
        val currentPriceInfoData = coinDataSource.getCurrentPriceInfo(markets)

        currentPriceInfoData.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it != null){
                    listener.getNetworkSuccess(it)
                }else{
                    listener.getNetworkFailed("body is null")
                }
            }, { e ->
                if (e is HttpException) {
                    listener.getNetworkFailed(e.toString())
                }
            })
    }


    override fun sendMarket(listener: BaseResult<List<Market>>) {
        val marketNameData = coinDataSource.getMarket()
        marketNameData.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it != null){
                    listener.getNetworkSuccess(it)
                }else{
                    listener.getNetworkFailed("body is null")
                }
            }, { e ->
                if (e is HttpException) {
                    listener.getNetworkFailed(e.toString())
                }
            })

    }
}