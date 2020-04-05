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

    val needLogin: ObservableField<Unit> = ObservableField()

    val getAuthError: ObservableField<Unit> = ObservableField()

    val toMain: ObservableField<Unit> = ObservableField()

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
                            needLogin.notifyChange()
                        } else {
                            getAuthError.notifyChange()
                        }
                    }
                } else {
                    toMain.notifyChange()
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(splashTimer)
    }

    private data class ResponseGetAuth(
        val responseError: Boolean,
        val responseResult: Auth = Auth(autoLogin = false),
        val throwable: Throwable? = null
    )
}