package com.showmiso.architecturestudy.model

import com.showmiso.architecturestudy.api.MovieModel
import com.showmiso.architecturestudy.data.repository.NaverRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MoviePresenter(
    private val naverRepository: NaverRepository,
    private val view: MovieContract.View
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

            }).addTo(disposable)
    }

    override fun clear() {
        disposable.clear()
    }
}