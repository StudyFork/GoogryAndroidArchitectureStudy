package com.example.kangraemin.ui.main

import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.MovieSearchRepository
import com.example.kangraemin.model.local.datadao.LocalMovieDataSourceImpl
import com.example.kangraemin.model.remote.datadao.MovieRemoteDataSourceImpl
import com.example.kangraemin.util.NetworkUtil
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(
    private val mainView: MainContract.View,
    private val remoteMovieDataSource: MovieRemoteDataSourceImpl,
    private val localMovieDataSource: LocalMovieDataSourceImpl,
    private val authRepository: AuthRepository,
    private val networkUtil: NetworkUtil
) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun checkAutoLoginStatus() {
        val getAuth = Flowable
            .just("")
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

    override fun deleteAutoLoginStatus() {
        val deleteAuth = Flowable
            .just("")
            .switchMapCompletable {
                authRepository.deleteAuth()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mainView.startLoginActivity()
            }, { it.printStackTrace() })
        compositeDisposable.add(deleteAuth)
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
        val whenArrivedMovieData = Flowable
            .just(searchText)
            .startWith("")
            .switchMap {
                MovieSearchRepository(
                    remoteMovieDataSource = remoteMovieDataSource,
                    localMovieDataSource = localMovieDataSource
                )
                    .getMovieData(query = it).cache()
                    .observeOn(AndroidSchedulers.mainThread())
            }
            .subscribe({ movies ->
                mainView.setMoviesInAdapter(movies = movies.items)
            }, { it.printStackTrace() })
        compositeDisposable.add(whenArrivedMovieData)
    }

    override fun onViewDestroy() {
        compositeDisposable.dispose()
    }
}