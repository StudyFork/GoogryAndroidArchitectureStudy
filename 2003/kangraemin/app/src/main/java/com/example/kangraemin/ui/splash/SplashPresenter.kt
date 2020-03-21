package com.example.kangraemin.ui.splash

import androidx.room.EmptyResultSetException
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datamodel.Auth
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SplashPresenter(
    private val splashView: SplashContract.View,
    private val authRepository: AuthRepository
) : SplashContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private data class ResponseGetAuth(
        val responseError: Boolean,
        val responseResult: Auth = Auth(autoLogin = false),
        val throwable: Throwable? = null
    )

    init {
        val splashTimer = Completable
            .timer(3, TimeUnit.SECONDS)
            .andThen(
                Flowable.just(Unit)
            )
            .switchMap {
                authRepository.getAuth()
                    .map {
                        ResponseGetAuth(responseError = false, responseResult = it)
                    }
                    .onErrorReturn { ResponseGetAuth(responseError = true, throwable = it) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ responseGetAuth ->
                if (responseGetAuth.responseError) {
                    if (responseGetAuth.throwable != null) {
                        responseGetAuth.throwable.printStackTrace()
                        if (responseGetAuth.throwable is EmptyResultSetException) {
                            splashView.startLoginActivity()
                        } else {
                            splashView.showGetAuthError()
                        }
                    }
                } else {
                    splashView.startMainActivity()
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(splashTimer)
    }

    override fun onViewDestroy() {
        compositeDisposable.dispose()
    }
}