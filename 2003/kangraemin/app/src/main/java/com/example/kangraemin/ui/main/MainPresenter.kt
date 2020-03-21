package com.example.kangraemin.ui.main

import androidx.room.EmptyResultSetException
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.MovieSearchRepository
import com.example.kangraemin.model.local.datamodel.Auth
import com.example.kangraemin.model.remote.datamodel.Movies
import com.example.kangraemin.util.NetworkUtil
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class MainPresenter(
    private val mainView: MainContract.View,
    private val movieSearchRepository: MovieSearchRepository,
    private val authRepository: AuthRepository,
    private val networkUtil: NetworkUtil
) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private val searchTextSubject = PublishSubject.create<String>()

    private data class ResponseMovieData(
        val responseError: Boolean,
        val responseResult: Movies = Movies(items = ArrayList()),
        val throwable: Throwable? = null
    )

    private val deleteAuthSubject = PublishSubject.create<Unit>()

    private data class ResponseDeleteAuth(
        val responseError: Boolean,
        val throwable: Throwable? = null
    )

    private val getAuthSubject = PublishSubject.create<Unit>()

    private data class ResponseGetAuth(
        val responseError: Boolean,
        val responseResult: Auth = Auth(autoLogin = false),
        val throwable: Throwable? = null
    )

    init {
        val whenArrivedMovieData = searchTextSubject
            .toFlowable(BackpressureStrategy.DROP)
            .startWith("")
            .switchMap {
                movieSearchRepository
                    .getMovieData(query = it).cache()
                    .map { movies ->
                        ResponseMovieData(responseError = false, responseResult = movies)
                    }
                    .onErrorReturn { ResponseMovieData(responseError = true, throwable = it) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ responseMovieData ->
                if (responseMovieData.responseError) {
                    mainView.showGetMovieError()
                    responseMovieData.throwable?.apply {
                        printStackTrace()
                    }
                } else {
                    mainView.setMoviesInAdapter(movies = responseMovieData.responseResult.items)
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(whenArrivedMovieData)

        val deleteAuth = deleteAuthSubject
            .toFlowable(BackpressureStrategy.DROP)
            .switchMap {
                authRepository.deleteAuth()
                    .andThen(Flowable.just(ResponseDeleteAuth(responseError = false)))
                    .onErrorReturn { ResponseDeleteAuth(responseError = true, throwable = it) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ responseDeleteAuth ->
                if (responseDeleteAuth.responseError) {
                    mainView.showLogOutError()
                    responseDeleteAuth.throwable?.apply {
                        printStackTrace()
                    }
                } else {
                    mainView.startLoginActivity()
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(deleteAuth)

        val getAuth = getAuthSubject
            .toFlowable(BackpressureStrategy.DROP)
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
                        if (this !is EmptyResultSetException) {
                            mainView.showGetAuthError()
                        }
                        printStackTrace()
                    }
                } else {
                    mainView.showLogOutButton()
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(getAuth)
    }

    override fun checkAutoLoginStatus() {
        getAuthSubject.onNext(Unit)
    }

    override fun deleteAutoLoginStatus(unit: Unit) {
        deleteAuthSubject.onNext(unit)
    }

    override fun hasEnteredSearchText(searchText: String) {
        if (searchText.isNotEmpty()) {
            mainView.enableSearchButton()
        } else {
            mainView.disableSearchButton()
        }
    }

    override fun checkNetworkStatus() {
        when (networkUtil.getConnectivityStatus()) {
            NetworkUtil.NetworkStatus.NOT_CONNECTED -> {
                mainView.showNetworkErrorText()
            }
            else -> {
                mainView.hideNetworkErrorText()
            }
        }
    }

    override fun getMovies(searchText: String) {
        searchTextSubject.onNext(searchText)
    }

    override fun onViewDestroy() {
        compositeDisposable.dispose()
    }
}