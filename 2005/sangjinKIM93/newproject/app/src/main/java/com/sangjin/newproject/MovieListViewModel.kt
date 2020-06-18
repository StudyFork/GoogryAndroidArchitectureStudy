package com.sangjin.newproject

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.repository.NaverMoviesRepositoryImpl
import com.sangjin.newproject.data.source.local.LocalDataSourceImpl
import com.sangjin.newproject.data.source.remote.RemoteDataSourceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    private val repository by lazy {
        NaverMoviesRepositoryImpl(
            RemoteDataSourceImpl(),
            LocalDataSourceImpl(context)
        )
    }

    private val compositeDisposable = CompositeDisposable()

    private var _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    var keyword = MutableLiveData<String>()

    private var _toastMsg = MutableLiveData<String>()
    val toastMsg: LiveData<String> = _toastMsg

    private var _hideKeypad = MutableLiveData<Unit>()
    val hideKeypad: LiveData<Unit> = _hideKeypad

    private val refreshMovieSubject = PublishSubject.create<String>()


    init {
        setRefreshMovieSubject()
        loadCache()
    }


    private fun loadCache() {
        repository.loadCachedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                if (!it.isNullOrEmpty()) {

                    //리스트 최신화
                    _movieList.value = it

                    //기록했던 검색어 출력
                    keyword.value = repository.getCacheKeyword()
                }
            },
                {
                }
            ).let {
                compositeDisposable.add(it)
            }

    }


    fun refreshList() {

        val keyword = keyword.value ?: return

        if (TextUtils.isEmpty(keyword)) {
            _toastMsg.value = context.getString(R.string.no_keyword)
        } else {
            refreshMovieSubject.onNext(keyword)
        }
    }


    //버튼이 2번 연속으로 눌리는 경우 한번만 요청이 가도록 하는 기능
    private fun setRefreshMovieSubject() {
        refreshMovieSubject
            .subscribeOn(AndroidSchedulers.mainThread())
            .throttleFirst(2000L, TimeUnit.MILLISECONDS, Schedulers.computation())     //클릭 후 2초 안에 눌린 다른 클릭에는 반응하 않도록 설정
            .subscribe { keyword ->
                repository.getNaverMovies(keyword)
                    .subscribe(
                        {
                            checkMovieResult(it.items)
                        },
                        {
                            _toastMsg.value = it.toString()
                        }
                    )
            }.let {
                compositeDisposable.add(it)
            }
    }


    private fun checkMovieResult(movies: List<Movie>) {

        //리스트 최신화
        _movieList.value = movies
        _hideKeypad.value = Unit

        if (movies.isNullOrEmpty()) {
            _toastMsg.value = context.getString(R.string.no_movie_list)
        }

    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}