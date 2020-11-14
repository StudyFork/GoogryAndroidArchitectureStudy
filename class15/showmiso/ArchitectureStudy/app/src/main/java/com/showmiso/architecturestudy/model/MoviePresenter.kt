package com.showmiso.architecturestudy.model

import com.showmiso.architecturestudy.data.repository.NaverRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MoviePresenter(
    private val view: MovieContract.View,
    private val naverRepository: NaverRepository
) : MovieContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun getMovies(query: String) {
        if (query.isEmpty()) {
            view.showEmptyQuery()
            return
        }

        naverRepository.getMoviesList(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isEmpty()) {
                    view.showNoMovieResult()
                } else {
                    view.updateMovieList(it)
                }
            }, {
                view.throwError(it)
            }).addTo(disposable)
    }

    override fun clear() {
        disposable.clear()
    }
}