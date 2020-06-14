package com.sangjin.newproject

import android.text.TextUtils
import android.util.Log
import androidx.databinding.ObservableField
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.repository.NaverMoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(private val repository: NaverMoviesRepository) {

    private val disposables = CompositeDisposable()

    private var _movieList = ObservableField<List<Movie>>()
    val movieList = _movieList

    private var _keyword = ObservableField<String>()
    val keyword = _keyword

    private var _toastMsgRes = ObservableField<Int>()
    val toastMsgRes = _toastMsgRes
    private var _toastMsgString = ObservableField<String>()
    val toastMsgString = _toastMsgString

    private var _hideKeypad = ObservableField<Unit>()
    val hideKeypad = _hideKeypad

    init {
        loadCache()
    }


    private fun loadCache() {
        repository.loadCachedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                if (!it.isNullOrEmpty()) {

                    //리스트 최신화
                    _movieList.set(it)

                    //기록했던 검색어 출력
                    _keyword.set(repository.getCacheKeyword())
                }
            },
                {

                }).let {
                disposables.add(it)
            }

    }


    fun refreshList(keyword: String){
        Log.d("Toast", keyword)
        if (TextUtils.isEmpty(keyword)) {
            _toastMsgRes.set(R.string.no_keyword)
        } else {
            repository.getNaverMovies(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    checkMovieResult(it.items)
                },
                    {
                        _toastMsgString.set(it.toString())

                    }).let {
                    disposables.add(it)
                }
        }
    }

    private fun checkMovieResult(movies: List<Movie>) {

        //리스트 최신화
        _movieList.set(movies)
        _hideKeypad.notifyChange()

        if (movies.isNullOrEmpty()) {
            _toastMsgRes.set(R.string.no_movie_list)
        }

    }


    fun removeDisposable(){
        disposables.dispose()
    }
}