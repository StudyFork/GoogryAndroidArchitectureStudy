package com.example.kangraemin.ui.login

import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.local.datamodel.Auth
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class LoginPresenter(
    private val loginView: LoginContract.View,
    private val authRepository: AuthRepository
) : LoginContract.Presenter {

    private val compositeDisposable = CompositeDisposable()


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

    override fun checkLoginInfoHasEntered(id: String, password: String): Boolean {
        return id.isNotEmpty() && password.isNotEmpty()
    }

    override fun checkAutoLoginStatus() {
        val getAuth = Flowable
            .just("")
            .switchMap {
                authRepository.getAuth()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.autoLogin) {
                    loginView.startMainActivity()
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(getAuth)
    }

    override fun activateButton(allValueEntered: Boolean) {
        if (allValueEntered) {
            loginView.enableLoginButton()
        } else {
            loginView.disableLoginButton()
        }
    }

    override fun addAutoLoginStatus() {
        val auth = Auth(autoLogin = true)
        val addAuth = Flowable
            .just("")
            .switchMapCompletable {
                authRepository.addAuth(auth = auth)
            }
            .subscribe({ // no-op

            }, { it.printStackTrace() })
        compositeDisposable.add(addAuth)
    }

    override fun login(id: String, password: String, isAutoLogin: Boolean) {
        if (isAutoLogin) {
            addAutoLoginStatus()
        }
        if (id != "id" || password != "P@ssw0rd") {
            loginView.showFailedLoginError()
        } else {
            loginView.hideFailedLoginError()
            loginView.startMainActivity()
        }
    }

    override fun onViewDestroy() {
        compositeDisposable.dispose()
    }
}