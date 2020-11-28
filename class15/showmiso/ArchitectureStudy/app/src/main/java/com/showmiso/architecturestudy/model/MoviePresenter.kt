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
        view.showProgress()
        naverRepository.addHistory(query)
        naverRepository.getMoviesList(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideKeyboard()
                view.hideProgress()
                if (it.isEmpty()) {
                    view.showNoMovieResult()
                } else {
                    view.updateMovieList(it)
                }
            }, {
                view.hideProgress()
                view.throwError(it)
            }).addTo(disposable)
    }

    override fun clearObservable() {
        disposable.clear()
    }
}