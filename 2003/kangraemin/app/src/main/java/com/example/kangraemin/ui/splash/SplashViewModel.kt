package com.example.kangraemin.ui.splash

import androidx.databinding.ObservableField
import androidx.room.EmptyResultSetException
import com.example.kangraemin.base.KangBaseViewModel
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datamodel.Auth
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashViewModel(
    private val authRepository: AuthRepository
) : KangBaseViewModel() {

    val observableSplashResult = ObservableField<SplashResult>()

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
                    responseGetAuth.throwable?.apply {
                        printStackTrace()
                        if (responseGetAuth.throwable is EmptyResultSetException) {
                            observableSplashResult.set(SplashResult.NEED_LOGIN)
                        } else {
                            observableSplashResult.set(SplashResult.AUTH_ERROR)
                        }
                    }
                } else {
                    observableSplashResult.set(SplashResult.MAIN_VIEW)
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(splashTimer)
    }

    enum class SplashResult {
        NEED_LOGIN,
        AUTH_ERROR,
        MAIN_VIEW
    }

    private data class ResponseGetAuth(
        val responseError: Boolean,
        val responseResult: Auth = Auth(autoLogin = false),
        val throwable: Throwable? = null
    )
}