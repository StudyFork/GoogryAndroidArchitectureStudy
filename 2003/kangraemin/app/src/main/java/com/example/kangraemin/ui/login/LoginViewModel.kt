package com.example.kangraemin.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.data.model.Auth
import com.example.kangraemin.base.KangBaseViewModel
import com.example.data.source.AuthRepository
import com.example.kangraemin.util.NonNullMutableLiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class LoginViewModel(
    private val authRepository: AuthRepository
) : KangBaseViewModel() {

    private val addAuthSubject = PublishSubject.create<Auth>()

    private val _loginError: MutableLiveData<Unit> = MutableLiveData()

    val loginError: LiveData<Unit> = _loginError

    private val _loginSuccess: MutableLiveData<Unit> = MutableLiveData()

    val loginSuccess: LiveData<Unit> = _loginSuccess

    val loginInfoEntered: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _idIsEmpty: MutableLiveData<Boolean> = MutableLiveData()

    val idIsEmpty: LiveData<Boolean> = _idIsEmpty

    private val _pwIsEmpty: MutableLiveData<Boolean> = MutableLiveData()

    val pwIsEmpty: LiveData<Boolean> = _pwIsEmpty

    private val _id: NonNullMutableLiveData<String> = NonNullMutableLiveData("")

    val id: MutableLiveData<String> = _id

    private val _pw: NonNullMutableLiveData<String> = NonNullMutableLiveData("")

    val pw: MutableLiveData<String> = _pw

    private val _autoLogin: NonNullMutableLiveData<Boolean> = NonNullMutableLiveData(false)

    val autoLogin: MutableLiveData<Boolean> = _autoLogin

    private val _addAuthSuccess: MutableLiveData<Unit> = MutableLiveData()

    val addAuthSuccess: LiveData<Unit> = _addAuthSuccess

    private val _addAuthFail: MutableLiveData<Unit> = MutableLiveData()

    val addAuthFail: LiveData<Unit> = _addAuthFail

    private val loginInfoObserver = Observer<String> {
        checkLoginInfoHasEntered()
    }

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
                    _addAuthFail.value = Unit
                    responseAddAuth.throwable?.apply {
                        printStackTrace()
                    }
                } else {
                    _addAuthSuccess.value = Unit
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(addAuth)

        id.observeForever(loginInfoObserver)

        pw.observeForever(loginInfoObserver)
    }

    fun checkIdIsEmpty(hasFocus: Boolean) {
        if (!hasFocus) {
            _idIsEmpty.value = _id.value.isEmpty()
        } else {
            _idIsEmpty.value = false
        }
    }

    fun checkPasswordIsEmpty(hasFocus: Boolean) {
        if (!hasFocus) {
            _pwIsEmpty.value = _pw.value.isEmpty()
        } else {
            _pwIsEmpty.value = false
        }
    }

    fun checkLoginInfoHasEntered() {
        loginInfoEntered.value = !(_id.value.isEmpty() || _pw.value.isEmpty())
    }

    fun login() {
        if (id.value != "id" || pw.value != "P@ssw0rd") {
            _loginError.value = Unit
        } else {
            if (_autoLogin.value) {
                addAuthSubject.onNext(Auth(autoLogin = true))
            } else {
                _loginSuccess.value = Unit
            }
        }
    }

    private data class ResponseAddAuth(
        val responseError: Boolean,
        val throwable: Throwable? = null
    )

    override fun onCleared() {
        id.removeObserver(loginInfoObserver)
        pw.removeObserver(loginInfoObserver)
        super.onCleared()
    }
}