package study.architecture.model

import android.annotation.SuppressLint
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.architecture.vo.Market
import study.architecture.vo.Ticker
import java.util.concurrent.TimeUnit

class DataParser(index: Int, resultCallback: ResultCallback) {
    private lateinit var stateString: String
    private var list = ""

    private val callback = resultCallback

    interface ResultCallback {
        fun successMarketList()
        fun successTickerList(list: List<Ticker>)
    }

    init {
        when (index) {
            0 -> stateString = "KRW"
            1 -> stateString = "BTC"
            2 -> stateString = "ETH"
            3 -> stateString = "USDT"
        }
    }


    private val client =
        Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val api = client.create(UpbitApi::class.java)

    /**
     * 해당하는 Fragment index 정보를 통해 가져올 Market 정보를 구하는 함수
     * @see study.architecture.MainPresenter
     */
    fun paresMarketList() {
        api.getMarkets()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Market>> {
                override fun onSuccess(t: List<Market>) {
                    for (item in t) {
                        if (item.market.contains(stateString)) list += "${item.market},"
                    }
                    list = list.substring(0, list.lastIndex)

                    callback.successMarketList()
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Log.e("Error", e.printStackTrace().toString())
                }
            })
    }

    @SuppressLint("CheckResult")
    fun parseTickerList() {
        Observable.interval(0,3,TimeUnit.SECONDS)
            .flatMap { api.getTickers(list) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Ticker>>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: List<Ticker>) {
                    callback.successTickerList(t)
                }

                override fun onError(e: Throwable) {
                    Log.e("onError",e.message)
                }
            })
    }


    companion object {
        private val url = "https://api.upbit.com/v1/"
    }

}