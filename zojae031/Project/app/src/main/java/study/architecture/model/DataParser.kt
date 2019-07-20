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

/**
 * Data 관리하는 비즈니스로직이 담긴 클래스
 */
class DataParser(index: MainFragment.FragIndex, private val resultCallback: ResultCallback) {

    private val stateString: String = index.name
    private var list = ""

    private val client =
        Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val api by lazy { client.create(UpbitApi::class.java) }

    /**
     * 해당하는 Fragment index 정보를 통해 가져올 Market 정보를 구하는 함수
     * @see study.architecture.mainjob.MainPresenter.onCreate
     */
    fun paresMarketList() {
        api.getMarkets()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                list->
                list.filter { it.market.startsWith(stateString) }
                    .joinToString(",") { it.market }
            }
            .subscribe(
                { marketList ->
                    list = marketList
                    resultCallback.successMarketList()
                },
                { e -> Log.e("onErrorMarketList", e.message) }
            )
//            .subscribe(
//                { marketList ->
//                    for (item in marketList) {
//                        if (item.market.substringBefore("-") == stateString) list += "${item.market},"
//                    }
//                    list = list.substring(0, list.lastIndex)
//                    resultCallback.successMarketList()
//                },
//                { e -> Log.e("onErrorMarketList", e.message) }
//            )
    }

    /**
     * Market정보를 얻어온 이후 Ticekr정보를 가져오는 클래스
     * @see study.architecture.mainjob.MainPresenter.onResume
     */
    @SuppressLint("CheckResult")
    fun parseTickerList(): Disposable =
        Observable.interval(0, 5, TimeUnit.SECONDS)
            .flatMap { api.getTickers(list) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    resultCallback.successTickerList(list)
                },
                { e -> Log.e("onError", e.message) }
            )


    companion object {
        private val url = "https://api.upbit.com/v1/"
    }

    /**
     * Presenter에게 데이터를 알려주기 위한 콜백 인터페이스
     */
    interface ResultCallback {
        fun successMarketList()
        fun successTickerList(list: List<Ticker>)
    }

}