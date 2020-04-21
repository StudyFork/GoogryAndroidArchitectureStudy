package io.github.sooakim.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.sooakim.R
import io.github.sooakim.domain.repository.SAAuthRepository
import com.example.common.exception.InvalidPasswordException
import com.example.common.exception.UserNotFoundException
import io.github.sooakim.ui.base.SAViewModel
import io.github.sooakim.util.NotNullMutableLiveData
import io.github.sooakim.util.ResourceProvider
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

class SALoginViewModel(
    private val resourceProvider: ResourceProvider,
    authRepository: SAAuthRepository
) : SAViewModel<SALoginState>() {
    private val _isLoading: NotNullMutableLiveData<Boolean> =
        NotNullMutableLiveData(false)
    private val _id: NotNullMutableLiveData<String> =
        NotNullMutableLiveData("")
    private val _password: NotNullMutableLiveData<String> =
        NotNullMutableLiveData("")
    private val _errorId: MutableLiveData<String> = MutableLiveData()
    private val _errorPassword: MutableLiveData<String> = MutableLiveData()
    private val _loginClick: PublishSubject<Unit> = PublishSubject.create()

    val isLoading: LiveData<Boolean> = _isLoading
    val id: MutableLiveData<String> = _id
    val password: MutableLiveData<String> = _password
    val errorId: LiveData<String> = _errorId
    val errorPassword: LiveData<String> = _errorPassword
    val loginClick: Subject<Unit> = _loginClick

    init {
        _loginClick
            .toFlowable(BackpressureStrategy.DROP)
            .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .doOnNext { clearErrors() }
            .doOnNext { showLoading() }
            .switchMap { authRepository.login(_id.value, _password.value) }
            .doOnNext { hideLoading() }
            .doOnError { hideLoading() }
            .doOnError { error -> handleError(error) }
            .retry()
            .subscribe { runState(SALoginState.ShowMain) }
            .addTo(compositeDisposable)
    }

    private fun handleError(throwable: Throwable) {
        when (throwable) {
            is UserNotFoundException -> showIdError()
            is InvalidPasswordException -> showPasswordError()
            else -> {
                clearErrors()
            }
        }
    }

    private fun clearErrors() {
        _errorId.value = null
        _errorPassword.value = null
    }

    private fun showIdError() {
        _errorId.value = resourceProvider.getString(R.string.activity_login_iet_id_error)
    }

    private fun showPasswordError() {
        _errorPassword.value =
            resourceProvider.getString(R.string.activity_login_iet_password_error)
    }

    private fun showLoading() {
        _isLoading.value = true
    }

    private fun hideLoading() {
        _isLoading.value = false
    }
}