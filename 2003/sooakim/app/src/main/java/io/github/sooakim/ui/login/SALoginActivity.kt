package io.github.sooakim.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.github.sooakim.R
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.movie.SAMovieSearchActivity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class SALoginActivity : SAActivity() {
    private lateinit var idInputEditText: TextInputEditText
    private lateinit var passwordInputEditText: TextInputEditText
    private lateinit var loginButton: AppCompatButton
    private lateinit var loadingProgressBar: ProgressBar

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override val commonProgressView: View?
        get() = loadingProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        bindRx()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun initView() {
        idInputEditText = findViewById(R.id.iet_id)
        passwordInputEditText = findViewById(R.id.iet_password)
        loginButton = findViewById(R.id.btn_login)
        loadingProgressBar = findViewById(R.id.pgb_loading)
    }

    private fun bindRx() {
        val idChanges = idInputEditText.textChanges()
            .map(CharSequence::toString)
            .toFlowable(BackpressureStrategy.DROP)
        val pwChanges = passwordInputEditText.textChanges()
            .map(CharSequence::toString)
            .toFlowable(BackpressureStrategy.DROP)
        val request = Flowable.combineLatest(
            idChanges,
            pwChanges,
            BiFunction<String, String, Pair<String, String>> { id, password ->
                id to password
            }
        )

        loginButton.clicks()
            .toFlowable(BackpressureStrategy.DROP)
            .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .doOnNext { clearError() }
            .flatMap { request }
            .doOnNext { showLoading() }
            .switchMap { (id, password) ->
                requireApplication().authRepository.login(id, password)
            }
            .doOnNext { hideLoading() }
            .doOnError { hideLoading() }
            .doOnError(this::showError)
            .retry { error -> (error is HttpException) }
            .subscribe { routeMovieSearch() }
            .addTo(compositeDisposable)
    }

    private fun clearError() {
        idInputEditText.error = null
        passwordInputEditText.error = null
    }

    private fun showError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> when (throwable.code()) {
                404 -> {
                    idInputEditText.error = getString(R.string.activity_login_iet_id_error)
                }
                409 -> {
                    passwordInputEditText.error =
                        getString(R.string.activity_login_iet_password_error)
                }
                else -> {
                    clearError()
                }
            }
            else -> {
                clearError()
            }
        }
    }

    private fun routeMovieSearch() {
        startActivity(Intent(application, SAMovieSearchActivity::class.java))
        finish()
    }
}