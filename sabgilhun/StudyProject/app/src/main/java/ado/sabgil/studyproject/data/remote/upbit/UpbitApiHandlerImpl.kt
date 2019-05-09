package ado.sabgil.studyproject.data.remote.upbit

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.upbit.request.UpbitTickerListRequest
import ado.sabgil.studyproject.data.remote.upbit.response.UpbitMarketCodeResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object UpbitApiHandlerImpl : UpbitApiHandler {

    private val retrofit: UpbitApi

    private const val baseURL = "https://api.upbit.com/v1/"

    private var cachedMarketCode: List<UpbitMarketCodeResponse>? = null

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

    override fun getMarketList(): Single<List<String>> {
        return retrofit.getMarketCode()
            .map { response ->
                cachedMarketCode = response

                response.map {
                    it.market.substringBefore('-')
                }.distinct()
            }
    }

    override fun subscribeRemoteData(): Observable<List<Ticker>> {
        if (behaviorSubject == null) {
            behaviorSubject = BehaviorSubject.create<List<Ticker>>()

            compositeDisposable.add(Observable.interval(0, 5000, TimeUnit.MILLISECONDS)
                .flatMap {
                    getAllTickers().toObservable()
                }
                .subscribe {
                    behaviorSubject!!.onNext(it)
                }
            )
        }
        subscribeCnt++

        return behaviorSubject!!
    }

    override fun unSubscribe() {
        check(subscribeCnt > 0)

        subscribeCnt--
        if (subscribeCnt == 0) {
            compositeDisposable.clear()
            behaviorSubject = null
        }
    }

    private fun getAllTickers(): Single<List<Ticker>> {
        requireNotNull(cachedMarketCode)

        return Single.fromObservable(retrofit.getTickerList(UpbitTickerListRequest.of(cachedMarketCode!!).marketCodeQuery)
            .map { response ->
                response.map { Ticker.from(it) }
            })
    }
}