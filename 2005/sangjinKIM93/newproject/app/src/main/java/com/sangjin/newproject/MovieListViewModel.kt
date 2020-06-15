package com.sangjin.newproject

import android.app.Application
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.repository.NaverMoviesRepository
import com.sangjin.newproject.data.repository.NaverMoviesRepositoryImpl
import com.sangjin.newproject.data.source.local.LocalDataSourceImpl
import com.sangjin.newproject.data.source.remote.RemoteDataSourceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(application: Application) :AndroidViewModel(application){

    private val repository by lazy {
        NaverMoviesRepositoryImpl(
            RemoteDataSourceImpl(),
            LocalDataSourceImpl(application)
        )
    }

    private val disposables = CompositeDisposable()

    private var _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    var keyword = MutableLiveData<String>()

    private var _toastMsgRes = MutableLiveData<Int>()
    val toastMsgRes: LiveData<Int> = _toastMsgRes
    private var _toastMsgString = MutableLiveData<String>()
    val toastMsgString: LiveData<String> = _toastMsgString

    private var _hideKeypad = MutableLiveData<Unit>()
    val hideKeypad: LiveData<Unit> = _hideKeypad


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
                    _movieList.value = it

                    //기록했던 검색어 출력
                    keyword.value = repository.getCacheKeyword()
                }
            },
                {

                }).let {
                disposables.add(it)
            }

    }


    fun refreshList() {

        val keyword = keyword.value ?: return

        if (TextUtils.isEmpty(keyword)) {
            _toastMsgRes.value = R.string.no_keyword
        } else {
            repository.getNaverMovies(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    checkMovieResult(it.items)
                },
                    {
                        _toastMsgString.value = it.toString()

                    }).let {
                    disposables.add(it)
                }
        }
    }

    private fun checkMovieResult(movies: List<Movie>) {

        //리스트 최신화
        _movieList.value = movies
        _hideKeypad.value = Unit

        if (movies.isNullOrEmpty()) {
            _toastMsgRes.value = R.string.no_movie_list
        }

    }


    fun removeDisposable() {
        disposables.dispose()
    }
}