package com.example.architecturestudy.ui.movie

import com.example.architecturestudy.data.repository.NaverSearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviePresenter(
    val view: MovieContract.View,
    private val repository: NaverSearchRepository?
) : MovieContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun taskSearch(keyword: String) {
        repository?.let {
            val searchSingle = repository.getMovie(
                keyword = keyword
            )
                // 스레드 변경
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view.showResult(it.items)
                    },
                    {
                        it.printStackTrace()
                        view.showErrorMessage(it.toString())
                    }
                )
            disposable.add(searchSingle)
        }
    }

    override fun getLastData() {
        repository?.let {
            val data = repository.getLastMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view.showResult(it)
                    },
                    {
                        it.printStackTrace()
                        view.showErrorMessage(it.toString())
                    }
                )
            disposable.add(data)
        }
    }

    override fun onStop() {
        disposable.clear()
    }
}