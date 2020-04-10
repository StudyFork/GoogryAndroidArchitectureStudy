package com.example.kangraemin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.EmptyResultSetException
import com.example.kangraemin.base.KangBaseViewModel
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.MovieSearchRepository
import com.example.kangraemin.model.local.datamodel.Auth
import com.example.kangraemin.model.remote.datamodel.MovieDetail
import com.example.kangraemin.model.remote.datamodel.Movies
import com.example.kangraemin.util.NetworkUtil
import com.example.kangraemin.util.NonNullMutableLiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel(
    private val movieSearchRepository: MovieSearchRepository,
    private val authRepository: AuthRepository,
    private val networkUtil: NetworkUtil
) : KangBaseViewModel() {

    private val searchTextSubject = PublishSubject.create<String>()

    private val _getMovieError: MutableLiveData<Boolean> = MutableLiveData()

    val getMovieError: LiveData<Boolean> = _getMovieError

    private val _movies: MutableLiveData<ArrayList<MovieDetail>> = MutableLiveData()

    val movies: LiveData<ArrayList<MovieDetail>> = _movies

    private val deleteAuthSubject = PublishSubject.create<Unit>()

    private val _logOutSuccess: MutableLiveData<Unit> = MutableLiveData()

    val logOutSuccess: LiveData<Unit> = _logOutSuccess

    private val _logOutFail: MutableLiveData<Unit> = MutableLiveData()

    val logOutFail: LiveData<Unit> = _logOutFail

    private val getAuthSubject = PublishSubject.create<Unit>()

    private val _getAuthError: MutableLiveData<Unit> = MutableLiveData()

    val getAuthError: LiveData<Unit> = _getAuthError

    private val _showLogoutButton: MutableLiveData<Boolean> = MutableLiveData()

    val showLogoutButton: LiveData<Boolean> = _showLogoutButton

    private val _searchText: NonNullMutableLiveData<String> = NonNullMutableLiveData("")

    val searchText: MutableLiveData<String> = _searchText

    private val _isNetworkConnected: MutableLiveData<Boolean> = MutableLiveData()

    val isNetworkConnected: LiveData<Boolean> = _isNetworkConnected

    init {

        val whenArrivedMovieData = searchTextSubject
            .doOnNext {
                getNetworkStatus()
            }
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
                    _getMovieError.value = true
                    responseMovieData.throwable?.apply {
                        printStackTrace()
                    }
                } else {
                    _movies.value = responseMovieData.responseResult.items
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
                    _logOutFail.value = Unit
                    responseDeleteAuth.throwable?.apply {
                        printStackTrace()
                    }
                } else {
                    _logOutSuccess.value = Unit
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
                            _getAuthError.value = Unit
                        }
                        printStackTrace()
                    }
                } else {
                    _showLogoutButton.value = true
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(getAuth)
    }

    fun checkAutoLoginStatus() {
        getAuthSubject.onNext(Unit)
    }

    fun deleteAutoLoginStatus() {
        deleteAuthSubject.onNext(Unit)
    }

    private fun getNetworkStatus() {
        return when (networkUtil.getConnectivityStatus()) {
            NetworkUtil.NetworkStatus.NOT_CONNECTED -> {
                _isNetworkConnected.value = false
            }
            else -> {
                _isNetworkConnected.value = true
            }
        }
    }

    fun movieSearch() {
        searchTextSubject.onNext(_searchText.value)
    }

    private data class ResponseMovieData(
        val responseError: Boolean,
        val responseResult: Movies = Movies(items = ArrayList()),
        val throwable: Throwable? = null
    )

    private data class ResponseDeleteAuth(
        val responseError: Boolean,
        val throwable: Throwable? = null
    )

    private data class ResponseGetAuth(
        val responseError: Boolean,
        val responseResult: Auth = Auth(autoLogin = false),
        val throwable: Throwable? = null
    )
}