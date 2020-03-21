package com.example.kangraemin.ui.main

import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.MovieSearchRepository
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

    private val deleteAuthSubject = PublishSubject.create<Unit>()

    private val getAuthSubject = PublishSubject.create<Unit>()

    init {
        val whenArrivedMovieData = searchTextSubject
            .toFlowable(BackpressureStrategy.DROP)
            .startWith("")
            .switchMap {
                movieSearchRepository
                    .getMovieData(query = it).cache()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies ->
                mainView.setMoviesInAdapter(movies = movies.items)
            }, { it.printStackTrace() })
        compositeDisposable.add(whenArrivedMovieData)

        val deleteAuth = deleteAuthSubject
            .toFlowable(BackpressureStrategy.DROP)
            .switchMap {
                authRepository.deleteAuth()
                    .andThen(Flowable.just(Unit))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mainView.startLoginActivity()
            }, { it.printStackTrace() })
        compositeDisposable.add(deleteAuth)

        val getAuth = getAuthSubject
            .toFlowable(BackpressureStrategy.DROP)
            .switchMap {
                authRepository.getAuth()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.autoLogin) {
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