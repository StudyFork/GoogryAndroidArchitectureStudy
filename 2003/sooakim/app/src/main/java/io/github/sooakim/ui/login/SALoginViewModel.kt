package io.github.sooakim.ui.login

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.github.sooakim.R
import io.github.sooakim.domain.repository.SAAuthRepository
import io.github.sooakim.ui.base.SAViewModel
import io.github.sooakim.util.NotNullObservableField
import io.github.sooakim.util.ResourceProvider
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

class SALoginViewModel(
    private val resourceProvider: ResourceProvider,
    authRepository: SAAuthRepository,
    navigator: SALoginNavigator
) : SAViewModel<SALoginNavigator>(navigator) {
    private val _isLoading: ObservableBoolean = ObservableBoolean(false)
    private val _id: NotNullObservableField<String> = NotNullObservableField("")
    private val _password: NotNullObservableField<String> = NotNullObservableField("")
    private val _errorId: ObservableField<String> = ObservableField()
    private val _errorPassword: ObservableField<String> = ObservableField()
    private val _loginClick: PublishSubject<Unit> = PublishSubject.create()

    val isLoading: ObservableBoolean = _isLoading
    val id: ObservableField<String> = _id
    val password: ObservableField<String> = _password
    val errorId: ObservableField<String> = _errorId
    val errorPassword: ObservableField<String> = _errorPassword
    val loginClick: Subject<Unit> = _loginClick

    init {
        _loginClick
            .toFlowable(BackpressureStrategy.DROP)
            .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .doOnNext { clearErrors() }
            .doOnNext { showLoading() }
            .switchMap { authRepository.login(_id.get(), _password.get()) }
            .doOnNext { hideLoading() }
            .doOnError { hideLoading() }
            .doOnError { error -> handleError(error) }
            .retry { error -> (error is HttpException) }
            .subscribe { navigator.showMain() }
            .addTo(compositeDisposable)
    }

    private fun handleError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> when (throwable.code()) {
                404 -> {
                    showIdError()
                }
                409 -> {
                    showPasswordError()
                }
                else -> {
                    clearErrors()
                }
            }
            else -> {
                clearErrors()
            }
        }
    }

    private fun clearErrors() {
        _errorId.set(null)
        _errorPassword.set(null)
    }

    private fun showIdError() {
        _errorId.set(resourceProvider.getString(R.string.activity_login_iet_id_error))
    }

    private fun showPasswordError() {
        _errorPassword.set(resourceProvider.getString(R.string.activity_login_iet_password_error))
    }

    private fun showLoading() {
        _isLoading.set(true)
    }

    private fun hideLoading() {
        _isLoading.set(false)
    }
}