package com.example.kangraemin.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.exception.RoomEmptyResultException
import com.example.data.model.Auth
import com.example.kangraemin.base.KangBaseViewModel
import com.example.data.source.AuthRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashViewModel(
    private val authRepository: AuthRepository
) : KangBaseViewModel() {

    private val _needLogin = MutableLiveData<Unit>()

    val needLogin: LiveData<Unit> = _needLogin

    private val _getAuthError = MutableLiveData<Unit>()

    val getAuthError: LiveData<Unit> = _getAuthError

    private val _toMain = MutableLiveData<Unit>()

    val toMain: LiveData<Unit> = _toMain

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
                        if (responseGetAuth.throwable is RoomEmptyResultException) {
                            _needLogin.value = Unit
                        } else {
                            _getAuthError.value = Unit
                        }
                    }
                } else {
                    _toMain.value = Unit
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(splashTimer)
    }

    private data class ResponseGetAuth(
        val responseError: Boolean,
        val responseResult: Auth = Auth(
            autoLogin = false
        ),
        val throwable: Throwable? = null
    )
}