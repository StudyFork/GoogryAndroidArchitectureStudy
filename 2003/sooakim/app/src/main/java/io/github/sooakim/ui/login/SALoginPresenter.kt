package io.github.sooakim.ui.login

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.github.sooakim.domain.repository.SAAuthRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SALoginPresenter(
    authRepository: SAAuthRepository,
    private val view: SALoginContractor.View
) : SALoginContractor.Presenter {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val loginAction: PublishSubject<Pair<String, String>> = PublishSubject.create()

    init {
        loginAction
            .toFlowable(BackpressureStrategy.DROP)
            .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .doOnNext { view.clearErrors() }
            .switchMap { (id, password) -> authRepository.login(id, password) }
            .doOnNext { view.hideLoading() }
            .doOnError { view.hideLoading() }
            .doOnError(this::handleError)
            .retry { error -> (error is HttpException) }
            .subscribe { view.showMovieSearch() }
            .addTo(compositeDisposable)
    }

    private fun handleError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> when (throwable.code()) {
                404 -> {
                    view.showIdError()
                }
                409 -> {
                    view.showPasswordError()
                }
                else -> {
                    view.clearErrors()
                }
            }
            else -> {
                view.clearErrors()
            }
        }
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    override fun login(id: String, password: String) {
        loginAction.onNext(id to password)
    }
}