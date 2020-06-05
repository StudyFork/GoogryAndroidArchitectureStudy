package com.sangjin.newproject

import android.text.TextUtils
import android.util.Log
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.repository.NaverMoviesRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListPresenter(
    private val view: MovieListContract.View,
    private val repository: NaverMoviesRepository
) : MovieListContract.Presenter {

    private val disposables = CompositeDisposable()


    override fun loadCache() {
        repository.loadCachedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(!it.isNullOrEmpty()){

                    view.refreshMovieList(it)

                    view.setCacheKeyword(extractKeyword(it))
                }
            },
                {

                }).let {
                disposables.add(it)
            }
    }

    
    //**검색 결과를 바탕으로 검색한 keyword 추출하기
    private fun extractKeyword(it: List<Movie>): String {
        val title = it[0].title
        val keyWord = title.split("<b>", "</b>")[1]
        return keyWord
    }


    override fun searchMovie(keyWord: String) {
        if (TextUtils.isEmpty(keyWord)) {
            view.noKeyword()
        } else {
            repository.getNaverMovies(keyWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    checkMovieResult(it.items)
                },
                    {
                        view.onError(it)
                    }).let {
                    disposables.add(it)
                }
        }
    }


    override fun checkMovieResult(movies: List<Movie>) {

        //리스트 최신화
        view.refreshMovieList(movies)

        if (movies.isNullOrEmpty()) {
            view.noResult(movies)
        }

    }


    override fun clearDisposable() {
        disposables.dispose()
    }
}