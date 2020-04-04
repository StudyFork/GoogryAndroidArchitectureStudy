package com.example.kangraemin.ui.login

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.example.kangraemin.base.KangBaseViewModel
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datamodel.Auth
import com.example.kangraemin.util.NonNullObservableField
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class LoginViewModel(
    private val authRepository: AuthRepository
) : KangBaseViewModel() {

    private val addAuthSubject = PublishSubject.create<Auth>()

    val addAuthResponse: ObservableField<AddAuthResponse> = ObservableField()

    val loginError: ObservableField<Unit> = ObservableField()

    val loginSuccess: ObservableField<Unit> = ObservableField()

    val loginInfoEntered: ObservableBoolean = ObservableBoolean(false)

    val idIsEmpty: ObservableBoolean = ObservableBoolean(false)

    val pwIsEmpty: ObservableBoolean = ObservableBoolean(false)

    val id: NonNullObservableField<String> = NonNullObservableField("")

    val pw: NonNullObservableField<String> = NonNullObservableField("")

    val autoLogin: ObservableBoolean = ObservableBoolean(false)

    init {
        val addAuth = addAuthSubject
            .toFlowable(BackpressureStrategy.DROP)
            .switchMap {
                authRepository.addAuth(auth = it)
                    .andThen(Flowable.just(ResponseAddAuth(responseError = false)))
                    .onErrorReturn { ResponseAddAuth(responseError = true, throwable = it) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ responseAddAuth ->
                if (responseAddAuth.responseError) {
                    addAuthResponse.set(AddAuthResponse.ADD_AUTH_ERROR)
                    responseAddAuth.throwable?.apply {
                        printStackTrace()
                    }
                } else {
                    addAuthResponse.set(AddAuthResponse.ADD_AUTH_SUCCESS)
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(addAuth)

        id.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                checkLoginInfoHasEntered()
            }
        })

        pw.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                checkLoginInfoHasEntered()
            }
        })
    }

    fun checkIdIsEmpty(hasFocus: Boolean) {
        if (!hasFocus) {
            if (id.get().isEmpty()) {
                idIsEmpty.set(true)
            } else {
                idIsEmpty.set(false)
            }
        } else {
            idIsEmpty.set(false)
        }
    }

    fun checkPasswordIsEmpty(hasFocus: Boolean) {
        if (!hasFocus) {
            if (pw.get().isEmpty()) {
                pwIsEmpty.set(true)
            } else {
                pwIsEmpty.set(false)
            }
        } else {
            pwIsEmpty.set(false)
        }
    }

    fun checkLoginInfoHasEntered() {
        if (id.get().isEmpty() || pw.get().isEmpty()) {
            loginInfoEntered.set(false)
        } else {
            loginInfoEntered.set(true)
        }
    }

    fun login() {
        if (id.get() != "id" || pw.get() != "P@ssw0rd") {
            loginError.notifyChange()
        } else {
            if (autoLogin.get()) {
                addAuthSubject.onNext(Auth(autoLogin = true))
            } else {
                loginSuccess.notifyChange()
            }
        }
    }

    enum class AddAuthResponse {
        ADD_AUTH_ERROR,
        ADD_AUTH_SUCCESS
    }

    private data class ResponseAddAuth(
        val responseError: Boolean,
        val throwable: Throwable? = null
    )
}