package com.showmiso.architecturestudy.model

import com.showmiso.architecturestudy.api.MovieModel
import io.reactivex.Single

class MoviePresenter(
) : MovieContract.Presenter {
    override fun getMovies(query: String): Single<MovieModel.MovieResponse> {
        TODO("Not yet implemented")
    }
}