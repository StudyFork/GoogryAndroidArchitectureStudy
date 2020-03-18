package io.github.sooakim.ui.login

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.github.sooakim.domain.repository.SAAuthRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

class SALoginPresenter(
    authRepository: SAAuthRepository,
    view: SALoginContractor.View
) : SALoginContractor.Presenter {
    private val viewRef: WeakReference<SALoginContractor.View> = WeakReference(view)
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val loginAction: PublishSubject<Pair<String, String>> = PublishSubject.create()

    init {
        loginAction
            .toFlowable(BackpressureStrategy.DROP)
            .takeUntil { viewRef.get() == null }
            .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .doOnNext { viewRef.get()?.clearErrors() }
            .switchMap { (id, password) -> authRepository.login(id, password) }
            .doOnNext { viewRef.get()?.hideLoading() }
            .doOnError { viewRef.get()?.hideLoading() }
            .doOnError(this::handleError)
            .retry { error -> (error is HttpException) }
            .subscribe { viewRef.get()?.showMovieSearch() }
            .addTo(compositeDisposable)
    }

    private fun handleError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> when (throwable.code()) {
                404 -> {
                    viewRef.get()?.showIdError()
                }
                409 -> {
                    viewRef.get()?.showPasswordError()
                }
                else -> {
                    viewRef.get()?.clearErrors()
                }
            }
            else -> {
                viewRef.get()?.clearErrors()
            }
        }
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    override fun doLogin(id: String, password: String) {
        loginAction.onNext(id to password)
    }
}