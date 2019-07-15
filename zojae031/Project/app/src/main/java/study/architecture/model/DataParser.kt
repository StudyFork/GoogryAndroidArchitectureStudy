package study.architecture.model

import android.annotation.SuppressLint
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.architecture.MainFragment
import study.architecture.vo.Market
import study.architecture.vo.Ticker

object DataParser {
    lateinit var marketList: List<Market>
    private val lists = Array(4) { "" }
    val tickerList = Array<List<Ticker>>(4) { listOf() }


    private val client =
        Retrofit
            .Builder()
            .baseUrl(MainFragment.url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val api = client.create(UpbitApi::class.java)

    init {
        api.getMarkets()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Market>> {
                override fun onSuccess(t: List<Market>) {
                    for (item in t) {
                        when {
                            item.market.contains("KRW") -> lists[0] += "${item.market},"
                            item.market.contains("BTC") -> lists[1] += "${item.market},"
                            item.market.contains("ETH") -> lists[2] += "${item.market},"
                            item.market.contains("USDT") -> lists[3] += "${item.market},"
                        }
                    }
                    parseTickerList()
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Log.e("Error",e.printStackTrace().toString())
                }
            })
    }


    @SuppressLint("CheckResult")
    fun parseTickerList() {
        for (i in 0..3) {
            api.getTickers(lists[i])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { item ->
                    tickerList[i] = item
                }
        }
    }


}