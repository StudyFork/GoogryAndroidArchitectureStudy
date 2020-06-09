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

    var movieList = ObservableField<List<Movie>>()
    var keyword = ObservableField<String>()

    var toastMsgNoKeyword = ObservableField<Unit>()
    var toastMsgNoResult = ObservableField<Unit>()
    var toastMsgError = ObservableField<Throwable>()

    var hideKeypad = ObservableField<Unit>()


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
                    movieList.set(it)

                    //기록했던 검색어 출력
                    keyword.set(extractKeyword(it))
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
        val keyWord = title.split("<b>", "</b>")
        return if (keyWord.size > 1) {
            keyWord[1]
        } else {
            keyWord[0]
        }

    }

    fun refreshList(keyword: String){
        Log.d("Toast", keyword)
        if (TextUtils.isEmpty(keyword)) {
            toastMsgNoKeyword.notifyChange()
        } else {
            repository.getNaverMovies(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    checkMovieResult(it.items)
                },
                    {
                        toastMsgError.set(it)
                        toastMsgError.notifyChange()
                    }).let {
                    disposables.add(it)
                }
        }
    }

    private fun checkMovieResult(movies: List<Movie>) {

        //리스트 최신화
        movieList.set(movies)
        hideKeypad.notifyChange()

        if (movies.isNullOrEmpty()) {
            toastMsgNoResult.notifyChange()
        }

    }


    fun removeDisposable(){
        disposables.dispose()
    }
}