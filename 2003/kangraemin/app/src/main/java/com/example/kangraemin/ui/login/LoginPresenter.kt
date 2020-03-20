package com.example.kangraemin.ui.login

import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datamodel.Auth
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class LoginPresenter(
    private val loginView: LoginContract.View,
    private val authRepository: AuthRepository
) : LoginContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private val addAuthSubject = PublishSubject.create<Auth>()

    init {
        val addAuth = addAuthSubject
            .toFlowable(BackpressureStrategy.DROP)
            .switchMap {
                authRepository.addAuth(auth = it)
                    .andThen(Flowable.just(Unit))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loginView.hideFailedLoginError()
                loginView.startMain()
            }, { it.printStackTrace() })
        compositeDisposable.add(addAuth)
    }

    override fun checkIdIsEmpty(id: String, hasFocus: Boolean) {
        if (!hasFocus) {
            if (id.isEmpty()) {
                loginView.showEmptyIdError()
            } else {
                loginView.hideEmptyIdError()
            }
        } else {
            loginView.hideEmptyIdError()
        }
    }

    override fun checkPasswordIsEmpty(password: String, hasFocus: Boolean) {
        if (!hasFocus) {
            if (password.isEmpty()) {
                loginView.showPasswordEmptyError()
            } else {
                loginView.hidePasswordEmptyError()
            }
        } else {
            loginView.hidePasswordEmptyError()
        }
    }

    override fun checkLoginInfoHasEntered(id: String, password: String) {
        if (id.isEmpty() || password.isEmpty()) {
            loginView.disableLoginButton()
        } else {
            loginView.enableLoginButton()
        }
    }

    override fun login(id: String, password: String, isAutoLogin: Boolean) {
        if (id != "id" || password != "P@ssw0rd") {
            loginView.showFailedLoginError()
        } else {
            if (isAutoLogin) {
                addAuthSubject.onNext(Auth(autoLogin = true))
            } else {
                loginView.hideFailedLoginError()
                loginView.startMain()
            }
        }
    }

    override fun onViewDestroy() {
        compositeDisposable.dispose()
    }
}