package com.tsdev.tsandroid.util.widget

import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.tsdev.tsandroid.constant.Const
import com.tsdev.tsandroid.provider.ResourceProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

typealias OnClickListener = (View) -> Unit

class ThrottleClickListener(
    disposable: CompositeDisposable,
    private val listener: OnClickListener
) : View.OnClickListener {

    private val throttleBehaviorSubject = BehaviorSubject.create<View>()

    /***
     * debounce -> 매개변수로 지정한 TimeUnit 가 지난 후 이벤트가 없으면 데이터를 발행.
     * throttlexxx ->  첫 이벤트가 들어온 후 매개변수로 지정한 TimeUnit 동안 들어온 이벤트를 모두 무시.
     ***/

    init {
        disposable.add(
            throttleBehaviorSubject
                .subscribeOn(Schedulers.computation())
                .onErrorReturn {
                    Snackbar.make(throttleBehaviorSubject.value, "재 검색을 해주세요", Snackbar.LENGTH_LONG)
                        .view
                }
                .throttleFirst(Const.SEARCH_BUTTON_THROTTLE_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    it.run(listener)
                }
        )
    }

    override fun onClick(v: View?) {
        throttleBehaviorSubject.onNext(v)
    }
}