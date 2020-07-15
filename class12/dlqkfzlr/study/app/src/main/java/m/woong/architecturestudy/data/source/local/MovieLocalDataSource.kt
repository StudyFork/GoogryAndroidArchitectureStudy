package m.woong.architecturestudy.data.source.local

import m.woong.architecturestudy.data.source.remote.model.MovieResponse

interface MovieLocalDataSource {

    fun getMovie()

    fun saveMovie(movie: MovieResponse)

}