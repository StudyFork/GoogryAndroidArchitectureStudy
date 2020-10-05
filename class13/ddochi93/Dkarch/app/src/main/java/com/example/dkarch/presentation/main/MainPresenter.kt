package com.example.dkarch.presentation.main

import com.example.dkarch.domain.repository.NaverMovieRepository
import com.example.dkarch.presentation.base.BasePresenter
import io.reactivex.rxkotlin.addTo
import retrofit2.HttpException

class MainPresenter(
    override val view: MainContract.View,
    private val naverMovieRepository: NaverMovieRepository
) : BasePresenter(view), MainContract.Presenter {
    override fun getMovieList(query: String) {
        naverMovieRepository.getMovies(query).subscribe({ movieResponse ->
            movieResponse.body()?.let {
                view.showMovieList(it.items)
            }
        }, {
            handleError(it)
        }).addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun handleError(throwable: Throwable) {
        if (throwable is HttpException) {
            val statusCode = throwable.code()
            if (statusCode == 400) {

            }
        }
    }

}
