package study.architecture.model

import android.annotation.SuppressLint
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.architecture.mainjob.MainFragment
import study.architecture.vo.Ticker
import java.util.concurrent.TimeUnit

class DataParser(index: MainFragment.FragIndex, resultCallback: ResultCallback) {
    private var stateString: String = when (index) {
        MainFragment.FragIndex.KRW -> "KRW"
        MainFragment.FragIndex.BTC -> "BTC"
        MainFragment.FragIndex.ETH -> "ETH"
        MainFragment.FragIndex.USDT -> "USDT"
    }
    private var list = ""

    private val callback = resultCallback

    interface ResultCallback {
        fun successMarketList()
        fun successTickerList(list: List<Ticker>)
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
            .subscribe(
                { marketList ->
                    for (item in marketList) {
                        if (item.market.substringBefore("-") == stateString) list += "${item.market},"
                    }
                    list = list.substring(0, list.lastIndex)
                    callback.successMarketList()
                },
                { e -> Log.e("onErrorMarketList", e.message) }
            )
    }

    @SuppressLint("CheckResult")
    fun parseTickerList(): Disposable =
        Observable.interval(0, 5, TimeUnit.SECONDS)
            .flatMap { api.getTickers(list) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    callback.successTickerList(list)

                },
                { e -> Log.e("onError", e.message) },
                { Log.e("onSuccess", "발행 완료") }
            )


    companion object {
        private val url = "https://api.upbit.com/v1/"
    }

}