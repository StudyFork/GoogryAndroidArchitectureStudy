package com.example.kangraemin.presenter

import com.example.kangraemin.contract.MainContract
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.MovieSearchRepository
import com.example.kangraemin.model.local.datadao.LocalMovieDataSourceImpl
import com.example.kangraemin.model.remote.datadao.MovieRemoteDataSourceImpl
import com.example.kangraemin.util.NetworkUtil
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(
    private val mainView: MainContract.View
) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun checkAutoLoginStatus(authRepository: AuthRepository) {
        val getAuth = authRepository
            .getAuth()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.autoLogin) {
                    mainView.showLogOutButton()
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(getAuth)
    }

    override fun deleteAutoLoginStatus(authRepository: AuthRepository) {
        val deleteAuth = authRepository
            .deleteAuth()
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

    override fun checkNetworkStatus(networkStatus: NetworkUtil.NetworkStatus) {
        when (networkStatus) {
            NetworkUtil.NetworkStatus.NOT_CONNECTED -> {
                mainView.showNetworkErrorText()
            }
            else -> {
                mainView.hideNetworkErrorText()
            }
        }
    }

    override fun getMovies(
        remoteMovieDataSource: MovieRemoteDataSourceImpl,
        localMovieDataSource: LocalMovieDataSourceImpl,
        searchText: String
    ) {
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

    override fun disposeCompositeDisposable() {
        compositeDisposable.dispose()
    }
}