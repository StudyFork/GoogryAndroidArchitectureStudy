package app.ch.study.data.repository

import app.ch.study.data.remote.response.MovieModel
import io.reactivex.Single

interface NaverQueryRepository {

    fun searchMovie() : Single<MutableList<MovieModel>>
}