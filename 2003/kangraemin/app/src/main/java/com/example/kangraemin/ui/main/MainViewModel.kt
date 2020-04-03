package com.example.kangraemin.ui.main

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.room.EmptyResultSetException
import com.example.kangraemin.base.KangBaseViewModel
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.MovieSearchRepository
import com.example.kangraemin.model.local.datamodel.Auth
import com.example.kangraemin.model.remote.datamodel.MovieDetail
import com.example.kangraemin.model.remote.datamodel.Movies
import com.example.kangraemin.util.NetworkUtil
import com.example.kangraemin.util.NonNullObservableField
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

    private data class ResponseMovieData(
        val responseError: Boolean,
        val responseResult: Movies = Movies(items = ArrayList()),
        val throwable: Throwable? = null
    )

    val getMovieError: ObservableBoolean = ObservableBoolean(false)

    val movies: NonNullObservableField<ArrayList<MovieDetail>> = NonNullObservableField(ArrayList())

    private val deleteAuthSubject = PublishSubject.create<Unit>()

    private data class ResponseDeleteAuth(
        val responseError: Boolean,
        val throwable: Throwable? = null
    )

    val logoutResponse = ObservableField<LogoutResponse>()

    enum class LogoutResponse {
        LOGOUT_ERROR,
        LOGOUT_SUCCESS
    }

    private val getAuthSubject = PublishSubject.create<Unit>()

    private data class ResponseGetAuth(
        val responseError: Boolean,
        val responseResult: Auth = Auth(autoLogin = false),
        val throwable: Throwable? = null
    )

    val getAuthError: ObservableField<Unit> = ObservableField(Unit)

    val showLogoutButton: ObservableBoolean = ObservableBoolean(false)

    val searchText: NonNullObservableField<String> = NonNullObservableField("")

    val isNetworkConnected: ObservableBoolean = ObservableBoolean(true)

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
                    getMovieError.set(true)
                    responseMovieData.throwable?.apply {
                        printStackTrace()
                    }
                } else {
                    movies.set(responseMovieData.responseResult.items)
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
                    logoutResponse.set(LogoutResponse.LOGOUT_ERROR)
                    responseDeleteAuth.throwable?.apply {
                        printStackTrace()
                    }
                } else {
                    logoutResponse.set(LogoutResponse.LOGOUT_SUCCESS)
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
                            getAuthError.set(Unit)
                        }
                        printStackTrace()
                    }
                } else {
                    showLogoutButton.set(true)
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
                isNetworkConnected.set(false)
                isNetworkConnected.notifyChange()
            }
            else -> {
                isNetworkConnected.set(true)
            }
        }
    }

    fun movieSearch() {
        searchTextSubject.onNext(searchText.get())
    }
}