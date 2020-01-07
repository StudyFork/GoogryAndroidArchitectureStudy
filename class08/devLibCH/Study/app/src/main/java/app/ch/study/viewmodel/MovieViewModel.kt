package app.ch.study.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.ch.study.core.BaseViewModel
import app.ch.study.data.db.dao.MovieDao
import app.ch.study.data.db.entitiy.MovieModel
import app.ch.study.data.remote.api.WebApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieViewModel(private val api: WebApi, private val movieDao: MovieDao) : BaseViewModel() {

    val _error = MutableLiveData<String>()
    val _movieList = MutableLiveData<List<MovieModel>>()

    val error: LiveData<String> get() = _error
    val movieList: LiveData<List<MovieModel>> get() = _movieList

    fun searchMovie(name: String) {
        if(name.isEmpty()) {
            _error.value = "검색어를 입력하세요."
            return
        }

        val search = api.searchMovie(name)

        addDisposable(search
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> insertMovieList(response.items)
            }, {
                handleError(it)
            })
        )
    }

    private fun insertMovieList(list: List<MovieModel>) {
        val deleteAll = movieDao.deleteAll()

        addDisposable(deleteAll
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                val insertAll = movieDao.insertAll(list)
                addDisposable(insertAll
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        getMovieList()
                    }, {
                        handleError(it)
                    })
                )

            }, {
                handleError(it)
            })
        )
    }

    private fun getMovieList() {
        val findAll = movieDao.findAll()

        addDisposable(findAll
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list -> _movieList.value = list
            }, {
                handleError(it)
            })
        )

    }
}