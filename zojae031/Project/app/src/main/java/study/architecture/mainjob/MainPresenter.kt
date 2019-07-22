package study.architecture.mainjob

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import study.architecture.model.repository.Repository
import java.util.concurrent.TimeUnit

/**
 * 1. 업비트 데이터를 가져와 View에게 알려준다.
 * 2. Observable 생명주기를 관리한다.
 * 3. Callback을 구현하여 발행된 데이터에 대한 행동을 한다. ( Presenter <-> Model 간의 의존성이 생긴다 ㅠㅠ... )
 */
class MainPresenter(private val view: MainContract.View, private val index: MainFragment.FragIndex) :
    MainContract.Presenter {
    private val dispose: Disposable
    private var list = ""
    private val compositeDisposable = CompositeDisposable()

    init {
        dispose =
            Repository.getMarketList(index)
                .subscribe(
                    { marketList ->
                        list = marketList
                        tickerRequest()
                    },
                    { e ->
                        Log.e("onErrorMarketList", e.message)
                    })
    }


    override fun onResume() {
        if (dispose.isDisposed) {
            tickerRequest()
        }
    }


    override fun onPause() {
        compositeDisposable.clear()
    }

    @SuppressLint("CheckResult")
    private fun tickerRequest() {
        compositeDisposable.add(
            Observable.interval(0, 8, TimeUnit.SECONDS)
                .flatMapSingle { Repository.getTickerList(list) }
                //TODO onBindViewHolder에서 하는 Ticker 데이터 가공작업 해서 view 에게 넘겨주기
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { lists ->
                        view.notifyAdapter(lists)
                    },
                    { e ->
                        Log.e("onError", e.message)
                    }
                )
        )
    }


}