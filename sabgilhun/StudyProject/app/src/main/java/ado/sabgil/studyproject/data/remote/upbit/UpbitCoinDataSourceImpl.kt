package ado.sabgil.studyproject.data.remote.upbit

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.CoinDataSource
import ado.sabgil.studyproject.data.remote.upbit.request.UpbitTickerListRequest
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object UpbitCoinDataSourceImpl : CoinDataSource {

    private val retrofit: UpbitApi

    private const val baseURL = "https://api.upbit.com/v1/"

    private var cachedTickerListRequest: UpbitTickerListRequest? = null

    private var behaviorSubject: BehaviorSubject<List<Ticker>>? = null

    private var subscribeCnt = 0

    private val compositeDisposable = CompositeDisposable()

    init {
        retrofit = run {
            Retrofit.Builder().baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UpbitApi::class.java)
        }
    }

    override fun loadMarketList(): Single<List<String>> {
        return retrofit.getMarketCode()
            .map { response ->
                cachedTickerListRequest = UpbitTickerListRequest.of(response)
                response.map {
                    it.market.substringBefore('-')
                }.distinct()
            }
    }

    override fun subscribeCoinDataChange(): Observable<List<Ticker>> {
        if (behaviorSubject == null) {
            behaviorSubject = BehaviorSubject.create<List<Ticker>>()

            compositeDisposable.add(Observable.interval(0, 5000, TimeUnit.MILLISECONDS)
                .flatMap {
                    loadAllTicker().toObservable()
                }
                .subscribe({
                    behaviorSubject!!.onNext(it)
                }, {
                    behaviorSubject!!.onError(it)
                })
            )
        }
        subscribeCnt++

        return behaviorSubject!!
    }

    override fun unSubscribeCoinDataChange() {
        check(subscribeCnt > 0)

        subscribeCnt--
        if (subscribeCnt == 0) {
            compositeDisposable.clear()
            behaviorSubject = null
        }
    }

    private fun loadAllTicker(): Single<List<Ticker>> {
        requireNotNull(cachedTickerListRequest)

        return Single.fromObservable(retrofit.getTickerList(cachedTickerListRequest!!.marketCodeQuery)
            .map { response ->
                response.map { Ticker.from(it) }
            })
    }
}