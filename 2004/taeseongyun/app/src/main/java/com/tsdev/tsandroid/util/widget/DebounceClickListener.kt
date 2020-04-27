package com.tsdev.tsandroid.util.widget

import android.view.View
import com.tsdev.tsandroid.constant.Const
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

typealias OnClickListener = (View) -> Unit

class DebounceClickListener(
    disposable: CompositeDisposable,
    private val listener: OnClickListener
) : View.OnClickListener {

    private lateinit var view: View
    protected val debounceOnClickBehaviorSubject = BehaviorSubject.createDefault(0L)

    init {
        disposable.add(
            debounceOnClickBehaviorSubject
                .subscribeOn(Schedulers.computation())
                .buffer(2, 1)
                .map { it[0] to it[1] }
                .subscribe({
                    if (it.second < it.first + Const.SEARCH_BUTTON_THROTTLE_TIME) {
                        return@subscribe
                    } else {
                        if (::view.isInitialized)
                            view.run(listener)
                    }
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun onClick(v: View?) {
        v?.let { view = it }
        debounceOnClickBehaviorSubject.onNext(System.currentTimeMillis())
    }

}