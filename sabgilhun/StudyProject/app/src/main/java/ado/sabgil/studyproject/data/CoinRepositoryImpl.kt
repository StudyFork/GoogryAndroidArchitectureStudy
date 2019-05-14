package ado.sabgil.studyproject.data

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.upbit.UpbitCoinDataSourceImpl
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object CoinRepositoryImpl : CoinRepository {

    private val dataSource = UpbitCoinDataSourceImpl

    override fun loadMarketList(
        success: (List<String>) -> Unit,
        fail: (String) -> Unit
    ): Disposable {
        return dataSource
            .loadMarketList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(success, { fail.invoke(it.message ?: "error") })
    }

    override fun subscribeCoinDataChange(
        baseCurrency: String,
        success: (List<Ticker>) -> Unit,
        fail: (String) -> Unit
    ): Disposable {
        return dataSource
            .subscribeCoinDataChange()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                success.invoke(result.filter { it.base == baseCurrency })
            }
    }

    override fun unSubscribeCoinDataChange() {
        dataSource.unSubscribeCoinDataChange()
    }

}