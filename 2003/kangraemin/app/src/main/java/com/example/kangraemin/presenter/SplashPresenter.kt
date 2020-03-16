package com.example.kangraemin.presenter

import com.example.kangraemin.contract.SplashContract
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SplashPresenter(
    private val splashView: SplashContract.View
) : SplashContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun startTimer() {
        val splashTimer = Completable
            .timer(3, TimeUnit.SECONDS)
            .subscribe({
                splashView.startLoginActivity()
            }, { it.printStackTrace() })
        compositeDisposable.add(splashTimer)
    }

    override fun disposeCompositeDisposable() {
        compositeDisposable.dispose()
    }
}